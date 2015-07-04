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
@Table(name="sis_registro")
public class SisRegistro implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="cnpj")
   private String cnpj;

   @Column(name="razao_social")
   private String razaoSocial;

   @Column(name="nome_fantasia")
   private String nomeFantasia;

   @Column(name="telefone")
   private String telefone;

   @Column(name="cidade_ibge")
   private String cidadeIbge;

   @Column(name="bairro")
   private String bairro;

   @Column(name="endereco")
   private String endereco;

   @Column(name="cep")
   private String cep;

   @Column(name="caminho_conn_sql")
   private String caminhoConnSql;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }

   public String getCnpj() { 
      return cnpj;
   }
   public void  setCnpj(String cnpj) { 
      this.cnpj = cnpj;
   }

   public String getRazaoSocial() { 
      return razaoSocial;
   }
   public void  setRazaoSocial(String razaoSocial) { 
      this.razaoSocial = razaoSocial;
   }

   public String getNomeFantasia() { 
      return nomeFantasia;
   }
   public void  setNomeFantasia(String nomeFantasia) { 
      this.nomeFantasia = nomeFantasia;
   }

   public String getTelefone() { 
      return telefone;
   }
   public void  setTelefone(String telefone) { 
      this.telefone = telefone;
   }

   public String getCidadeIbge() { 
      return cidadeIbge;
   }
   public void  setCidadeIbge(String cidadeIbge) { 
      this.cidadeIbge = cidadeIbge;
   }

   public String getBairro() { 
      return bairro;
   }
   public void  setBairro(String bairro) { 
      this.bairro = bairro;
   }

   public String getEndereco() { 
      return endereco;
   }
   public void  setEndereco(String endereco) { 
      this.endereco = endereco;
   }

   public String getCep() { 
      return cep;
   }
   public void  setCep(String cep) { 
      this.cep = cep;
   }

   public String getCaminhoConnSql() { 
      return caminhoConnSql;
   }
   public void  setCaminhoConnSql(String caminhoConnSql) { 
      this.caminhoConnSql = caminhoConnSql;
   }

}
