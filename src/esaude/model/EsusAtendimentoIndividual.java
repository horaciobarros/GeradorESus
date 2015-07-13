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
import java.math.BigDecimal;
import java.util.*;

@Entity 
@Table(name="esus_atendimento_individual")
public class EsusAtendimentoIndividual implements Serializable {

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

   @Column(name="cns_profissional02")
   private String cnsProfissional02;

   @Column(name="cbo_profissional02")
   private String cboProfissional02;

   @ManyToOne
   @JoinColumn(name = "id_prontuario")
   private PProntuario pProntuario;

   @ManyToOne
   @JoinColumn(name = "id_localdeatendimento")
   private EsusLocaldeatendimento esusLocaldeatendimento;

   @ManyToOne
   @JoinColumn(name = "id_tipodeatendimento")
   private EsusTipodeatendimento esusTipodeatendimento;

   @ManyToOne
   @JoinColumn(name = "id_turno")
   private EsusTurno esusTurno;

   @Column(name="pesoacompanhemento")
   private BigDecimal pesoacompanhemento;

   @Column(name="alturaacompanhamento")
   private BigDecimal alturaacompanhamento;

   @ManyToOne
   @JoinColumn(name = "id_aleitamentomaterno")
   private EsusAleitamentomaterno esusAleitamentomaterno;

   @Column(name="idadegestacional")
   private Long idadegestacional;

   @Column(name="atencaodomicmodalidade")
   private Long atencaodomicmodalidade;

   @ManyToOne
   @JoinColumn(name = "id_praticasintegrativascomp")
   private EsusPraticasintegrativascomplementares esusPraticasintegrativascomplementares;

   @Column(name="ficou_observacao")
   private boolean ficouObservacao;

   @Column(name="gravidezplanejada")
   private boolean gravidezplanejada;

   @Column(name="nugestasprevias")
   private Long nugestasprevias;

   @Column(name="nupartos")
   private Long nupartos;

   @Column(name="dt_atendimento")
   private Date dtAtendimento;

   @Column(name="vacinaemdia")
   private boolean vacinaemdia;

   @Column(name="dumdagestante")
   private Date dumdagestante;

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

   public String getCnsProfissional02() { 
      return cnsProfissional02;
   }
   public void  setCnsProfissional02(String cnsProfissional02) { 
      this.cnsProfissional02 = cnsProfissional02;
   }

   public String getCboProfissional02() { 
      return cboProfissional02;
   }
   public void  setCboProfissional02(String cboProfissional02) { 
      this.cboProfissional02 = cboProfissional02;
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


   public  EsusTipodeatendimento  getEsusTipodeatendimento() { 
      return esusTipodeatendimento;
   }
   public void  setEsusTipodeatendimento(EsusTipodeatendimento esusTipodeatendimento) { 
      this.esusTipodeatendimento = esusTipodeatendimento;
   }


   public  EsusTurno  getEsusTurno() { 
      return esusTurno;
   }
   public void  setEsusTurno(EsusTurno esusTurno) { 
      this.esusTurno = esusTurno;
   }

   public BigDecimal getPesoacompanhemento() { 
      return pesoacompanhemento;
   }
   public void  setPesoacompanhemento(BigDecimal pesoacompanhemento) { 
      this.pesoacompanhemento = pesoacompanhemento;
   }

   public BigDecimal getAlturaacompanhamento() { 
      return alturaacompanhamento;
   }
   public void  setAlturaacompanhamento(BigDecimal alturaacompanhamento) { 
      this.alturaacompanhamento = alturaacompanhamento;
   }


   public  EsusAleitamentomaterno  getEsusAleitamentomaterno() { 
      return esusAleitamentomaterno;
   }
   public void  setEsusAleitamentomaterno(EsusAleitamentomaterno esusAleitamentomaterno) { 
      this.esusAleitamentomaterno = esusAleitamentomaterno;
   }

   public Long getIdadegestacional() { 
      return idadegestacional;
   }
   public void  setIdadegestacional(Long idadegestacional) { 
      this.idadegestacional = idadegestacional;
   }

   public Long getAtencaodomicmodalidade() { 
      return atencaodomicmodalidade;
   }
   public void  setAtencaodomicmodalidade(Long atencaodomicmodalidade) { 
      this.atencaodomicmodalidade = atencaodomicmodalidade;
   }

   public  EsusPraticasintegrativascomplementares  getEsusPraticasintegrativascomplementares() { 
      return esusPraticasintegrativascomplementares;
   }
   public void  setEsusPraticasintegrativascomplementares(EsusPraticasintegrativascomplementares esusPraticasintegrativascomplementares) { 
      this.esusPraticasintegrativascomplementares = esusPraticasintegrativascomplementares;
   }

   public boolean getFicouObservacao() { 
      return ficouObservacao;
   }
   public void  setFicouObservacao(boolean ficouObservacao) { 
      this.ficouObservacao = ficouObservacao;
   }

   public boolean getGravidezplanejada() { 
      return gravidezplanejada;
   }
   public void  setGravidezplanejada(boolean gravidezplanejada) { 
      this.gravidezplanejada = gravidezplanejada;
   }

   public Long getNugestasprevias() { 
      return nugestasprevias;
   }
   public void  setNugestasprevias(Long nugestasprevias) { 
      this.nugestasprevias = nugestasprevias;
   }

   public Long getNupartos() { 
      return nupartos;
   }
   public void  setNupartos(Long nupartos) { 
      this.nupartos = nupartos;
   }

   public Date getDtAtendimento() { 
      return dtAtendimento;
   }
   public void  setDtAtendimento(Date dtAtendimento) { 
      this.dtAtendimento = dtAtendimento;
   }

   public boolean getVacinaemdia() { 
      return vacinaemdia;
   }
   public void  setVacinaemdia(boolean vacinaemdia) { 
      this.vacinaemdia = vacinaemdia;
   }

   public Date getDumdagestante() { 
      return dumdagestante;
   }
   public void  setDumdagestante(Date dumdagestante) { 
      this.dumdagestante = dumdagestante;
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