package esaude.service;

import esaude.dao.EsusCadastroDomiciliarDao;
import esaude.model.EsusCadastroDomiciliar;
import java.util.List;

public class EsusCadastroDomiciliarService {
	private EsusCadastroDomiciliarDao dao = new EsusCadastroDomiciliarDao();

	public List<EsusCadastroDomiciliar> findNaoEnvidados() {
		return dao.findNaoEnviados();
	}
}
