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
@Table(name="cnes_estabelecimentos")
public class CnesEstabelecimentos implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="codigo")
   private String codigo;

   @Column(name="cnes")
   private String cnes;

   @Column(name="razao_social")
   private String razaoSocial;

   @Column(name="nome_fantasia")
   private String nomeFantasia;

   @Column(name="nome_amigavel")
   private String nomeAmigavel;

   @Column(name="endereco")
   private String endereco;

   @Column(name="endereco_num")
   private String enderecoNum;

   @Column(name="bairro")
   private String bairro;

   @Column(name="cidade")
   private String cidade;

   @Column(name="cep")
   private String cep;

   @Column(name="telefone")
   private String telefone;

   @Column(name="mantenedora_cnpj")
   private String mantenedoraCnpj;

   @Column(name="mostrar")
   private String mostrar;

   @Column(name="ativo")
   private String ativo;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }

   public String getCodigo() { 
      return codigo;
   }
   public void  setCodigo(String codigo) { 
      this.codigo = codigo;
   }

   public String getCnes() { 
      return cnes;
   }
   public void  setCnes(String cnes) { 
      this.cnes = cnes;
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

   public String getNomeAmigavel() { 
      return nomeAmigavel;
   }
   public void  setNomeAmigavel(String nomeAmigavel) { 
      this.nomeAmigavel = nomeAmigavel;
   }

   public String getEndereco() { 
      return endereco;
   }
   public void  setEndereco(String endereco) { 
      this.endereco = endereco;
   }

   public String getEnderecoNum() { 
      return enderecoNum;
   }
   public void  setEnderecoNum(String enderecoNum) { 
      this.enderecoNum = enderecoNum;
   }

   public String getBairro() { 
      return bairro;
   }
   public void  setBairro(String bairro) { 
      this.bairro = bairro;
   }

   public String getCidade() { 
      return cidade;
   }
   public void  setCidade(String cidade) { 
      this.cidade = cidade;
   }

   public String getCep() { 
      return cep;
   }
   public void  setCep(String cep) { 
      this.cep = cep;
   }

   public String getTelefone() { 
      return telefone;
   }
   public void  setTelefone(String telefone) { 
      this.telefone = telefone;
   }

   public String getMantenedoraCnpj() { 
      return mantenedoraCnpj;
   }
   public void  setMantenedoraCnpj(String mantenedoraCnpj) { 
      this.mantenedoraCnpj = mantenedoraCnpj;
   }

   public String getMostrar() { 
      return mostrar;
   }
   public void  setMostrar(String mostrar) { 
      this.mostrar = mostrar;
   }

   public String getAtivo() { 
      return ativo;
   }
   public void  setAtivo(String ativo) { 
      this.ativo = ativo;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
      result = prime * result + ((cnes == null) ? 0 : cnes.hashCode());
      result = prime * result + ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
      result = prime * result + ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
      result = prime * result + ((nomeAmigavel == null) ? 0 : nomeAmigavel.hashCode());
      result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
      result = prime * result + ((enderecoNum == null) ? 0 : enderecoNum.hashCode());
      result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
      result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
      result = prime * result + ((cep == null) ? 0 : cep.hashCode());
      result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
      result = prime * result + ((mantenedoraCnpj == null) ? 0 : mantenedoraCnpj.hashCode());
      result = prime * result + ((mostrar == null) ? 0 : mostrar.hashCode());
      result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
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
      CnesEstabelecimentos other = (CnesEstabelecimentos) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (codigo == null) {
          if (other.codigo != null) return false;
       }
      if (cnes == null) {
          if (other.cnes != null) return false;
       }
      if (razaoSocial == null) {
          if (other.razaoSocial != null) return false;
       }
      if (nomeFantasia == null) {
          if (other.nomeFantasia != null) return false;
       }
      if (nomeAmigavel == null) {
          if (other.nomeAmigavel != null) return false;
       }
      if (endereco == null) {
          if (other.endereco != null) return false;
       }
      if (enderecoNum == null) {
          if (other.enderecoNum != null) return false;
       }
      if (bairro == null) {
          if (other.bairro != null) return false;
       }
      if (cidade == null) {
          if (other.cidade != null) return false;
       }
      if (cep == null) {
          if (other.cep != null) return false;
       }
      if (telefone == null) {
          if (other.telefone != null) return false;
       }
      if (mantenedoraCnpj == null) {
          if (other.mantenedoraCnpj != null) return false;
       }
      if (mostrar == null) {
          if (other.mostrar != null) return false;
       }
      if (ativo == null) {
          if (other.ativo != null) return false;
       }
      return true;
   }
}