package esaude.controller;

import java.util.List;

import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.model.EsusCadastroDomiciliar;
import esaude.service.EsusCadastroDomiciliarService;
import esaude.util.GeradorZip;

public class Controller {
	
	private EsusCadastroDomiciliarService cadastroDomiciliarService = new EsusCadastroDomiciliarService();
	private GeradorZip geradorZip = new GeradorZip();
	
	public void geraArquivos() {
		List<DadoTransporteThrift> dadosTransportCadastroDomiciliar = cadastroDomiciliarService.buscaRegistros();
		geradorZip.empacotaZir(dadosTransportCadastroDomiciliar);
		
	}

}
