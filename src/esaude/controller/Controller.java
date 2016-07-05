/**
 * Ficha de Cadastro Individual	2 L	
	Ficha de Cadastro Domiciliar	3 L	
 	Ficha de Atendimento Individual	4 L	
 	Ficha de Atendimento Odontológico	5 L	
 	Ficha de Atividade Coletiva	6 L	
 	Ficha de Procedimentos	7 L	
 	Ficha de Visita Domiciliar	8 L	
 	Atendimento realizado pelo software de prontuário 9 L

 */

package esaude.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.service.EsusAtendimentoIndividualService;
import esaude.service.EsusAtendimentoOdontologicoService;
import esaude.service.EsusAtividadeColetivaService;
import esaude.service.EsusCadastroDomiciliarService;
import esaude.service.EsusCadastroIndividualService;
import esaude.service.EsusConsumoAlimentarService;
import esaude.service.EsusFichaProcedimentoService;
import esaude.service.EsusVisitaDomiciliarService;
import esaude.service.MasterService;
import esaude.util.GeradorZip;
import esaude.view.TelaPrincipal;

public class Controller {
	static Logger log = Logger.getLogger(Controller.class.getName());

	private EsusCadastroDomiciliarService cadastroDomiciliarService = new EsusCadastroDomiciliarService();
	private EsusAtividadeColetivaService atividadeColetivaService = new EsusAtividadeColetivaService();
	private EsusCadastroIndividualService cadastroIndividualService = new EsusCadastroIndividualService();
	private EsusVisitaDomiciliarService visitaDomiciliarService = new EsusVisitaDomiciliarService();
	private EsusAtendimentoIndividualService atendimentoIndividualService = new EsusAtendimentoIndividualService();
	private EsusAtendimentoOdontologicoService atendimentoOdontologicoService = new EsusAtendimentoOdontologicoService();
	private EsusFichaProcedimentoService fichaProcedimentoService = new EsusFichaProcedimentoService();
	private EsusConsumoAlimentarService consumoAlimentarService = new EsusConsumoAlimentarService();
	private GeradorZip geradorZip = new GeradorZip();

	String userHome = System.getProperty("user.dir");
	String pathPadrao;

	private MasterService masterService = new MasterService();

