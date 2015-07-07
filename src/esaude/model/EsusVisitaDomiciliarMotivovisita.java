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
@Table(name="esus_visita_domiciliar_motivovisita")
public class EsusVisitaDomiciliarMotivovisita implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_visita_domiciliar")
   private EsusVisitaDomiciliar esusVisitaDomiciliar;

   @ManyToOne
   @JoinColumn(name = "id_motivovisita")
   private EsusMotivovisita esusMotivovisita;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }


   public  EsusVisitaDomiciliar  getEsusVisitaDomiciliar() { 
      return esusVisitaDomiciliar;
   }
   public void  setEsusVisitaDomiciliar(EsusVisitaDomiciliar esusVisitaDomiciliar) { 
      this.esusVisitaDomiciliar = esusVisitaDomiciliar;
   }


   public  EsusMotivovisita  getEsusMotivovisita() { 
      return esusMotivovisita;
   }
   public void  setEsusMotivovisita(EsusMotivovisita esusMotivovisita) { 
      this.esusMotivovisita = esusMotivovisita;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((esusVisitaDomiciliar == null) ? 0 : esusVisitaDomiciliar.hashCode());
      result = prime * result + ((esusMotivovisita == null) ? 0 : esusMotivovisita.hashCode());
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
      EsusVisitaDomiciliarMotivovisita other = (EsusVisitaDomiciliarMotivovisita) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (esusVisitaDomiciliar == null) {
          if (other.esusVisitaDomiciliar != null) return false;
       }
      if (esusMotivovisita == null) {
          if (other.esusMotivovisita != null) return false;
       }
      return true;
   }
}