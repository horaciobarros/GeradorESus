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
@Table(name="esus_condicoesdesaude")
public class EsusCondicoesdesaude implements Serializable {

   private static final long serialVersionUID = 1L;

   @Column(name="descricaocausaInternacaoem12meses")
   private String descricaocausainternacaoem12meses;

   @Column(name="descricaooutracondicao1")
   private String descricaooutracondicao1;

   @Column(name="descricaooutracondicao2")
   private String descricaooutracondicao2;

   @Column(name="descricaooutracondicao3")
   private String descricaooutracondicao3;

   @Column(name="descricaoplantasmedicinaisusadas")
   private String descricaoplantasmedicinaisusadas;

   @Column(name="doencacardiaca")
   private int doencacardiaca;

   @Column(name="doencarespiratoria")
   private int doencarespiratoria;

   @Column(name="doencarins")
   private int doencarins;

   @Column(name="maternidadedereferencia")
   private String maternidadedereferencia;

   @Column(name="situacaopeso")
   private int situacaopeso;

   @Column(name="statusehdependentealcool")
   private boolean statusehdependentealcool;

   @Column(name="statusehdependenteoutrasdrogas")
   private boolean statusehdependenteoutrasdrogas;

   @Column(name="statusehfumante")
   private boolean statusehfumante;

   @Column(name="statusehgestante")
   private boolean statusehgestante;

   @Column(name="statusestaacabado")
   private boolean statusestaacabado;

   @Column(name="statusestadomiciliado")
   private boolean statusestadomiciliado;

   @Column(name="statustemdiabete")
   private boolean statustemdiabete;

   @Column(name="statustemdoencarespiratorio")
   private boolean statustemdoencarespiratorio;

   @Column(name="statustemhanseniase")
   private boolean statustemhanseniase;

   @Column(name="statustemhipertensaoarterial")
   private boolean statustemhipertensaoarterial;

   @Column(name="statustemtevecancer")
   private boolean statustemtevecancer;

   @Column(name="statustemtevedoencasrins")
   private boolean statustemtevedoencasrins;

   @Column(name="statustemtuberculose")
   private boolean statustemtuberculose;

   @Column(name="statusteveavcderrame")
   private boolean statusteveavcderrame;

   @Column(name="statustevedoencacardiaca")
   private boolean statustevedoencacardiaca;

   @Column(name="statusteveinfarto")
   private boolean statusteveinfarto;

   @Column(name="statuseveinternadoem12meses")
   private boolean statuseveinternadoem12meses;

   @Column(name="statustratamentopsiquicoouproblemamental")
   private boolean statustratamentopsiquicoouproblemamental;

   @Column(name="statususaoutrapraticasintegrativasoucomplementares")
   private boolean statususaoutrapraticasintegrativasoucomplementares;

   @Column(name="statususaplantasmedicinais")
   private boolean statususaplantasmedicinais;

   public String getDescricaocausainternacaoem12meses() { 
      return descricaocausainternacaoem12meses;
   }
   public void  setDescricaocausainternacaoem12meses(String descricaocausainternacaoem12meses) { 
      this.descricaocausainternacaoem12meses = descricaocausainternacaoem12meses;
   }

   public String getDescricaooutracondicao1() { 
      return descricaooutracondicao1;
   }
   public void  setDescricaooutracondicao1(String descricaooutracondicao1) { 
      this.descricaooutracondicao1 = descricaooutracondicao1;
   }

   public String getDescricaooutracondicao2() { 
      return descricaooutracondicao2;
   }
   public void  setDescricaooutracondicao2(String descricaooutracondicao2) { 
      this.descricaooutracondicao2 = descricaooutracondicao2;
   }

   public String getDescricaooutracondicao3() { 
      return descricaooutracondicao3;
   }
   public void  setDescricaooutracondicao3(String descricaooutracondicao3) { 
      this.descricaooutracondicao3 = descricaooutracondicao3;
   }

   public String getDescricaoplantasmedicinaisusadas() { 
      return descricaoplantasmedicinaisusadas;
   }
   public void  setDescricaoplantasmedicinaisusadas(String descricaoplantasmedicinaisusadas) { 
      this.descricaoplantasmedicinaisusadas = descricaoplantasmedicinaisusadas;
   }

   public int getDoencacardiaca() { 
      return doencacardiaca;
   }
   public void  setDoencacardiaca(int doencacardiaca) { 
      this.doencacardiaca = doencacardiaca;
   }

   public int getDoencarespiratoria() { 
      return doencarespiratoria;
   }
   public void  setDoencarespiratoria(int doencarespiratoria) { 
      this.doencarespiratoria = doencarespiratoria;
   }

   public int getDoencarins() { 
      return doencarins;
   }
   public void  setDoencarins(int doencarins) { 
      this.doencarins = doencarins;
   }

   public String getMaternidadedereferencia() { 
      return maternidadedereferencia;
   }
   public void  setMaternidadedereferencia(String maternidadedereferencia) { 
      this.maternidadedereferencia = maternidadedereferencia;
   }

   public int getSituacaopeso() { 
      return situacaopeso;
   }
   public void  setSituacaopeso(int situacaopeso) { 
      this.situacaopeso = situacaopeso;
   }

