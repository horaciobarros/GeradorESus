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
@Table(name="esus_atendimento_odontologico")
public class EsusAtendimentoOdontologico implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_prontuario")
   private PProntuario pProntuario;

   @Column(name="gestante")
   private boolean gestante;

   @Column(name="necessidadesespeciais")
   private boolean necessidadesespeciais;

   @ManyToOne
   @JoinColumn(name = "id_localdeatendimento")
   private EsusLocaldeatendimento esusLocaldeatendimento;

   @ManyToOne
   @JoinColumn(name = "id_tipodeatendimento")
   private EsusTipodeatendimento esusTipodeatendimento;

   @ManyToOne
   @JoinColumn(name = "id_sexo")
   private EsusSexo esusSexo;

   @ManyToOne
   @JoinColumn(name = "id_turno")
   private EsusTurno esusTurno;

   @Column(name="cnes_unidade")
   private String cnesUnidade;

   @Column(name="cns_profissional")
   private String cnsProfissional;

   @Column(name="ine_equipe")
   private String ineEquipe;

   @Column(name="cbo_profissional")
   private String cboProfissional;

   @Column(name="dt_atendimento")
   private Date dtAtendimento;

   @Column(name="cns_profissional02")
   private String cnsProfissional02;

   @Column(name="cbo_profissional02")
   private String cboProfissional02;

   @ManyToOne
   @JoinColumn(name = "id_tipoconsulta")
   private EsusTipodeconsultaodonto esusTipodeconsultaodonto;

   @Column(name="descricao")
   private String descricao;

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

   public boolean getGestante() { 
      return gestante;
   }
   public void  setGestante(boolean gestante) { 
      this.gestante = gestante;
   }

   public boolean getNecessidadesespeciais() { 
      return necessidadesespeciais;
   }
   public void  setNecessidadesespeciais(boolean necessidadesespeciais) { 
      this.necessidadesespeciais = necessidadesespeciais;
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


   public  EsusSexo  getEsusSexo() { 
      return esusSexo;
   }
   public void  setEsusSexo(EsusSexo esusSexo) { 
      this.esusSexo = esusSexo;
   }


   public  EsusTurno  getEsusTurno() { 
      return esusTurno;
   }
   public void  setEsusTurno(EsusTurno esusTurno) { 
      this.esusTurno = esusTurno;
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

   public String getIneEquipe() { 
      return ineEquipe;
   }
   public void  setIneEquipe(String ineEquipe) { 
      this.ineEquipe = ineEquipe;
   }

   public String getCboProfissional() { 
      return cboProfissional;
   }
   public void  setCboProfissional(String cboProfissional) { 
      this.cboProfissional = cboProfissional;
   }

   public Date getDtAtendimento() { 
      return dtAtendimento;
   }
   public void  setDtAtendimento(Date dtAtendimento) { 
      this.dtAtendimento = dtAtendimento;
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


   public  EsusTipodeconsultaodonto  getEsusTipodeconsultaodonto() { 
      return esusTipodeconsultaodonto;
   }
   public void  setEsusTipodeconsultaodonto(EsusTipodeconsultaodonto esusTipodeconsultaodonto) { 
      this.esusTipodeconsultaodonto = esusTipodeconsultaodonto;
   }

   public String getDescricao() { 
      return descricao;
   }
   public void  setDescricao(String descricao) { 
      this.descricao = descricao;
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