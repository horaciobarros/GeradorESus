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
@Table(name="esus_consumo_alimentar")
public class EsusConsumoAlimentar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="cnes_unidade")
	private String cnesUnidade;

	@Column(name="cns_profissional")
	private String cnsProfissional;

	@Column(name="cbo_profissional")
	private String cboProfissional;

	@Column(name="ine_equipe")
	private String ineEquipe;

	@Column(name="dt_atendimento")
	private Date dtAtendimento;

	@ManyToOne
	@JoinColumn(name = "id_prontuario")
	private PProntuario pProntuario;

	@ManyToOne
	@JoinColumn(name = "id_local_atendimento")
	private EsusLocaldeatendimento esusLocaldeatendimento;

	@Column(name="st_envio")
	private Long stEnvio;

	@Column(name="dt_envio")
	private Date dtEnvio;

	@Column(name="uuid")
	private String uuid;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private FrUsuario frUsuario;

	@Column(name="dt_nascimento")
	private Date dtNascimento;

	public Long getId() { 
		return id;
	}
	public void  setId(Long id) { 
		this.id = id;
	}

	public String getCnesUnidade() { 
		return cnesUnidade;
	}
	public void  setCnesUnidade(String cnesUnidade) { 
		this.cnesUnidade = cnesUnidade;
	}

	public String getCnsProfissional() { 
		return cnsProfissional;
	}
	public void  setCnsProfissional(String cnsProfissional) { 
		this.cnsProfissional = cnsProfissional;
	}

	public String getCboProfissional() { 
		return cboProfissional;
	}
	public void  setCboProfissional(String cboProfissional) { 
		this.cboProfissional = cboProfissional;
	}

	public String getIneEquipe() { 
		return ineEquipe;
	}
	public void  setIneEquipe(String ineEquipe) { 
		this.ineEquipe = ineEquipe;
	}

	public Date getDtAtendimento() { 
		return dtAtendimento;
	}
	public void  setDtAtendimento(Date dtAtendimento) { 
		this.dtAtendimento = dtAtendimento;
	}


	public  PProntuario  getPProntuario() { 
		return pProntuario;
	}
	public void  setPProntuario(PProntuario pProntuario) { 
		this.pProntuario = pProntuario;
	}


	public  EsusLocaldeatendimento  getEsusLocaldeatendimento() { 
		return esusLocaldeatendimento;
	}
	public void  setEsusLocaldeatendimento(EsusLocaldeatendimento esusLocaldeatendimento) { 
		this.esusLocaldeatendimento = esusLocaldeatendimento;
	}

	public Long getStEnvio() { 
		return stEnvio;
	}
	public void  setStEnvio(Long stEnvio) { 
		this.stEnvio = stEnvio;
	}

	public Date getDtEnvio() { 
		return dtEnvio;
	}
	public void  setDtEnvio(Date dtEnvio) { 
		this.dtEnvio = dtEnvio;
	}

	public String getUuid() { 
		return uuid;
	}
	public void  setUuid(String uuid) { 
		this.uuid = uuid;
	}


	public  FrUsuario  getFrUsuario() { 
		return frUsuario;
	}
	public void  setFrUsuario(FrUsuario frUsuario) { 
		this.frUsuario = frUsuario;
	}

	public Date getDtNascimento() { 
		return dtNascimento;
	}
	public void  setDtNascimento(Date dtNascimento) { 
		this.dtNascimento = dtNascimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((cnesUnidade == null) ? 0 : cnesUnidade.hashCode());
		result = prime * result + ((cnsProfissional == null) ? 0 : cnsProfissional.hashCode());
		result = prime * result + ((cboProfissional == null) ? 0 : cboProfissional.hashCode());
		result = prime * result + ((ineEquipe == null) ? 0 : ineEquipe.hashCode());
		result = prime * result + ((dtAtendimento == null) ? 0 : dtAtendimento.hashCode());
		result = prime * result + ((pProntuario == null) ? 0 : pProntuario.hashCode());
		result = prime * result + ((esusLocaldeatendimento == null) ? 0 : esusLocaldeatendimento.hashCode());
		result = prime * result + ((stEnvio == null) ? 0 : stEnvio.hashCode());
		result = prime * result + ((dtEnvio == null) ? 0 : dtEnvio.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + ((frUsuario == null) ? 0 : frUsuario.hashCode());
		result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
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

		EsusConsumoAlimentar other = (EsusConsumoAlimentar) obj;

		if (id == null){
			if (other.id!= null)
				return false;
		} else if (!id.equals(other.id)){
			return false;
		}

		if (cnesUnidade == null){
			if (other.cnesUnidade!= null)
				return false;
		} else if (!cnesUnidade.equals(other.cnesUnidade)){
			return false;
		}

		if (cnsProfissional == null){
			if (other.cnsProfissional!= null)
				return false;
		} else if (!cnsProfissional.equals(other.cnsProfissional)){
			return false;
		}

		if (cboProfissional == null){
			if (other.cboProfissional!= null)
				return false;
		} else if (!cboProfissional.equals(other.cboProfissional)){
			return false;
		}

		if (ineEquipe == null){
			if (other.ineEquipe!= null)
				return false;
		} else if (!ineEquipe.equals(other.ineEquipe)){
			return false;
		}

		if (dtAtendimento == null){
			if (other.dtAtendimento!= null)
				return false;
		} else if (!dtAtendimento.equals(other.dtAtendimento)){
			return false;
		}

		if (pProntuario == null){
			if (other.pProntuario!= null)
				return false;
		} else if (!pProntuario.equals(other.pProntuario)){
			return false;
		}

		if (esusLocaldeatendimento == null){
			if (other.esusLocaldeatendimento!= null)
				return false;
		} else if (!esusLocaldeatendimento.equals(other.esusLocaldeatendimento)){
			return false;
		}

		if (stEnvio == null){
			if (other.stEnvio!= null)
				return false;
		} else if (!stEnvio.equals(other.stEnvio)){
			return false;
		}

		if (dtEnvio == null){
			if (other.dtEnvio!= null)
				return false;
		} else if (!dtEnvio.equals(other.dtEnvio)){
			return false;
		}

		if (uuid == null){
			if (other.uuid!= null)
				return false;
		} else if (!uuid.equals(other.uuid)){
			return false;
		}

		if (frUsuario == null){
			if (other.frUsuario!= null)
				return false;
		} else if (!frUsuario.equals(other.frUsuario)){
			return false;
		}

		if (dtNascimento == null){
			if (other.dtNascimento!= null)
				return false;
		} else if (!dtNascimento.equals(other.dtNascimento)){
			return false;
		}

		return true;
	}
}