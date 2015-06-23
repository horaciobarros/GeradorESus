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
@Table(name="esus_atividade_coletiva")
public class EsusAtividadeColetiva implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="dt_atividade")
   private Date dtAtividade;

   @Column(name="num_participantes_programados")
   private Long numParticipantesProgramados;

   @Column(name="local_atividade")
   private String localAtividade;

   @Column(name="hr_inicio")
   private String hrInicio;

   @Column(name="hr_fim")
   private String hrFim;

   @Column(name="inep")
   private Long inep;

   @Column(name="cns_responsavel")
   private String cnsResponsavel;

   @Column(name="cnes_unidade")
   private String cnesUnidade;

   @Column(name="ine_equipe")
   private String ineEquipe;

   @Column(name="num_participantes")
   private Long numParticipantes;

   @Column(name="num_avaliacoes")
   private Long numAvaliacoes;

   @ManyToOne
   @JoinColumn(name = "id_tipoatividadecoletiva")
   private EsusTipoatividadecoletiva esusTipoatividadecoletiva;

   @Column(name="ibge_municipio")
   private String ibgeMunicipio;

   @Column(name="st_envio")
   private Long stEnvio;

   @Column(name="dt_envio")
   private Date dtEnvio;
   
   private transient List<EsusAtividadeColetivaParticipantes> participantes;
   
   private transient List<EsusAtividadeColetivaProfissional> profissionais;

   public List<EsusAtividadeColetivaParticipantes> getParticipantes() {
	return participantes;
}
public void setParticipantes(
		List<EsusAtividadeColetivaParticipantes> participantes) {
	this.participantes = participantes;
}
public List<EsusAtividadeColetivaProfissional> getProfissionais() {
	return profissionais;
}
public void setProfissionais(
		List<EsusAtividadeColetivaProfissional> profissionais) {
	this.profissionais = profissionais;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }

   public Date getDtAtividade() { 
      return dtAtividade;
   }
   public void  setDtAtividade(Date dtAtividade) { 
      this.dtAtividade = dtAtividade;
   }

   public Long getNumParticipantesProgramados() { 
      return numParticipantesProgramados;
   }
   public void  setNumParticipantesProgramados(Long numParticipantesProgramados) { 
      this.numParticipantesProgramados = numParticipantesProgramados;
   }

   public String getLocalAtividade() { 
      return localAtividade;
   }
   public void  setLocalAtividade(String localAtividade) { 
      this.localAtividade = localAtividade;
   }

   public String getHrInicio() { 
      return hrInicio;
   }
   public void  setHrInicio(String hrInicio) { 
      this.hrInicio = hrInicio;
   }

   public String getHrFim() { 
      return hrFim;
   }
   public void  setHrFim(String hrFim) { 
      this.hrFim = hrFim;
   }

   public Long getInep() { 
      return inep;
   }
   public void  setInep(Long inep) { 
      this.inep = inep;
   }

   public String getCnsResponsavel() { 
      return cnsResponsavel;
   }
   public void  setCnsResponsavel(String cnsResponsavel) { 
      this.cnsResponsavel = cnsResponsavel;
   }

   public String getCnesUnidade() { 
      return cnesUnidade;
   }
   public void  setCnesUnidade(String cnesUnidade) { 
      this.cnesUnidade = cnesUnidade;
   }

   public String getIneEquipe() { 
      return ineEquipe;
   }
   public void  setIneEquipe(String ineEquipe) { 
      this.ineEquipe = ineEquipe;
   }

   public Long getNumParticipantes() { 
      return numParticipantes;
   }
   public void  setNumParticipantes(Long numParticipantes) { 
      this.numParticipantes = numParticipantes;
   }

   public Long getNumAvaliacoes() { 
      return numAvaliacoes;
   }
   public void  setNumAvaliacoes(Long numAvaliacoes) { 
      this.numAvaliacoes = numAvaliacoes;
   }


   public  EsusTipoatividadecoletiva  getEsusTipoatividadecoletiva() { 
      return esusTipoatividadecoletiva;
   }
   public void  setEsusTipoatividadecoletiva(EsusTipoatividadecoletiva esusTipoatividadecoletiva) { 
      this.esusTipoatividadecoletiva = esusTipoatividadecoletiva;
   }

   public String getIbgeMunicipio() { 
      return ibgeMunicipio;
   }
   public void  setIbgeMunicipio(String ibgeMunicipio) { 
      this.ibgeMunicipio = ibgeMunicipio;
   }

   public Long getStEnvio() { 
      return stEnvio;
   }
   public void  setStEnvio(Long stEnvio) { 
      this.stEnvio = stEnvio;
   }

   public Date getDtEnvio() { 
      return dtEnvio;
   }
   public void  setDtEnvio(Date dtEnvio) { 
      this.dtEnvio = dtEnvio;
   }


}