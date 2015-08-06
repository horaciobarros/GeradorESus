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
@Table(name="esus_ficha_procedimento_atendimento_proc")
public class EsusFichaProcedimentoAtendimentoProc implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_ficha_proc_atend")
   private EsusFichaProcedimentoAtendimento esusFichaProcedimentoAtendimento;

   @Column(name="co_procedimento_ciap")
   private String coProcedimentoCiap;

   @Column(name="co_procedimento_sigtap")
   private String coProcedimentoSigtap;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }


   public  EsusFichaProcedimentoAtendimento  getEsusFichaProcedimentoAtendimento() { 
      return esusFichaProcedimentoAtendimento;
   }
   public void  setEsusFichaProcedimentoAtendimento(EsusFichaProcedimentoAtendimento esusFichaProcedimentoAtendimento) { 
      this.esusFichaProcedimentoAtendimento = esusFichaProcedimentoAtendimento;
   }

   public String getCoProcedimentoCiap() { 
      return coProcedimentoCiap;
   }
   public void  setCoProcedimentoCiap(String coProcedimentoCiap) { 
      this.coProcedimentoCiap = coProcedimentoCiap;
   }

   public String getCoProcedimentoSigtap() { 
      return coProcedimentoSigtap;
   }
   public void  setCoProcedimentoSigtap(String coProcedimentoSigtap) { 
      this.coProcedimentoSigtap = coProcedimentoSigtap;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((esusFichaProcedimentoAtendimento == null) ? 0 : esusFichaProcedimentoAtendimento.hashCode());
      result = prime * result + ((coProcedimentoCiap == null) ? 0 : coProcedimentoCiap.hashCode());
      result = prime * result + ((coProcedimentoSigtap == null) ? 0 : coProcedimentoSigtap.hashCode());
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
      EsusFichaProcedimentoAtendimentoProc other = (EsusFichaProcedimentoAtendimentoProc) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (esusFichaProcedimentoAtendimento == null) {
          if (other.esusFichaProcedimentoAtendimento != null) return false;
       }
      if (coProcedimentoCiap == null) {
          if (other.coProcedimentoCiap != null) return false;
       }
      if (coProcedimentoSigtap == null) {
          if (other.coProcedimentoSigtap != null) return false;
       }
      return true;
   }
}