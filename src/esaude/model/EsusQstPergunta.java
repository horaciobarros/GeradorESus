package esaude.model; 

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.sql.Time;

@Entity 
@Table(name="esus_qst_pergunta")
public class EsusQstPergunta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="co_qst_pergunta")
	private Long coQstPergunta;

	@Column(name="no_qst_pergunta")
	private String noQstPergunta;

	@Column(name="tp_opcao_pergunta")
	private Long tpOpcaoPergunta;

	public Long getCoQstPergunta() { 
		return coQstPergunta;
	}
	public void  setCoQstPergunta(Long coQstPergunta) { 
		this.coQstPergunta = coQstPergunta;
	}

	public String getNoQstPergunta() { 
		return noQstPergunta;
	}
	public void  setNoQstPergunta(String noQstPergunta) { 
		this.noQstPergunta = noQstPergunta;
	}

	public Long getTpOpcaoPergunta() { 
		return tpOpcaoPergunta;
	}
	public void  setTpOpcaoPergunta(Long tpOpcaoPergunta) { 
		this.tpOpcaoPergunta = tpOpcaoPergunta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coQstPergunta == null) ? 0 : coQstPergunta.hashCode());
		result = prime * result + ((noQstPergunta == null) ? 0 : noQstPergunta.hashCode());
		result = prime * result + ((tpOpcaoPergunta == null) ? 0 : tpOpcaoPergunta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		EsusQstPergunta other = (EsusQstPergunta) obj;

		if (coQstPergunta == null){
			if (other.coQstPergunta!= null)
				return false;
		} else if (!coQstPergunta.equals(other.coQstPergunta)){
			return false;
		}

		if (noQstPergunta == null){
			if (other.noQstPergunta!= null)
				return false;
		} else if (!noQstPergunta.equals(other.noQstPergunta)){
			return false;
		}

		if (tpOpcaoPergunta == null){
			if (other.tpOpcaoPergunta!= null)
				return false;
		} else if (!tpOpcaoPergunta.equals(other.tpOpcaoPergunta)){
			return false;
		}

		return true;
	}
}