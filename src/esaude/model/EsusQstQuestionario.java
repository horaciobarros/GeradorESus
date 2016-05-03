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
@Table(name="esus_qst_questionario")
public class EsusQstQuestionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="co_qst_questionario")
	private Long coQstQuestionario;

	@Column(name="ds_questionario")
	private String dsQuestionario;

	@Column(name="st_ativo")
	private Long stAtivo;

	@Column(name="tp_qst_questionario")
	private Long tpQstQuestionario;

	@Column(name="nu_idade_meses_limite_inferior")
	private Long nuIdadeMesesLimiteInferior;

	@Column(name="nu_idade_meses_limite_superior")
	private Long nuIdadeMesesLimiteSuperior;

	public Long getCoQstQuestionario() { 
		return coQstQuestionario;
	}
	public void  setCoQstQuestionario(Long coQstQuestionario) { 
		this.coQstQuestionario = coQstQuestionario;
	}

	public String getDsQuestionario() { 
		return dsQuestionario;
	}
	public void  setDsQuestionario(String dsQuestionario) { 
		this.dsQuestionario = dsQuestionario;
	}

	public Long getStAtivo() { 
		return stAtivo;
	}
	public void  setStAtivo(Long stAtivo) { 
		this.stAtivo = stAtivo;
	}

	public Long getTpQstQuestionario() { 
		return tpQstQuestionario;
	}
	public void  setTpQstQuestionario(Long tpQstQuestionario) { 
		this.tpQstQuestionario = tpQstQuestionario;
	}

	public Long getNuIdadeMesesLimiteInferior() { 
		return nuIdadeMesesLimiteInferior;
	}
	public void  setNuIdadeMesesLimiteInferior(Long nuIdadeMesesLimiteInferior) { 
		this.nuIdadeMesesLimiteInferior = nuIdadeMesesLimiteInferior;
	}

	public Long getNuIdadeMesesLimiteSuperior() { 
		return nuIdadeMesesLimiteSuperior;
	}
	public void  setNuIdadeMesesLimiteSuperior(Long nuIdadeMesesLimiteSuperior) { 
		this.nuIdadeMesesLimiteSuperior = nuIdadeMesesLimiteSuperior;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coQstQuestionario == null) ? 0 : coQstQuestionario.hashCode());
		result = prime * result + ((dsQuestionario == null) ? 0 : dsQuestionario.hashCode());
		result = prime * result + ((stAtivo == null) ? 0 : stAtivo.hashCode());
		result = prime * result + ((tpQstQuestionario == null) ? 0 : tpQstQuestionario.hashCode());
		result = prime * result + ((nuIdadeMesesLimiteInferior == null) ? 0 : nuIdadeMesesLimiteInferior.hashCode());
		result = prime * result + ((nuIdadeMesesLimiteSuperior == null) ? 0 : nuIdadeMesesLimiteSuperior.hashCode());
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

		EsusQstQuestionario other = (EsusQstQuestionario) obj;

		if (coQstQuestionario == null){
			if (other.coQstQuestionario!= null)
				return false;
		} else if (!coQstQuestionario.equals(other.coQstQuestionario)){
			return false;
		}

		if (dsQuestionario == null){
			if (other.dsQuestionario!= null)
				return false;
		} else if (!dsQuestionario.equals(other.dsQuestionario)){
			return false;
		}

		if (stAtivo == null){
			if (other.stAtivo!= null)
				return false;
		} else if (!stAtivo.equals(other.stAtivo)){
			return false;
		}

		if (tpQstQuestionario == null){
			if (other.tpQstQuestionario!= null)
				return false;
		} else if (!tpQstQuestionario.equals(other.tpQstQuestionario)){
			return false;
		}

		if (nuIdadeMesesLimiteInferior == null){
			if (other.nuIdadeMesesLimiteInferior!= null)
				return false;
		} else if (!nuIdadeMesesLimiteInferior.equals(other.nuIdadeMesesLimiteInferior)){
			return false;
		}

		if (nuIdadeMesesLimiteSuperior == null){
			if (other.nuIdadeMesesLimiteSuperior!= null)
				return false;
		} else if (!nuIdadeMesesLimiteSuperior.equals(other.nuIdadeMesesLimiteSuperior)){
			return false;
		}

		return true;
	}
}