package esaude.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.procedimento.FichaProcedimentoChildThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.procedimento.FichaProcedimentoMasterThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;

public class ExemploDadosFormParaThrift {

	public static void main(String[] args) {
		// Passo 1: criar e preencher o thrift do atendimento;
		ExemploDadosFormParaThrift exemploDadosParaThrift = new ExemploDadosFormParaThrift();
		InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();
		ExemploFichaProcedimentoInformacoesGerais fichaProcedimento = new ExemploFichaProcedimentoInformacoesGerais();
		FichaProcedimentoMasterThrift thriftAtendimento = exemploDadosParaThrift.converterParaThrift(fichaProcedimento, informacoesEnvioDto);

		// Passo 2: serializar o thrift do atendimento;
		byte[] dadoSerializado = ThriftSerializer.serialize(thriftAtendimento);

		// Passo 3: coletar as informações do envio e das instalações;
		informacoesEnvioDto.setTipoDadoSerializado(7l);
		informacoesEnvioDto.setDadoSerializado(dadoSerializado);

		// Passo 4: preencher o thrift de transporte com as informações coletadas;
		DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio.getInfoInstalacao(informacoesEnvioDto);

		// Passo 5: serializar o thrift de transporte e gerar o arquivo zip;
		ZipWriter.generateZip(dadoTransporteThrift);
	}

	/**
	 * Realiza a conversão dos dados de uma ficha para o Thrift.
	 * 
	 * @param fichaProcedimento
	 * @return
	 */
	private FichaProcedimentoMasterThrift converterParaThrift(ExemploFichaProcedimentoInformacoesGerais fichaProcedimento, InformacoesEnvioDto informacoesEnvioDto) {
		FichaProcedimentoMasterThrift thriftProcedimentos = new FichaProcedimentoMasterThrift();

		String uuidFicha = UUID.randomUUID() + "";
		thriftProcedimentos.setUuidFicha(uuidFicha); // Utilizar o UUID da ficha;
		informacoesEnvioDto.setUuidDadoSerializado(uuidFicha);

		thriftProcedimentos.setAtendProcedimentos(this.converterAtendimentos(fichaProcedimento.getAtendimentos()));
		thriftProcedimentos.setHeaderTransport(this.converterInformacoesAtendimento(fichaProcedimento, informacoesEnvioDto));
		Integer afericaoPa = fichaProcedimento.getAfericaoPa();
		thriftProcedimentos.setNumTotalAfericaoPa(afericaoPa == null ? 0 : afericaoPa);

		Integer afericaoTemperatura = fichaProcedimento.getAfericaoTemperatura();
		thriftProcedimentos.setNumTotalAfericaoTemperatura(afericaoTemperatura == null ? 0 : afericaoTemperatura);

		Integer coletaMaterialParaExameLaboratorial = fichaProcedimento.getColetaMaterialParaExameLaboratorial();
		thriftProcedimentos.setNumTotalColetaMaterialParaExameLaboratorial(coletaMaterialParaExameLaboratorial == null ? 0 : coletaMaterialParaExameLaboratorial);

		Integer curativoSimples = fichaProcedimento.getCurativoSimples();
		thriftProcedimentos.setNumTotalCurativoSimples(curativoSimples == null ? 0 : curativoSimples);

		Integer glicemiaCapilar = fichaProcedimento.getGlicemiaCapilar();
		thriftProcedimentos.setNumTotalGlicemiaCapilar(glicemiaCapilar == null ? 0 : glicemiaCapilar);

		Integer medicaoAltura = fichaProcedimento.getMedicaoAltura();
		thriftProcedimentos.setNumTotalMedicaoAltura(medicaoAltura == null ? 0 : medicaoAltura);

		Integer medicaoPeso = fichaProcedimento.getMedicaoPeso();
		thriftProcedimentos.setNumTotalMedicaoPeso(medicaoPeso == null ? 0 : medicaoPeso);

		thriftProcedimentos.setTpCdsOrigem(3);

		return thriftProcedimentos;
	}

