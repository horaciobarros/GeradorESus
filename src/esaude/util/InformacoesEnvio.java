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

			dadoTransporteThrift.setTipoDadoSerializado(informacoesEnvioDto
					.getTipoDadoSerializado());

			dadoTransporteThrift.setCnesDadoSerializado(informacoesEnvioDto
					.getCnesDadoSerializado());

			dadoTransporteThrift.setDadoSerializado(informacoesEnvioDto
					.getDadoSerializado());

			DadoInstalacaoThrift originadora = new DadoInstalacaoThrift();
			originadora.setContraChave(esusRegistro.getOrigContrachave());
			originadora.setCpfOuCnpj(esusRegistro.getOrigCnpjcpf());
			originadora.setEmail(esusRegistro.getOrigEmail());
			originadora.setFone(esusRegistro.getOrigFone());
			originadora.setNomeOuRazaoSocial(esusRegistro.getOrigRazaosocial());
			originadora.setUuidInstalacao(esusRegistro.getOrigUuidinstalacao());
			dadoTransporteThrift.setOriginadora(originadora);

			DadoInstalacaoThrift remetente = new DadoInstalacaoThrift();
			remetente.setContraChave(esusRegistro.getRemContrachave());
			remetente.setCpfOuCnpj(esusRegistro.getRemCnpjcpf());
			remetente.setEmail(esusRegistro.getRemEmail());
			remetente.setFone(esusRegistro.getRemFone());
			remetente.setNomeOuRazaoSocial(esusRegistro.getRemRazaosocial());
			remetente.setUuidInstalacao(esusRegistro.getRemUuidinstalacao());
			dadoTransporteThrift.setRemetente(remetente);

			// Opcionais;
			String codIbge = informacoesEnvioDto.getCodIbge();
			dadoTransporteThrift.setCodIbge(codIbge == null ? "" : codIbge);

			String ineDadoSerializado = informacoesEnvioDto
					.getIneDadoSerializado();
			dadoTransporteThrift
					.setIneDadoSerializado(ineDadoSerializado == null ? ""
							: ineDadoSerializado);


			long numLote = 1;
			dadoTransporteThrift.setNumLote(numLote);

			VersaoThrift versaoThrift = new VersaoThrift();
			versaoThrift.setMajor(2);
			versaoThrift.setMinor(0);
			versaoThrift.setRevision(0);
			dadoTransporteThrift.setVersao(versaoThrift);
		} catch (Exception e) {
			log.error(e);
		}

		return dadoTransporteThrift;
	}
}
