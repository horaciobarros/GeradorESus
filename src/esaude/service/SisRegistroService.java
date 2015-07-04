package esaude.service; 

import java.io.Serializable;

import org.apache.log4j.Logger;

import esaude.dao.SisRegistroDao;
import esaude.model.SisRegistro;
public class SisRegistroService implements Serializable{
	
	static Logger log = Logger.getLogger(EsusCadastroDomiciliarService.class
			.getName());
	private SisRegistroDao dao = new SisRegistroDao();

   private static final long serialVersionUID = 1L;

   public SisRegistro buscaSisRegistro(){
      return dao.buscaSisRegistro();
   }
}