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
@Table(name="esus_atividade_coletiva_praticatemas")
public class EsusAtividadeColetivaPraticatemas implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_atividade_coletiva")
   private EsusAtividadeColetiva esusAtividadeColetiva;

   @ManyToOne
   @JoinColumn(name = "id_praticatemasaude")
   private EsusPraticastemasparasaude esusPraticastemasparasaude;

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


   public  EsusPraticastemasparasaude  getEsusPraticastemasparasaude() { 
      return esusPraticastemasparasaude;
   }
   public void  setEsusPraticastemasparasaude(EsusPraticastemasparasaude esusPraticastemasparasaude) { 
      this.esusPraticastemasparasaude = esusPraticastemasparasaude;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((esusAtividadeColetiva == null) ? 0 : esusAtividadeColetiva.hashCode());
      result = prime * result + ((esusPraticastemasparasaude == null) ? 0 : esusPraticastemasparasaude.hashCode());
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
      EsusAtividadeColetivaPraticatemas other = (EsusAtividadeColetivaPraticatemas) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (esusAtividadeColetiva == null) {
          if (other.esusAtividadeColetiva != null) return false;
       }
      if (esusPraticastemasparasaude == null) {
          if (other.esusPraticastemasparasaude != null) return false;
       }
      return true;
   }
}