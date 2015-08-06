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
@Table(name="esus_ficha_procedimento_ciap")
public class EsusFichaProcedimentoCiap implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="co_ciap")
   private String coCiap;

   @Column(name="ds_ciap")
   private String dsCiap;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }

   public String getCoCiap() { 
      return coCiap;
   }
   public void  setCoCiap(String coCiap) { 
      this.coCiap = coCiap;
   }

   public String getDsCiap() { 
      return dsCiap;
   }
   public void  setDsCiap(String dsCiap) { 
      this.dsCiap = dsCiap;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((coCiap == null) ? 0 : coCiap.hashCode());
      result = prime * result + ((dsCiap == null) ? 0 : dsCiap.hashCode());
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
      EsusFichaProcedimentoCiap other = (EsusFichaProcedimentoCiap) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (coCiap == null) {
          if (other.coCiap != null) return false;
       }
      if (dsCiap == null) {
          if (other.dsCiap != null) return false;
       }
      return true;
   }
}