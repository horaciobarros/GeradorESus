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
@Table(name="esus_materialpredominantenaconstrucao")
public class EsusMaterialpredominantenaconstrucao implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="descricao")
   private String descricao;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }

   public String getDescricao() { 
      return descricao;
   }
   public void  setDescricao(String descricao) { 
      this.descricao = descricao;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
      EsusMaterialpredominantenaconstrucao other = (EsusMaterialpredominantenaconstrucao) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (descricao == null) {
          if (other.descricao != null) return false;
       }
      return true;
   }
}