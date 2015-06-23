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
@Table(name="fr_usuario")
public class FrUsuario implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @Column(name="usr_codigo")
   private String usrCodigo;

   @Column(name="usr_login")
   private String usrLogin;

   @Column(name="usr_senha")
   private String usrSenha;

   @Column(name="usr_administrador")
   private String usrAdministrador;

   @Column(name="usr_tipo_expiracao")
   private String usrTipoExpiracao;

   @Column(name="usr_dias_expiracao")
   private String usrDiasExpiracao;

   @Column(name="usr_imagem_digital")
   private String usrImagemDigital;

   @Column(name="usr_foto")
   private String usrFoto;

   @Column(name="usr_nome")
   private String usrNome;

   @Column(name="usr_email")
   private String usrEmail;

   @Column(name="usr_digital")
   private Long usrDigital;

   @Column(name="usr_inicio_expiracao")
   private Date usrInicioExpiracao;

   public String getUsrCodigo() { 
      return usrCodigo;
   }
   public void  setUsrCodigo(String usrCodigo) { 
      this.usrCodigo = usrCodigo;
   }

   public String getUsrLogin() { 
      return usrLogin;
   }
   public void  setUsrLogin(String usrLogin) { 
      this.usrLogin = usrLogin;
   }

   public String getUsrSenha() { 
      return usrSenha;
   }
   public void  setUsrSenha(String usrSenha) { 
      this.usrSenha = usrSenha;
   }

   public String getUsrAdministrador() { 
      return usrAdministrador;
   }
   public void  setUsrAdministrador(String usrAdministrador) { 
      this.usrAdministrador = usrAdministrador;
   }

   public String getUsrTipoExpiracao() { 
      return usrTipoExpiracao;
   }
   public void  setUsrTipoExpiracao(String usrTipoExpiracao) { 
      this.usrTipoExpiracao = usrTipoExpiracao;
   }

   public String getUsrDiasExpiracao() { 
      return usrDiasExpiracao;
   }
   public void  setUsrDiasExpiracao(String usrDiasExpiracao) { 
      this.usrDiasExpiracao = usrDiasExpiracao;
   }

   public String getUsrImagemDigital() { 
      return usrImagemDigital;
   }
   public void  setUsrImagemDigital(String usrImagemDigital) { 
      this.usrImagemDigital = usrImagemDigital;
   }

   public String getUsrFoto() { 
      return usrFoto;
   }
   public void  setUsrFoto(String usrFoto) { 
      this.usrFoto = usrFoto;
   }

   public String getUsrNome() { 
      return usrNome;
   }
   public void  setUsrNome(String usrNome) { 
      this.usrNome = usrNome;
   }

   public String getUsrEmail() { 
      return usrEmail;
   }
   public void  setUsrEmail(String usrEmail) { 
      this.usrEmail = usrEmail;
   }

   public Long getUsrDigital() { 
      return usrDigital;
   }
   public void  setUsrDigital(Long usrDigital) { 
      this.usrDigital = usrDigital;
   }

   public Date getUsrInicioExpiracao() { 
      return usrInicioExpiracao;
   }
   public void  setUsrInicioExpiracao(Date usrInicioExpiracao) { 
      this.usrInicioExpiracao = usrInicioExpiracao;
   }

}