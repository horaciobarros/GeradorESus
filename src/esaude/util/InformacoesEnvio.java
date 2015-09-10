package esaude.util;

import org.apache.log4j.Logger;

import br.gov.saude.esus.transport.common.api.configuracaodestino.VersaoThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoInstalacaoThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.model.EsusRegistro;
import esaude.service.EsusCadastroDomiciliarService;

public class InformacoesEnvio {
	static Logger log = Logger.getLogger(EsusCadastroDomiciliarService.class
			.getName());

	public static DadoTransporteThrift getInfoInstalacao(
			InformacoesEnvioDto informacoesEnvioDto, EsusRegistro esusRegistro) {
		DadoTransporteThrift dadoTransporteThrift = new DadoTransporteThrift();
		dadoTransporteThrift.setUuidDadoSerializado(informacoesEnvioDto
				.getUuidDadoSerializado());
		
		
		try {
			
			// Obrigatóos;
			dadoTransporteThrift.setUuidDadoSerializado(informacoesEnvioDto
					.getUuidDadoSerializado());
			dadoTransporteThrift.setUuidDadoSerializadoIsSet(true);

			dadoTransporteThrift.setTipoDadoSerializado(informacoesEnvioDto
					.getTipoDadoSerializado());
			dadoTransporteThrift.setTipoDadoSerializadoIsSet(true);

			dadoTransporteThrift.setCnesDadoSerializado(informacoesEnvioDto
					.getCnesDadoSerializado());
			dadoTransporteThrift.setCnesDadoSerializadoIsSet(true);

			dadoTransporteThrift.setDadoSerializado(informacoesEnvioDto
					.getDadoSerializado());
			dadoTransporteThrift.setDadoSerializadoIsSet(true);

			DadoInstalacaoThrift originadora = new DadoInstalacaoThrift();
			originadora.setContraChave(esusRegistro.getOrigContrachave());
			originadora.setContraChaveIsSet(true);
			originadora.setCpfOuCnpj(esusRegistro.getOrigCnpjcpf());
			originadora.setCpfOuCnpjIsSet(true);
			originadora.setEmail(esusRegistro.getOrigEmail());
			originadora.setEmailIsSet(true);
			originadora.setFone(esusRegistro.getOrigFone());
			originadora.setFoneIsSet(true);
			originadora.setNomeOuRazaoSocial(esusRegistro.getOrigRazaosocial());
			originadora.setNomeOuRazaoSocialIsSet(true);
			originadora.setUuidInstalacao(esusRegistro.getOrigUuidinstalacao());
			originadora.setUuidInstalacaoIsSet(true);
			dadoTransporteThrift.setOriginadora(originadora);
			dadoTransporteThrift.setOriginadoraIsSet(true);

			DadoInstalacaoThrift remetente = new DadoInstalacaoThrift();
			remetente.setContraChave(esusRegistro.getRemContrachave());
			remetente.setContraChaveIsSet(true);
			remetente.setCpfOuCnpj(esusRegistro.getRemCnpjcpf());
			remetente.setCpfOuCnpjIsSet(true);
			remetente.setEmail(esusRegistro.getRemEmail());
			remetente.setEmailIsSet(true);
			remetente.setFone(esusRegistro.getRemFone());
			remetente.setFoneIsSet(true);
			remetente.setNomeOuRazaoSocial(esusRegistro.getRemRazaosocial());
			remetente.setNomeOuRazaoSocialIsSet(true);
			remetente.setUuidInstalacao(esusRegistro.getRemUuidinstalacao());
			remetente.setUuidInstalacaoIsSet(true);
			dadoTransporteThrift.setRemetente(remetente);
			dadoTransporteThrift.setRemetenteIsSet(true);

			// Opcionais;
			String codIbge = informacoesEnvioDto.getCodIbge();
			dadoTransporteThrift.setCodIbge(codIbge == null ? "" : codIbge);
			dadoTransporteThrift.setCodIbgeIsSet(true);
			
			String ineDadoSerializado = informacoesEnvioDto
					.getIneDadoSerializado();
			dadoTransporteThrift
					.setIneDadoSerializado(ineDadoSerializado == null ? ""
							: ineDadoSerializado);
			dadoTransporteThrift.setIneDadoSerializadoIsSet(true);


			long numLote = 2; 
			dadoTransporteThrift.setNumLote(numLote);
			dadoTransporteThrift.setNumLoteIsSet(true);
			
			VersaoThrift versaoThrift = new VersaoThrift();
			versaoThrift.setMajor(2);
			versaoThrift.setMinor(0);
			versaoThrift.setRevision(0);
			dadoTransporteThrift.setVersao(versaoThrift);
			dadoTransporteThrift.setVersaoIsSet(true);
		} catch (Exception e) {
			log.error(e);
		}

		return dadoTransporteThrift;
	}
}
