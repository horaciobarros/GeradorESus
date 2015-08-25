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
@Table(name="esus_atendimento_individual_exames")
public class EsusAtendimentoIndividualExames implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_atendimento_individual")
   private EsusAtendimentoIndividual esusAtendimentoIndividual;

   @Column(name = "co_exame")
   private String coExame;

   @Column(name="status")
   private String status;

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


   public String getStatus() { 
      return status;
   }
   public void  setStatus(String status) { 
      this.status = status;
   }
public String getCoExame() {
	return coExame;
}
public void setCoExame(String coExame) {
	this.coExame = coExame;
}

}