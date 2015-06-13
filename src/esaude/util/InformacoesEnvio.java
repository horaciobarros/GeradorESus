package esaude.util;

import br.gov.saude.esus.transport.common.api.configuracaodestino.VersaoThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoInstalacaoThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;

public class InformacoesEnvio {

	public static DadoTransporteThrift getInfoInstalacao(InformacoesEnvioDto informacoesEnvioDto) {
		DadoTransporteThrift dadoTransporteThrift = new DadoTransporteThrift();

		// Obrigatórios;
		dadoTransporteThrift.setCnesDadoSerializado(informacoesEnvioDto.getCnesDadoSerializado());
		dadoTransporteThrift.setDadoSerializado(informacoesEnvioDto.getDadoSerializado());

		DadoInstalacaoThrift originadora = new DadoInstalacaoThrift();
		originadora.setContraChave("123456");
		originadora.setCpfOuCnpj("11111111111");
		originadora.setEmail("a@b.com");
		originadora.setFone("999999999");
		originadora.setNomeOuRazaoSocial("Nome ou Razao Social Originadora");
		originadora.setUuidInstalacao("UUIDUNICO111");
		dadoTransporteThrift.setOriginadora(originadora);

		DadoInstalacaoThrift remetente = new DadoInstalacaoThrift();
		remetente.setContraChave("789010");
		remetente.setCpfOuCnpj("11111111111");
		remetente.setEmail("b@a.com");
		remetente.setFone("98888888");
		remetente.setNomeOuRazaoSocial("Nome ou Razao Social Remetente");
		remetente.setUuidInstalacao("UUIDUNICO222");
		dadoTransporteThrift.setRemetente(remetente);

		dadoTransporteThrift.setTipoDadoSerializado(informacoesEnvioDto.getTipoDadoSerializado());
		dadoTransporteThrift.setUuidDadoSerializado(informacoesEnvioDto.getUuidDadoSerializado());

		// Opcionais;
		String codIbge = informacoesEnvioDto.getCodIbge();
		dadoTransporteThrift.setCodIbge(codIbge == null ? "" : codIbge);

		String ineDadoSerializado = informacoesEnvioDto.getIneDadoSerializado();
		dadoTransporteThrift.setIneDadoSerializado(ineDadoSerializado == null ? "" : ineDadoSerializado);

		Long numLote = informacoesEnvioDto.getNumLote();
		dadoTransporteThrift.setNumLote(numLote == null ? 0l : numLote);

		VersaoThrift versaoThrift = new VersaoThrift();
		versaoThrift.setMajor(1);
		versaoThrift.setMinor(0);
		versaoThrift.setRevision(0);
		dadoTransporteThrift.setVersao(versaoThrift);

		return dadoTransporteThrift;
	}
}
