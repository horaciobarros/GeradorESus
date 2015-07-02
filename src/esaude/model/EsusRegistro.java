package esaude.model; 

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="esus_registro")
public class EsusRegistro implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="orig_contrachave")
   private String origContrachave;

   @Column(name="orig_cnpjcpf")
   private String origCnpjcpf;

   @Column(name="orig_email")
   private String origEmail;

   @Column(name="orig_fone")
   private String origFone;

   @Column(name="orig_razaosocial")
   private String origRazaosocial;

   @Column(name="orig_uuidinstalacao")
   private String origUuidinstalacao;

   @Column(name="rem_contrachave")
   private String remContrachave;

   @Column(name="rem_cnpjcpf")
   private String remCnpjcpf;

   @Column(name="rem_email")
   private String remEmail;

   @Column(name="rem_fone")
   private String remFone;

   @Column(name="rem_razaosocial")
   private String remRazaosocial;

   @Column(name="rem_uuidinstalacao")
   private String remUuidinstalacao;

   @Column(name="lote")
   private Long lote;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }

   public String getOrigContrachave() { 
      return origContrachave;
   }
   public void  setOrigContrachave(String origContrachave) { 
      this.origContrachave = origContrachave;
   }

   public String getOrigCnpjcpf() { 
      return origCnpjcpf;
   }
   public void  setOrigCnpjcpf(String origCnpjcpf) { 
      this.origCnpjcpf = origCnpjcpf;
   }

   public String getOrigEmail() { 
      return origEmail;
   }
   public void  setOrigEmail(String origEmail) { 
      this.origEmail = origEmail;
   }

   public String getOrigFone() { 
      return origFone;
   }
   public void  setOrigFone(String origFone) { 
      this.origFone = origFone;
   }

   public String getOrigRazaosocial() { 
      return origRazaosocial;
   }
   public void  setOrigRazaosocial(String origRazaosocial) { 
      this.origRazaosocial = origRazaosocial;
   }

   public String getOrigUuidinstalacao() { 
      return origUuidinstalacao;
   }
   public void  setOrigUuidinstalacao(String origUuidinstalacao) { 
      this.origUuidinstalacao = origUuidinstalacao;
   }

   public String getRemContrachave() { 
      return remContrachave;
   }
   public void  setRemContrachave(String remContrachave) { 
      this.remContrachave = remContrachave;
   }

   public String getRemCnpjcpf() { 
      return remCnpjcpf;
   }
   public void  setRemCnpjcpf(String remCnpjcpf) { 
      this.remCnpjcpf = remCnpjcpf;
   }

   public String getRemEmail() { 
      return remEmail;
   }
   public void  setRemEmail(String remEmail) { 
      this.remEmail = remEmail;
   }

   public String getRemFone() { 
      return remFone;
   }
   public void  setRemFone(String remFone) { 
      this.remFone = remFone;
   }

   public String getRemRazaosocial() { 
      return remRazaosocial;
   }
   public void  setRemRazaosocial(String remRazaosocial) { 
      this.remRazaosocial = remRazaosocial;
   }

   public String getRemUuidinstalacao() { 
      return remUuidinstalacao;
   }
   public void  setRemUuidinstalacao(String remUuidinstalacao) { 
      this.remUuidinstalacao = remUuidinstalacao;
   }

   public Long getLote() { 
      return lote;
   }
   public void  setLote(Long lote) { 
      this.lote = lote;
   }

   @Override 
   public int hashCode() { 
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((origContrachave == null) ? 0 : origContrachave.hashCode());
      result = prime * result + ((origCnpjcpf == null) ? 0 : origCnpjcpf.hashCode());
      result = prime * result + ((origEmail == null) ? 0 : origEmail.hashCode());
      result = prime * result + ((origFone == null) ? 0 : origFone.hashCode());
      result = prime * result + ((origRazaosocial == null) ? 0 : origRazaosocial.hashCode());
      result = prime * result + ((origUuidinstalacao == null) ? 0 : origUuidinstalacao.hashCode());
      result = prime * result + ((remContrachave == null) ? 0 : remContrachave.hashCode());
      result = prime * result + ((remCnpjcpf == null) ? 0 : remCnpjcpf.hashCode());
      result = prime * result + ((remEmail == null) ? 0 : remEmail.hashCode());
      result = prime * result + ((remFone == null) ? 0 : remFone.hashCode());
      result = prime * result + ((remRazaosocial == null) ? 0 : remRazaosocial.hashCode());
      result = prime * result + ((remUuidinstalacao == null) ? 0 : remUuidinstalacao.hashCode());
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
      EsusRegistro other = (EsusRegistro) obj;
      if (id == null) {
          if (other.id != null) return false;
       }
      if (origContrachave == null) {
          if (other.origContrachave != null) return false;
       }
      if (origCnpjcpf == null) {
          if (other.origCnpjcpf != null) return false;
       }
      if (origEmail == null) {
          if (other.origEmail != null) return false;
       }
      if (origFone == null) {
          if (other.origFone != null) return false;
       }
      if (origRazaosocial == null) {
          if (other.origRazaosocial != null) return false;
       }
      if (origUuidinstalacao == null) {
          if (other.origUuidinstalacao != null) return false;
       }
      if (remContrachave == null) {
          if (other.remContrachave != null) return false;
       }
      if (remCnpjcpf == null) {
          if (other.remCnpjcpf != null) return false;
       }
      if (remEmail == null) {
          if (other.remEmail != null) return false;
       }
      if (remFone == null) {
          if (other.remFone != null) return false;
       }
      if (remRazaosocial == null) {
          if (other.remRazaosocial != null) return false;
       }
      if (remUuidinstalacao == null) {
          if (other.remUuidinstalacao != null) return false;
       }
      return true;
   }
}