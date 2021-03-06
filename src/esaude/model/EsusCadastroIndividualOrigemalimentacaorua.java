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
@Table(name="esus_cadastro_individual_origemalimentacaorua")
public class EsusCadastroIndividualOrigemalimentacaorua implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_cadastro_individual")
   private EsusCadastroIndividual esusCadastroIndividual;

   @ManyToOne
   @JoinColumn(name = "id_origemalimentacao")
   private EsusOrigemalimentacao esusOrigemalimentacao;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }


   public  EsusCadastroIndividual  getEsusCadastroIndividual() { 
      return esusCadastroIndividual;
   }
   public void  setEsusCadastroIndividual(EsusCadastroIndividual esusCadastroIndividual) { 
      this.esusCadastroIndividual = esusCadastroIndividual;
   }


   public  EsusOrigemalimentacao  getEsusOrigemalimentacao() { 
      return esusOrigemalimentacao;
   }
   public void  setEsusOrigemalimentacao(EsusOrigemalimentacao esusOrigemalimentacao) { 
      this.esusOrigemalimentacao = esusOrigemalimentacao;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((esusCadastroIndividual == null) ? 0 : esusCadastroIndividual.hashCode());
      result = prime * result + ((esusOrigemalimentacao == null) ? 0 : esusOrigemalimentacao.hashCode());
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
      EsusCadastroIndividualOrigemalimentacaorua other = (EsusCadastroIndividualOrigemalimentacaorua) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (esusCadastroIndividual == null) {
          if (other.esusCadastroIndividual != null) return false;
       }
      if (esusOrigemalimentacao == null) {
          if (other.esusOrigemalimentacao != null) return false;
       }
      return true;
   }
}