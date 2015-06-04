package esaude.view;

import esaude.controller.Controller;

public class Principal {
	
	private Controller controller = new Controller();
	
	public static void main (String args[]) {
		Principal principal = new Principal();
		principal.geraArquivos();
		
	}
	
	private void geraArquivos() {
		controller.geraArquivos();
	}

}
