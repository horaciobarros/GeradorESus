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
@Table(name="p_prontuario_cns")
public class PProntuarioCns implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @Column(name="co_numero_cartao")
   private String coNumeroCartao;

   @Column(name="tp_cartao")
   private String tpCartao;

   @Column(name="dt_atribuicao")
   private Date dtAtribuicao;

   @Column(name="ds_erros")
   private String dsErros;

   @Column(name="co_usuario")
   private String coUsuario;

   @ManyToOne
   @JoinColumn(name = "id_prontuario")
   private PProntuario pProntuario;

   public String getCoNumeroCartao() { 
      return coNumeroCartao;
   }
   public void  setCoNumeroCartao(String coNumeroCartao) { 
      this.coNumeroCartao = coNumeroCartao;
   }

   public String getTpCartao() { 
      return tpCartao;
   }
   public void  setTpCartao(String tpCartao) { 
      this.tpCartao = tpCartao;
   }

   public Date getDtAtribuicao() { 
      return dtAtribuicao;
   }
   public void  setDtAtribuicao(Date dtAtribuicao) { 
      this.dtAtribuicao = dtAtribuicao;
   }

   public String getDsErros() { 
      return dsErros;
   }
   public void  setDsErros(String dsErros) { 
      this.dsErros = dsErros;
   }

   public String getCoUsuario() { 
      return coUsuario;
   }
   public void  setCoUsuario(String coUsuario) { 
      this.coUsuario = coUsuario;
   }


   public  PProntuario  getPProntuario() { 
      return pProntuario;
   }
   public void  setPProntuario(PProntuario pProntuario) { 
      this.pProntuario = pProntuario;
   }
}