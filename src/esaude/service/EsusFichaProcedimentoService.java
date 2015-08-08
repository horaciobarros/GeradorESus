package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.procedimento.FichaProcedimentoChildThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.procedimento.FichaProcedimentoMasterThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusFichaProcedimentoDao;
import esaude.model.EsusFichaProcedimento;
import esaude.model.EsusFichaProcedimentoAtendimento;
import esaude.model.EsusFichaProcedimentoAtendimentoProc;
import esaude.model.EsusRegistro;
import esaude.model.SisRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

public class EsusFichaProcedimentoService extends MasterService{
	static Logger log = Logger.getLogger(EsusAtendimentoIndividualService.class
			.getName());
	private EsusFichaProcedimentoDao dao = new EsusFichaProcedimentoDao();
	private EsusRegistro esusRegistro = new EsusRegistro();
	private SisRegistro sisRegistro = new SisRegistro();
	private MasterService masterService = new MasterService();

	public List<EsusFichaProcedimento> findNaoEnvidados() {
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

		log.info(new Date() + " -- Gerando Ficha Procedimento -------");
		TelaPrincipal.enviaLog(new Date()
				+ " -- Gerando Ficha Procedimento -------");

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		try {

			for (EsusFichaProcedimento cad : dao.findNaoEnviados()) {
				try {
					FichaProcedimentoMasterThrift thriftFichaProcedimento = converterParaThrift(cad);

					InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

					byte[] dadoSerializado;

					// Passo 2: serializar o thrift
					dadoSerializado = ThriftSerializer
							.serialize(thriftFichaProcedimento);

					// Passo 3: coletar as informações do envio
					informacoesEnvioDto.setTipoDadoSerializado(7l);
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
							+ " -- Gerando Ficha Procedimento --> "
							+ cad.getId() + " - " + cad.getId());
					System.out.println("Gerando Ficha Procedimento --> "
							+ cad.getId());

					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);

					System.out.println("Ficha Procedimento:" + cad.getId());

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

	private FichaProcedimentoMasterThrift converterParaThrift(
			EsusFichaProcedimento cad) {
		FichaProcedimentoMasterThrift c = new FichaProcedimentoMasterThrift();
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
			UnicaLotacaoHeaderThrift unicaLotacao = new UnicaLotacaoHeaderThrift();
			unicaLotacao.setCboCodigo_2002(cad.getCoOcupacao());
			unicaLotacao.setCboCodigo_2002IsSet(true);
			unicaLotacao.setCnes(cad.getCnesUnidade());
			unicaLotacao.setCnesIsSet(true);
			unicaLotacao.setProfissionalCNS(cad.getCnsProfissional());
			unicaLotacao.setProfissionalCNSIsSet(true);
			unicaLotacao.setDataAtendimento(cad.getDtAtendimento().getTime());
			unicaLotacao.setDataAtendimentoIsSet(true);
			c.setHeaderTransport(unicaLotacao);
		} catch (Exception e) {

		}
		
		try {
			
		} catch (Exception e) {
			c.setNumTotalAfericaoPa(cad.getTotalafericaopa());
			c.setNumTotalAfericaoPaIsSet(true);
			c.setNumTotalAfericaoTemperatura(cad.getTotalafericaotemperatura());
			c.setNumTotalAfericaoTemperaturaIsSet(true);
			c.setNumTotalColetaMaterialParaExameLaboratorial(cad.getTotalcoletamaterial());
			c.setNumTotalColetaMaterialParaExameLaboratorialIsSet(true);
			c.setNumTotalCurativoSimples(cad.getTotalcurativosimples());
			c.setNumTotalCurativoSimplesIsSet(true);
			c.setNumTotalGlicemiaCapilar(cad.getTotalglicemiacapilar());
			c.setNumTotalGlicemiaCapilarIsSet(true);
			c.setNumTotalMedicaoAltura(cad.getTotalmedicaoaltura());
			c.setNumTotalMedicaoAlturaIsSet(true);
			c.setNumTotalMedicaoPeso(cad.getTotalmedicaopeso());
			c.setNumTotalMedicaoPesoIsSet(true);
			
		}
		
		try {
			List<FichaProcedimentoChildThrift> procedimentos = buscaProcedimentos(cad);
			c.setAtendProcedimentos(procedimentos);
			c.setAtendProcedimentosIsSet(true);
		} catch (Exception e) {
			
		}

		return c;
	}

	private List<FichaProcedimentoChildThrift> buscaProcedimentos(
			EsusFichaProcedimento cad) {
		
		List<FichaProcedimentoChildThrift> fichas = new ArrayList<FichaProcedimentoChildThrift>();
		
		for (EsusFichaProcedimentoAtendimento f : dao.findAtendimentos(cad.getId())) {
			
			FichaProcedimentoChildThrift ficha = new FichaProcedimentoChildThrift();
			ficha.setDtNascimento(f.getPProntuario().getCoProntuario());
			ficha.setDtNascimentoIsSet(true);
			ficha.setLocalAtendimento(f.getEsusLocaldeatendimento().getId());
			ficha.setLocalAtendimentoIsSet(true);
			ficha.setNumCartaoSus(f.getPProntuario().getCoProntuario().toString());
			ficha.setNumCartaoSusIsSet(true);
			ficha.setNumProntuario(f.getPProntuario().getCoProntuario().toString());
			ficha.setNumProntuarioIsSet(true);
			
			try {
				long sexo;
				if (f.getPProntuario().getCoSexo().equals("M")) {
					sexo = 0;
				} else  {
					sexo = 1;
				}
				ficha.setSexo(sexo);
				ficha.setSexoIsSet(true);
			} catch (Exception e) {
				log.error("Erro no envio do sexo. id:" + cad.getId());

			}
			
			ficha.setTurno(f.getEsusTurno().getId());
			ficha.setTurnoIsSet(true);
			
			ficha.setStatusEscutaInicialOrientacao(f.getEscutaInicial());
			ficha.setStatusEscutaInicialOrientacao(true);
			
			
			ficha.setProcedimentos(findProcedimentos(f));
			ficha.setProcedimentosIsSet(true);
			
			ficha.setOutrosSiaProcedimentos(findOutrosProcedimentos(f));
			ficha.setOutrosSiaProcedimentosIsSet(true);
			
			fichas.add(ficha);	
		}

		return fichas;
	}

	private List<String> findOutrosProcedimentos(
			EsusFichaProcedimentoAtendimento f) {
		
		return findProcedimentos(f); 
	}

	private List<String> findProcedimentos(EsusFichaProcedimentoAtendimento f) {
		List<String> procs = new ArrayList<String>();
		
		for (EsusFichaProcedimentoAtendimentoProc proc : dao.findProcedimentos(f.getId())) {
			procs.add(proc.getCoProcedimentoCiap());
		}
		
		return procs;
	}

}
