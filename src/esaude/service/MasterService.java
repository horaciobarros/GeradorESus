package esaude.service;

import java.util.UUID;

public class MasterService {
	
	protected String gerarUuid(String cnesUnidade) {
		
		String retorno = cnesUnidade.substring(0, 6) + "-" + UUID.randomUUID().toString();
		return retorno;
	}

}
