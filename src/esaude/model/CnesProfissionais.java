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
@Table(name="cnes_profissionais")
public class CnesProfissionais implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="cpf")
   private String cpf;

   @Column(name="cns")
   private String cns;

   @Column(name="nome")
   private String nome;

   @Column(name="apelido")
   private String apelido;

   @Column(name="nascimento")
   private Date nascimento;

   @Column(name="sexo")
   private String sexo;

   @Column(name="pispasep")
   private String pispasep;

   @Column(name="endereco")
   private String endereco;

   @Column(name="endereco_num")
   private String enderecoNum;

   @Column(name="bairro")
   private String bairro;

   @Column(name="cep")
   private String cep;

   @Column(name="telefone")
   private String telefone;

   @Column(name="cidade_ibge")
   private String cidadeIbge;

   @Column(name="num_registro")
   private String numRegistro;

   @Column(name="email")
   private String email;

   @ManyToOne
   @JoinColumn(name = "id_usuario")
   private FrUsuario frUsuario;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }

   public String getCpf() { 
      return cpf;
   }
   public void  setCpf(String cpf) { 
      this.cpf = cpf;
   }

   public String getCns() { 
      return cns;
   }
   public void  setCns(String cns) { 
      this.cns = cns;
   }

   public String getNome() { 
      return nome;
   }
   public void  setNome(String nome) { 
      this.nome = nome;
   }

   public String getApelido() { 
      return apelido;
   }
   public void  setApelido(String apelido) { 
      this.apelido = apelido;
   }

   public Date getNascimento() { 
      return nascimento;
   }
   public void  setNascimento(Date nascimento) { 
      this.nascimento = nascimento;
   }

   public String getSexo() { 
      return sexo;
   }
   public void  setSexo(String sexo) { 
      this.sexo = sexo;
   }

   public String getPispasep() { 
      return pispasep;
   }
   public void  setPispasep(String pispasep) { 
      this.pispasep = pispasep;
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

   public String getCidadeIbge() { 
      return cidadeIbge;
   }
   public void  setCidadeIbge(String cidadeIbge) { 
      this.cidadeIbge = cidadeIbge;
   }

   public String getNumRegistro() { 
      return numRegistro;
   }
   public void  setNumRegistro(String numRegistro) { 
      this.numRegistro = numRegistro;
   }

   public String getEmail() { 
      return email;
   }
   public void  setEmail(String email) { 
      this.email = email;
   }


   public  FrUsuario  getFrUsuario() { 
      return frUsuario;
   }
   public void  setFrUsuario(FrUsuario frUsuario) { 
      this.frUsuario = frUsuario;
   }


   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
      result = prime * result + ((cns == null) ? 0 : cns.hashCode());
      result = prime * result + ((nome == null) ? 0 : nome.hashCode());
      result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
      result = prime * result + ((nascimento == null) ? 0 : nascimento.hashCode());
      result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
      result = prime * result + ((pispasep == null) ? 0 : pispasep.hashCode());
      result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
      result = prime * result + ((enderecoNum == null) ? 0 : enderecoNum.hashCode());
      result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
      result = prime * result + ((cep == null) ? 0 : cep.hashCode());
      result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
      result = prime * result + ((cidadeIbge == null) ? 0 : cidadeIbge.hashCode());
      result = prime * result + ((numRegistro == null) ? 0 : numRegistro.hashCode());
      result = prime * result + ((email == null) ? 0 : email.hashCode());
      result = prime * result + ((frUsuario == null) ? 0 : frUsuario.hashCode());
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
      CnesProfissionais other = (CnesProfissionais) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (cpf == null) {
          if (other.cpf != null) return false;
       }
      if (cns == null) {
          if (other.cns != null) return false;
       }
      if (nome == null) {
          if (other.nome != null) return false;
       }
      if (apelido == null) {
          if (other.apelido != null) return false;
       }
      if (nascimento == null) {
          if (other.nascimento != null) return false;
       }
      if (sexo == null) {
          if (other.sexo != null) return false;
       }
      if (pispasep == null) {
          if (other.pispasep != null) return false;
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
      if (cep == null) {
          if (other.cep != null) return false;
       }
      if (telefone == null) {
          if (other.telefone != null) return false;
       }
      if (cidadeIbge == null) {
          if (other.cidadeIbge != null) return false;
       }
      if (numRegistro == null) {
          if (other.numRegistro != null) return false;
       }
      if (email == null) {
          if (other.email != null) return false;
       }
      if (frUsuario == null) {
          if (other.frUsuario != null) return false;
       }
      return true;
   }
}