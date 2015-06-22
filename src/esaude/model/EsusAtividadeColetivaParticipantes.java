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
@Table(name="esus_atividade_coletiva_participantes")
public class EsusAtividadeColetivaParticipantes implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "id_atividade_coletiva")
   private EsusAtividadeColetiva esusAtividadeColetiva;

   @Column(name="avaliacao_alterada")
   private boolean avaliacaoAlterada;

   @Column(name="peso")
   private double peso;

   @Column(name="altura")
   private Long altura;

   @Column(name="cessou_habito_fumar")
   private boolean cessouHabitoFumar;

   @Column(name="abandonou_grupo")
   private boolean abandonouGrupo;

   @ManyToOne
   @JoinColumn(name = "id_prontuario")
   private PProntuario pProntuario;

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

   public boolean getAvaliacaoAlterada() { 
      return avaliacaoAlterada;
   }
   public void  setAvaliacaoAlterada(boolean avaliacaoAlterada) { 
      this.avaliacaoAlterada = avaliacaoAlterada;
   }

   public double getPeso() { 
      return peso;
   }
   public void  setPeso(double peso) { 
      this.peso = peso;
   }

   public Long getAltura() { 
      return altura;
   }
   public void  setAltura(Long altura) { 
      this.altura = altura;
   }

   public boolean getCessouHabitoFumar() { 
      return cessouHabitoFumar;
   }
   public void  setCessouHabitoFumar(boolean cessouHabitoFumar) { 
      this.cessouHabitoFumar = cessouHabitoFumar;
   }

   public boolean getAbandonouGrupo() { 
      return abandonouGrupo;
   }
   public void  setAbandonouGrupo(boolean abandonouGrupo) { 
      this.abandonouGrupo = abandonouGrupo;
   }


   public  PProntuario  getPProntuario() { 
      return pProntuario;
   }
   public void  setPProntuario(PProntuario pProntuario) { 
      this.pProntuario = pProntuario;
   }

}