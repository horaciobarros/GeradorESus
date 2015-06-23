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
@Table(name="p_municipio")
public class PMunicipio implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @Column(name="co_municipio")
   private String coMunicipio;

   @Column(name="nu_dv_municipio")
   private String nuDvMunicipio;

   @Column(name="ds_municipio")
   private String dsMunicipio;

   @Column(name="sg_uf")
   private String sgUf;

   public String getCoMunicipio() { 
      return coMunicipio;
   }
   public void  setCoMunicipio(String coMunicipio) { 
      this.coMunicipio = coMunicipio;
   }

   public String getNuDvMunicipio() { 
      return nuDvMunicipio;
   }
   public void  setNuDvMunicipio(String nuDvMunicipio) { 
      this.nuDvMunicipio = nuDvMunicipio;
   }

   public String getDsMunicipio() { 
      return dsMunicipio;
   }
   public void  setDsMunicipio(String dsMunicipio) { 
      this.dsMunicipio = dsMunicipio;
   }

   public String getSgUf() { 
      return sgUf;
   }
   public void  setSgUf(String sgUf) { 
      this.sgUf = sgUf;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((coMunicipio == null) ? 0 : coMunicipio.hashCode());
      result = prime * result + ((nuDvMunicipio == null) ? 0 : nuDvMunicipio.hashCode());
      result = prime * result + ((dsMunicipio == null) ? 0 : dsMunicipio.hashCode());
      result = prime * result + ((sgUf == null) ? 0 : sgUf.hashCode());
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
      PMunicipio other = (PMunicipio) obj;
      if (coMunicipio == null) {
          if (other.coMunicipio != null) return false;
       }
      if (nuDvMunicipio == null) {
          if (other.nuDvMunicipio != null) return false;
       }
      if (dsMunicipio == null) {
          if (other.dsMunicipio != null) return false;
       }
      if (sgUf == null) {
          if (other.sgUf != null) return false;
       }
      return true;
   }
}