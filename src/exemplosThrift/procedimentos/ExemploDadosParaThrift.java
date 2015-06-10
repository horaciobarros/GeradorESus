package exemplosThrift.procedimentos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.procedimento.FichaProcedimentoChildThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.procedimento.FichaProcedimentoMasterThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import exemplosThrift.InformacoesEnvioDto;
import exemplosThrift.InformacoesEnvioExemplo;
import exemplosThrift.utils.ThriftSerializer;
import exemplosThrift.utils.ZipWriterExemplo;

public class ExemploDadosParaThrift {

	public static void main(String[] args) {
		// Passo 1: criar e preencher o thrift do atendimento;
		ExemploDadosParaThrift exemploConversaoDadosParaThrift = new ExemploDadosParaThrift();
		InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();
		FichaProcedimentoMasterThrift thriftAtendimento = exemploConversaoDadosParaThrift.converterParaThrift(informacoesEnvioDto);

		// Passo 2: serializar o thrift do atendimento;
		byte[] dadoSerializado = ThriftSerializer.serialize(thriftAtendimento);

		// Passo 3: coletar as informações do envio e das instalações;
		informacoesEnvioDto.setTipoDadoSerializado(7l);
		informacoesEnvioDto.setDadoSerializado(dadoSerializado);

		// Passo 4: preencher o thrift de transporte com as informações coletadas;
		DadoTransporteThrift dadoTransporteThrift = InformacoesEnvioExemplo.getInfoInstalacao(informacoesEnvioDto);

		// Passo 5: serializar o thrift de transporte e gerar o arquivo zip;
		ZipWriterExemplo.generateZip(dadoTransporteThrift);
	}

	/**
	 * Realiza a conversão dos dados de uma ficha para o Thrift.
	 */
	private FichaProcedimentoMasterThrift converterParaThrift(InformacoesEnvioDto informacoesEnvioDto) {
		FichaProcedimentoMasterThrift thriftProcedimentos = new FichaProcedimentoMasterThrift();

		String uuidFicha = UUID.randomUUID() + "";
		thriftProcedimentos.setUuidFicha(uuidFicha); // Utilizar o UUID da ficha;
		informacoesEnvioDto.setUuidDadoSerializado(uuidFicha);

		thriftProcedimentos.setAtendProcedimentos(this.converterAtendimentos());
		thriftProcedimentos.setHeaderTransport(this.converterInformacoesAtendimento(informacoesEnvioDto));

		thriftProcedimentos.setNumTotalAfericaoPa(1);
		thriftProcedimentos.setNumTotalAfericaoTemperatura(1);
		thriftProcedimentos.setNumTotalColetaMaterialParaExameLaboratorial(1);
		thriftProcedimentos.setNumTotalCurativoSimples(1);
		thriftProcedimentos.setNumTotalGlicemiaCapilar(1);
		thriftProcedimentos.setNumTotalMedicaoAltura(1);
		thriftProcedimentos.setNumTotalMedicaoPeso(1);

		thriftProcedimentos.setTpCdsOrigem(3);

		return thriftProcedimentos;
	}

	/**
	 * Converte as informações gerais do atendimento.
	 * 
	 * @param informacoesEnvioDto
	 */
	private UnicaLotacaoHeaderThrift converterInformacoesAtendimento(InformacoesEnvioDto informacoesEnvioDto) {
		UnicaLotacaoHeaderThrift headerThrift = new UnicaLotacaoHeaderThrift();

		Calendar dataAtendimento = Calendar.getInstance();
		dataAtendimento.set(2014, 11, 20);
		headerThrift.setDataAtendimento(dataAtendimento.getTimeInMillis());

		headerThrift.setCboCodigo_2002("223212");

		String cnes = "7381123";
		headerThrift.setCnes(cnes);
		informacoesEnvioDto.setCnesDadoSerializado(cnes);

		String codigoIbgeMunicipio = "4205407";
		headerThrift.setCodigoIbgeMunicipio(codigoIbgeMunicipio);
		informacoesEnvioDto.setCodIbge(codigoIbgeMunicipio);

		String ine = "0000406465";
		headerThrift.setIne(ine);
		informacoesEnvioDto.setIneDadoSerializado(ine);

		headerThrift.setProfissionalCNS("898001160660761");

		return headerThrift;
	}

	/**
	 * Realiza a conversão dos dados dos procedimentos realizados durante o atendimento.
	 */
	private List<FichaProcedimentoChildThrift> converterAtendimentos() {
		List<FichaProcedimentoChildThrift> listaProcedimentosAtendimento = new ArrayList<>();

		for (Integer numeroAtendimentos = 0; numeroAtendimentos < 2; numeroAtendimentos++) {
			FichaProcedimentoChildThrift atendimentoProcedimentoThrift = new FichaProcedimentoChildThrift();

			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.set(2014, 11, 20);
			atendimentoProcedimentoThrift.setDtNascimento(dataNascimento.getTimeInMillis());

			atendimentoProcedimentoThrift.setLocalAtendimento(1);
			atendimentoProcedimentoThrift.setNumCartaoSus("898001161218973");
			atendimentoProcedimentoThrift.setNumProntuario("43143");
			atendimentoProcedimentoThrift.setSexo(1);
			atendimentoProcedimentoThrift.setTurno(1);

			atendimentoProcedimentoThrift.setOutrosSiaProcedimentos(this.converterProcedimentosSia());
			atendimentoProcedimentoThrift.setProcedimentos(this.converterProcedimentos());

			listaProcedimentosAtendimento.add(atendimentoProcedimentoThrift);
		}

		return listaProcedimentosAtendimento;
	}

	/**
	 * Converte códigos gerais de procedimentos SIA.
	 */
	private List<String> converterProcedimentosSia() {
		List<String> siaList = new ArrayList<>();

		siaList.add("ABEX010"); // MAMOGRAFIA BILATERAL;
		siaList.add("ABEX007"); // HDL;
		siaList.add("ABEX009"); // LDL;

		return siaList;
	}

	/**
	 * Converte códigos gerais de procedimentos realizados.
	 */
	private List<String> converterProcedimentos() {
		List<String> procedimentosList = new ArrayList<>();

		procedimentosList.add("ABPG019"); // SUTURA SIMPLES;
		procedimentosList.add("ABEX004"); // ELETROCARDIOGRAMA;

		return procedimentosList;
	}

}
