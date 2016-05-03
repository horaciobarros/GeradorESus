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
@Table(name="esus_qst_questionario_pergunta")
public class EsusQstQuestionarioPergunta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="co_qst_questionario_pergunta")
	private Long coQstQuestionarioPergunta;

	@Column(name="ds_ordenacao")
	private String dsOrdenacao;

	@ManyToOne
	@JoinColumn(name = "co_qst_questionario")
	private EsusQstQuestionario esusQstQuestionario;

	@ManyToOne
	@JoinColumn(name = "co_qst_pergunta")
	private EsusQstPergunta esusQstPergunta;

	@Column(name="st_ativo")
	private Long stAtivo;

	public Long getCoQstQuestionarioPergunta() { 
		return coQstQuestionarioPergunta;
	}
	public void  setCoQstQuestionarioPergunta(Long coQstQuestionarioPergunta) { 
		this.coQstQuestionarioPergunta = coQstQuestionarioPergunta;
	}

	public String getDsOrdenacao() { 
		return dsOrdenacao;
	}
	public void  setDsOrdenacao(String dsOrdenacao) { 
		this.dsOrdenacao = dsOrdenacao;
	}


	public  EsusQstQuestionario  getEsusQstQuestionario() { 
		return esusQstQuestionario;
	}
	public void  setEsusQstQuestionario(EsusQstQuestionario esusQstQuestionario) { 
		this.esusQstQuestionario = esusQstQuestionario;
	}


	public  EsusQstPergunta  getEsusQstPergunta() { 
		return esusQstPergunta;
	}
	public void  setEsusQstPergunta(EsusQstPergunta esusQstPergunta) { 
		this.esusQstPergunta = esusQstPergunta;
	}

	public Long getStAtivo() { 
		return stAtivo;
	}
	public void  setStAtivo(Long stAtivo) { 
		this.stAtivo = stAtivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coQstQuestionarioPergunta == null) ? 0 : coQstQuestionarioPergunta.hashCode());
		result = prime * result + ((dsOrdenacao == null) ? 0 : dsOrdenacao.hashCode());
		result = prime * result + ((esusQstQuestionario == null) ? 0 : esusQstQuestionario.hashCode());
		result = prime * result + ((esusQstPergunta == null) ? 0 : esusQstPergunta.hashCode());
		result = prime * result + ((stAtivo == null) ? 0 : stAtivo.hashCode());
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

		EsusQstQuestionarioPergunta other = (EsusQstQuestionarioPergunta) obj;

		if (coQstQuestionarioPergunta == null){
			if (other.coQstQuestionarioPergunta!= null)
				return false;
		} else if (!coQstQuestionarioPergunta.equals(other.coQstQuestionarioPergunta)){
			return false;
		}

		if (dsOrdenacao == null){
			if (other.dsOrdenacao!= null)
				return false;
		} else if (!dsOrdenacao.equals(other.dsOrdenacao)){
			return false;
		}

		if (esusQstQuestionario == null){
			if (other.esusQstQuestionario!= null)
				return false;
		} else if (!esusQstQuestionario.equals(other.esusQstQuestionario)){
			return false;
		}

		if (esusQstPergunta == null){
			if (other.esusQstPergunta!= null)
				return false;
		} else if (!esusQstPergunta.equals(other.esusQstPergunta)){
			return false;
		}

		if (stAtivo == null){
			if (other.stAtivo!= null)
				return false;
		} else if (!stAtivo.equals(other.stAtivo)){
			return false;
		}

		return true;
	}
}