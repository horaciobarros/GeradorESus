package esaude.controller;

import java.util.List;

import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.service.EsusCadastroDomiciliarService;
import esaude.util.GeradorZip;
import esaude.view.TelaPrincipal;

public class Controller {
	
	private EsusCadastroDomiciliarService cadastroDomiciliarService = new EsusCadastroDomiciliarService();
	private GeradorZip geradorZip = new GeradorZip();
	
	public void geraArquivos() {
		TelaPrincipal.enviaLog("Importando e Convertendo Cadastro Domiciliar");
		List<DadoTransporteThrift> dadosTransportCadastroDomiciliar = cadastroDomiciliarService.buscaRegistros();
		TelaPrincipal.enviaLog("Criando Arquivo Serializado");
		geradorZip.empacotaZir(dadosTransportCadastroDomiciliar);
		TelaPrincipal.enviaLog("Processo Finalizado");
	}

}
