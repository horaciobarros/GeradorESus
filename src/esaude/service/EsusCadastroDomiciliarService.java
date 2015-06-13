package esaude.service;

import esaude.dao.EsusCadastroDomiciliarDao;
import esaude.model.EsusCadastroDomiciliar;
import exemplosThrift.InformacoesEnvioDto;
import exemplosThrift.InformacoesEnvio;
import exemplosThrift.utils.ThriftSerializer;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.CadastroDomiciliarThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.EnderecoLocalPermanenciaThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.FamiliaRowThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.HeaderCdsCadastroThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;

public class EsusCadastroDomiciliarService {
	static Logger log = Logger.getLogger(EsusCadastroDomiciliarService.class
			.getName());
	private EsusCadastroDomiciliarDao dao = new EsusCadastroDomiciliarDao();

	public List<EsusCadastroDomiciliar> findNaoEnvidados() {
		return dao.findNaoEnviados();
	}

	public List<DadoTransporteThrift> buscaRegistros() {

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		try {
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
					informacoesEnvioDto.setIneDadoSerializado(cad
							.getIneEquipe());
					informacoesEnvioDto.setCnesDadoSerializado(cad
							.getCnesUnidade());

					// Passo 4: preencher o thrift de transporte com as
					// informadosçõeso
					// coletadas;
					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio
							.getInfoInstalacao(informacoesEnvioDto);

					dados.add(dadoTransporteThrift);
					System.out.println("Gerando cadastro -- "
							+ cad.getNuDomicilio() + " "
							+ cad.getNoLogradouro());

				} catch (JDBCConnectionException e) {
					log.info(e.getMessage());
				} catch (Exception e) {
					log.info(e.getMessage());
				}
			}
			
		} catch (JDBCConnectionException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return dados;
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

}