	public void geraArquivos(boolean geraCadastroIndividual, boolean geraCadastroDomiciliar,
			boolean geraAtendimentoIndividual, boolean geraAtendimentoOdontologico, boolean geraAtividadeColetiva,
			boolean geraFichaProcedimento, boolean geraVisitaDomiciliar, boolean geraConsumoAlimentar) {

		try {
			criaPastas();

			List<DadoTransporteThrift> dadosTransportCadastroIndividual       = null;
			List<DadoTransporteThrift> dadosTransportCadastroDomiciliar		= null;
			List<DadoTransporteThrift> dadosTransportAtendimentoIndividual  = null;
			List<DadoTransporteThrift> dadosTransportAtendimentoOdontologico = null;
			List<DadoTransporteThrift> dadosTransportAtividadeColetiva = null;
			List<DadoTransporteThrift> dadosTransportProcedimento = null;
			List<DadoTransporteThrift> dadosTransportVisitaDomiciliar = null;
			List<DadoTransporteThrift> dadosTransportConsumoAlimentar = null;
			
			if (geraCadastroIndividual) { 
				TelaPrincipal.enviaLog("Importando e Convertendo Cadastro Individual");
				dadosTransportCadastroIndividual = cadastroIndividualService
						.buscaRegistros();
				TelaPrincipal.enviaLog("----> Total de registros:"
						+ (dadosTransportCadastroIndividual != null ? dadosTransportCadastroIndividual.size() : 0));
			}

			if (geraCadastroDomiciliar) {
				TelaPrincipal.enviaLog("Importando e Convertendo Cadastro Domiciliar");
				dadosTransportCadastroDomiciliar = cadastroDomiciliarService
						.buscaRegistros();
				TelaPrincipal.enviaLog("----> Total de registros:"
						+ (dadosTransportCadastroDomiciliar != null ? dadosTransportCadastroDomiciliar.size() : 0));
			}

			if (geraAtendimentoIndividual) {
				TelaPrincipal.enviaLog("Importando e Convertendo Atendimento Individual");
				dadosTransportAtendimentoIndividual = atendimentoIndividualService
						.buscaRegistros();
				TelaPrincipal.enviaLog("----> Total de registros:" + (dadosTransportAtendimentoIndividual != null
						? dadosTransportAtendimentoIndividual.size() : 0));
			}

			if (geraAtendimentoOdontologico) {
				TelaPrincipal.enviaLog("Importando e Convertendo Atendimento Odontologico");
				dadosTransportAtendimentoOdontologico = atendimentoOdontologicoService
						.buscaRegistros();
				TelaPrincipal.enviaLog("----> Total de registros:" + (dadosTransportAtendimentoOdontologico != null
						? dadosTransportAtendimentoOdontologico.size() : 0));
			}

			if (geraAtividadeColetiva) {
				TelaPrincipal.enviaLog("Importando e Convertendo Atividade Coletiva");
				dadosTransportAtividadeColetiva = atividadeColetivaService.buscaRegistros();
				TelaPrincipal.enviaLog("----> Total de registros:"
						+ (dadosTransportAtividadeColetiva != null ? dadosTransportAtividadeColetiva.size() : 0));
			}

			if (geraFichaProcedimento) {
				TelaPrincipal.enviaLog("Importando e Convertendo Ficha Procedimento");
				dadosTransportProcedimento = fichaProcedimentoService.buscaRegistros();
				TelaPrincipal.enviaLog("----> Total de registros:"
						+ (dadosTransportProcedimento != null ? dadosTransportProcedimento.size() : 0));
			}

			if (geraVisitaDomiciliar) {
				TelaPrincipal.enviaLog("Importando e Convertendo Visita Domiciliar");
				dadosTransportVisitaDomiciliar = visitaDomiciliarService.buscaRegistros();
				TelaPrincipal.enviaLog("----> Total de registros:"
						+ (dadosTransportVisitaDomiciliar != null ? dadosTransportVisitaDomiciliar.size() : 0));
			}

			if (geraConsumoAlimentar) {
				TelaPrincipal.enviaLog("Importando e Convertendo Consumo alimentar");
				dadosTransportConsumoAlimentar = consumoAlimentarService.buscaRegistros();
				TelaPrincipal.enviaLog("----> Total de registros:"
						+ (dadosTransportConsumoAlimentar != null ? dadosTransportConsumoAlimentar.size() : 0));
			}
			
			TelaPrincipal.enviaLog("Criando Arquivo Serializado");
			geradorZip.empacotaZip(dadosTransportCadastroDomiciliar, dadosTransportAtividadeColetiva,
					dadosTransportCadastroIndividual, dadosTransportVisitaDomiciliar,
					dadosTransportAtendimentoIndividual, dadosTransportAtendimentoOdontologico,
					dadosTransportProcedimento, dadosTransportConsumoAlimentar, pathPadrao);
			TelaPrincipal.enviaLog("Processo Finalizado");
		} catch (Exception e) {
			log.error(new Date() + " " + e.getStackTrace());
			JOptionPane.showMessageDialog(null, "Erro grave: " + e.getStackTrace());
			TelaPrincipal.enviaLog(new Date() + " - " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	private void criaPastas() {

		pathPadrao = userHome + "\\gerador_esus";
		File d1 = new File(pathPadrao);
		d1.mkdir();

	}

	public void cancelaEnvio(Date dataGeracao, boolean geraCadastroIndividual, boolean geraCadastroDomiciliar,
			boolean geraAtendimentoIndividual, boolean geraAtendimentoOdontologico, boolean geraAtividadeColetiva,
			boolean geraFichaProcedimento, boolean geraVisitaDomiciliar, boolean geraConsumoAlimentar) throws Exception {
		List<String> nomes = new ArrayList<String>();
		if (geraCadastroDomiciliar) nomes.add("EsusCadastroDomiciliar");
		if (geraCadastroIndividual) nomes.add("EsusCadastroIndividual");
		if (geraAtividadeColetiva) nomes.add("EsusAtividadeColetiva");
		if (geraAtendimentoOdontologico) nomes.add("EsusAtendimentoOdontologico");
		if (geraAtendimentoIndividual) nomes.add("EsusAtendimentoIndividual");
		if (geraFichaProcedimento) nomes.add("EsusFichaProcedimento");
		if (geraVisitaDomiciliar) nomes.add("EsusVisitaDomiciliar");
		if (geraConsumoAlimentar) nomes.add("EsusConsumoAlimentar");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		TelaPrincipal.enviaLog("Cancelamento de geração do dia: " + sdf.format(dataGeracao));
		int entidadesCanceladas = 0;
		for (String nomeEntidade : nomes) {
			masterService.cancelaEnvio(nomeEntidade, dataGeracao);
			TelaPrincipal.enviaLog("cancelando -->" + nomeEntidade);
			entidadesCanceladas++;
		}
		try {
			pathPadrao = userHome + "\\gerador_esus";
			File folder = new File(pathPadrao);
			Calendar c = Calendar.getInstance();
			c.setTime(dataGeracao);
			String data = "_" + c.get(Calendar.DAY_OF_MONTH) + "_" + (c.get(Calendar.MONTH) + 1) + "_"
					+ c.get(Calendar.YEAR);
			String arquivoSerExcluido = "esaude_exportacao" + data;
			if (folder.isDirectory()) {
				File[] sun = folder.listFiles();
				for (File toDelete : sun) {
					if (toDelete.getPath().contains(arquivoSerExcluido)) {
						toDelete.delete();
					}
				}
			}
			if (entidadesCanceladas > 0) {
			TelaPrincipal.enviaLog("--- Cancelamento finalizado --");
			} else {
				TelaPrincipal.enviaLog("--- Nenhum registro cancelado --");
			}
		} catch (Exception e) {
			TelaPrincipal.enviaLog("--- Erro no cancelamento --");
			e.printStackTrace();
		}

	}

}
