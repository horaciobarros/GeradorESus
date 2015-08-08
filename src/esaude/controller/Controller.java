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
import esaude.service.EsusFichaProcedimentoService;
import esaude.service.EsusVisitaDomiciliarService;
import esaude.service.MasterService;
import esaude.util.GeradorZip;
import esaude.view.TelaPrincipal;

public class Controller {
	static Logger log = Logger.getLogger(Controller.class
			.getName());

	private EsusCadastroDomiciliarService cadastroDomiciliarService = new EsusCadastroDomiciliarService();
	private EsusAtividadeColetivaService atividadeColetivaService = new EsusAtividadeColetivaService();
	private EsusCadastroIndividualService cadastroIndividualService = new EsusCadastroIndividualService();
	private EsusVisitaDomiciliarService visitaDomiciliarService = new EsusVisitaDomiciliarService();
	private EsusAtendimentoIndividualService atendimentoIndividualService = new EsusAtendimentoIndividualService();
	private EsusAtendimentoOdontologicoService atendimentoOdontologicoService = new EsusAtendimentoOdontologicoService();
	private EsusFichaProcedimentoService fichaProcedimentoService = new EsusFichaProcedimentoService();
	private GeradorZip geradorZip = new GeradorZip();
	
	String userHome = System.getProperty("user.dir");
	String pathPadrao;
	
	private MasterService masterService = new MasterService();
	
	public void geraArquivos() {
		
		try {
			criaPastas();
			TelaPrincipal
					.enviaLog("Importando e Convertendo Cadastro Domiciliar");
			List<DadoTransporteThrift> dadosTransportCadastroDomiciliar = cadastroDomiciliarService
					.buscaRegistros();
			TelaPrincipal
			.enviaLog("----> Total de registros:" + (dadosTransportCadastroDomiciliar != null ? dadosTransportCadastroDomiciliar.size() : 0));

			TelaPrincipal
			.enviaLog("Importando e Convertendo Atividade Coletiva");
			List<DadoTransporteThrift> dadosTransportAtividadeColetiva = atividadeColetivaService
					.buscaRegistros();
			TelaPrincipal
			.enviaLog("----> Total de registros:" + (dadosTransportAtividadeColetiva != null ? dadosTransportAtividadeColetiva.size() : 0));

			TelaPrincipal
			.enviaLog("Importando e Convertendo Cadastro Individual");
			List<DadoTransporteThrift> dadosTransportCadastroIndividual = cadastroIndividualService
					.buscaRegistros();
			TelaPrincipal
			.enviaLog("----> Total de registros:" + (dadosTransportCadastroIndividual != null ? dadosTransportCadastroIndividual.size() : 0));

			
			TelaPrincipal
			.enviaLog("Importando e Convertendo Visita domiciliar");
			List<DadoTransporteThrift> dadosTransportVisitaDomiciliar = visitaDomiciliarService
					.buscaRegistros();
			TelaPrincipal
			.enviaLog("----> Total de registros:" + (dadosTransportVisitaDomiciliar != null ? dadosTransportVisitaDomiciliar.size() : 0));

			
			TelaPrincipal
			.enviaLog("Importando e Convertendo Atendimento Individual");
			List<DadoTransporteThrift> dadosTransportAtendimentoIndividual = atendimentoIndividualService
					.buscaRegistros();
			TelaPrincipal
			.enviaLog("----> Total de registros:" + (dadosTransportAtendimentoIndividual != null ? dadosTransportAtendimentoIndividual.size() : 0));

			
			TelaPrincipal
			.enviaLog("Importando e Convertendo Atendimento Odontologico");
			List<DadoTransporteThrift> dadosTransportAtendimentoOdontologico = atendimentoOdontologicoService
					.buscaRegistros();
			//List<DadoTransporteThrift> dadosTransportAtendimentoOdontologico = new ArrayList<DadoTransporteThrift>();
			TelaPrincipal
			.enviaLog("----> Total de registros:" + (dadosTransportAtendimentoOdontologico != null ? dadosTransportAtendimentoOdontologico.size() : 0));
			
			TelaPrincipal
			.enviaLog("Importando e Convertendo Ficha Procedimento");
			List<DadoTransporteThrift> dadosTransportProcedimento = fichaProcedimentoService
					.buscaRegistros();
			TelaPrincipal
			.enviaLog("----> Total de registros:" + (dadosTransportProcedimento != null ? dadosTransportProcedimento.size() : 0));


			TelaPrincipal.enviaLog("Criando Arquivo Serializado");
			geradorZip.empacotaZir(dadosTransportCadastroDomiciliar, dadosTransportAtividadeColetiva, 
					dadosTransportCadastroIndividual, dadosTransportVisitaDomiciliar, dadosTransportAtendimentoIndividual, dadosTransportAtendimentoOdontologico
					, dadosTransportProcedimento, pathPadrao);
			TelaPrincipal.enviaLog("Processo Finalizado");
		} catch (Exception e) {
			log.error(new Date() + " " +  e.getStackTrace());
			JOptionPane
					.showMessageDialog(null, "Erro grave: " + e.getStackTrace());
			TelaPrincipal.enviaLog(new Date()+" - "+e.getStackTrace());
		}
	}

	private void criaPastas() {
		
		pathPadrao = userHome + "\\gerador_esus";
		File d1 = new File(pathPadrao);
        d1.mkdir();
		
	}
	
	public void cancelaEnvio(Date dataGeracao) throws Exception {
		List<String> nomes = new ArrayList<String>();
		nomes.add("EsusCadastroDomiciliar");
		nomes.add("EsusCadastroIndividual");
		nomes.add("EsusAtividadeColetiva");
		nomes.add("EsusAtendimentoOdontologico");
		nomes.add("EsusAtendimentoIndividual");
		nomes.add("EsusFichaProcedimento");
		nomes.add("EsusVisitaDomiciliar");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/YYYY");
		TelaPrincipal.enviaLog("Cancelamento de geração do dia: " + sdf.format(dataGeracao) );
		for (String nomeEntidade : nomes) {
			masterService.cancelaEnvio(nomeEntidade, dataGeracao);
			TelaPrincipal.enviaLog("cancelando -->" + nomeEntidade);
		}
		
	}

}
