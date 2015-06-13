package esaude.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExemploFichaProcedimento {

	private Date dataNascimento;
	private String localAtendimento;
	private String cns;
	private String numeroProntuario;
	private String sexo;
	private String turno;
	private List<String> procedimentos;

	public ExemploFichaProcedimento() {
		this.numeroProntuario = "46236";
		this.cns = "898001145760000";
		this.sexo = "FEMININO";
		this.localAtendimento = "UBS";
		this.turno = "noturno";

		Calendar dataAtendimento = Calendar.getInstance();
		dataAtendimento.set(1984, 11, 20);
		this.dataNascimento = dataAtendimento.getTime();

		this.procedimentos = new ArrayList<>();
		this.procedimentos.add("SUTURA SIMPLES");
		this.procedimentos.add("TRIAGEM OFTALMOLOGICA");
	}

	public String getNumeroProntuario() {
		return numeroProntuario;
	}

	public void setNumeroProntuario(String numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(String localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public List<String> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<String> procedimentos) {
		this.procedimentos = procedimentos;
	}
}
