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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "p_prontuario")
public class PProntuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "co_prontuario")
	private Long coProntuario;

	@Column(name = "co_usuario")
	private String coUsuario;

	@Column(name = "no_usuario")
	private String noUsuario;

	@Column(name = "no_mae")
	private String noMae;

	@Column(name = "no_pai")
	private String noPai;

	@Column(name = "dt_nascimento")
	private Date dtNascimento;

	@Column(name = "co_escolaridade")
	private String coEscolaridade;

	@Column(name = "co_situacao_familiar")
	private String coSituacaoFamiliar;

	@Column(name = "co_estado_civil")
	private String coEstadoCivil;

	@ManyToOne
	@JoinColumn(name = "co_raca")
	private PRacaCor pRacaCor;

	@ManyToOne
	@JoinColumn(name = "co_pais")
	private PNacionalidade pNacionalidade;

	@Column(name = "co_municipio_nasc")
	private String coMunicipioNasc;

	@Column(name = "nu_ddd_1")
	private String nuDdd1;

	@Column(name = "nu_telefone")
	private String nuTelefone;

	@Column(name = "nu_ddd_2")
	private String nuDdd2;

	@Column(name = "nu_telefone_2")
	private String nuTelefone2;

	@Column(name = "co_sexo")
	private String coSexo;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "co_municipio_residencia")
	private PMunicipio pMunicipio;

	@Column(name = "nu_usuario_no_domicilio")
	private String nuUsuarioNoDomicilio;

	@Column(name = "co_cep")
	private String coCep;

	@Column(name = "no_bairro")
	private String noBairro;

	@Column(name = "no_compl_logradouro")
	private String noComplLogradouro;

	@Column(name = "nu_logradouro")
	private String nuLogradouro;

	@Column(name = "no_logradouro")
	private String noLogradouro;

	@Column(name = "co_tipo_logradouro")
	private String coTipoLogradouro;

	@Column(name = "ds_email")
	private String dsEmail;

	@Column(name = "tp_sanguineo")
	private String tpSanguineo;

	@Column(name = "dt_obito")
	private Date dtObito;

	@Column(name = "doc_cpf")
	private String docCpf;

	@Column(name = "doc_dnv")
	private String docDnv;

	@Column(name = "doc_nis")
	private String docNis;

	@Column(name = "doc_rg_numero")
	private String docRgNumero;

	@Column(name = "doc_rg_co_orgao")
	private String docRgCoOrgao;

	@Column(name = "doc_rg_uf")
	private String docRgUf;

	@Column(name = "doc_rg_dt_emissao")
	private Date docRgDtEmissao;

	@Column(name = "certidao_antiga_cartorio")
	private String certidaoAntigaCartorio;

	@Column(name = "certidao_dt_emissao")
	private Date certidaoDtEmissao;

	@Column(name = "certidao_tipo_certidao")
	private String certidaoTipoCertidao;

	@Column(name = "certidao_antiga_livro")
	private String certidaoAntigaLivro;

	@Column(name = "certidao_antiga_folha")
	private String certidaoAntigaFolha;

	@Column(name = "certidao_antiga_termo")
	private String certidaoAntigaTermo;

	@Column(name = "certidao_nova")
	private String certidaoNova;

	@Column(name = "dt_cadastro")
	private Date dtCadastro;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private FrUsuario frUsuario;

	@Column(name = "cod_familia")
	private String codFamilia;

	@Column(name = "cod_area")
	private String codArea;

	@Column(name = "cod_subarea")
	private String codSubarea;

	@Column(name = "doc_prenatal")
	private String docPrenatal;

	@ManyToOne
	@JoinColumn(name = "id_estabelecimento")
	private CnesEstabelecimentos cnesEstabelecimentos;

	@Column(name = "certidao_antiga_num")
	private String certidaoAntigaNum;

	@Column(name = "co_etinia")
	private String coEtinia;

	@Column(name = "foto")
	private String foto;

	@Column(name = "nome_social")
	private String nomeSocial;

	@Column(name = "hiperdia")
	private String hiperdia;

	@Column(name = "obs")
	private String obs;

	@Column(name = "alergia_medicamento")
	private String alergiaMedicamento;

	@Column(name = "motivo_alteracao")
	private String motivoAlteracao;

	@Column(name = "id_usuario_alteracao")
	private Long idUsuarioAlteracao;

	@Column(name = "dt_alteracao")
	private Date dtAlteracao;

	public Boolean getDesconheceMae() {
		return desconheceMae;
	}

	public void setDesconheceMae(Boolean desconheceMae) {
		this.desconheceMae = desconheceMae;
	}

	@Column(name = "desconhece_mae")
	private Boolean desconheceMae;

	public Long getCoProntuario() {
		return coProntuario;
	}

	public void setCoProntuario(Long coProntuario) {
		this.coProntuario = coProntuario;
	}

	public String getCoUsuario() {
		return coUsuario;
	}

	public void setCoUsuario(String coUsuario) {
		this.coUsuario = coUsuario;
	}

	public String getNoUsuario() {
		return noUsuario;
	}

	public void setNoUsuario(String noUsuario) {
		this.noUsuario = noUsuario;
	}

	public String getNoMae() {
		return noMae;
	}

	public void setNoMae(String noMae) {
		this.noMae = noMae;
	}

	public String getNoPai() {
		return noPai;
	}

	public void setNoPai(String noPai) {
		this.noPai = noPai;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getCoEscolaridade() {
		return coEscolaridade;
	}

	public void setCoEscolaridade(String coEscolaridade) {
		this.coEscolaridade = coEscolaridade;
	}

	public String getCoSituacaoFamiliar() {
		return coSituacaoFamiliar;
	}

	public void setCoSituacaoFamiliar(String coSituacaoFamiliar) {
		this.coSituacaoFamiliar = coSituacaoFamiliar;
	}

	public String getCoEstadoCivil() {
		return coEstadoCivil;
	}

	public void setCoEstadoCivil(String coEstadoCivil) {
		this.coEstadoCivil = coEstadoCivil;
	}

	public PRacaCor getPRacaCor() {
		return pRacaCor;
	}

	public void setPRacaCor(PRacaCor pRacaCor) {
		this.pRacaCor = pRacaCor;
	}

	public PNacionalidade getPNacionalidade() {
		return pNacionalidade;
	}

	public void setPNacionalidade(PNacionalidade pNacionalidade) {
		this.pNacionalidade = pNacionalidade;
	}

	public String getCoMunicipioNasc() {
		return coMunicipioNasc;
	}

	public void setCoMunicipioNasc(String coMunicipioNasc) {
		this.coMunicipioNasc = coMunicipioNasc;
	}

	public String getNuDdd1() {
		return nuDdd1;
	}

	public void setNuDdd1(String nuDdd1) {
		this.nuDdd1 = nuDdd1;
	}

	public String getNuTelefone() {
		return nuTelefone;
	}

	public void setNuTelefone(String nuTelefone) {
		this.nuTelefone = nuTelefone;
	}

	public String getNuDdd2() {
		return nuDdd2;
	}

	public void setNuDdd2(String nuDdd2) {
		this.nuDdd2 = nuDdd2;
	}

	public String getNuTelefone2() {
		return nuTelefone2;
	}

	public void setNuTelefone2(String nuTelefone2) {
		this.nuTelefone2 = nuTelefone2;
	}

	public String getCoSexo() {
		return coSexo;
	}

	public void setCoSexo(String coSexo) {
		this.coSexo = coSexo;
	}

	public PMunicipio getPMunicipio() {
		return pMunicipio;
	}

	public void setPMunicipio(PMunicipio pMunicipio) {
		this.pMunicipio = pMunicipio;
	}

	public String getNuUsuarioNoDomicilio() {
		return nuUsuarioNoDomicilio;
	}

	public void setNuUsuarioNoDomicilio(String nuUsuarioNoDomicilio) {
		this.nuUsuarioNoDomicilio = nuUsuarioNoDomicilio;
	}

	public String getCoCep() {
		return coCep;
	}

	public void setCoCep(String coCep) {
		this.coCep = coCep;
	}

	public String getNoBairro() {
		return noBairro;
	}

	public void setNoBairro(String noBairro) {
		this.noBairro = noBairro;
	}

	public String getNoComplLogradouro() {
		return noComplLogradouro;
	}

	public void setNoComplLogradouro(String noComplLogradouro) {
		this.noComplLogradouro = noComplLogradouro;
	}

	public String getNuLogradouro() {
		return nuLogradouro;
	}

	public void setNuLogradouro(String nuLogradouro) {
		this.nuLogradouro = nuLogradouro;
	}

	public String getNoLogradouro() {
		return noLogradouro;
	}

	public void setNoLogradouro(String noLogradouro) {
		this.noLogradouro = noLogradouro;
	}

	public String getCoTipoLogradouro() {
		return coTipoLogradouro;
	}

	public void setCoTipoLogradouro(String coTipoLogradouro) {
		this.coTipoLogradouro = coTipoLogradouro;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getTpSanguineo() {
		return tpSanguineo;
	}

	public void setTpSanguineo(String tpSanguineo) {
		this.tpSanguineo = tpSanguineo;
	}

	public Date getDtObito() {
		return dtObito;
	}

	public void setDtObito(Date dtObito) {
		this.dtObito = dtObito;
	}

	public String getDocCpf() {
		return docCpf;
	}

	public void setDocCpf(String docCpf) {
		this.docCpf = docCpf;
	}

	public String getDocDnv() {
		return docDnv;
	}

	public void setDocDnv(String docDnv) {
		this.docDnv = docDnv;
	}

	public String getDocNis() {
		return docNis;
	}

	public void setDocNis(String docNis) {
		this.docNis = docNis;
	}

	public String getDocRgNumero() {
		return docRgNumero;
	}

	public void setDocRgNumero(String docRgNumero) {
		this.docRgNumero = docRgNumero;
	}

	public String getDocRgCoOrgao() {
		return docRgCoOrgao;
	}

	public void setDocRgCoOrgao(String docRgCoOrgao) {
		this.docRgCoOrgao = docRgCoOrgao;
	}

	public String getDocRgUf() {
		return docRgUf;
	}

	public void setDocRgUf(String docRgUf) {
		this.docRgUf = docRgUf;
	}

	public Date getDocRgDtEmissao() {
		return docRgDtEmissao;
	}

	public void setDocRgDtEmissao(Date docRgDtEmissao) {
		this.docRgDtEmissao = docRgDtEmissao;
	}

	public String getCertidaoAntigaCartorio() {
		return certidaoAntigaCartorio;
	}

	public void setCertidaoAntigaCartorio(String certidaoAntigaCartorio) {
		this.certidaoAntigaCartorio = certidaoAntigaCartorio;
	}

	public Date getCertidaoDtEmissao() {
		return certidaoDtEmissao;
	}

	public void setCertidaoDtEmissao(Date certidaoDtEmissao) {
		this.certidaoDtEmissao = certidaoDtEmissao;
	}

	public String getCertidaoTipoCertidao() {
		return certidaoTipoCertidao;
	}

	public void setCertidaoTipoCertidao(String certidaoTipoCertidao) {
		this.certidaoTipoCertidao = certidaoTipoCertidao;
	}

	public String getCertidaoAntigaLivro() {
		return certidaoAntigaLivro;
	}

	public void setCertidaoAntigaLivro(String certidaoAntigaLivro) {
		this.certidaoAntigaLivro = certidaoAntigaLivro;
	}

	public String getCertidaoAntigaFolha() {
		return certidaoAntigaFolha;
	}

	public void setCertidaoAntigaFolha(String certidaoAntigaFolha) {
		this.certidaoAntigaFolha = certidaoAntigaFolha;
	}

	public String getCertidaoAntigaTermo() {
		return certidaoAntigaTermo;
	}

	public void setCertidaoAntigaTermo(String certidaoAntigaTermo) {
		this.certidaoAntigaTermo = certidaoAntigaTermo;
	}

	public String getCertidaoNova() {
		return certidaoNova;
	}

	public void setCertidaoNova(String certidaoNova) {
		this.certidaoNova = certidaoNova;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public FrUsuario getFrUsuario() {
		return frUsuario;
	}

	public void setFrUsuario(FrUsuario frUsuario) {
		this.frUsuario = frUsuario;
	}

	public String getCodFamilia() {
		return codFamilia;
	}

	public void setCodFamilia(String codFamilia) {
		this.codFamilia = codFamilia;
	}

	public String getCodArea() {
		return codArea;
	}

	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}

	public String getCodSubarea() {
		return codSubarea;
	}

	public void setCodSubarea(String codSubarea) {
		this.codSubarea = codSubarea;
	}

	public String getDocPrenatal() {
		return docPrenatal;
	}

	public void setDocPrenatal(String docPrenatal) {
		this.docPrenatal = docPrenatal;
	}

	public CnesEstabelecimentos getCnesEstabelecimentos() {
		return cnesEstabelecimentos;
	}

	public void setCnesEstabelecimentos(
			CnesEstabelecimentos cnesEstabelecimentos) {
		this.cnesEstabelecimentos = cnesEstabelecimentos;
	}

	public String getCertidaoAntigaNum() {
		return certidaoAntigaNum;
	}

	public void setCertidaoAntigaNum(String certidaoAntigaNum) {
		this.certidaoAntigaNum = certidaoAntigaNum;
	}

	public String getCoEtinia() {
		return coEtinia;
	}

	public void setCoEtinia(String coEtinia) {
		this.coEtinia = coEtinia;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getHiperdia() {
		return hiperdia;
	}

	public void setHiperdia(String hiperdia) {
		this.hiperdia = hiperdia;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getAlergiaMedicamento() {
		return alergiaMedicamento;
	}

	public void setAlergiaMedicamento(String alergiaMedicamento) {
		this.alergiaMedicamento = alergiaMedicamento;
	}

	public String getMotivoAlteracao() {
		return motivoAlteracao;
	}

	public void setMotivoAlteracao(String motivoAlteracao) {
		this.motivoAlteracao = motivoAlteracao;
	}

	public Long getIdUsuarioAlteracao() {
		return idUsuarioAlteracao;
	}

	public void setIdUsuarioAlteracao(Long idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
	
	public boolean isDesconheceMae() {
		if (getDesconheceMae() == null) {
			return false;
		}
		return getDesconheceMae();
	}

}