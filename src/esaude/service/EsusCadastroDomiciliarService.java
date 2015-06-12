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
import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.EnderecoLocalPermanenciaThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.FamiliaRowThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.HeaderCdsCadastroThrift;
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
			try {
				CadastroDomiciliarThrift thriftCadastroDomiciliar = converterParaThrift(cad);

				InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

				byte[] dadoSerializado;

				// Passo 2: serializar o thrift
				dadoSerializado = ThriftSerializer
						.serialize(thriftCadastroDomiciliar);

				// Passo 3: coletar as informações do envio
				informacoesEnvioDto.setTipoDadoSerializado(7l);
				informacoesEnvioDto.setDadoSerializado(dadoSerializado);
				informacoesEnvioDto.setUuidDadoSerializado(cad.getId()
						.toString());
				informacoesEnvioDto.setIneDadoSerializado(cad.getIneEquipe());
				informacoesEnvioDto
						.setCnesDadoSerializado(cad.getCnesUnidade());

				// Passo 4: preencher o thrift de transporte com as
				// informadosçõeso
				// coletadas;
				DadoTransporteThrift dadoTransporteThrift = InformacoesEnvioExemplo
						.getInfoInstalacao(informacoesEnvioDto);

				dados.add(dadoTransporteThrift);
				System.out.println("Gerando cadastro -- " + cad.getNuDomicilio() + " " + cad.getNoLogradouro());

			} catch (Exception e) {

			}
		}
		// Passo 5: serializar o thrift de transporte e gerar o arquivo zip;
		empacotaZipCadastroDomiciliar(dados);
	}

	private CadastroDomiciliarThrift converterParaThrift(
			EsusCadastroDomiciliar cad) {
		CadastroDomiciliarThrift cadastroDomiciliarThrift = new CadastroDomiciliarThrift();

		cadastroDomiciliarThrift.setUuid(cad.getId().toString());

		cadastroDomiciliarThrift.setAnimaisNoDomicilio(null);
		cadastroDomiciliarThrift.setCondicaoMoradia(null);

		// Dados gerais
		HeaderCdsCadastroThrift dadosGerais = new HeaderCdsCadastroThrift();
		dadosGerais.setCnesUnidadeSaude(cad.getCnesUnidade());
		dadosGerais.setCnesUnidadeSaudeIsSet(true); // ?? TODO
		dadosGerais.setCnsProfissional(cad.getCnsProfissional());
		dadosGerais.setCodigoIbgeMunicipio(cad.getCoMunicipio());
		dadosGerais.setCodigoIbgeMunicipioIsSet(false); // ?? TODO
		dadosGerais.setDataAtendimento(cad.getDtCadastro().getTime());
		dadosGerais.setDataAtendimentoIsSet(false); // ?? TODO
		dadosGerais.setIneEquipe(cad.getIneEquipe());
		dadosGerais.setIneEquipeIsSet(false);
		dadosGerais.setMicroarea(cad.getMicroarea());
		dadosGerais.setMicroareaIsSet(false);// ?? TODO
		cadastroDomiciliarThrift.setDadosGerais(dadosGerais);

		// Endereco
		EnderecoLocalPermanenciaThrift endereco = new EnderecoLocalPermanenciaThrift();
		endereco.setBairro(cad.getNoBairro());
		endereco.setBairroIsSet(false);// ?? TODO
		endereco.setCep(cad.getNuCep());
		endereco.setCepIsSet(false);// ?? TODO
		endereco.setCodigoIbgeMunicipio(cad.getCoMunicipio());
		endereco.setCodigoIbgeMunicipioIsSet(false);// ?? TODO
		endereco.setComplemento(cad.getDsComplemento());
		endereco.setComplementoIsSet(false);// ?? TODO
		endereco.setNomeLogradouro(cad.getNoLogradouro());
		endereco.setNomeLogradouroIsSet(false);// ?? TODO
		endereco.setNumero(cad.getNuDomicilio());
		endereco.setNumeroIsSet(false);// ?? TODO
		endereco.setTelReferencial(cad.getNuFoneReferencia());
		endereco.setTelReferencialIsSet(false);// ?? TODO
		endereco.setTelResidencial(cad.getNuFoneResidencia());
		endereco.setTelResidencialIsSet(false);// ?? TODO
		endereco.setTipoLogradouroNumeroDne(cad.getTpLogradouro().toString());
		endereco.setTipoLogradouroNumeroDneIsSet(false);// ?? TODO
		cadastroDomiciliarThrift.setEnderecoLocalPermanencia(endereco);

		FamiliaRowThrift familia = new FamiliaRowThrift();
		if (cad.getQtMembrosFamilia() != null) {
			familia.setNumeroMembrosFamilia(Integer.parseInt(Long.toString(cad
					.getQtMembrosFamilia())));
		}
		familia.setNumeroCnsResponsavelIsSet(false);// ?? TODO
		if (cad.getIdProntuarioResponsavel() != null) {
			familia.setNumeroProntuario(Long.toString(cad
					.getIdProntuarioResponsavel()));
		}
		familia.setNumeroProntuarioIsSet(false);// ?? TODO
		try {
			familia.setRendaFamiliar(cad.getCoCdsRendaFamiliar());
		} catch (Exception e) {

		}
		familia.setRendaFamiliarIsSet(false);// ?? TODO
		if (cad.getDtMudanca() != null) {
			familia.setResideDesde(cad.getDtMudanca().getTime());
		}

		familia.setStMudancaIsSet(false);// ?? TODO
		List<FamiliaRowThrift> familias = new ArrayList<FamiliaRowThrift>();
		familias.add(familia);
		cadastroDomiciliarThrift.setFamilias(familias);

		cadastroDomiciliarThrift.setFichaAtualizada(true);
		cadastroDomiciliarThrift.setFichaAtualizadaIsSet(true);
		if (cad.getQuantidadeAnimais() != null) {
			cadastroDomiciliarThrift.setQuantosAnimaisNoDomicilio(Long
					.toString(cad.getQuantidadeAnimais()));
		}
		cadastroDomiciliarThrift.setStAnimaisNoDomicilioIsSet(false);// ?? TODO
		cadastroDomiciliarThrift.setStAnimaisNoDomicilio(false);// ?? TODO
		cadastroDomiciliarThrift
				.setStatusTermoRecusaCadastroDomiciliarAtencaoBasicaIsSet(false);
		cadastroDomiciliarThrift
				.setStatusTermoRecusaCadastroDomiciliarAtencaoBasica(cad
						.getStRecusaCadastro());
		cadastroDomiciliarThrift.setTpCdsOrigemIsSet(false);
		cadastroDomiciliarThrift.setTpCdsOrigem(0);

		return cadastroDomiciliarThrift;
	}

	private void empacotaZipCadastroDomiciliar(
			List<DadoTransporteThrift> dadosTransport) {

		final File f = new File("c:\\temp\\cadastro_domiciliar.zip");
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

	public static byte[] serialize(TBase<?, ? extends TFieldIdEnum> thrift)
			throws TException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TIOStreamTransport transport = new TIOStreamTransport(baos);
		TBinaryProtocol protocol = new TBinaryProtocol(transport);
		thrift.write(protocol);
		return baos.toByteArray();
	}

	public static void unserialize(byte[] data,
			TBase<?, ? extends TFieldIdEnum> thrift) throws TException {
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
					return value.equals("") ? defaultValue : new Long(
							(String) value);
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
					return value.equals("") ? defaultValue : new Integer(
							(String) value);
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
