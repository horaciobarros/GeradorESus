package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.atendimentoodontologico.FichaAtendimentoOdontologicoChildThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atendimentoodontologico.FichaAtendimentoOdontologicoMasterThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atendimentoodontologico.ProcedimentoQuantidadeThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atividadeindividual.FichaAtendimentoIndividualChildThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atividadeindividual.ProblemaCondicaoAvaliacaoAIThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.VariasLotacoesHeaderThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusAtendimentoOdontologicoDao;
import esaude.model.EsusAtendimentoOdontologico;
import esaude.model.EsusRegistro;
import esaude.model.SisRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

public class EsusAtendimentoOdontologicoService {
	static Logger log = Logger
			.getLogger(EsusAtendimentoOdontologicoService.class.getName());
	private EsusAtendimentoOdontologicoDao dao = new EsusAtendimentoOdontologicoDao();
	private EsusRegistro esusRegistro = new EsusRegistro();
	private SisRegistro sisRegistro = new SisRegistro();

	public List<EsusAtendimentoOdontologico> findNaoEnvidados() {
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

		log.info(new Date() + " -- Gerando Atendimento Odontologico -------");
		TelaPrincipal.enviaLog(new Date()
				+ " -- Gerando Atendimento Odontologico -------");

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		try {

			for (EsusAtendimentoOdontologico cad : dao.findNaoEnviados()) {
				try {
					FichaAtendimentoOdontologicoMasterThrift thriftAtendimentoOdontologico = converterParaThrift(cad);

					InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

					byte[] dadoSerializado;

					// Passo 2: serializar o thrift
					dadoSerializado = ThriftSerializer
							.serialize(thriftAtendimentoOdontologico);

					// Passo 3: coletar as informações do envio
					informacoesEnvioDto.setTipoDadoSerializado(5l);
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
							+ " -- Gerando cadastro Odontologico --> "
							+ cad.getId() + " - " + cad.getId());
					System.out.println("Gerando cadastro Odontologico --> "
							+ cad.getId());

					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);

					System.out.println("Cadastro odontologico:" + cad.getId());

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

	private FichaAtendimentoOdontologicoMasterThrift converterParaThrift(
			EsusAtendimentoOdontologico cad) {
		FichaAtendimentoOdontologicoMasterThrift c = new FichaAtendimentoOdontologicoMasterThrift();
		try {
			c.setUuidFicha(cad.getId().toString());
			c.setUuidFichaIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio do UuidFicha");
		}

		c.setTpCdsOrigem(3);
		c.setTpCdsOrigemIsSet(true);

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
			log.error("Erro no envio do HeaderTransport. id:" + cad.getId());

		}

		try {
			List<FichaAtendimentoOdontologicoChildThrift> atendimentosOdontologicos = buscaAtendimentosOdontologicos(cad);
			c.setAtendimentosOdontologicos(atendimentosOdontologicos);
			c.setAtendimentosOdontologicosIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio das child fichas. id:" + cad.getId());
		}

		return c;
	}

	private List<FichaAtendimentoOdontologicoChildThrift> buscaAtendimentosOdontologicos(
			EsusAtendimentoOdontologico cad) {
		FichaAtendimentoOdontologicoChildThrift ficha = new FichaAtendimentoOdontologicoChildThrift();

		try {
			ficha.setDtNascimento(cad.getPProntuario().getDtNascimento()
					.getTime());
			ficha.setDtNascimentoIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio da data de nascimento. id:" + cad.getId());
		}

		try {
			ficha.setNumProntuario(cad.getPProntuario().getCoProntuario()
					.toString());
			ficha.setNumProntuarioIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setGestante(cad.getGestante());
			ficha.setGestanteIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setLocalAtendimento(cad.getEsusLocaldeatendimento().getId());
			ficha.setLocalAtendimentoIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setTipoAtendimento(cad.getEsusTipodeatendimento().getId());
			ficha.setTipoAtendimentoIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setNecessidadesEspeciais(cad.getNecessidadesespeciais());
			ficha.setNecessidadesEspeciaisIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setTiposEncamOdonto(buscaTiposEncamOdonto(cad));
			ficha.setTiposEncamOdontoIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setTiposFornecimOdonto(buscaTiposFornecimOdonto(cad));
			ficha.setTiposFornecimOdontoIsSet(true);
		} catch (Exception e) {

		}
		
		try {
			ficha.setTiposVigilanciaSaudeBucal(buscaTiposVigilanciaSaudeBucal(cad));
			ficha.setTiposVigilanciaSaudeBucalIsSet(true);
		} catch (Exception e) {

		}
		
		try {
			ficha.setTiposConsultaOdonto(buscaTiposConsultaOdonto(cad));
			ficha.setTiposConsultaOdontoIsSet(true);
		} catch (Exception e) {

		}
		
		try {
			ficha.setProcedimentosRealizados(buscaProcedimentosRealizados(cad));
			ficha.setProcedimentosRealizadosIsSet(true);
		} catch (Exception e) {

		}

		try {
			ficha.setOutrosSiaProcedimentos(buscaOutrosSiaProcedimentos(cad));
			ficha.setOutrosSiaProcedimentosIsSet(true);
		} catch (Exception e) {

		}
		

		
		

		List<FichaAtendimentoOdontologicoChildThrift> fichas = new ArrayList<FichaAtendimentoOdontologicoChildThrift>();
		fichas.add(ficha);

		return fichas;

	}

	private List<ProcedimentoQuantidadeThrift> buscaOutrosSiaProcedimentos(
			EsusAtendimentoOdontologico cad) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<ProcedimentoQuantidadeThrift> buscaProcedimentosRealizados(
			EsusAtendimentoOdontologico cad) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Long> buscaTiposConsultaOdonto(EsusAtendimentoOdontologico cad) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Long> buscaTiposVigilanciaSaudeBucal(
			EsusAtendimentoOdontologico cad) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Long> buscaTiposFornecimOdonto(EsusAtendimentoOdontologico cad) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Long> buscaTiposEncamOdonto(EsusAtendimentoOdontologico cad) {
		// TODO Auto-generated method stub
		return null;
	}

}
