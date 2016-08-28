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
@Table(name="esus_cadastro_domiciliar_familia")
public class EsusCadastroDomiciliarFamilia implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @Column(name="seq")
   private Long seq;

   @ManyToOne
   @JoinColumn(name = "cadastro_domiciliar")
   private EsusCadastroDomiciliar esusCadastroDomiciliar;

   @ManyToOne
   @JoinColumn(name = "rendafamiliar")
   private EsusRendafamiliar esusRendafamiliar;

   @Column(name="dt_mudanca")
   private Date dtMudanca;

   @Column(name="qt_membro_familia")
   private Long qtMembroFamilia;

   @Column(name="st_mudanca")
   private Long stMudanca;

   @ManyToOne
   @JoinColumn(name = "id_prontuario")
   private PProntuario pProntuario;

   @Column(name="num_prontuario_familiar")
   private String numProntuarioFamiliar;

   public Long getSeq() { 
      return seq;
   }
   public void  setSeq(Long seq) { 
      this.seq = seq;
   }


   public  EsusCadastroDomiciliar  getEsusCadastroDomiciliar() { 
      return esusCadastroDomiciliar;
   }
   public void  setEsusCadastroDomiciliar(EsusCadastroDomiciliar esusCadastroDomiciliar) { 
      this.esusCadastroDomiciliar = esusCadastroDomiciliar;
   }


   public  EsusRendafamiliar  getEsusRendafamiliar() { 
      return esusRendafamiliar;
   }
   public void  setEsusRendafamiliar(EsusRendafamiliar esusRendafamiliar) { 
      this.esusRendafamiliar = esusRendafamiliar;
   }

   public Date getDtMudanca() { 
      return dtMudanca;
   }
   public void  setDtMudanca(Date dtMudanca) { 
      this.dtMudanca = dtMudanca;
   }

   public Long getQtMembroFamilia() { 
      return qtMembroFamilia;
   }
   public void  setQtMembroFamilia(Long qtMembroFamilia) { 
      this.qtMembroFamilia = qtMembroFamilia;
   }

   public Long getStMudanca() { 
      return stMudanca;
   }
   public void  setStMudanca(Long stMudanca) { 
      this.stMudanca = stMudanca;
   }


   public  PProntuario  getPProntuario() { 
      return pProntuario;
   }
   public void  setPProntuario(PProntuario pProntuario) { 
      this.pProntuario = pProntuario;
   }

   public String getNumProntuarioFamiliar() { 
      return numProntuarioFamiliar;
   }
   public void  setNumProntuarioFamiliar(String numProntuarioFamiliar) { 
      this.numProntuarioFamiliar = numProntuarioFamiliar;
   }
}