	/**
	 * Converte as informações gerais do atendimento.
	 * 
	 * @param fichaProcedimento
	 * @param informacoesEnvioDto
	 */
	private UnicaLotacaoHeaderThrift converterInformacoesAtendimento(ExemploFichaProcedimentoInformacoesGerais fichaProcedimento, InformacoesEnvioDto informacoesEnvioDto) {
		UnicaLotacaoHeaderThrift headerThrift = new UnicaLotacaoHeaderThrift();

		Date dataAtendimento = fichaProcedimento.getDataAtendimento();
		if (dataAtendimento != null) {
			headerThrift.setDataAtendimento(dataAtendimento.getTime());
		}

		headerThrift.setCboCodigo_2002(fichaProcedimento.getCbo2002Profissional());

		String cnes = fichaProcedimento.getCnes();
		headerThrift.setCnes(cnes);
		informacoesEnvioDto.setCnesDadoSerializado(cnes);

		String codigoIbgeMunicipio = fichaProcedimento.getCodigoIbgeMunicipio();
		headerThrift.setCodigoIbgeMunicipio(codigoIbgeMunicipio);
		informacoesEnvioDto.setCodIbge(codigoIbgeMunicipio);

		String ine = fichaProcedimento.getIne();
		headerThrift.setIne(ine);
		informacoesEnvioDto.setIneDadoSerializado(ine);

		headerThrift.setProfissionalCNS(fichaProcedimento.getCnsProfissional());

		return headerThrift;
	}

	/**
	 * Realiza a conversão dos dados dos procedimentos realizados durante o atendimento.
	 * 
	 * @param atendimentos
	 */
	private List<FichaProcedimentoChildThrift> converterAtendimentos(List<ExemploFichaProcedimento> atendimentos) {
		List<FichaProcedimentoChildThrift> listaProcedimentosAtendimento = new ArrayList<>();

		for (ExemploFichaProcedimento atendimento : atendimentos) {
			FichaProcedimentoChildThrift atendimentoProcedimentoThrift = new FichaProcedimentoChildThrift();

			Date dataNascimentoCidadao = atendimento.getDataNascimento();
			if (dataNascimentoCidadao != null) {
				atendimentoProcedimentoThrift.setDtNascimento(dataNascimentoCidadao.getTime());
			}

			atendimentoProcedimentoThrift.setLocalAtendimento(this.converterLocalAtendimento(atendimento.getLocalAtendimento()));
			atendimentoProcedimentoThrift.setNumCartaoSus(atendimento.getCns());
			atendimentoProcedimentoThrift.setNumProntuario(atendimento.getNumeroProntuario());
			atendimentoProcedimentoThrift.setSexo(this.converterSexo(atendimento.getSexo()));
			atendimentoProcedimentoThrift.setTurno(this.converterTurno(atendimento.getTurno()));

			atendimentoProcedimentoThrift.setProcedimentos(this.converterProcedimentos(atendimento.getProcedimentos()));

			listaProcedimentosAtendimento.add(atendimentoProcedimentoThrift);
		}

		return listaProcedimentosAtendimento;
	}

	/**
	 * Converte códigos gerais de procedimentos realizados.
	 * 
	 * @param procedimentos
	 */
	private List<String> converterProcedimentos(List<String> procedimentos) {
		List<String> procedimentosList = new ArrayList<>();

		for (String procedimento : procedimentos) {
			procedimentosList.add(procedimento);
		}

		return procedimentosList;
	}

	/**
	 * Exemplo de conversão do local de atendimento.
	 * 
	 * @param localAtendimento
	 * @return O código do local de atendimento no thrift.
	 */
	private Long converterLocalAtendimento(String localAtendimento) {
		if (localAtendimento.equals("UNIDADE MOVEL")) {
			return 2l;
		}

		return 1l;
	}

	/**
	 * Exemplo de conversão do sexo do cidadão.
	 * 
	 * @param sexo
	 * @return O código do sexo do cidadão.
	 */
	private Long converterSexo(String sexo) {
		if (sexo.equals("MASCULINO")) {
			return 0l;
		} else if (sexo.equals("FEMININO")) {
			return 1l;
		}

		return 3l;
	}

	/**
	 * Exemplo de conversão do turno do atendimento.
	 * 
	 * @param turno
	 * @return O código do turno do atendimento.
	 */
	private long converterTurno(String turno) {
		if (turno.equals("MATUTINO")) {
			return 1l;
		} else if (turno.equals("VESPERTINO")) {
			return 2L;
		} else if (turno.equals("NOTURNO")) {
			return 3l;
		}

		return 1l;
	}
}
