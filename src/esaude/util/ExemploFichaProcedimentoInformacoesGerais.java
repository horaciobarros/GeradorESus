package esaude.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExemploFichaProcedimentoInformacoesGerais {

	// Dados gerais;
	private Date dataAtendimento;
	private String cbo2002Profissional;
	private String cnes;
	private String codigoIbgeMunicipio;
	private String ine;
	private String cnsProfissional;

	private List<ExemploFichaProcedimento> atendimentos;

	// Dados do atendimento;
	private Integer afericaoPa;
	private Integer glicemiaCapilar;
	private Integer afericaoTemperatura;
	private Integer medicaoAltura;
	private Integer curativoSimples;
	private Integer medicaoPeso;
	private Integer coletaMaterialParaExameLaboratorial;

	public ExemploFichaProcedimentoInformacoesGerais() {
		this.cnsProfissional = "898000390514000";
		this.cbo2002Profissional = "22050";
		this.cnes = "0019771";
		this.ine = "";
		
		Calendar dataAtendimento = Calendar.getInstance();
		dataAtendimento.set(2014, 11, 20);
		this.dataAtendimento = dataAtendimento.getTime();
		this.codigoIbgeMunicipio = "4205407";

		this.atendimentos = new ArrayList<>();
		ExemploFichaProcedimento fichaProcedimentoAtendimento = new ExemploFichaProcedimento();
		this.atendimentos.add(fichaProcedimentoAtendimento);

		this.afericaoPa = 1;
		this.glicemiaCapilar = 2;
		this.afericaoPa = 0;
		this.afericaoTemperatura = 0;
		this.medicaoAltura = 1;
		this.medicaoPeso = 1;
		this.curativoSimples = 1;
		this.coletaMaterialParaExameLaboratorial = 0;
	}

	public String getCnsProfissional() {
		return cnsProfissional;
	}

	public void setCnsProfissional(String cnsProfissional) {
		this.cnsProfissional = cnsProfissional;
	}

	public String getCbo2002Profissional() {
		return cbo2002Profissional;
	}

	public void setCbo2002Profissional(String cbo2002Profissional) {
		this.cbo2002Profissional = cbo2002Profissional;
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	public String getIne() {
		return ine;
	}

	public void setIne(String ine) {
		this.ine = ine;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getCodigoIbgeMunicipio() {
		return codigoIbgeMunicipio;
	}

	public void setCodigoIbgeMunicipio(String codigoIbgeMunicipio) {
		this.codigoIbgeMunicipio = codigoIbgeMunicipio;
	}

	public List<ExemploFichaProcedimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<ExemploFichaProcedimento> atendimentosProcedimento) {
		this.atendimentos = atendimentosProcedimento;
	}

	public Integer getAfericaoPa() {
		return afericaoPa;
	}

	public void setAfericaoPa(Integer afericaoPa) {
		this.afericaoPa = afericaoPa;
	}

	public Integer getGlicemiaCapilar() {
		return glicemiaCapilar;
	}

	public void setGlicemiaCapilar(Integer glicemiaCapilar) {
		this.glicemiaCapilar = glicemiaCapilar;
	}

	public Integer getAfericaoTemperatura() {
		return afericaoTemperatura;
	}

	public void setAfericaoTemperatura(Integer afericaoTemperatura) {
		this.afericaoTemperatura = afericaoTemperatura;
	}

	public Integer getMedicaoAltura() {
		return medicaoAltura;
	}

	public void setMedicaoAltura(Integer medicaoAltura) {
		this.medicaoAltura = medicaoAltura;
	}

	public Integer getCurativoSimples() {
		return curativoSimples;
	}

	public void setCurativoSimples(Integer curativoSimples) {
		this.curativoSimples = curativoSimples;
	}

	public Integer getMedicaoPeso() {
		return medicaoPeso;
	}

	public void setMedicaoPeso(Integer medicaoPeso) {
		this.medicaoPeso = medicaoPeso;
	}

	public Integer getColetaMaterialParaExameLaboratorial() {
		return coletaMaterialParaExameLaboratorial;
	}

	public void setColetaMaterialParaExameLaboratorial(Integer coletaMaterialParaExameLaboratorial) {
		this.coletaMaterialParaExameLaboratorial = coletaMaterialParaExameLaboratorial;
	}

}
