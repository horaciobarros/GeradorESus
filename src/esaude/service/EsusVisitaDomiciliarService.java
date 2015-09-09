package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.visitadomiciliar.FichaVisitaDomiciliarChildThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.visitadomiciliar.FichaVisitaDomiciliarMasterThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusVisitaDomiciliarDao;
import esaude.model.EsusMotivovisita;
import esaude.model.EsusRegistro;
import esaude.model.EsusVisitaDomiciliar;
import esaude.model.EsusVisitaDomiciliarMotivovisita;
import esaude.model.SisRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

public class EsusVisitaDomiciliarService {
	static Logger log = Logger.getLogger(EsusVisitaDomiciliarService.class
			.getName());
	private EsusVisitaDomiciliarDao dao = new EsusVisitaDomiciliarDao();
	private EsusRegistro esusRegistro = new EsusRegistro();
	private SisRegistro sisRegistro = new SisRegistro();
	private MasterService masterService = new MasterService();

	public List<EsusVisitaDomiciliar> findNaoEnvidados() {
		return dao.findNaoEnviados();
	}

	public List<DadoTransporteThrift> buscaRegistros() {

		EsusRegistroServiceImpl esusRegistroService = new EsusRegistroServiceImpl();
		try {
			esusRegistro = esusRegistroService.buscaEsusRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Esusregistro não encontrado");
			throw e;
		}
		SisRegistroService sisRegistroService = new SisRegistroService();
		try {
			sisRegistro = sisRegistroService.buscaSisRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sisregistro não encontrado");
			throw e;
		}

		log.info(new Date() + " -- Gerando Visita domiciliar -------");
		TelaPrincipal.enviaLog(new Date()
				+ " -- Gerando Visita domiciliar -------");

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		try {

			for (EsusVisitaDomiciliar cad : dao.findNaoEnviados()) {
				try {
					FichaVisitaDomiciliarMasterThrift thriftVisitaDomiciliar = converterParaThrift(cad);

					InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

					byte[] dadoSerializado;

					dadoSerializado = ThriftSerializer
							.serialize(thriftVisitaDomiciliar);

					informacoesEnvioDto.setTipoDadoSerializado(8l);
					informacoesEnvioDto.setDadoSerializado(dadoSerializado);
					informacoesEnvioDto.setUuidDadoSerializado(thriftVisitaDomiciliar.getUuidFicha());
					if (cad.getId() == null) {
						log.info("UuidDadoSerializado inexistente para o cbo: "
								+ cad.getCboProfissional());
					}
					informacoesEnvioDto.setIneDadoSerializado(cad
							.getIneEquipe());
					informacoesEnvioDto.setCnesDadoSerializado(cad
							.getCnesUnidade());

					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio
							.getInfoInstalacao(informacoesEnvioDto,
									esusRegistro);

					dados.add(dadoTransporteThrift);

					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);
					
					log.info(new Date() + " -- Gerando visita domiciliar  --> "
							+ cad.getId() + " - " + thriftVisitaDomiciliar.getUuidFicha());
					System.out.println("Gerando visita domiciliar --> " + cad.getId()
							);


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

	private FichaVisitaDomiciliarMasterThrift converterParaThrift(
			EsusVisitaDomiciliar cad) {
		FichaVisitaDomiciliarMasterThrift thrift = new FichaVisitaDomiciliarMasterThrift();

		thrift.setUuidFicha(masterService.gerarUuid(cad.getCnesUnidade()));
		cad.setUuid(thrift.getUuidFicha());
		thrift.setUuidFichaIsSet(true);
		thrift.setTpCdsOrigem(3);
		thrift.setTpCdsOrigemIsSet(true);

		UnicaLotacaoHeaderThrift ulht = new UnicaLotacaoHeaderThrift();
		ulht.setProfissionalCNS(cad.getCnsProfissional());
		ulht.setProfissionalCNSIsSet(true);
		ulht.setCnes(cad.getCnesUnidade());
		ulht.setCnesIsSet(true);
		ulht.setCodigoIbgeMunicipio(sisRegistro.getCidadeIbge());
		ulht.setCodigoIbgeMunicipioIsSet(true);
		ulht.setDataAtendimento(cad.getDtAtendimento().getTime());
		ulht.setDataAtendimentoIsSet(true);
		ulht.setIne(cad.getIneEquipe());
		ulht.setIneIsSet(true);
		ulht.setCboCodigo_2002(cad.getCboProfissional());
		ulht.setCboCodigo_2002IsSet(true);
		thrift.setHeaderTransport(ulht);
		thrift.setHeaderTransportIsSet(true);

		List<FichaVisitaDomiciliarChildThrift> visitasDomiciliares = new ArrayList<FichaVisitaDomiciliarChildThrift>();
		FichaVisitaDomiciliarChildThrift child = new FichaVisitaDomiciliarChildThrift();
		child.setDesfecho(cad.getEsusDesfecho().getId());
		child.setDesfechoIsSet(true);
		try {
			child.setDtNascimento(cad.getDtNascimento().getTime());
			child.setDtNascimentoIsSet(true);
		} catch (Exception e) {
			log.info("Dt nascimento não informada: " + cad.getId());
		}
		List<EsusVisitaDomiciliarMotivovisita> motivosVisita = dao
				.findMotivos(cad.getId());
		List<Long> motivos = converteMotivos(motivosVisita);
		child.setMotivosVisita(motivos);
		child.setMotivosVisitaIsSet(true);
		child.setNumCartaoSus(cad.getNumCartaosus());
		child.setNumCartaoSusIsSet(true);
		try {
			child.setNumProntuario(cad.getPProntuario().getCoProntuario()
					.toString());
			child.setNumProntuarioIsSet(true);
		} catch (Exception e) {
			log.info("Prontuario não informado: " + cad.getId());
		}

		try {
			child.setSexo(cad.getEsusSexo().getId());
			child.setSexoIsSet(true);
		} catch (Exception e) {
			log.info("Sexo não informado: " + cad.getId());
		}

		visitasDomiciliares.add(child);
		thrift.setVisitasDomiciliares(visitasDomiciliares);
		thrift.setVisitasDomiciliaresIsSet(true);

		return thrift;
	}

	private List<Long> converteMotivos(
			List<EsusVisitaDomiciliarMotivovisita> motivosVisita) {
		List<Long> lista = new ArrayList<Long>();
		for (EsusVisitaDomiciliarMotivovisita m : motivosVisita) {
			lista.add(m.getEsusMotivovisita().getId());
		}

		return lista;
	}

}
