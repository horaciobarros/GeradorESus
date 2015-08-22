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
@Table(name="esus_atendimento_odontologico_ciap")
public class EsusAtendimentoOdontologicoCiap implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_atend_odonto")
   private EsusAtendimentoOdontologico esusAtendimentoOdontologico;

   @Column(name="co_ciap")
   private String coCiap;

   @Column(name="qtd")
   private Long qtd;

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

   public String getCoCiap() { 
      return coCiap;
   }
   public void  setCoCiap(String coCiap) { 
      this.coCiap = coCiap;
   }

   public Long getQtd() { 
      return qtd;
   }
   public void  setQtd(Long qtd) { 
      this.qtd = qtd;
   }
}