package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.atendimentoodontologico.FichaAtendimentoOdontologicoChildThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atendimentoodontologico.FichaAtendimentoOdontologicoMasterThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atendimentoodontologico.ProcedimentoQuantidadeThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.VariasLotacoesHeaderThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusAtendimentoOdontologicoDao;
import esaude.model.EsusAtendimentoOdontologico;
import esaude.model.EsusAtendimentoOdontologicoCiap;
import esaude.model.EsusAtendimentoOdontologicoEncam;
import esaude.model.EsusAtendimentoOdontologicoVigilancia;
import esaude.model.EsusCondutaencaminhamentoodonto;
import esaude.model.EsusRegistro;
import esaude.model.EsusVigilanciaemsaudebucal;
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
	private MasterService masterService = new MasterService();

	public List<EsusAtendimentoOdontologico> findNaoEnvidados() {
		return dao.findNaoEnviados();
	}

	public List<DadoTransporteThrift> buscaRegistros() {

		EsusRegistroServiceImpl esusRegistroService = new EsusRegistroServiceImpl();
		try {
			esusRegistro = esusRegistroService.buscaEsusRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Esus registro não encontrado");
			throw e;
		}
		SisRegistroService sisRegistroService = new SisRegistroService();
		try {
			sisRegistro = sisRegistroService.buscaSisRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sisregistro não encontrado");
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

					// Passo 3: coletar as informaÃ§Ãµes do envio
					informacoesEnvioDto.setTipoDadoSerializado(5l);
					informacoesEnvioDto.setDadoSerializado(dadoSerializado);
					informacoesEnvioDto.setUuidDadoSerializado(thriftAtendimentoOdontologico.getUuidFicha());
					informacoesEnvioDto.setCnesDadoSerializado(cad
							.getCnesUnidade());
					informacoesEnvioDto.setCodIbge(sisRegistro.getCidadeIbge());

					// Passo 4: preencher o thrift de transporte com as
					// informadosÃ§Ãµeso
					// coletadas;
					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio
							.getInfoInstalacao(informacoesEnvioDto,
									esusRegistro);

					dados.add(dadoTransporteThrift);

					log.info(new Date()
							+ " -- Gerando cadastro Odontologico --> "
							+ cad.getId() + " - " + thriftAtendimentoOdontologico.getUuidFicha());
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

			c.setUuidFicha(masterService.gerarUuid(cad.getCnesUnidade()));
			cad.setUuid(c.getUuidFicha());
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
			unicaLotacao.setCodigoIbgeMunicipio(sisRegistro.getCidadeIbge());
			unicaLotacao.setCodigoIbgeMunicipioIsSet(true);
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
			log.error("Erro no envio do prontuario. id:" + cad.getId());
		}

		try {
			ficha.setGestante(cad.getGestante());
			ficha.setGestanteIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio da situação gestante. id:" + cad.getId());
		}

		try {
			ficha.setLocalAtendimento(cad.getEsusLocaldeatendimento().getId());
			ficha.setLocalAtendimentoIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio do local de atendimento. id:" + cad.getId());

		}

		try {
			ficha.setTipoAtendimento(cad.getEsusTipodeatendimento().getId());
			if (cad.getEsusTipodeatendimento() == null) {
				ficha.setTipoAtendimento(11l);
			}
			ficha.setTipoAtendimentoIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio do tipo de atendimento. id:" + cad.getId());
			ficha.setTipoAtendimento(11l);
			ficha.setTipoAtendimentoIsSet(true);
		}

		try {
			ficha.setNecessidadesEspeciais(cad.getNecessidadesespeciais());
			ficha.setNecessidadesEspeciaisIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio das necessidades especiais. id:" + cad.getId());

		}

		try {
			List<Long> tiposEncamOdonto = buscaTiposEncamOdonto(cad);
			
			if (ficha.getTiposEncamOdonto() == null || ficha.getTiposEncamOdonto().size() == 0) {
				tiposEncamOdonto.add(11l);
			}
			ficha.setTiposEncamOdonto(tiposEncamOdonto);
			ficha.setTiposEncamOdontoIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio dos tipos de encam odonto. id:" + cad.getId());

		}

		try {
			ficha.setTiposFornecimOdonto(buscaTiposFornecimOdonto(cad));
			ficha.setTiposFornecimOdontoIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio dos tipos de fornec odonto. id:" + cad.getId());

		}
		
		List<Long> tiposVigilanciaSaudeBucal = new ArrayList<Long>();
		try {
			tiposVigilanciaSaudeBucal = buscaTiposVigilanciaSaudeBucal(cad);			
			if (tiposVigilanciaSaudeBucal != null && tiposVigilanciaSaudeBucal.size() != 0) {
				ficha.setTiposVigilanciaSaudeBucal(tiposVigilanciaSaudeBucal);
				ficha.setTiposVigilanciaSaudeBucalIsSet(true);
			} else {
				tiposVigilanciaSaudeBucal = new ArrayList<Long>();
				tiposVigilanciaSaudeBucal.add(99l);
				ficha.setTiposVigilanciaSaudeBucal(tiposVigilanciaSaudeBucal);
				ficha.setTiposVigilanciaSaudeBucalIsSet(true);
			}
		} catch (Exception e) {
			log.error("Erro no envio dos tipos vigil saude bucal. id:" + cad.getId() + " " + e.getMessage());
			tiposVigilanciaSaudeBucal.add(99l);
			ficha.setTiposVigilanciaSaudeBucal(tiposVigilanciaSaudeBucal);
			ficha.setTiposVigilanciaSaudeBucalIsSet(true);
		}
		
		try {
			ficha.setTiposConsultaOdonto(buscaTiposConsultaOdonto(cad));
			ficha.setTiposConsultaOdontoIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio dos tipos consulta odonto. id:" + cad.getId());

		}
		
		try {
			ficha.setProcedimentosRealizados(buscaProcedimentosRealizados(cad));
			ficha.setProcedimentosRealizadosIsSet(false);
		} catch (Exception e) {
			log.error("Erro no envio dos procedimentos. id:" + cad.getId() + e.getMessage());

		}

		try {
			ficha.setOutrosSiaProcedimentos(buscaOutrosSiaProcedimentos(cad));
			ficha.setOutrosSiaProcedimentosIsSet(false);
		} catch (Exception e) {
			log.error("Erro no envio de outros sia procedimentos. id:" + cad.getId());

		}
		
		try {
			long sexo;
			if (cad.getPProntuario().getCoSexo().equals("M")) {
				sexo = 0;
			} else  {
				sexo = 1;
			}
			ficha.setSexo(sexo);
			ficha.setSexoIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio do sexo. id:" + cad.getId());

		}

		try {
			ficha.setTurno(cad.getEsusTurno().getId());
			ficha.setTurnoIsSet(true);
		} catch (Exception e) {
			log.error("Erro no envio do turno. id:" + cad.getId());

		}

		List<FichaAtendimentoOdontologicoChildThrift> fichas = new ArrayList<FichaAtendimentoOdontologicoChildThrift>();
		fichas.add(ficha);

		return fichas;

	}

	private List<ProcedimentoQuantidadeThrift> buscaOutrosSiaProcedimentos(
			EsusAtendimentoOdontologico cad) {
		
		// não encontrada tabela correspondente. ver com cliente.
		return null;
	}

	private List<ProcedimentoQuantidadeThrift> buscaProcedimentosRealizados(
			EsusAtendimentoOdontologico cad) {
		List<ProcedimentoQuantidadeThrift> procedimentos = new ArrayList<ProcedimentoQuantidadeThrift>();
		for (EsusAtendimentoOdontologicoCiap ciap : dao.findCiap(cad)) {
			ProcedimentoQuantidadeThrift procedimento = new ProcedimentoQuantidadeThrift();
			procedimento.setCoMsProcedimento(ciap.getCoCiap());
			procedimento.setCoMsProcedimentoIsSet(true);
			procedimento.setQuantidade(Integer.parseInt(ciap.getQtd().toString()));
			procedimento.setQuantidadeIsSet(true);
			procedimentos.add(procedimento);
		}
		return procedimentos;
	}

	private List<Long> buscaTiposConsultaOdonto(EsusAtendimentoOdontologico cad) {
		List<Long> retorno = new ArrayList<Long>();
		retorno.add(cad.getEsusTipodeconsultaodonto().getId());
		return retorno;
	}

	private List<Long> buscaTiposVigilanciaSaudeBucal(
			EsusAtendimentoOdontologico cad) {
		List<Long> retorno = new ArrayList<Long>();
		
		for (EsusAtendimentoOdontologicoVigilancia v : dao.findVigilancia(cad)) {
			retorno.add(v.getEsusVigilanciaemsaudebucal().getId());
		}
		
		return retorno;
	}

	private List<Long> buscaTiposFornecimOdonto(EsusAtendimentoOdontologico cad) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Long> buscaTiposEncamOdonto(EsusAtendimentoOdontologico cad) {
		List<Long> retorno = new ArrayList<Long>();
		for (EsusAtendimentoOdontologicoEncam enc : dao.findEncaminhamentoOdontologico(cad)){
			retorno.add(enc.getEsusCondutaencaminhamentoodonto().getId());
		}
		
		return retorno;
	}

}
