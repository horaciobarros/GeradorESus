package esaude.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;

public class GeradorZip {
	static Logger log = Logger.getLogger(GeradorZip.class.getName());
	private int totalGeralFichas = 0;

	public void empacotaZip(List<DadoTransporteThrift> dadosTransportCadastroDomiciliar,
			List<DadoTransporteThrift> dadosTransportAtividadeColetiva,
			List<DadoTransporteThrift> dadosTransportCadastroIndividual,
			List<DadoTransporteThrift> dadosTransportVisitaDomiciliar,
			List<DadoTransporteThrift> dadosTransportAtendimentoIndividual,
			List<DadoTransporteThrift> dadosTransportAtendimentoOdontologico,
			List<DadoTransporteThrift> dadosTransportProcedimento,
			List<DadoTransporteThrift> dadosTransportConsumoAlimentar, String pathPadrao) {

		final File f = new File(pathPadrao + "\\esaude_exportacao" + getInstante() + ".zip");
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(f));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		if (out != null) {

			geraEntradaEmArquivoZip(out, dadosTransportCadastroIndividual, "cadastro_individual");
			geraEntradaEmArquivoZip(out, dadosTransportCadastroDomiciliar, "cadastro_domiciliar");
			geraEntradaEmArquivoZip(out, dadosTransportAtendimentoIndividual, "atendimento_individual");
			geraEntradaEmArquivoZip(out, dadosTransportAtendimentoOdontologico, "atendimento_odontologico");
			geraEntradaEmArquivoZip(out, dadosTransportAtividadeColetiva, "atividade_coletiva");
			geraEntradaEmArquivoZip(out, dadosTransportProcedimento, "ficha_procedimento");
			geraEntradaEmArquivoZip(out, dadosTransportVisitaDomiciliar, "visita_domiciliar");
			geraEntradaEmArquivoZip(out, dadosTransportConsumoAlimentar, "consumo_alimentar");
			log.warn("Total Geral de fichas enviadas: " + totalGeralFichas);
			try {
				out.closeEntry();
				out.close();
			} catch (IOException e) {
				log.error(e);
				e.printStackTrace();

			}

		}
	}

	private void geraEntradaEmArquivoZip(ZipOutputStream out, List<DadoTransporteThrift> dadosTransport,
			String nomeArquivo) {
		if (dadosTransport != null) {
			for (DadoTransporteThrift dado : dadosTransport) {
				log.warn(getInstante() + " Empacotando ficha: " + dado.getUuidDadoSerializado() + " - "
						+ dado.getDadoSerializado() + " - " + dado.getOriginadora().getCpfOuCnpj());
				byte[] data;
				try {
					String entryName = nomeArquivo + "_" + ZipWriter.resolveZipEntry(dado);
					out.putNextEntry(new ZipEntry(entryName));
					data = ThriftSerializer.serialize(dado);
					out.write(data);
					totalGeralFichas++;
				} catch (FileNotFoundException e1) {
					log.error(e1);
					e1.printStackTrace();
				} catch (IOException e) {
					log.error(e);
					e.printStackTrace();

				} catch (Exception e) {
					log.error(e);
					e.printStackTrace();

				}
			}
		}

	}

	private String getInstante() {
		Calendar c = Calendar.getInstance();
		String data = "_" + c.get(Calendar.DAY_OF_MONTH) + "_" + (c.get(Calendar.MONTH) + 1) + "_"
				+ c.get(Calendar.YEAR) + "_" + c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.MINUTE) + "_"
				+ c.get(Calendar.SECOND);
		return data;
	}

}
