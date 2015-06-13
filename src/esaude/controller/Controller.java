package esaude.controller;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

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

	public void geraArquivos() {
		try {
			TelaPrincipal
					.enviaLog("Importando e Convertendo Cadastro Domiciliar");
			List<DadoTransporteThrift> dadosTransportCadastroDomiciliar = cadastroDomiciliarService
					.buscaRegistros();
			TelaPrincipal.enviaLog("Criando Arquivo Serializado");
			geradorZip.empacotaZir(dadosTransportCadastroDomiciliar);
			TelaPrincipal.enviaLog("Processo Finalizado");
		} catch (Exception e) {
			log.error(new Date() + " " +  e.getMessage());
			JOptionPane
					.showMessageDialog(null, "Erro grave: " + e.getMessage());
			
		}
	}

}
