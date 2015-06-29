package esaude.model; 

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.*;
import java.util.*;

@Entity 
@Table(name="esus_cadastro_individual")
public class EsusCadastroIndividual implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_prontuario")
   private PProntuario pProntuario;

   @Column(name="cns_profissional")
   private String cnsProfissional;

   @Column(name="cnes_unidade")
   private String cnesUnidade;

   @Column(name="ine_equipe")
   private String ineEquipe;

   @Column(name="data_atendimento")
   private Date dataAtendimento;

   @Column(name="microarea")
   private int microarea;

   @Column(name="frequenta_escola")
   private boolean frequentaEscola;

   @Column(name="tem_alguma_deficiencia")
   private boolean temAlgumaDeficiencia;

   @Column(name="em_situacao_rua")
   private boolean emSituacaoRua;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "tempo_situacao_rua")
   private EsusTemposituacaoderua esusTemposituacaoderua;

   @Column(name="recebe_beneficio")
   private boolean recebeBeneficio;

   @Column(name="possui_referencia_familiar")
   private boolean possuiReferenciaFamiliar;

   @Column(name="acompanhado_outra_inst")
   private boolean acompanhadoOutraInst;

   @Column(name="outra_inst_que_acompanha")
   private String outraInstQueAcompanha;

   @Column(name="visita_familiar_frequentemente")
   private boolean visitaFamiliarFrequentemente;

   @Column(name="grau_parentesco_familiar_freq")
   private String grauParentescoFamiliarFreq;

   @Column(name="esta_fumante")
   private boolean estaFumante;

   @Column(name="dependente_alcool")
   private boolean dependenteAlcool;

   @Column(name="dependente_droga")
   private boolean dependenteDroga;

   @Column(name="hipertenso")
   private boolean hipertenso;

   @Column(name="diabete")
   private boolean diabete;

   @Column(name="avc_derrame")
   private boolean avcDerrame;

   @Column(name="infarto")
   private boolean infarto;

   @Column(name="hanseniase")
   private boolean hanseniase;

   @Column(name="tuberculose")
   private boolean tuberculose;

   @Column(name="cancer")
   private boolean cancer;

   @Column(name="st_recusa_cadastro")
   private boolean stRecusaCadastro;

   @Column(name="esta_gestante")
   private boolean estaGestante;

   @Column(name="st_envio")
   private Long stEnvio;

   @Column(name="dt_envio")
   private Date dtEnvio;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }


   public  PProntuario  getPProntuario() { 
      return pProntuario;
   }
   public void  setPProntuario(PProntuario pProntuario) { 
      this.pProntuario = pProntuario;
   }

   public String getCnsProfissional() { 
      return cnsProfissional;
   }
   public void  setCnsProfissional(String cnsProfissional) { 
      this.cnsProfissional = cnsProfissional;
   }

   public String getCnesUnidade() { 
      return cnesUnidade;
   }
   public void  setCnesUnidade(String cnesUnidade) { 
      this.cnesUnidade = cnesUnidade;
   }

   public String getIneEquipe() { 
      return ineEquipe;
   }
   public void  setIneEquipe(String ineEquipe) { 
      this.ineEquipe = ineEquipe;
   }

   public Date getDataAtendimento() { 
      return dataAtendimento;
   }
   public void  setDataAtendimento(Date dataAtendimento) { 
      this.dataAtendimento = dataAtendimento;
   }

   public int getMicroarea() { 
      return microarea;
   }
   public void  setMicroarea(int microarea) { 
      this.microarea = microarea;
   }

   public boolean getFrequentaEscola() { 
      return frequentaEscola;
   }
   public void  setFrequentaEscola(boolean frequentaEscola) { 
      this.frequentaEscola = frequentaEscola;
   }

   public boolean getTemAlgumaDeficiencia() { 
      return temAlgumaDeficiencia;
   }
   public void  setTemAlgumaDeficiencia(boolean temAlgumaDeficiencia) { 
      this.temAlgumaDeficiencia = temAlgumaDeficiencia;
   }

   public boolean getEmSituacaoRua() { 
      return emSituacaoRua;
   }
   public void  setEmSituacaoRua(boolean emSituacaoRua) { 
      this.emSituacaoRua = emSituacaoRua;
   }


   public  EsusTemposituacaoderua  getEsusTemposituacaoderua() { 
      return esusTemposituacaoderua;
   }
   public void  setEsusTemposituacaoderua(EsusTemposituacaoderua esusTemposituacaoderua) { 
      this.esusTemposituacaoderua = esusTemposituacaoderua;
   }

   public boolean getRecebeBeneficio() { 
      return recebeBeneficio;
   }
   public void  setRecebeBeneficio(boolean recebeBeneficio) { 
      this.recebeBeneficio = recebeBeneficio;
   }

   public boolean getPossuiReferenciaFamiliar() { 
      return possuiReferenciaFamiliar;
   }
   public void  setPossuiReferenciaFamiliar(boolean possuiReferenciaFamiliar) { 
      this.possuiReferenciaFamiliar = possuiReferenciaFamiliar;
   }

   public boolean getAcompanhadoOutraInst() { 
      return acompanhadoOutraInst;
   }
   public void  setAcompanhadoOutraInst(boolean acompanhadoOutraInst) { 
      this.acompanhadoOutraInst = acompanhadoOutraInst;
   }

   public String getOutraInstQueAcompanha() { 
      return outraInstQueAcompanha;
   }
   public void  setOutraInstQueAcompanha(String outraInstQueAcompanha) { 
      this.outraInstQueAcompanha = outraInstQueAcompanha;
   }

   public boolean getVisitaFamiliarFrequentemente() { 
      return visitaFamiliarFrequentemente;
   }
   public void  setVisitaFamiliarFrequentemente(boolean visitaFamiliarFrequentemente) { 
      this.visitaFamiliarFrequentemente = visitaFamiliarFrequentemente;
   }

   public String getGrauParentescoFamiliarFreq() { 
      return grauParentescoFamiliarFreq;
   }
   public void  setGrauParentescoFamiliarFreq(String grauParentescoFamiliarFreq) { 
      this.grauParentescoFamiliarFreq = grauParentescoFamiliarFreq;
   }

   public boolean getEstaFumante() { 
      return estaFumante;
   }
   public void  setEstaFumante(boolean estaFumante) { 
      this.estaFumante = estaFumante;
   }

   public boolean getDependenteAlcool() { 
      return dependenteAlcool;
   }
   public void  setDependenteAlcool(boolean dependenteAlcool) { 
      this.dependenteAlcool = dependenteAlcool;
   }

   public boolean getDependenteDroga() { 
      return dependenteDroga;
   }
   public void  setDependenteDroga(boolean dependenteDroga) { 
      this.dependenteDroga = dependenteDroga;
   }

   public boolean getHipertenso() { 
      return hipertenso;
   }
   public void  setHipertenso(boolean hipertenso) { 
      this.hipertenso = hipertenso;
   }

   public boolean getDiabete() { 
      return diabete;
   }
   public void  setDiabete(boolean diabete) { 
      this.diabete = diabete;
   }

   public boolean getAvcDerrame() { 
      return avcDerrame;
   }
   public void  setAvcDerrame(boolean avcDerrame) { 
      this.avcDerrame = avcDerrame;
   }

   public boolean getInfarto() { 
      return infarto;
   }
   public void  setInfarto(boolean infarto) { 
      this.infarto = infarto;
   }

   public boolean getHanseniase() { 
      return hanseniase;
   }
   public void  setHanseniase(boolean hanseniase) { 
      this.hanseniase = hanseniase;
   }

   public boolean getTuberculose() { 
      return tuberculose;
   }
   public void  setTuberculose(boolean tuberculose) { 
      this.tuberculose = tuberculose;
   }

   public boolean getCancer() { 
      return cancer;
   }
   public void  setCancer(boolean cancer) { 
      this.cancer = cancer;
   }

   public boolean getStRecusaCadastro() { 
      return stRecusaCadastro;
   }
   public void  setStRecusaCadastro(boolean stRecusaCadastro) { 
      this.stRecusaCadastro = stRecusaCadastro;
   }

   public boolean getEstaGestante() { 
      return estaGestante;
   }
   public void  setEstaGestante(boolean estaGestante) { 
      this.estaGestante = estaGestante;
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

}