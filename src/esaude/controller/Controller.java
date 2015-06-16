package esaude.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.service.EsusCadastroDomiciliarService;
import esaude.util.GeradorZip;
import esaude.view.TelaPrincipal;

public class Controller {
	static Logger log = Logger.getLogger(Controller.class
			.getName());

	private EsusCadastroDomiciliarService cadastroDomiciliarService = new EsusCadastroDomiciliarService();
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
			TelaPrincipal.enviaLog("Criando Arquivo Serializado");
			geradorZip.empacotaZir(dadosTransportCadastroDomiciliar, pathPadrao);
			TelaPrincipal.enviaLog("Processo Finalizado");
		} catch (Exception e) {
			log.error(new Date() + " " +  e.getMessage());
			JOptionPane
					.showMessageDialog(null, "Erro grave: " + e.getMessage());
			
		}
	}

	private void criaPastas() {
		
		pathPadrao = userHome + "\\gerador_esus";
		File d1 = new File(pathPadrao);
        d1.mkdir();
		
	}

}
