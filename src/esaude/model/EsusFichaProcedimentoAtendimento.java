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
@Table(name="esus_ficha_procedimento_atendimento")
public class EsusFichaProcedimentoAtendimento implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_ficha_procedimento")
   private EsusFichaProcedimento esusFichaProcedimento;

   @ManyToOne
   @JoinColumn(name = "id_prontuario")
   private PProntuario pProntuario;

   @ManyToOne
   @JoinColumn(name = "id_turno")
   private EsusTurno esusTurno;

   @Column(name="escuta_inicial")
   private boolean escutaInicial;

   @ManyToOne
   @JoinColumn(name = "id_local_atendimento")
   private EsusLocaldeatendimento esusLocaldeatendimento;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }


   public  EsusFichaProcedimento  getEsusFichaProcedimento() { 
      return esusFichaProcedimento;
   }
   public void  setEsusFichaProcedimento(EsusFichaProcedimento esusFichaProcedimento) { 
      this.esusFichaProcedimento = esusFichaProcedimento;
   }


   public  PProntuario  getPProntuario() { 
      return pProntuario;
   }
   public void  setPProntuario(PProntuario pProntuario) { 
      this.pProntuario = pProntuario;
   }


   public  EsusTurno  getEsusTurno() { 
      return esusTurno;
   }
   public void  setEsusTurno(EsusTurno esusTurno) { 
      this.esusTurno = esusTurno;
   }

   public boolean getEscutaInicial() { 
      return escutaInicial;
   }
   public void  setEscutaInicial(boolean escutaInicial) { 
      this.escutaInicial = escutaInicial;
   }


   public  EsusLocaldeatendimento  getEsusLocaldeatendimento() { 
      return esusLocaldeatendimento;
   }
   public void  setEsusLocaldeatendimento(EsusLocaldeatendimento esusLocaldeatendimento) { 
      this.esusLocaldeatendimento = esusLocaldeatendimento;
   }

  
  
}