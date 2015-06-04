package esaude.controller;

import esaude.model.EsusCadastroDomiciliar;
import esaude.service.EsusCadastroDomiciliarService;

public class Controller {
	
	private EsusCadastroDomiciliarService cadastroDomiciliarService = new EsusCadastroDomiciliarService();
	
	public void geraArquivos() {
		geraCadastroDomiciliar();
		
	}
	
	private void geraCadastroDomiciliar() {
		for (EsusCadastroDomiciliar cadDomiciliar : cadastroDomiciliarService.findNaoEnvidados()) {
			System.out.println(cadDomiciliar.getNuDomicilio());
		}
	}

}
