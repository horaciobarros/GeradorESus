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

@Entity 
@Table(name="esus_cadastro_domiciliar")
public class EsusCadastroDomiciliar implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="cnes_unidade")
   private String cnesUnidade;

   @Column(name="cns_profissional")
   private String cnsProfissional;

   @Column(name="co_municipio")
   private String coMunicipio;

   @Column(name="dt_cadastro")
   private Date dtCadastro;

   @Column(name="ine_equipe")
   private String ineEquipe;

   @Column(name="microarea")
   private Long microarea;

   @Column(name="tp_logradouro")
   private Long tpLogradouro;

   @Column(name="no_logradouro")
   private String noLogradouro;

   @Column(name="nu_domicilio")
   private String nuDomicilio;

   @Column(name="ds_complemento")
   private String dsComplemento;

   @Column(name="no_bairro")
   private String noBairro;

   @Column(name="nu_cep")
   private String nuCep;

   @Column(name="nu_fone_residencia")
   private String nuFoneResidencia;

   @Column(name="nu_fone_referencia")
   private String nuFoneReferencia;

   @Column(name="ds_rg_recusa_cad")
   private String dsRgRecusaCad;

   @Column(name="st_envio")
   private Long stEnvio;

   @Column(name="co_uf")
   private Long coUf;

   @Column(name="co_cds_renda_familiar")
   private Long coCdsRendaFamiliar;

   @Column(name="dt_mudanca")
   private Date dtMudanca;

   @Column(name="qt_membros_familia")
   private Long qtMembrosFamilia;

   @ManyToOne
   @JoinColumn(name = "id_situacaomoradia")
   private EsusSituacaodemoradia esusSituacaodemoradia;

   @ManyToOne
   @JoinColumn(name = "id_tipodedomicilio")
   private EsusTipodedomicilio esusTipodedomicilio;

   @ManyToOne
   @JoinColumn(name = "id_localizacaodamoradia")
   private EsusLocalizacaodamoradia esusLocalizacaodamoradia;

   @ManyToOne
   @JoinColumn(name = "id_tipodeacessoaodomicilio")
   private EsusTipodeacessoaodomicilio esusTipodeacessoaodomicilio;

   @Column(name="energia_eletrica")
   private boolean energiaEletrica;

   @ManyToOne
   @JoinColumn(name = "id_materialpredominanteconst")
   private EsusMaterialpredominantenaconstrucao esusMaterialpredominantenaconstrucao;

   @ManyToOne
   @JoinColumn(name = "id_condicaodeposseeusodaterra")
   private EsusCondicaodeposseeusodaterra esusCondicaodeposseeusodaterra;

   @ManyToOne
   @JoinColumn(name = "id_abastecimentodeagua")
   private EsusAbastecimentodeagua esusAbastecimentodeagua;

   @ManyToOne
   @JoinColumn(name = "id_tratamentodeaguanodomicilio")
   private EsusTratamentodeaguanodomicilio esusTratamentodeaguanodomicilio;

   @ManyToOne
   @JoinColumn(name = "id_formadeescoamentodobanheiro")
   private EsusFormadeescoamentodobanheiroousanitario esusFormadeescoamentodobanheiroousanitario;

   @ManyToOne
   @JoinColumn(name = "id_destinodolixo")
   private EsusDestinodolixo esusDestinodolixo;

   @Column(name="quantidade_moradores")
   private Long quantidadeMoradores;

   @Column(name="numero_comodos")
   private Long numeroComodos;

   @Column(name="quantidade_animais")
   private Long quantidadeAnimais;

   @Column(name="st_recusa_cadastro")
   private boolean stRecusaCadastro;

   @Column(name="id_prontuario_responsavel")
   private Long idProntuarioResponsavel;

   @Column(name="dt_envio")
   private Date dtEnvio;

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

   public String getCoMunicipio() { 
      return coMunicipio;
   }
   public void  setCoMunicipio(String coMunicipio) { 
      this.coMunicipio = coMunicipio;
   }

   public Date getDtCadastro() { 
      return dtCadastro;
   }
   public void  setDtCadastro(Date dtCadastro) { 
      this.dtCadastro = dtCadastro;
   }

   public String getIneEquipe() { 
      return ineEquipe;
   }
   public void  setIneEquipe(String ineEquipe) { 
      this.ineEquipe = ineEquipe;
   }

   public Long getMicroarea() { 
      return microarea;
   }
   public void  setMicroarea(Long microarea) { 
      this.microarea = microarea;
   }

   public Long getTpLogradouro() { 
      return tpLogradouro;
   }
   public void  setTpLogradouro(Long tpLogradouro) { 
      this.tpLogradouro = tpLogradouro;
   }

   public String getNoLogradouro() { 
      return noLogradouro;
   }
   public void  setNoLogradouro(String noLogradouro) { 
      this.noLogradouro = noLogradouro;
   }

   public String getNuDomicilio() { 
      return nuDomicilio;
   }
   public void  setNuDomicilio(String nuDomicilio) { 
      this.nuDomicilio = nuDomicilio;
   }

   public String getDsComplemento() { 
      return dsComplemento;
   }
   public void  setDsComplemento(String dsComplemento) { 
      this.dsComplemento = dsComplemento;
   }

   public String getNoBairro() { 
      return noBairro;
   }
   public void  setNoBairro(String noBairro) { 
      this.noBairro = noBairro;
   }

   public String getNuCep() { 
      return nuCep;
   }
   public void  setNuCep(String nuCep) { 
      this.nuCep = nuCep;
   }

   public String getNuFoneResidencia() { 
      return nuFoneResidencia;
   }
   public void  setNuFoneResidencia(String nuFoneResidencia) { 
      this.nuFoneResidencia = nuFoneResidencia;
   }

   public String getNuFoneReferencia() { 
      return nuFoneReferencia;
   }
   public void  setNuFoneReferencia(String nuFoneReferencia) { 
      this.nuFoneReferencia = nuFoneReferencia;
   }

   public String getDsRgRecusaCad() { 
      return dsRgRecusaCad;
   }
   public void  setDsRgRecusaCad(String dsRgRecusaCad) { 
      this.dsRgRecusaCad = dsRgRecusaCad;
   }

   public Long getStEnvio() { 
      return stEnvio;
   }
   public void  setStEnvio(Long stEnvio) { 
      this.stEnvio = stEnvio;
   }

   public Long getCoUf() { 
      return coUf;
   }
   public void  setCoUf(Long coUf) { 
      this.coUf = coUf;
   }

   public Long getCoCdsRendaFamiliar() { 
      return coCdsRendaFamiliar;
   }
   public void  setCoCdsRendaFamiliar(Long coCdsRendaFamiliar) { 
      this.coCdsRendaFamiliar = coCdsRendaFamiliar;
   }

   public Date getDtMudanca() { 
      return dtMudanca;
   }
   public void  setDtMudanca(Date dtMudanca) { 
      this.dtMudanca = dtMudanca;
   }

   public Long getQtMembrosFamilia() { 
      return qtMembrosFamilia;
   }
   public void  setQtMembrosFamilia(Long qtMembrosFamilia) { 
      this.qtMembrosFamilia = qtMembrosFamilia;
   }


   public  EsusSituacaodemoradia  getEsusSituacaodemoradia() { 
      return esusSituacaodemoradia;
   }
   public void  setEsusSituacaodemoradia(EsusSituacaodemoradia esusSituacaodemoradia) { 
      this.esusSituacaodemoradia = esusSituacaodemoradia;
   }


   public  EsusTipodedomicilio  getEsusTipodedomicilio() { 
      return esusTipodedomicilio;
   }
   public void  setEsusTipodedomicilio(EsusTipodedomicilio esusTipodedomicilio) { 
      this.esusTipodedomicilio = esusTipodedomicilio;
   }


   public  EsusLocalizacaodamoradia  getEsusLocalizacaodamoradia() { 
      return esusLocalizacaodamoradia;
   }
   public void  setEsusLocalizacaodamoradia(EsusLocalizacaodamoradia esusLocalizacaodamoradia) { 
      this.esusLocalizacaodamoradia = esusLocalizacaodamoradia;
   }


   public  EsusTipodeacessoaodomicilio  getEsusTipodeacessoaodomicilio() { 
      return esusTipodeacessoaodomicilio;
   }
   public void  setEsusTipodeacessoaodomicilio(EsusTipodeacessoaodomicilio esusTipodeacessoaodomicilio) { 
      this.esusTipodeacessoaodomicilio = esusTipodeacessoaodomicilio;
   }

   public boolean getEnergiaEletrica() { 
      return energiaEletrica;
   }
   public void  setEnergiaEletrica(boolean energiaEletrica) { 
      this.energiaEletrica = energiaEletrica;
   }


   public  EsusMaterialpredominantenaconstrucao  getEsusMaterialpredominantenaconstrucao() { 
      return esusMaterialpredominantenaconstrucao;
   }
   public void  setEsusMaterialpredominantenaconstrucao(EsusMaterialpredominantenaconstrucao esusMaterialpredominantenaconstrucao) { 
      this.esusMaterialpredominantenaconstrucao = esusMaterialpredominantenaconstrucao;
   }


   public  EsusCondicaodeposseeusodaterra  getEsusCondicaodeposseeusodaterra() { 
      return esusCondicaodeposseeusodaterra;
   }
   public void  setEsusCondicaodeposseeusodaterra(EsusCondicaodeposseeusodaterra esusCondicaodeposseeusodaterra) { 
      this.esusCondicaodeposseeusodaterra = esusCondicaodeposseeusodaterra;
   }


   public  EsusAbastecimentodeagua  getEsusAbastecimentodeagua() { 
      return esusAbastecimentodeagua;
   }
   public void  setEsusAbastecimentodeagua(EsusAbastecimentodeagua esusAbastecimentodeagua) { 
      this.esusAbastecimentodeagua = esusAbastecimentodeagua;
   }


   public  EsusTratamentodeaguanodomicilio  getEsusTratamentodeaguanodomicilio() { 
      return esusTratamentodeaguanodomicilio;
   }
   public void  setEsusTratamentodeaguanodomicilio(EsusTratamentodeaguanodomicilio esusTratamentodeaguanodomicilio) { 
      this.esusTratamentodeaguanodomicilio = esusTratamentodeaguanodomicilio;
   }


   public  EsusFormadeescoamentodobanheiroousanitario  getEsusFormadeescoamentodobanheiroousanitario() { 
      return esusFormadeescoamentodobanheiroousanitario;
   }
   public void  setEsusFormadeescoamentodobanheiroousanitario(EsusFormadeescoamentodobanheiroousanitario esusFormadeescoamentodobanheiroousanitario) { 
      this.esusFormadeescoamentodobanheiroousanitario = esusFormadeescoamentodobanheiroousanitario;
   }


   public  EsusDestinodolixo  getEsusDestinodolixo() { 
      return esusDestinodolixo;
   }
   public void  setEsusDestinodolixo(EsusDestinodolixo esusDestinodolixo) { 
      this.esusDestinodolixo = esusDestinodolixo;
   }

   public Long getQuantidadeMoradores() { 
      return quantidadeMoradores;
   }
   public void  setQuantidadeMoradores(Long quantidadeMoradores) { 
      this.quantidadeMoradores = quantidadeMoradores;
   }

   public Long getNumeroComodos() { 
      return numeroComodos;
   }
   public void  setNumeroComodos(Long numeroComodos) { 
      this.numeroComodos = numeroComodos;
   }

   public Long getQuantidadeAnimais() { 
      return quantidadeAnimais;
   }
   public void  setQuantidadeAnimais(Long quantidadeAnimais) { 
      this.quantidadeAnimais = quantidadeAnimais;
   }

   public boolean getStRecusaCadastro() { 
      return stRecusaCadastro;
   }
   public void  setStRecusaCadastro(boolean stRecusaCadastro) { 
      this.stRecusaCadastro = stRecusaCadastro;
   }

   public Long getIdProntuarioResponsavel() { 
      return idProntuarioResponsavel;
   }
   public void  setIdProntuarioResponsavel(Long idProntuarioResponsavel) { 
      this.idProntuarioResponsavel = idProntuarioResponsavel;
   }

   public Date getDtEnvio() { 
      return dtEnvio;
   }
   public void  setDtEnvio(Date dtEnvio) { 
      this.dtEnvio = dtEnvio;
   }

  
  }