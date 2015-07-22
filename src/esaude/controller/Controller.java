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
import esaude.service.EsusVisitaDomiciliarService;
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
	private GeradorZip geradorZip = new GeradorZip();
	
	String userHome = System.getProperty("user.dir");
	String pathPadrao;
	
	public void geraArquivos() {
		
		try {
			criaPastas();
			TelaPrincipal
					.enviaLog("Importando e Convertendo Cadastro Domiciliar");
			List<DadoTransporteThrift> dadosTransportCadastroDomiciliar = cadastroDomiciliarService
					.buscaRegistros();
			TelaPrincipal
			.enviaLog("Importando e Convertendo Atividade Coletiva");
			List<DadoTransporteThrift> dadosTransportAtividadeColetiva = atividadeColetivaService
					.buscaRegistros();
			TelaPrincipal
			.enviaLog("Importando e Convertendo Cadastro Individual");
			List<DadoTransporteThrift> dadosTransportCadastroIndividual = cadastroIndividualService
					.buscaRegistros();
			
			TelaPrincipal
			.enviaLog("Importando e Convertendo Visita domiciliar");
			List<DadoTransporteThrift> dadosTransportVisitaDomiciliar = visitaDomiciliarService
					.buscaRegistros();
			
			TelaPrincipal
			.enviaLog("Importando e Convertendo Atendimento Individual");
			List<DadoTransporteThrift> dadosTransportAtendimentoIndividual = atendimentoIndividualService
					.buscaRegistros();
			
			//TelaPrincipal
			//.enviaLog("Importando e Convertendo Atendimento Odontologico");
			//List<DadoTransporteThrift> dadosTransportAtendimentoOdontologico = atendimentoOdontologicoService
			//		.buscaRegistros();
			List<DadoTransporteThrift> dadosTransportAtendimentoOdontologico = new ArrayList<DadoTransporteThrift>();
			
			
			TelaPrincipal.enviaLog("Criando Arquivo Serializado");
			geradorZip.empacotaZir(dadosTransportCadastroDomiciliar, dadosTransportAtividadeColetiva, 
					dadosTransportCadastroIndividual, dadosTransportVisitaDomiciliar, dadosTransportAtendimentoIndividual, dadosTransportAtendimentoOdontologico
					, pathPadrao);
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

}
