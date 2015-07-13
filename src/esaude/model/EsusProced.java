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
@Table(name="esus_proced")
public class EsusProced implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="co_proced")
   private String coProced;

   @Column(name="no_proced")
   private String noProced;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }

   public String getCoProced() { 
      return coProced;
   }
   public void  setCoProced(String coProced) { 
      this.coProced = coProced;
   }

   public String getNoProced() { 
      return noProced;
   }
   public void  setNoProced(String noProced) { 
      this.noProced = noProced;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((coProced == null) ? 0 : coProced.hashCode());
      result = prime * result + ((noProced == null) ? 0 : noProced.hashCode());
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
      EsusProced other = (EsusProced) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (coProced == null) {
          if (other.coProced != null) return false;
       }
      if (noProced == null) {
          if (other.noProced != null) return false;
       }
      return true;
   }
}