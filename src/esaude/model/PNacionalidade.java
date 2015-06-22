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
@Table(name="p_nacionalidade")
public class PNacionalidade implements Serializable {

   private static final long serialVersionUID = 1L;

   @Column(name="co_pais")
   private String coPais;

   @Column(name="ds_pais")
   private String dsPais;

   public String getCoPais() { 
      return coPais;
   }
   public void  setCoPais(String coPais) { 
      this.coPais = coPais;
   }

   public String getDsPais() { 
      return dsPais;
   }
   public void  setDsPais(String dsPais) { 
      this.dsPais = dsPais;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((coPais == null) ? 0 : coPais.hashCode());
      result = prime * result + ((dsPais == null) ? 0 : dsPais.hashCode());
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
      PNacionalidade other = (PNacionalidade) obj;
      if (coPais == null) {
          if (other.coPais != null) return false;
       }
      if (dsPais == null) {
          if (other.dsPais != null) return false;
       }
      return true;
   }
}