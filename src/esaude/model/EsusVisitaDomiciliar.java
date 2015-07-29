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
@Table(name="esus_visita_domiciliar")
public class EsusVisitaDomiciliar implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="cns_profissional")
   private String cnsProfissional;

   @Column(name="cbo_profissional")
   private String cboProfissional;

   @Column(name="cnes_unidade")
   private String cnesUnidade;

   @Column(name="ine_equipe")
   private String ineEquipe;

   @ManyToOne
   @JoinColumn(name = "id_turno")
   private EsusTurno esusTurno;

   @Column(name="num_cartaosus")
   private String numCartaosus;

   @Column(name="dt_nascimento")
   private Date dtNascimento;

   @ManyToOne
   @JoinColumn(name = "id_sexo")
   private EsusSexo esusSexo;

   @Column(name="st_visitacompartilhadaoutroprof")
   private boolean stVisitacompartilhadaoutroprof;

   @ManyToOne
   @JoinColumn(name = "id_desfecho")
   private EsusDesfecho esusDesfecho;

   @Column(name="dt_atendimento")
   private Date dtAtendimento;

   @ManyToOne
   @JoinColumn(name = "id_prontuario")
   private PProntuario pProntuario;

   @Column(name="st_envio")
   private Long stEnvio;

   @Column(name="dt_envio")
   private Date dtEnvio;
   
   @Column(name="uuid")
   private String uuid;


   public String getUuid() {
	return uuid;
}
public void setUuid(String uuid) {
	this.uuid = uuid;
}
public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
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


   public  EsusTurno  getEsusTurno() { 
      return esusTurno;
   }
   public void  setEsusTurno(EsusTurno esusTurno) { 
      this.esusTurno = esusTurno;
   }

   public String getNumCartaosus() { 
      return numCartaosus;
   }
   public void  setNumCartaosus(String numCartaosus) { 
      this.numCartaosus = numCartaosus;
   }

   public Date getDtNascimento() { 
      return dtNascimento;
   }
   public void  setDtNascimento(Date dtNascimento) { 
      this.dtNascimento = dtNascimento;
   }


   public  EsusSexo  getEsusSexo() { 
      return esusSexo;
   }
   public void  setEsusSexo(EsusSexo esusSexo) { 
      this.esusSexo = esusSexo;
   }

   public boolean getStVisitacompartilhadaoutroprof() { 
      return stVisitacompartilhadaoutroprof;
   }
   public void  setStVisitacompartilhadaoutroprof(boolean stVisitacompartilhadaoutroprof) { 
      this.stVisitacompartilhadaoutroprof = stVisitacompartilhadaoutroprof;
   }


   public  EsusDesfecho  getEsusDesfecho() { 
      return esusDesfecho;
   }
   public void  setEsusDesfecho(EsusDesfecho esusDesfecho) { 
      this.esusDesfecho = esusDesfecho;
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