   public boolean getStatusehdependentealcool() { 
      return statusehdependentealcool;
   }
   public void  setStatusehdependentealcool(boolean statusehdependentealcool) { 
      this.statusehdependentealcool = statusehdependentealcool;
   }

   public boolean getStatusehdependenteoutrasdrogas() { 
      return statusehdependenteoutrasdrogas;
   }
   public void  setStatusehdependenteoutrasdrogas(boolean statusehdependenteoutrasdrogas) { 
      this.statusehdependenteoutrasdrogas = statusehdependenteoutrasdrogas;
   }

   public boolean getStatusehfumante() { 
      return statusehfumante;
   }
   public void  setStatusehfumante(boolean statusehfumante) { 
      this.statusehfumante = statusehfumante;
   }

   public boolean getStatusehgestante() { 
      return statusehgestante;
   }
   public void  setStatusehgestante(boolean statusehgestante) { 
      this.statusehgestante = statusehgestante;
   }

   public boolean getStatusestaacabado() { 
      return statusestaacabado;
   }
   public void  setStatusestaacabado(boolean statusestaacabado) { 
      this.statusestaacabado = statusestaacabado;
   }

   public boolean getStatusestadomiciliado() { 
      return statusestadomiciliado;
   }
   public void  setStatusestadomiciliado(boolean statusestadomiciliado) { 
      this.statusestadomiciliado = statusestadomiciliado;
   }

   public boolean getStatustemdiabete() { 
      return statustemdiabete;
   }
   public void  setStatustemdiabete(boolean statustemdiabete) { 
      this.statustemdiabete = statustemdiabete;
   }

   public boolean getStatustemdoencarespiratorio() { 
      return statustemdoencarespiratorio;
   }
   public void  setStatustemdoencarespiratorio(boolean statustemdoencarespiratorio) { 
      this.statustemdoencarespiratorio = statustemdoencarespiratorio;
   }

   public boolean getStatustemhanseniase() { 
      return statustemhanseniase;
   }
   public void  setStatustemhanseniase(boolean statustemhanseniase) { 
      this.statustemhanseniase = statustemhanseniase;
   }

   public boolean getStatustemhipertensaoarterial() { 
      return statustemhipertensaoarterial;
   }
   public void  setStatustemhipertensaoarterial(boolean statustemhipertensaoarterial) { 
      this.statustemhipertensaoarterial = statustemhipertensaoarterial;
   }

   public boolean getStatustemtevecancer() { 
      return statustemtevecancer;
   }
   public void  setStatustemtevecancer(boolean statustemtevecancer) { 
      this.statustemtevecancer = statustemtevecancer;
   }

   public boolean getStatustemtevedoencasrins() { 
      return statustemtevedoencasrins;
   }
   public void  setStatustemtevedoencasrins(boolean statustemtevedoencasrins) { 
      this.statustemtevedoencasrins = statustemtevedoencasrins;
   }

   public boolean getStatustemtuberculose() { 
      return statustemtuberculose;
   }
   public void  setStatustemtuberculose(boolean statustemtuberculose) { 
      this.statustemtuberculose = statustemtuberculose;
   }

   public boolean getStatusteveavcderrame() { 
      return statusteveavcderrame;
   }
   public void  setStatusteveavcderrame(boolean statusteveavcderrame) { 
      this.statusteveavcderrame = statusteveavcderrame;
   }

   public boolean getStatustevedoencacardiaca() { 
      return statustevedoencacardiaca;
   }
   public void  setStatustevedoencacardiaca(boolean statustevedoencacardiaca) { 
      this.statustevedoencacardiaca = statustevedoencacardiaca;
   }

   public boolean getStatusteveinfarto() { 
      return statusteveinfarto;
   }
   public void  setStatusteveinfarto(boolean statusteveinfarto) { 
      this.statusteveinfarto = statusteveinfarto;
   }

   public boolean getStatuseveinternadoem12meses() { 
      return statuseveinternadoem12meses;
   }
   public void  setStatuseveinternadoem12meses(boolean statuseveinternadoem12meses) { 
      this.statuseveinternadoem12meses = statuseveinternadoem12meses;
   }

   public boolean getStatustratamentopsiquicoouproblemamental() { 
      return statustratamentopsiquicoouproblemamental;
   }
   public void  setStatustratamentopsiquicoouproblemamental(boolean statustratamentopsiquicoouproblemamental) { 
      this.statustratamentopsiquicoouproblemamental = statustratamentopsiquicoouproblemamental;
   }

   public boolean getStatususaoutrapraticasintegrativasoucomplementares() { 
      return statususaoutrapraticasintegrativasoucomplementares;
   }
   public void  setStatususaoutrapraticasintegrativasoucomplementares(boolean statususaoutrapraticasintegrativasoucomplementares) { 
      this.statususaoutrapraticasintegrativasoucomplementares = statususaoutrapraticasintegrativasoucomplementares;
   }

   public boolean getStatususaplantasmedicinais() { 
      return statususaplantasmedicinais;
   }
   public void  setStatususaplantasmedicinais(boolean statususaplantasmedicinais) { 
      this.statususaplantasmedicinais = statususaplantasmedicinais;
   }

}