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
@Table(name="esus_atendimento_odontologico_fornecimento")
public class EsusAtendimentoOdontologicoFornecimento implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_atendimento_odontologico")
   private EsusAtendimentoOdontologico esusAtendimentoOdontologico;

   @ManyToOne
   @JoinColumn(name = "id_fornecimentoodonto")
   private EsusFornecimentoodonto esusFornecimentoodonto;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }


   public  EsusAtendimentoOdontologico  getEsusAtendimentoOdontologico() { 
      return esusAtendimentoOdontologico;
   }
   public void  setEsusAtendimentoOdontologico(EsusAtendimentoOdontologico esusAtendimentoOdontologico) { 
      this.esusAtendimentoOdontologico = esusAtendimentoOdontologico;
   }


   public  EsusFornecimentoodonto  getEsusFornecimentoodonto() { 
      return esusFornecimentoodonto;
   }
   public void  setEsusFornecimentoodonto(EsusFornecimentoodonto esusFornecimentoodonto) { 
      this.esusFornecimentoodonto = esusFornecimentoodonto;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((esusAtendimentoOdontologico == null) ? 0 : esusAtendimentoOdontologico.hashCode());
      result = prime * result + ((esusFornecimentoodonto == null) ? 0 : esusFornecimentoodonto.hashCode());
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
      EsusAtendimentoOdontologicoFornecimento other = (EsusAtendimentoOdontologicoFornecimento) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (esusAtendimentoOdontologico == null) {
          if (other.esusAtendimentoOdontologico != null) return false;
       }
      if (esusFornecimentoodonto == null) {
          if (other.esusFornecimentoodonto != null) return false;
       }
      return true;
   }
}