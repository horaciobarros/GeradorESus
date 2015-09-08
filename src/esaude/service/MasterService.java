package esaude.service;

import java.util.Date;
import java.util.UUID;

import esaude.dao.Dao;

public class MasterService {
	
	Dao dao = new Dao();
	protected String gerarUuid(String cnesUnidade) {
		
		String retorno = cnesUnidade.substring(0, 7) + "-" + UUID.randomUUID();
		return retorno;
	}
	
	public void cancelaEnvio(String nomeEntidade, Date dataGeracao) throws Exception {
		
		dao.cancelaEnvio(nomeEntidade, dataGeracao);
		
	}

}
