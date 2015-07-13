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
@Table(name="esus_atendimento_individual_exames_sia")
public class EsusAtendimentoIndividualExamesSia implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_atendimento_individual")
   private EsusAtendimentoIndividual esusAtendimentoIndividual;

   @ManyToOne
   @JoinColumn(name = "id_proced")
   private EsusProced esusProced;

   @Column(name="status")
   private String status;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }


   public  EsusAtendimentoIndividual  getEsusAtendimentoIndividual() { 
      return esusAtendimentoIndividual;
   }
   public void  setEsusAtendimentoIndividual(EsusAtendimentoIndividual esusAtendimentoIndividual) { 
      this.esusAtendimentoIndividual = esusAtendimentoIndividual;
   }


   public  EsusProced  getEsusProced() { 
      return esusProced;
   }
   public void  setEsusProced(EsusProced esusProced) { 
      this.esusProced = esusProced;
   }

   public String getStatus() { 
      return status;
   }
   public void  setStatus(String status) { 
      this.status = status;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((esusAtendimentoIndividual == null) ? 0 : esusAtendimentoIndividual.hashCode());
      result = prime * result + ((esusProced == null) ? 0 : esusProced.hashCode());
      result = prime * result + ((status == null) ? 0 : status.hashCode());
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
      EsusAtendimentoIndividualExamesSia other = (EsusAtendimentoIndividualExamesSia) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (esusAtendimentoIndividual == null) {
          if (other.esusAtendimentoIndividual != null) return false;
       }
      if (esusProced == null) {
          if (other.esusProced != null) return false;
       }
      if (status == null) {
          if (other.status != null) return false;
       }
      return true;
   }
}