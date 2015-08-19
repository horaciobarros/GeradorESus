package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.cadastroindividual.CadastroIndividualThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastroindividual.CondicoesDeSaudeThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastroindividual.EmSituacaoDeRuaThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastroindividual.IdentificacaoUsuarioCidadaoThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastroindividual.InformacoesSocioDemograficasThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.HeaderCdsCadastroThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusCadastroIndividualDao;
import esaude.model.EsusCadastroIndividual;
import esaude.model.EsusCadastroIndividualDeficiencia;
import esaude.model.EsusCadastroIndividualHigienepessoalsituacaorua;
import esaude.model.EsusRegistro;
import esaude.model.SisRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

public class EsusCadastroIndividualService {
	static Logger log = Logger.getLogger(EsusCadastroIndividualService.class
			.getName());
	private EsusCadastroIndividualDao dao = new EsusCadastroIndividualDao();
	private EsusRegistro esusRegistro = new EsusRegistro();
	private SisRegistro sisRegistro = new SisRegistro();
	private MasterService masterService = new MasterService();

	public List<EsusCadastroIndividual> findNaoEnvidados() {
		return dao.findNaoEnviados();
	}

	public List<DadoTransporteThrift> buscaRegistros() {

		EsusRegistroServiceImpl esusRegistroService = new EsusRegistroServiceImpl();
		try {
			esusRegistro = esusRegistroService.buscaEsusRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Esus registro n�o encontrado");
			throw e;
		}
		SisRegistroService sisRegistroService = new SisRegistroService();
		try {
			sisRegistro = sisRegistroService.buscaSisRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sisregistro n�o encontrado");
			throw e;
		}

		log.info(new Date() + " -- Gerando Cadastro individual -------");
		TelaPrincipal.enviaLog(new Date()
				+ " -- Gerando Cadastro individual -------");

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		try {

			for (EsusCadastroIndividual cad : dao.findNaoEnviados()) {
				try {
					CadastroIndividualThrift thriftCadastroIndividual = converterParaThrift(cad);

					InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

					byte[] dadoSerializado;

					// Passo 2: serializar o thrift
					dadoSerializado = ThriftSerializer
							.serialize(thriftCadastroIndividual);

					// Passo 3: coletar as informações do envio
					informacoesEnvioDto.setTipoDadoSerializado(2l);
					informacoesEnvioDto.setDadoSerializado(dadoSerializado);
					informacoesEnvioDto.setUuidDadoSerializado(cad.getUuid()
							.toString());
					informacoesEnvioDto.setIneDadoSerializado(cad
							.getIneEquipe());
					informacoesEnvioDto.setCnesDadoSerializado(cad
							.getCnesUnidade());

					// Passo 4: preencher o thrift de transporte com as
					// informadosçõeso
					// coletadas;
					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio
							.getInfoInstalacao(informacoesEnvioDto,
									esusRegistro);

					dados.add(dadoTransporteThrift);
					
					log.info(new Date() + " -- Gerando cadastro Individual --> "
							+ cad.getId() + " - " + cad.getId());
					System.out.println("Gerando cadastro Individual --> " + cad.getId()
							);


					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);

					System.out.println("Cadastro individual:" + cad.getId());

				} catch (JDBCConnectionException e) {
					log.info(e.getMessage());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date() + " - " + e.getMessage());
				} catch (Exception e) {
					log.info(e.getMessage());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date() + " - " + e.getMessage());
				}
			}

		} catch (JDBCConnectionException e) {
			log.error(new Date() + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return dados;
	}

	private CadastroIndividualThrift converterParaThrift(
			EsusCadastroIndividual cad) {
		CadastroIndividualThrift c = new CadastroIndividualThrift();

		c.setUuid(masterService.gerarUuid(cad.getCnesUnidade()));
		c.setUuidIsSet(true);
		if (cad.getDtEnvio() != null) {
			c.setFichaAtualizada(true);
			c.setUuidFichaOriginadora(cad.getUuid());
		} else {
			c.setUuidFichaOriginadora(c.getUuid());
			c.setFichaAtualizada(false);
		}
		c.setUuidFichaOriginadoraIsSet(true);
		c.setFichaAtualizadaIsSet(true);

		cad.setUuid(c.getUuid());

		CondicoesDeSaudeThrift condicoesDeSaude = new CondicoesDeSaudeThrift();
		condicoesDeSaude.setStatusEhDependenteAlcool(cad.getDependenteAlcool());
		condicoesDeSaude.setStatusEhDependenteAlcoolIsSet(true);
		condicoesDeSaude.setStatusEhDependenteOutrasDrogas(cad
				.getDependenteDroga());
		condicoesDeSaude.setStatusEhDependenteOutrasDrogasIsSet(true);
		condicoesDeSaude.setStatusEhFumante(cad.getEstaFumante());
		condicoesDeSaude.setStatusEhFumanteIsSet(true);
		condicoesDeSaude.setStatusEhGestante(cad.getEstaGestante());
		condicoesDeSaude.setStatusEhGestanteIsSet(true);
		condicoesDeSaude.setStatusTemDiabetes(cad.getDiabete());
		condicoesDeSaude.setStatusTemDiabetesIsSet(true);
		c.setCondicoesDeSaude(condicoesDeSaude);
		c.setCondicoesDeSaudeIsSet(true);

		HeaderCdsCadastroThrift dadosGerais = new HeaderCdsCadastroThrift();
		dadosGerais.setCnesUnidadeSaude(cad.getCnesUnidade());
		dadosGerais.setCnesUnidadeSaudeIsSet(true);
		dadosGerais.setCnsProfissional(cad.getCnsProfissional());
		dadosGerais.setCnsProfissionalIsSet(true);
		dadosGerais.setCodigoIbgeMunicipio(sisRegistro.getCidadeIbge());
		dadosGerais.setCodigoIbgeMunicipioIsSet(true);
		dadosGerais.setDataAtendimento(cad.getDataAtendimento().getTime());
		dadosGerais.setDataAtendimentoIsSet(true);
		dadosGerais.setIneEquipe(cad.getIneEquipe());
		dadosGerais.setIneEquipeIsSet(true);
		dadosGerais.setMicroareaIsSet(true);
		c.setDadosGerais(dadosGerais);
		c.setDadosGeraisIsSet(true);

		IdentificacaoUsuarioCidadaoThrift identificacao = new IdentificacaoUsuarioCidadaoThrift();
		identificacao.setCodigoIbgeMunicipioNascimento(cad.getPProntuario()
				.getCoMunicipioNasc());
		identificacao.setCodigoIbgeMunicipioNascimentoIsSet(true);
		identificacao.setDataNascimentoCidadao(cad.getPProntuario()
				.getDtNascimento().getTime());
		identificacao.setDataNascimentoCidadaoIsSet(true);
		identificacao.setNomeCidadao(cad.getPProntuario().getNomeSocial());
		identificacao.setNomeCidadaoIsSet(true);
		if (cad.getpProntuario().getMaeDesconhecida() == 0) {
			identificacao.setDesconheceNomeMae(false);
			identificacao.setDesconheceNomeMaeIsSet(true);
			identificacao.setNomeMaeCidadao(cad.getPProntuario().getNoMae());
			identificacao.setNomeMaeCidadaoIsSet(true);
		} else {
			identificacao.setDesconheceNomeMae(true);
			identificacao.setDesconheceNomeMaeIsSet(true);
			
		}
		identificacao.setRacaCorCidadao(Long.valueOf(cad.getPProntuario()
				.getPRacaCor().getCoRaca()));
		identificacao.setRacaCorCidadaoIsSet(true);
		Long sexo = new Long(0);
		if (cad.getpProntuario().getCoSexo().equals("M")) {
			sexo = Long.valueOf(0);
		} else {
			sexo = Long.valueOf(1);
		}
		identificacao.setSexoCidadao(sexo);
		identificacao.setSexoCidadaoIsSet(true);
		c.setIdentificacaoUsuarioCidadao(identificacao);

		InformacoesSocioDemograficasThrift informacoesSocioDemograficas = new InformacoesSocioDemograficasThrift();
		List<Long> deficiencias = buscaDeficienciasCidadao(cad);
		informacoesSocioDemograficas.setDeficienciasCidadao(deficiencias);
		informacoesSocioDemograficas.setDeficienciasCidadaoIsSet(true);
		informacoesSocioDemograficas.setStatusFrequentaEscola(cad
				.getFrequentaEscola());
		informacoesSocioDemograficas.setStatusFrequentaEscolaIsSet(true);
		informacoesSocioDemograficas
				.setStatusTemAlgumaDeficiencia(deficiencias != null
						&& deficiencias.size() >= 0);
		informacoesSocioDemograficas
				.setStatusTemAlgumaDeficienciaIsSet(deficiencias != null
						&& deficiencias.size() >= 0);
		c.setInformacoesSocioDemograficas(informacoesSocioDemograficas);

		EmSituacaoDeRuaThrift emSituacaoDeRua = new EmSituacaoDeRuaThrift();
		emSituacaoDeRua.setStatusSituacaoRua(cad.getEmSituacaoRua());
		emSituacaoDeRua.setStatusSituacaoRuaIsSet(true);
		emSituacaoDeRua.setGrauParentescoFamiliarFrequentado(cad
				.getGrauParentescoFamiliarFreq());
		emSituacaoDeRua.setGrauParentescoFamiliarFrequentadoIsSet(true);
		try {
			emSituacaoDeRua.setHigienePessoalSituacaoRua(buscaHigiente(cad));
			emSituacaoDeRua.setHigienePessoalSituacaoRuaIsSet(true);
		} catch (Exception e) {

		}
		c.setEmSituacaoDeRua(emSituacaoDeRua);

		c.setTpCdsOrigem(3);
		c.setTpCdsOrigemIsSet(true);

		return c;
	}

	private List<Long> buscaHigiente(EsusCadastroIndividual cad) {
		List<Long> lista = new ArrayList<Long>();

		for (EsusCadastroIndividualHigienepessoalsituacaorua cid : dao
				.findHigiene(cad.getId())) {
			lista.add(cid.getEsusAcessohigiene().getId());
		}
		return lista;
	}

	private List<Long> buscaDeficienciasCidadao(EsusCadastroIndividual cad) {
		List<Long> lista = new ArrayList<Long>();

		for (EsusCadastroIndividualDeficiencia cid : dao.findDeficiencias(cad
				.getId())) {
			lista.add(cid.getEsusDeficienciacidadao().getId());
		}
		return lista;
	}

}
