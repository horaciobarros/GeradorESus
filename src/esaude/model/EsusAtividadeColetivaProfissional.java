package esaude.model; 

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.*;
import java.util.*;

@Entity 
@Table(name="esus_atividade_coletiva_profissional")
public class EsusAtividadeColetivaProfissional implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_atividade_coletiva")
   private EsusAtividadeColetiva esusAtividadeColetiva;

   @ManyToOne(fetch = FetchType.LAZY, optional = true)
   @JoinColumn(name = "id_profissional")
   private CnesProfissionais cnesProfissionais;

   @Column(name="cns_profissional")
   private String cnsProfissional;

   @Column(name="cbo")
   private String cbo;

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


   public  CnesProfissionais  getCnesProfissionais() { 
      return cnesProfissionais;
   }
   public void  setCnesProfissionais(CnesProfissionais cnesProfissionais) { 
      this.cnesProfissionais = cnesProfissionais;
   }

   public String getCnsProfissional() { 
      return cnsProfissional;
   }
   public void  setCnsProfissional(String cnsProfissional) { 
      this.cnsProfissional = cnsProfissional;
   }

   public String getCbo() { 
      return cbo;
   }
   public void  setCbo(String cbo) { 
      this.cbo = cbo;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((esusAtividadeColetiva == null) ? 0 : esusAtividadeColetiva.hashCode());
      result = prime * result + ((cnesProfissionais == null) ? 0 : cnesProfissionais.hashCode());
      result = prime * result + ((cnsProfissional == null) ? 0 : cnsProfissional.hashCode());
      result = prime * result + ((cbo == null) ? 0 : cbo.hashCode());
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
      EsusAtividadeColetivaProfissional other = (EsusAtividadeColetivaProfissional) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (esusAtividadeColetiva == null) {
          if (other.esusAtividadeColetiva != null) return false;
       }
      if (cnesProfissionais == null) {
          if (other.cnesProfissionais != null) return false;
       }
      if (cnsProfissional == null) {
          if (other.cnsProfissional != null) return false;
       }
      if (cbo == null) {
          if (other.cbo != null) return false;
       }
      return true;
   }
}