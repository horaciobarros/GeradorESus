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
@Table(name="esus_ficha_procedimento")
public class EsusFichaProcedimento implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="cns_profissional")
   private String cnsProfissional;

   @Column(name="co_ocupacao")
   private String coOcupacao;

   @Column(name="cnes_unidade")
   private String cnesUnidade;

   @Column(name="ine_equipe")
   private String ineEquipe;

   @Column(name="dt_atendimento")
   private Date dtAtendimento;

   @Column(name="totalafericaopa")
   private Long totalafericaopa;

   @Column(name="totalglicemiacapilar")
   private Long totalglicemiacapilar;

   @Column(name="totalafericaotemperatura")
   private Long totalafericaotemperatura;

   @Column(name="totalmedicaoaltura")
   private Long totalmedicaoaltura;

   @Column(name="totalcurativosimples")
   private Long totalcurativosimples;

   @Column(name="totalmedicaopeso")
   private Long totalmedicaopeso;

   @Column(name="totalcoletamaterial")
   private Long totalcoletamaterial;

   @Column(name="st_envio")
   private Long stEnvio;

   @Column(name="dt_envio")
   private Date dtEnvio;

   @Column(name="uuid")
   private String uuid;

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

   public String getCoOcupacao() { 
      return coOcupacao;
   }
   public void  setCoOcupacao(String coOcupacao) { 
      this.coOcupacao = coOcupacao;
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

   public Date getDtAtendimento() { 
      return dtAtendimento;
   }
   public void  setDtAtendimento(Date dtAtendimento) { 
      this.dtAtendimento = dtAtendimento;
   }

   public Long getTotalafericaopa() { 
      return totalafericaopa;
   }
   public void  setTotalafericaopa(Long totalafericaopa) { 
      this.totalafericaopa = totalafericaopa;
   }

   public Long getTotalglicemiacapilar() { 
      return totalglicemiacapilar;
   }
   public void  setTotalglicemiacapilar(Long totalglicemiacapilar) { 
      this.totalglicemiacapilar = totalglicemiacapilar;
   }

   public Long getTotalafericaotemperatura() { 
      return totalafericaotemperatura;
   }
   public void  setTotalafericaotemperatura(Long totalafericaotemperatura) { 
      this.totalafericaotemperatura = totalafericaotemperatura;
   }

   public Long getTotalmedicaoaltura() { 
      return totalmedicaoaltura;
   }
   public void  setTotalmedicaoaltura(Long totalmedicaoaltura) { 
      this.totalmedicaoaltura = totalmedicaoaltura;
   }

   public Long getTotalcurativosimples() { 
      return totalcurativosimples;
   }
   public void  setTotalcurativosimples(Long totalcurativosimples) { 
      this.totalcurativosimples = totalcurativosimples;
   }

   public Long getTotalmedicaopeso() { 
      return totalmedicaopeso;
   }
   public void  setTotalmedicaopeso(Long totalmedicaopeso) { 
      this.totalmedicaopeso = totalmedicaopeso;
   }

   public Long getTotalcoletamaterial() { 
      return totalcoletamaterial;
   }
   public void  setTotalcoletamaterial(Long totalcoletamaterial) { 
      this.totalcoletamaterial = totalcoletamaterial;
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

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((cnsProfissional == null) ? 0 : cnsProfissional.hashCode());
      result = prime * result + ((coOcupacao == null) ? 0 : coOcupacao.hashCode());
      result = prime * result + ((cnesUnidade == null) ? 0 : cnesUnidade.hashCode());
      result = prime * result + ((ineEquipe == null) ? 0 : ineEquipe.hashCode());
      result = prime * result + ((dtAtendimento == null) ? 0 : dtAtendimento.hashCode());
      result = prime * result + ((totalafericaopa == null) ? 0 : totalafericaopa.hashCode());
      result = prime * result + ((totalglicemiacapilar == null) ? 0 : totalglicemiacapilar.hashCode());
      result = prime * result + ((totalafericaotemperatura == null) ? 0 : totalafericaotemperatura.hashCode());
      result = prime * result + ((totalmedicaoaltura == null) ? 0 : totalmedicaoaltura.hashCode());
      result = prime * result + ((totalcurativosimples == null) ? 0 : totalcurativosimples.hashCode());
      result = prime * result + ((totalmedicaopeso == null) ? 0 : totalmedicaopeso.hashCode());
      result = prime * result + ((totalcoletamaterial == null) ? 0 : totalcoletamaterial.hashCode());
      result = prime * result + ((stEnvio == null) ? 0 : stEnvio.hashCode());
      result = prime * result + ((dtEnvio == null) ? 0 : dtEnvio.hashCode());
      result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
      return result;
   }

   @Override 
   public boolean equals(Object obj) {
      if (this == obj)
      return true;
      if (obj == null)
      return true;
      if (getClass() != obj.getClass())
      return false;
      EsusFichaProcedimento other = (EsusFichaProcedimento) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (cnsProfissional == null) {
          if (other.cnsProfissional != null) return false;
       }
      if (coOcupacao == null) {
          if (other.coOcupacao != null) return false;
       }
      if (cnesUnidade == null) {
          if (other.cnesUnidade != null) return false;
       }
      if (ineEquipe == null) {
          if (other.ineEquipe != null) return false;
       }
      if (dtAtendimento == null) {
          if (other.dtAtendimento != null) return false;
       }
      if (totalafericaopa == null) {
          if (other.totalafericaopa != null) return false;
       }
      if (totalglicemiacapilar == null) {
          if (other.totalglicemiacapilar != null) return false;
       }
      if (totalafericaotemperatura == null) {
          if (other.totalafericaotemperatura != null) return false;
       }
      if (totalmedicaoaltura == null) {
          if (other.totalmedicaoaltura != null) return false;
       }
      if (totalcurativosimples == null) {
          if (other.totalcurativosimples != null) return false;
       }
      if (totalmedicaopeso == null) {
          if (other.totalmedicaopeso != null) return false;
       }
      if (totalcoletamaterial == null) {
          if (other.totalcoletamaterial != null) return false;
       }
      if (stEnvio == null) {
          if (other.stEnvio != null) return false;
       }
      if (dtEnvio == null) {
          if (other.dtEnvio != null) return false;
       }
      if (uuid == null) {
          if (other.uuid != null) return false;
       }
      return true;
   }
}