package esaude.service;

import esaude.dao.EsusCadastroDomiciliarDao;
import esaude.model.EsusCadastroDomiciliar;
import exemplosThrift.InformacoesEnvioDto;
import exemplosThrift.InformacoesEnvioExemplo;
import exemplosThrift.utils.ThriftSerializer;
import exemplosThrift.utils.ZipWriterExemplo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.CadastroDomiciliarThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.procedimento.FichaProcedimentoChildThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.procedimento.FichaProcedimentoMasterThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;

public class EsusCadastroDomiciliarService {
	private EsusCadastroDomiciliarDao dao = new EsusCadastroDomiciliarDao();

	public List<EsusCadastroDomiciliar> findNaoEnvidados() {
		return dao.findNaoEnviados();
	}

	public void processaArquivo() {

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		for (EsusCadastroDomiciliar cad : dao.findNaoEnviados()) {
			CadastroDomiciliarThrift thriftCadastroDomiciliar = converterParaThrift(cad);

			InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

			byte[] dadoSerializado;

			// Passo 2: serializar o thrift
			dadoSerializado = ThriftSerializer
					.serialize(thriftCadastroDomiciliar);

			// Passo 3: coletar as informações do envio
			informacoesEnvioDto.setTipoDadoSerializado(7l);
			informacoesEnvioDto.setDadoSerializado(dadoSerializado);

			// Passo 4: preencher o thrift de transporte com as informadosçõeso
			// coletadas;
			DadoTransporteThrift dadoTransporteThrift = InformacoesEnvioExemplo
					.getInfoInstalacao(informacoesEnvioDto);
			dados.add(dadoTransporteThrift);
		}
		// Passo 5: serializar o thrift de transporte e gerar o arquivo zip;
		empacotaZipCadastroDomiciliar(dados);
	}

	private CadastroDomiciliarThrift converterParaThrift(
			EsusCadastroDomiciliar cad) {
		CadastroDomiciliarThrift cadastroDomiciliar = new CadastroDomiciliarThrift();

		// String uuidFicha = UUID.randomUUID() + "";
		// informacoesEnvioDto.setUuidDadoSerializado(uuidFicha);
		cadastroDomiciliar.setUuid(cad.getId().toString());
		cadastroDomiciliar.setAnimaisNoDomicilio(null);
		cadastroDomiciliar.setCondicaoMoradia(null);
		cadastroDomiciliar.setDadosGerais(null);
		cadastroDomiciliar.setEnderecoLocalPermanencia(null);
		cadastroDomiciliar.setFamilias(null);
		cadastroDomiciliar.setFichaAtualizadaIsSet(true);
		cadastroDomiciliar.setFichaAtualizada(true);
		cadastroDomiciliar.setQuantosAnimaisNoDomicilio(null);
		cadastroDomiciliar.setStAnimaisNoDomicilioIsSet(false);
		cadastroDomiciliar.setStAnimaisNoDomicilio(false);
		cadastroDomiciliar
				.setStatusTermoRecusaCadastroDomiciliarAtencaoBasicaIsSet(false);
		cadastroDomiciliar
				.setStatusTermoRecusaCadastroDomiciliarAtencaoBasica(false);
		cadastroDomiciliar.setTpCdsOrigemIsSet(false);
		cadastroDomiciliar.setTpCdsOrigem(0);

		return cadastroDomiciliar;
	}

	private void empacotaZipCadastroDomiciliar(
			List<DadoTransporteThrift> dadosTransport) {

		final File f = new File("c:\\temp\\cidadaos_exemplo.zip");
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(f));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		if (out != null) {
			for (DadoTransporteThrift dado : dadosTransport) {
				byte[] data;
				try {
					String entryName = ZipWriterExemplo.resolveZipEntry(dado);
					out.putNextEntry(new ZipEntry(entryName));
					data = ThriftSerializer.serialize(dado);
					out.write(data);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				out.closeEntry();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static byte[] serialize(TBase<?, ? extends TFieldIdEnum> thrift) throws TException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TIOStreamTransport transport = new TIOStreamTransport(baos);
        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        thrift.write(protocol);
        return baos.toByteArray();
    }

    public static void unserialize(byte[] data, TBase<?, ? extends TFieldIdEnum> thrift) throws TException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        TIOStreamTransport transport = new TIOStreamTransport(bais);
        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        thrift.read(protocol);
    }

    public static Long castToLong(Object value, Long defaultValue) {
        if (value != null) {
            if (value instanceof Long) {
                return (Long) value;
            } else if (value instanceof Number) {
                return new Long(((Number) value).longValue());
            } else if (value instanceof String) {
                try {
                    return value.equals("") ? defaultValue : new Long((String) value);
                } catch (NumberFormatException exn) {
                    return defaultValue;
                }
            } else if (value instanceof BigInteger) {
                return ((BigInteger) value).longValue();
            } else if (value instanceof BigDecimal) {
                return ((BigDecimal) value).longValue();
            }
        }
        return defaultValue;
    }

    public static Long castToLong(Object value) {
        return castToLong(value, null);
    }

    public static boolean isBlank(String text) {
        if (text != null && text.length() > 0) {
            for (int i = 0, iSize = text.length(); i < iSize; i++) {
                if (text.charAt(i) != ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isNotBlank(String nome) {
        return !isBlank(nome);
    }

    public static Integer castToInteger(Object value, Integer defaultValue) {
        if (value != null) {
            if (value instanceof Integer) {
                return (Integer) value;
            } else if (value instanceof Number) {
                return new Integer(((Number) value).intValue());
            } else if (value instanceof BigInteger) {
                return ((BigInteger) value).intValue();
            } else if (value instanceof BigDecimal) {
                return ((BigDecimal) value).intValue();
            } else if (value instanceof String) {
                try {
                    return value.equals("") ? defaultValue : new Integer((String) value);
                } catch (NumberFormatException exn) {
                    return defaultValue;
                }
            }
        }
        return defaultValue;
    }

    public static Integer castToInteger(Object value) {
        return castToInteger(value, null);
    }


}
