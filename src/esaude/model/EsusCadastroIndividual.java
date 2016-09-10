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

@Entity
@Table(name = "esus_cadastro_individual")
public class EsusCadastroIndividual implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_prontuario")
	private PProntuario pProntuario;

	public Boolean getResponsavelFamiliar() {
		return responsavelFamiliar;
	}

	public void setResponsavelFamiliar(Boolean responsavelFamiliar) {
		this.responsavelFamiliar = responsavelFamiliar;
	}

	public PProntuario getpProntuarioResponsavel() {
		return pProntuarioResponsavel;
	}

	public void setpProntuarioResponsavel(PProntuario pProntuarioResponsavel) {
		this.pProntuarioResponsavel = pProntuarioResponsavel;
	}

	public EsusRelacaoparentesco getEsusRelacaoparentesco() {
		return esusRelacaoparentesco;
	}

	public void setEsusRelacaoparentesco(EsusRelacaoparentesco esusRelacaoparentesco) {
		this.esusRelacaoparentesco = esusRelacaoparentesco;
	}

	@Column(name = "cns_profissional")
	private String cnsProfissional;

	@Column(name = "cnes_unidade")
	private String cnesUnidade;

	@Column(name = "ine_equipe")
	private String ineEquipe;

	@Column(name = "data_atendimento")
	private Date dataAtendimento;

	@Column(name = "microarea")
	private Long microarea;

	@Column(name = "frequenta_escola")
	private Boolean frequentaEscola;

	@Column(name = "tem_alguma_deficiencia")
	private Boolean temAlgumaDeficiencia;

	@Column(name = "em_situacao_rua")
	private Boolean emSituacaoRua;

	@ManyToOne
	@JoinColumn(name = "tempo_situacao_rua")
	private EsusTemposituacaoderua esusTemposituacaoderua;

	@Column(name = "recebe_beneficio")
	private Boolean recebeBeneficio;

	@Column(name = "possui_referencia_familiar")
	private Boolean possuiReferenciaFamiliar;

	@Column(name = "acompanhado_outra_inst")
	private Boolean acompanhadoOutraInst;

	@Column(name = "outra_inst_que_acompanha")
	private String outraInstQueAcompanha;

	@Column(name = "visita_familiar_frequentemente")
	private Boolean visitaFamiliarFrequentemente;

	@Column(name = "grau_parentesco_familiar_freq")
	private String grauParentescoFamiliarFreq;

	@Column(name = "esta_fumante")
	private Boolean estaFumante;

	@Column(name = "dependente_alcool")
	private Boolean dependenteAlcool;

	@Column(name = "dependente_droga")
	private Boolean dependenteDroga;

	@Column(name = "hipertenso")
	private Boolean hipertenso;

	@Column(name = "diabete")
	private Boolean diabete;

	@Column(name = "avc_derrame")
	private Boolean avcDerrame;

	@Column(name = "infarto")
	private Boolean infarto;

	@Column(name = "hanseniase")
	private Boolean hanseniase;

	@Column(name = "tuberculose")
	private Boolean tuberculose;

	@Column(name = "cancer")
	private Boolean cancer;

	@Column(name = "st_recusa_cadastro")
	private Boolean stRecusaCadastro;

	@Column(name = "esta_gestante")
	private Boolean estaGestante;

	@Column(name = "st_envio")
	private Long stEnvio;

	@Column(name = "dt_envio")
	private Date dtEnvio;

	@Column(name = "ficha_atualizada")
	private Boolean fichaAtualizada;

	@Column(name = "uuid")
	private String uuid;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private FrUsuario frUsuario;

	@ManyToOne
	@JoinColumn(name = "co_motivo_saida")
	private EsusMotivosaida esusMotivosaida;

	@Column(name = "id_origem")
	private Long idOrigem;
	
	@Column(name = "responsavel_familiar")
	private Boolean responsavelFamiliar;
	
	@ManyToOne
	@JoinColumn(name = "id_prontuario_responsavel")
	private PProntuario pProntuarioResponsavel;

	@ManyToOne
    @JoinColumn(name = "id_parentesco_responsavel")
    private EsusRelacaoparentesco esusRelacaoparentesco;

	
	public PProntuario getpProntuario() {
		return pProntuario;
	}

	public void setpProntuario(PProntuario pProntuario) {
		this.pProntuario = pProntuario;
	}

	public Long getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(Long idOrigem) {
		this.idOrigem = idOrigem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PProntuario getPProntuario() {
		return pProntuario;
	}

	public void setPProntuario(PProntuario pProntuario) {
		this.pProntuario = pProntuario;
	}

	public String getCnsProfissional() {
		return cnsProfissional;
	}

	public void setCnsProfissional(String cnsProfissional) {
		this.cnsProfissional = cnsProfissional;
	}

	public String getCnesUnidade() {
		return cnesUnidade;
	}

	public void setCnesUnidade(String cnesUnidade) {
		this.cnesUnidade = cnesUnidade;
	}

	public String getIneEquipe() {
		return ineEquipe;
	}

	public void setIneEquipe(String ineEquipe) {
		this.ineEquipe = ineEquipe;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Long getMicroarea() {
		return microarea;
	}

	public void setMicroarea(Long microarea) {
		this.microarea = microarea;
	}

	public Boolean getFrequentaEscola() {
		return frequentaEscola;
	}

	public void setFrequentaEscola(Boolean frequentaEscola) {
		this.frequentaEscola = frequentaEscola;
	}

	public Boolean getTemAlgumaDeficiencia() {
		return temAlgumaDeficiencia;
	}

	public void setTemAlgumaDeficiencia(Boolean temAlgumaDeficiencia) {
		this.temAlgumaDeficiencia = temAlgumaDeficiencia;
	}

	public Boolean getEmSituacaoRua() {
		return emSituacaoRua;
	}

	public void setEmSituacaoRua(Boolean emSituacaoRua) {
		this.emSituacaoRua = emSituacaoRua;
	}

	public EsusTemposituacaoderua getEsusTemposituacaoderua() {
		return esusTemposituacaoderua;
	}

	public void setEsusTemposituacaoderua(EsusTemposituacaoderua esusTemposituacaoderua) {
		this.esusTemposituacaoderua = esusTemposituacaoderua;
	}

	public Boolean getRecebeBeneficio() {
		return recebeBeneficio;
	}

	public void setRecebeBeneficio(Boolean recebeBeneficio) {
		this.recebeBeneficio = recebeBeneficio;
	}

	public Boolean getPossuiReferenciaFamiliar() {
		return possuiReferenciaFamiliar;
	}

	public void setPossuiReferenciaFamiliar(Boolean possuiReferenciaFamiliar) {
		this.possuiReferenciaFamiliar = possuiReferenciaFamiliar;
	}

	public Boolean getAcompanhadoOutraInst() {
		return acompanhadoOutraInst;
	}

	public void setAcompanhadoOutraInst(Boolean acompanhadoOutraInst) {
		this.acompanhadoOutraInst = acompanhadoOutraInst;
	}

	public String getOutraInstQueAcompanha() {
		return outraInstQueAcompanha;
	}

	public void setOutraInstQueAcompanha(String outraInstQueAcompanha) {
		this.outraInstQueAcompanha = outraInstQueAcompanha;
	}

	public Boolean getVisitaFamiliarFrequentemente() {
		return visitaFamiliarFrequentemente;
	}

	public void setVisitaFamiliarFrequentemente(Boolean visitaFamiliarFrequentemente) {
		this.visitaFamiliarFrequentemente = visitaFamiliarFrequentemente;
	}

	public String getGrauParentescoFamiliarFreq() {
		return grauParentescoFamiliarFreq;
	}

	public void setGrauParentescoFamiliarFreq(String grauParentescoFamiliarFreq) {
		this.grauParentescoFamiliarFreq = grauParentescoFamiliarFreq;
	}

	public Boolean getEstaFumante() {
		return estaFumante;
	}

	public void setEstaFumante(Boolean estaFumante) {
		this.estaFumante = estaFumante;
	}

	public Boolean getDependenteAlcool() {
		return dependenteAlcool;
	}

	public void setDependenteAlcool(Boolean dependenteAlcool) {
		this.dependenteAlcool = dependenteAlcool;
	}

	public Boolean getDependenteDroga() {
		return dependenteDroga;
	}

	public void setDependenteDroga(Boolean dependenteDroga) {
		this.dependenteDroga = dependenteDroga;
	}

	public Boolean getHipertenso() {
		return hipertenso;
	}

	public void setHipertenso(Boolean hipertenso) {
		this.hipertenso = hipertenso;
	}

	public Boolean getDiabete() {
		return diabete;
	}

	public void setDiabete(Boolean diabete) {
		this.diabete = diabete;
	}

	public Boolean getAvcDerrame() {
		return avcDerrame;
	}

	public void setAvcDerrame(Boolean avcDerrame) {
		this.avcDerrame = avcDerrame;
	}

	public Boolean getInfarto() {
		return infarto;
	}

	public void setInfarto(Boolean infarto) {
		this.infarto = infarto;
	}

	public Boolean getHanseniase() {
		return hanseniase;
	}

	public void setHanseniase(Boolean hanseniase) {
		this.hanseniase = hanseniase;
	}

	public Boolean getTuberculose() {
		return tuberculose;
	}

	public void setTuberculose(Boolean tuberculose) {
		this.tuberculose = tuberculose;
	}

	public Boolean getCancer() {
		return cancer;
	}

	public void setCancer(Boolean cancer) {
		this.cancer = cancer;
	}

	public Boolean getStRecusaCadastro() {
		return stRecusaCadastro;
	}

	public void setStRecusaCadastro(Boolean stRecusaCadastro) {
		this.stRecusaCadastro = stRecusaCadastro;
	}

	public Boolean getEstaGestante() {
		return estaGestante;
	}

	public void setEstaGestante(Boolean estaGestante) {
		this.estaGestante = estaGestante;
	}

	public Long getStEnvio() {
		return stEnvio;
	}

	public void setStEnvio(Long stEnvio) {
		this.stEnvio = stEnvio;
	}

	public Date getDtEnvio() {
		return dtEnvio;
	}

	public void setDtEnvio(Date dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public Boolean getFichaAtualizada() {
		return fichaAtualizada;
	}

	public void setFichaAtualizada(Boolean fichaAtualizada) {
		this.fichaAtualizada = fichaAtualizada;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public FrUsuario getFrUsuario() {
		return frUsuario;
	}

	public void setFrUsuario(FrUsuario frUsuario) {
		this.frUsuario = frUsuario;
	}

	public EsusMotivosaida getEsusMotivosaida() {
		return esusMotivosaida;
	}

	public void setEsusMotivosaida(EsusMotivosaida esusMotivosaida) {
		this.esusMotivosaida = esusMotivosaida;
	}
	
	public boolean isFichaAtualizada() {
		if (this.fichaAtualizada == null) {
			return false; 
		} else {
			return fichaAtualizada;
		}
	}

}