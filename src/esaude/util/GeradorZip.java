package esaude.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import exemplosThrift.utils.ThriftSerializer;
import exemplosThrift.utils.ZipWriter;

public class GeradorZip {
	static Logger log = Logger.getLogger(GeradorZip.class.getName());

	public void empacotaZir(
			List<DadoTransporteThrift> dadosTransportCadastroDomiciliar) {

		final File f = new File("\\temp\\esaude_exportacao.zip");
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
					String entryName = "cadastro_domiciliar"
							+ ZipWriter.resolveZipEntry(dado);
					out.putNextEntry(new ZipEntry(entryName));
					data = ThriftSerializer.serialize(dado);
					out.write(data);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					log.error(e1);
				} catch (IOException e) {
					e.printStackTrace();
					log.error(e);

				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
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

	

}
