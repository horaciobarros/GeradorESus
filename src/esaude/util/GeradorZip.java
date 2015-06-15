package esaude.util;

import java.io.File;
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

	public void empacotaZir(
			List<DadoTransporteThrift> dadosTransportCadastroDomiciliar, String pathPadrao) {

		final File f = new File(pathPadrao + "\\esaude_exportacao" + getInstante() + ".zip");
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(f));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		if (out != null) {

			for (DadoTransporteThrift dado : dadosTransportCadastroDomiciliar) {
				byte[] data;
				try {
					String entryName = "cadastro_domiciliar_"
							+ ZipWriter.resolveZipEntry(dado);
					out.putNextEntry(new ZipEntry(entryName));
					data = ThriftSerializer.serialize(dado);
					out.write(data);
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
			try {
				out.closeEntry();
				out.close();
			} catch (IOException e) {
				log.error(e);
				e.printStackTrace();
				
			}
		}
	}

	private String getInstante() {
		Calendar c = Calendar.getInstance(); 
		String data = "_" + c.get(Calendar.DAY_OF_MONTH) + "_" + c.get(Calendar.MONTH) + "_" + c.get(Calendar.YEAR) + 
				"_" + c.get(Calendar.MINUTE) + "_" + c.get(Calendar.SECOND);
		return data;
	}

	

}
