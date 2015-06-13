package esaude.service; 

import java.io.Serializable;

import esaude.model.EsusRegistro;
import esaude.dao.EsusRegistroDaoImpl;

import org.apache.log4j.Logger;
public class EsusRegistroServiceImpl implements Serializable{
	
	static Logger log = Logger.getLogger(EsusCadastroDomiciliarService.class
			.getName());
	private EsusRegistroDaoImpl dao = new EsusRegistroDaoImpl();

   private static final long serialVersionUID = 1L;

   public EsusRegistro buscaEsusRegistro(){
      return dao.buscaEsusRegistro();
   }
}