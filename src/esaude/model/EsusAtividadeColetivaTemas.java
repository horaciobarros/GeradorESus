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
@Table(name="esus_atividade_coletiva_temas")
public class EsusAtividadeColetivaTemas implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_atividade_coletiva")
   private EsusAtividadeColetiva esusAtividadeColetiva;

   @ManyToOne
   @JoinColumn(name = "id_temasparareuniao")
   private EsusTemasparareuniao esusTemasparareuniao;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }


   public  EsusAtividadeColetiva  getEsusAtividadeColetiva() { 
      return esusAtividadeColetiva;
   }
   public void  setEsusAtividadeColetiva(EsusAtividadeColetiva esusAtividadeColetiva) { 
      this.esusAtividadeColetiva = esusAtividadeColetiva;
   }


   public  EsusTemasparareuniao  getEsusTemasparareuniao() { 
      return esusTemasparareuniao;
   }
   public void  setEsusTemasparareuniao(EsusTemasparareuniao esusTemasparareuniao) { 
      this.esusTemasparareuniao = esusTemasparareuniao;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((esusAtividadeColetiva == null) ? 0 : esusAtividadeColetiva.hashCode());
      result = prime * result + ((esusTemasparareuniao == null) ? 0 : esusTemasparareuniao.hashCode());
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
      EsusAtividadeColetivaTemas other = (EsusAtividadeColetivaTemas) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (esusAtividadeColetiva == null) {
          if (other.esusAtividadeColetiva != null) return false;
       }
      if (esusTemasparareuniao == null) {
          if (other.esusTemasparareuniao != null) return false;
       }
      return true;
   }
}