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
@Table(name="esus_atendimento_individual_conduta")
public class EsusAtendimentoIndividualConduta implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_atendimento_individual")
   private EsusAtendimentoIndividual esusAtendimentoIndividual;

   @ManyToOne
   @JoinColumn(name = "id_conduta")
   private EsusCondutaencaminhada esusCondutaencaminhada;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }


   public  EsusAtendimentoIndividual  getEsusAtendimentoIndividual() { 
      return esusAtendimentoIndividual;
   }
   public void  setEsusAtendimentoIndividual(EsusAtendimentoIndividual esusAtendimentoIndividual) { 
      this.esusAtendimentoIndividual = esusAtendimentoIndividual;
   }


   public  EsusCondutaencaminhada  getEsusCondutaencaminhada() { 
      return esusCondutaencaminhada;
   }
   public void  setEsusCondutaencaminhada(EsusCondutaencaminhada esusCondutaencaminhada) { 
      this.esusCondutaencaminhada = esusCondutaencaminhada;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((esusAtendimentoIndividual == null) ? 0 : esusAtendimentoIndividual.hashCode());
      result = prime * result + ((esusCondutaencaminhada == null) ? 0 : esusCondutaencaminhada.hashCode());
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
      EsusAtendimentoIndividualConduta other = (EsusAtendimentoIndividualConduta) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (esusAtendimentoIndividual == null) {
          if (other.esusAtendimentoIndividual != null) return false;
       }
      if (esusCondutaencaminhada == null) {
          if (other.esusCondutaencaminhada != null) return false;
       }
      return true;
   }
}