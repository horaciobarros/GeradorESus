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
@Table(name="p_raca_cor")
public class PRacaCor implements Serializable {

   private static final long serialVersionUID = 1L;

   @ManyToOne
   @JoinColumn(name = "co_raca")
   private PRacaCor pRacaCor;

   @Column(name="ds_raca")
   private String dsRaca;


   public  PRacaCor  getPRacaCor() { 
      return pRacaCor;
   }
   public void  setPRacaCor(PRacaCor pRacaCor) { 
      this.pRacaCor = pRacaCor;
   }

   public String getDsRaca() { 
      return dsRaca;
   }
   public void  setDsRaca(String dsRaca) { 
      this.dsRaca = dsRaca;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((pRacaCor == null) ? 0 : pRacaCor.hashCode());
      result = prime * result + ((dsRaca == null) ? 0 : dsRaca.hashCode());
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
      PRacaCor other = (PRacaCor) obj;
      if (pRacaCor == null) {
          if (other.pRacaCor != null) return false;
       }
      if (dsRaca == null) {
          if (other.dsRaca != null) return false;
       }
      return true;
   }
}