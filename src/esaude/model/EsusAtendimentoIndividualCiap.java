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
import java.math.BigDecimal;

@Entity 
@Table(name="esus_atendimento_individual_ciap")
public class EsusAtendimentoIndividualCiap implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_atend_individual")
   private EsusAtendimentoIndividual esusAtendimentoIndividual;

   @Column(name="co_ciap")
   private String coCiap;

   @Column(name="co_cid")
   private String coCid;

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

   public String getCoCiap() { 
      return coCiap;
   }
   public void  setCoCiap(String coCiap) { 
      this.coCiap = coCiap;
   }

   public String getCoCid() { 
      return coCid;
   }
   public void  setCoCid(String coCid) { 
      this.coCid = coCid;
   }
}