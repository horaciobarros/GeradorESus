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
@Table(name="esus_qst_pergunta_respostas")
public class EsusQstPerguntaRespostas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_pergunta")
	private EsusQstPergunta esusQstPergunta;

	@ManyToOne
	@JoinColumn(name = "id_resposta")
	private EsusQstRespostas esusQstRespostas;

	public Long getId() { 
		return id;
	}
	public void  setId(Long id) { 
		this.id = id;
	}


	public  EsusQstPergunta  getEsusQstPergunta() { 
		return esusQstPergunta;
	}
	public void  setEsusQstPergunta(EsusQstPergunta esusQstPergunta) { 
		this.esusQstPergunta = esusQstPergunta;
	}


	public  EsusQstRespostas  getEsusQstRespostas() { 
		return esusQstRespostas;
	}
	public void  setEsusQstRespostas(EsusQstRespostas esusQstRespostas) { 
		this.esusQstRespostas = esusQstRespostas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((esusQstPergunta == null) ? 0 : esusQstPergunta.hashCode());
		result = prime * result + ((esusQstRespostas == null) ? 0 : esusQstRespostas.hashCode());
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

		EsusQstPerguntaRespostas other = (EsusQstPerguntaRespostas) obj;

		if (id == null){
			if (other.id!= null)
				return false;
		} else if (!id.equals(other.id)){
			return false;
		}

		if (esusQstPergunta == null){
			if (other.esusQstPergunta!= null)
				return false;
		} else if (!esusQstPergunta.equals(other.esusQstPergunta)){
			return false;
		}

		if (esusQstRespostas == null){
			if (other.esusQstRespostas!= null)
				return false;
		} else if (!esusQstRespostas.equals(other.esusQstRespostas)){
			return false;
		}

		return true;
	}
}