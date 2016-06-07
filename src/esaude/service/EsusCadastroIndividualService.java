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
	static Logger log = Logger.getLogger(EsusCadastroIndividualService.class.getName());
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
		TelaPrincipal.enviaLog(new Date() + " -- Gerando Cadastro individual -------");

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		try {

			for (EsusCadastroIndividual cad : dao.findNaoEnviados()) {
				try {
					CadastroIndividualThrift thriftCadastroIndividual = converterParaThrift(cad);

					InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

					byte[] dadoSerializado;

					// Passo 2: serializar o thrift
					dadoSerializado = ThriftSerializer.serialize(thriftCadastroIndividual);

					// Passo 3: coletar as informações do envio
					informacoesEnvioDto.setTipoDadoSerializado(2l);
					informacoesEnvioDto.setDadoSerializado(dadoSerializado);
					informacoesEnvioDto.setUuidDadoSerializado(thriftCadastroIndividual.getUuid());
					informacoesEnvioDto.setIneDadoSerializado(cad.getIneEquipe());
					informacoesEnvioDto.setCnesDadoSerializado(cad.getCnesUnidade());
					informacoesEnvioDto.setCodIbge(sisRegistro.getCidadeIbge());

					// Passo 4: preencher o thrift de transporte com as
					// informadosçõeso
					// coletadas;
					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio.getInfoInstalacao(informacoesEnvioDto,
							esusRegistro);

					dados.add(dadoTransporteThrift);

					log.info(new Date() + " -- Gerando cadastro Individual --> " + cad.getId() + " - " + cad.getUuid());
					System.out.println("Gerando cadastro Individual --> " + cad.getId());

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
			log.info(new Date() + " -- Cadastro individual - fichas geradas ----" + dados.size());

		} catch (JDBCConnectionException e) {
			log.error(new Date() + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return dados;
	}

	private CadastroIndividualThrift converterParaThrift(EsusCadastroIndividual cad) {
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
		condicoesDeSaude.setStatusEhDependenteOutrasDrogas(cad.getDependenteDroga());
		condicoesDeSaude.setStatusEhDependenteOutrasDrogasIsSet(true);
		condicoesDeSaude.setStatusEhFumante(cad.getEstaFumante());
		condicoesDeSaude.setStatusEhFumanteIsSet(true);
		if (cad.getPProntuario() != null && cad.getPProntuario().getCoSexo() != null
				&& !cad.getPProntuario().getCoSexo().equals("M") && cad.getEstaGestante()) {
			condicoesDeSaude.setStatusEhGestante(true);
			condicoesDeSaude.setStatusEhGestanteIsSet(true);
		} else {
			condicoesDeSaude.setStatusEhGestante(false);
			condicoesDeSaude.setStatusEhGestanteIsSet(false);

		}
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
		if (cad.getMicroarea() != null) {
			dadosGerais.setMicroarea(cad.getMicroarea());
			dadosGerais.setMicroareaIsSet(true);
		} else {
			dadosGerais.setMicroareaIsSet(false);
		}

		c.setDadosGerais(dadosGerais);
		c.setDadosGeraisIsSet(true);

		IdentificacaoUsuarioCidadaoThrift identificacao = new IdentificacaoUsuarioCidadaoThrift();
		try {
			identificacao.setCodigoIbgeMunicipioNascimento(cad.getPProntuario().getCoMunicipioNasc());
		} catch (NullPointerException e) {
			System.out.println("Erro em pProntuario");
		}
		identificacao.setCodigoIbgeMunicipioNascimentoIsSet(true);
		identificacao.setDataNascimentoCidadao(cad.getPProntuario().getDtNascimento().getTime());
		identificacao.setDataNascimentoCidadaoIsSet(true);
		try {
			identificacao.setNomeCidadao(cad.getPProntuario().getNoUsuario());
			identificacao.setNomeCidadaoIsSet(true);
		} catch (Exception e) {
			identificacao.setNomeCidadaoIsSet(false);
		}
		if (!cad.getPProntuario().isDesconheceMae()) {
			identificacao.setDesconheceNomeMae(false);
			identificacao.setDesconheceNomeMaeIsSet(true);
			identificacao.setNomeMaeCidadao(cad.getPProntuario().getNoMae());
			identificacao.setNomeMaeCidadaoIsSet(true);
		} else {
			identificacao.setDesconheceNomeMae(true);
			identificacao.setDesconheceNomeMaeIsSet(true);

		}

		try {
			identificacao.setRacaCorCidadao(Long.valueOf(cad.getPProntuario().getPRacaCor().getCoRaca()));
			if (cad.getPProntuario().getPRacaCor().getCoRaca().equals("99")) {
				identificacao.setRacaCorCidadao(1L);
			}
			identificacao.setRacaCorCidadaoIsSet(true);
		} catch (Exception e) {
			identificacao.setRacaCorCidadaoIsSet(false);
		}

		try {
			Long sexo = new Long(0);
			if (cad.getPProntuario().getCoSexo().equals("M")) {
				sexo = Long.valueOf(0);
			} else {
				sexo = Long.valueOf(1);
			}
			identificacao.setSexoCidadao(sexo);
			identificacao.setSexoCidadaoIsSet(true);
		} catch (Exception e) {
			identificacao.setSexoCidadaoIsSet(false);
		}

		try {
			if (cad.getPProntuario().getPNacionalidade().getCoPais() == null) {
				identificacao.setNacionalidadeCidadao(Long.valueOf(1l));
			} else if (!cad.getPProntuario().getPNacionalidade().getCoPais().equals("010")) {
				identificacao.setNacionalidadeCidadao(Long.valueOf(3l));
			} else {
				identificacao.setNacionalidadeCidadao(Long.valueOf(1l));
			}
			identificacao.setNacionalidadeCidadaoIsSet(true);
		} catch (Exception e) {
			identificacao.setNacionalidadeCidadao(Long.valueOf(1l));
			identificacao.setNacionalidadeCidadaoIsSet(true);
		}

		identificacao.setNomeSocialCidadao(cad.getPProntuario().getNoUsuario());
		identificacao.setNomeSocialCidadaoIsSet(true);

		c.setIdentificacaoUsuarioCidadao(identificacao);

		InformacoesSocioDemograficasThrift informacoesSocioDemograficas = new InformacoesSocioDemograficasThrift();
		List<Long> deficiencias = buscaDeficienciasCidadao(cad);
		informacoesSocioDemograficas.setDeficienciasCidadao(deficiencias);
		informacoesSocioDemograficas.setDeficienciasCidadaoIsSet(true);
		informacoesSocioDemograficas.setStatusFrequentaEscola(cad.getFrequentaEscola());
		informacoesSocioDemograficas.setStatusFrequentaEscolaIsSet(true);
		informacoesSocioDemograficas.setStatusTemAlgumaDeficiencia(deficiencias != null && deficiencias.size() >= 0);
		informacoesSocioDemograficas
				.setStatusTemAlgumaDeficienciaIsSet(deficiencias != null && deficiencias.size() >= 0);
		if (cad.getEsusMotivosaida() != null) {
			informacoesSocioDemograficas.setMotivoSaidaCidadao(cad.getEsusMotivosaida().getId());
			informacoesSocioDemograficas.setMotivoSaidaCidadaoIsSet(true);
		} else {
			informacoesSocioDemograficas.setMotivoSaidaCidadaoIsSet(false);
		}

		EmSituacaoDeRuaThrift emSituacaoDeRua = new EmSituacaoDeRuaThrift();
		emSituacaoDeRua.setStatusSituacaoRua(cad.getEmSituacaoRua());
		emSituacaoDeRua.setStatusSituacaoRuaIsSet(true);
		emSituacaoDeRua.setGrauParentescoFamiliarFrequentado(cad.getGrauParentescoFamiliarFreq());
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

		for (EsusCadastroIndividualHigienepessoalsituacaorua cid : dao.findHigiene(cad.getId())) {
			lista.add(cid.getEsusAcessohigiene().getId());
		}
		return lista;
	}

	private List<Long> buscaDeficienciasCidadao(EsusCadastroIndividual cad) {
		List<Long> lista = new ArrayList<Long>();

		for (EsusCadastroIndividualDeficiencia cid : dao.findDeficiencias(cad.getId())) {
			lista.add(cid.getEsusDeficienciacidadao().getId());
		}
		return lista;
	}

}
