package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.atividadeindividual.FichaAtendimentoIndividualChildThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atividadeindividual.FichaAtendimentoIndividualMasterThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atividadeindividual.OutrosSiaThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atividadeindividual.ProblemaCondicaoAvaliacaoAIThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.VariasLotacoesHeaderThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusAtendimentoIndividualDao;
import esaude.model.EsusAtendimentoIndividual;
import esaude.model.EsusAtendimentoIndividualExames;
import esaude.model.EsusRegistro;
import esaude.model.SisRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

public class EsusAtendimentoIndividualService extends MasterService{
	static Logger log = Logger.getLogger(EsusAtendimentoIndividualService.class
			.getName());
	private EsusAtendimentoIndividualDao dao = new EsusAtendimentoIndividualDao();
	private EsusRegistro esusRegistro = new EsusRegistro();
	private SisRegistro sisRegistro = new SisRegistro();
	private MasterService masterService = new MasterService();

	public List<EsusAtendimentoIndividual> findNaoEnvidados() {
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

		log.info(new Date() + " -- Gerando Atendimento Individual -------");
		TelaPrincipal.enviaLog(new Date()
				+ " -- Gerando Atendimento individual -------");

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		try {

			for (EsusAtendimentoIndividual cad : dao.findNaoEnviados()) {
				try {
					FichaAtendimentoIndividualMasterThrift thriftAtendimentoIndividual = converterParaThrift(cad);

					InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

					byte[] dadoSerializado;

					// Passo 2: serializar o thrift
					dadoSerializado = ThriftSerializer
							.serialize(thriftAtendimentoIndividual);

					// Passo 3: coletar as informações do envio
					informacoesEnvioDto.setTipoDadoSerializado(4l);
					informacoesEnvioDto.setDadoSerializado(dadoSerializado);
					informacoesEnvioDto.setUuidDadoSerializado(cad.getId()
							.toString());
					informacoesEnvioDto.setCnesDadoSerializado(cad
							.getCnesUnidade());

					// Passo 4: preencher o thrift de transporte com as
					// informadosçõeso
					// coletadas;
					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio
							.getInfoInstalacao(informacoesEnvioDto,
									esusRegistro);

					dados.add(dadoTransporteThrift);

					log.info(new Date()
							+ " -- Gerando atendimento Individual --> "
							+ cad.getId() + " - " + cad.getId());
					System.out.println("Gerando atendimento Individual --> "
							+ cad.getId());

					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);

					System.out.println("Atendimento individual:" + cad.getId());

				} catch (JDBCConnectionException e) {
					log.info(e.getStackTrace());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date() + " - "
							+ e.getStackTrace());
				} catch (Exception e) {
					log.info(e.getStackTrace());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date() + " - "
							+ e.getStackTrace());
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

	private FichaAtendimentoIndividualMasterThrift converterParaThrift(
			EsusAtendimentoIndividual cad) {
		FichaAtendimentoIndividualMasterThrift c = new FichaAtendimentoIndividualMasterThrift();
		try {
			c.setUuidFichaIsSet(true);
			c.setUuidFicha(masterService.gerarUuid(cad.getCnesUnidade()));
			cad.setUuid(c.getUuidFicha());

		} catch (Exception e) {

		}

		try {
			c.setTpCdsOrigem(3);
			c.setTpCdsOrigemIsSet(true);
		} catch (Exception e) {

		}

		try {
			VariasLotacoesHeaderThrift vl = new VariasLotacoesHeaderThrift();
			UnicaLotacaoHeaderThrift unicaLotacao = new UnicaLotacaoHeaderThrift();
			unicaLotacao.setCboCodigo_2002(cad.getCboProfissional());
			unicaLotacao.setCboCodigo_2002IsSet(true);
			unicaLotacao.setCnes(cad.getCnesUnidade());
			unicaLotacao.setCnesIsSet(true);
			unicaLotacao.setProfissionalCNS(cad.getCnsProfissional());
			unicaLotacao.setProfissionalCNSIsSet(true);
			unicaLotacao.setDataAtendimento(cad.getDtAtendimento().getTime());
			unicaLotacao.setDataAtendimentoIsSet(true);
			vl.setLotacaoForm(unicaLotacao);
			c.setHeaderTransport(vl);
		} catch (Exception e) {

		}

		try {
			List<FichaAtendimentoIndividualChildThrift> atendimentosIndividuais = buscaAtendimentosIndividuais(cad);
			c.setAtendimentosIndividuais(atendimentosIndividuais);
			c.setAtendimentosIndividuaisIsSet(true);
		} catch (Exception e) {

		}

		return c;
	}

	private List<FichaAtendimentoIndividualChildThrift> buscaAtendimentosIndividuais(
			EsusAtendimentoIndividual cad) {
		FichaAtendimentoIndividualChildThrift ficha = new FichaAtendimentoIndividualChildThrift();
		try {
			ficha.setAleitamentoMaterno(cad.getEsusAleitamentomaterno().getId());
			ficha.setAleitamentoMaternoIsSet(true);
		} catch (Exception e) {

		}
		try {
			ficha.setAlturaAcompanhamentoNutricional(cad
					.getAlturaacompanhamento().doubleValue());
			ficha.setAlturaAcompanhamentoNutricionalIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setAtencaoDomiciliarModalidade(cad
					.getAtencaodomicmodalidade());
			ficha.setAtencaoDomiciliarModalidadeIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setCns(cad.getCnsProfissional());
			ficha.setCnsIsSet(true);
		} catch (Exception e) {

		}

		try {
			List<Long> condutas = new ArrayList<Long>();
			condutas.add(cad.getEsusTipodeatendimento().getId());
			ficha.setCondutas(condutas);
			ficha.setCondutasIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setDataNascimento(cad.getPProntuario().getDtNascimento()
					.getTime());
			ficha.setDataNascimentoIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setLocalDeAtendimento(cad.getEsusLocaldeatendimento().getId());
			ficha.setLocalDeAtendimentoIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setTipoAtendimento(cad.getEsusTipodeatendimento().getId());
			ficha.setTipoAtendimentoIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setOutrosSia(buscaOutrosSia(cad));
			ficha.setOutrosSiaIsSet(true);
		} catch (Exception e) {

		}

		try {
			ProblemaCondicaoAvaliacaoAIThrift problema = new ProblemaCondicaoAvaliacaoAIThrift();
			ficha.setProblemaCondicaoAvaliada(problema);
			ficha.setProblemaCondicaoAvaliadaIsSet(true);
		} catch (Exception e) {

		}

		List<FichaAtendimentoIndividualChildThrift> fichas = new ArrayList<FichaAtendimentoIndividualChildThrift>();
		fichas.add(ficha);

		return fichas;
	}

	private List<OutrosSiaThrift> buscaOutrosSia(EsusAtendimentoIndividual cad) {

		List<OutrosSiaThrift> lista = new ArrayList<OutrosSiaThrift>();
		for (EsusAtendimentoIndividualExames aie : dao.findExames(cad)) {
			OutrosSiaThrift outro = new OutrosSiaThrift();
			outro.setCodigoExame(aie.getEsusExames().getCod());
			outro.setCodigoExameIsSet(true);
			List<String> solicitadoAvaliados = new ArrayList<String>();
			solicitadoAvaliados.add(aie.getStatus());
			outro.setSolicitadoAvaliado(solicitadoAvaliados);
			lista.add(outro);
		}

		return lista;
	}

}
