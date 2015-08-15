package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.CadastroDomiciliarThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.CondicaoMoradiaThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.FamiliaRowThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.EnderecoLocalPermanenciaThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.HeaderCdsCadastroThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusCadastroDomiciliarDao;
import esaude.model.EsusCadastroDomiciliar;
import esaude.model.EsusRegistro;
import esaude.model.SisRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

public class EsusCadastroDomiciliarService {
	static Logger log = Logger.getLogger(EsusCadastroDomiciliarService.class
			.getName());
	private EsusCadastroDomiciliarDao dao = new EsusCadastroDomiciliarDao();
	private EsusRegistro esusRegistro = new EsusRegistro();
	private SisRegistro sisRegistro = new SisRegistro();
	private MasterService masterService = new MasterService();

	public List<EsusCadastroDomiciliar> findNaoEnvidados() {
		return dao.findNaoEnviados();
	}

	public List<DadoTransporteThrift> buscaRegistros() {

		EsusRegistroServiceImpl esusRegistroService = new EsusRegistroServiceImpl();
		try {
			esusRegistro = esusRegistroService.buscaEsusRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Esusregistro não encontrado");
			throw e;
		}
		SisRegistroService sisRegistroService = new SisRegistroService();
		try {
			sisRegistro = sisRegistroService.buscaSisRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sisregistro não encontrado");
			throw e;
		}

		log.info(new Date() + " -- Gerando Cadastro domiciliar -------");
		TelaPrincipal.enviaLog(new Date()
				+ " -- Gerando Cadastro domiciliar -------");

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

					// Passo 3: coletar as informaÃ§Ãµes do envio
					informacoesEnvioDto.setTipoDadoSerializado(3l);
					informacoesEnvioDto.setDadoSerializado(dadoSerializado);
					informacoesEnvioDto.setUuidDadoSerializado(cad.getId()
							.toString());
					if (cad.getId() == null) {
						log.info("UuidDadoSerializado inexistente para o endereço: "
								+ cad.getDsComplemento());
					}
					informacoesEnvioDto.setIneDadoSerializado(cad
							.getIneEquipe());
					informacoesEnvioDto.setCnesDadoSerializado(cad
							.getCnesUnidade());

					// Passo 4: preencher o thrift de transporte com as
					// informadosÃ§Ãµeso
					// coletadas;
					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio
							.getInfoInstalacao(informacoesEnvioDto,
									esusRegistro);
					if (dadoTransporteThrift.getUuidDadoSerializado() == null) {
						log.info("UuidDadoSerializado inexistente para o complemento: "
								+ cad.getDsComplemento());
					}

					dados.add(dadoTransporteThrift);
					log.info(new Date()
							+ " -- Gerando cadastro Domiciliar --> "
							+ cad.getId() + " - " + cad.getNuDomicilio()
							+ " - " + cad.getNoLogradouro());
					System.out.println("Gerando cadastro Domiciliar --> "
							+ cad.getId() + " - " + cad.getNuDomicilio()
							+ " - " + cad.getNoLogradouro());

					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);

				} catch (JDBCConnectionException e) {
					log.info(e.getMessage());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date() + " - " + e.getMessage());
				} catch (Exception e) {
					log.info(e.getMessage());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date() + " - " + e.getMessage());
				}
			}

		} catch (JDBCConnectionException e) {
			log.error(new Date() + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return dados;
	}

	private CadastroDomiciliarThrift converterParaThrift(
			EsusCadastroDomiciliar cad) {
		CadastroDomiciliarThrift cadastroDomiciliarThrift = new CadastroDomiciliarThrift();

		cadastroDomiciliarThrift.setUuid(masterService.gerarUuid(cad
				.getCnesUnidade()));
		cad.setUuid(cadastroDomiciliarThrift.getUuid());

		cadastroDomiciliarThrift.setAnimaisNoDomicilio(null);

		CondicaoMoradiaThrift condicaoMoradia = new CondicaoMoradiaThrift();
		condicaoMoradia.setAbastecimentoAgua(cad.getEsusAbastecimentodeagua()
				.getId());
		condicaoMoradia.setAbastecimentoAguaIsSet(true);
		condicaoMoradia.setDestinoLixo(cad.getEsusDestinodolixo().getId());
		condicaoMoradia.setDestinoLixoIsSet(true);
		condicaoMoradia.setLocalizacao(cad.getEsusLocalizacaodamoradia()
				.getId());
		condicaoMoradia.setLocalizacaoIsSet(true);
		condicaoMoradia.setNuMoradores(cad.getQuantidadeMoradores().toString());
		condicaoMoradia.setNuMoradoresIsSet(true);
		try {
			condicaoMoradia.setSituacaoMoradiaPosseTerra(cad
					.getEsusCondicaodeposseeusodaterra().getId());
			condicaoMoradia.setSituacaoMoradiaPosseTerraIsSet(true);

		} catch (Exception e) {

		}
		try {
			condicaoMoradia.setFormaEscoamentoBanheiro(cad
					.getEsusFormadeescoamentodobanheiroousanitario().getId());
			condicaoMoradia.setFormaEscoamentoBanheiroIsSet(true);

		} catch (Exception e) {

		}

		cadastroDomiciliarThrift.setCondicaoMoradia(condicaoMoradia);

		// Dados gerais
		HeaderCdsCadastroThrift dadosGerais = new HeaderCdsCadastroThrift();
		dadosGerais.setCnesUnidadeSaude(cad.getCnesUnidade());
		dadosGerais.setCnesUnidadeSaudeIsSet(true);
		dadosGerais.setCnsProfissional(cad.getCnsProfissional());
		dadosGerais.setCnsProfissionalIsSet(true);
		dadosGerais.setCodigoIbgeMunicipio(cad.getCoMunicipio());
		dadosGerais.setCodigoIbgeMunicipioIsSet(true); // ?? TODO
		dadosGerais.setDataAtendimento(cad.getDtCadastro().getTime());
		dadosGerais.setDataAtendimentoIsSet(true); // ?? TODO
		dadosGerais.setIneEquipe(cad.getIneEquipe());
		dadosGerais.setIneEquipeIsSet(true);
		try {
			dadosGerais.setMicroarea(cad.getMicroarea());
			dadosGerais.setMicroareaIsSet(false);// ?? TODO
		} catch (Exception e) {

		}
		cadastroDomiciliarThrift.setDadosGerais(dadosGerais);

		// Endereco
		EnderecoLocalPermanenciaThrift endereco = new EnderecoLocalPermanenciaThrift();
		endereco.setBairro(cad.getNoBairro());
		endereco.setBairroIsSet(true);
		endereco.setCep(cad.getNuCep());
		endereco.setCepIsSet(true);
		endereco.setCodigoIbgeMunicipio(cad.getCoMunicipio());
		endereco.setCodigoIbgeMunicipioIsSet(true);
		endereco.setComplemento(cad.getDsComplemento());
		endereco.setComplementoIsSet(false);
		endereco.setNomeLogradouro(cad.getNoLogradouro());
		endereco.setNomeLogradouroIsSet(true);
		endereco.setNumero(cad.getNuDomicilio());
		if (cad.getNuDomicilio() == null) {
			endereco.setNumero("SN");
		}
		endereco.setNumeroIsSet(true);
		endereco.setTelReferencial(cad.getNuFoneReferencia());
		endereco.setTelReferencialIsSet(false);
		endereco.setTelResidencial(cad.getNuFoneResidencia());
		endereco.setTelResidencialIsSet(false);
		endereco.setTipoLogradouroNumeroDne(cad.getTpLogradouro().toString());
		endereco.setTipoLogradouroNumeroDneIsSet(false);
		cadastroDomiciliarThrift.setEnderecoLocalPermanencia(endereco);

		FamiliaRowThrift familia = new FamiliaRowThrift();
		if (cad.getQtMembrosFamilia() != null) {
			familia.setNumeroMembrosFamilia(Integer.parseInt(Long.toString(cad
					.getQtMembrosFamilia())));
			familia.setNumeroMembrosFamiliaIsSet(true);
		}
		familia.setNumeroCnsResponsavel(cad.getCnesUnidade());
		familia.setNumeroCnsResponsavelIsSet(true);
		if (cad.getIdProntuarioResponsavel() != null) {
			familia.setNumeroProntuario(Long.toString(cad
					.getIdProntuarioResponsavel()));
			familia.setNumeroProntuarioIsSet(true);
		}
		try {
			familia.setRendaFamiliar(cad.getCoCdsRendaFamiliar());
			familia.setRendaFamiliarIsSet(true);
		} catch (Exception e) {

		}
		if (cad.getDtMudanca() != null) {
			familia.setResideDesde(cad.getDtMudanca().getTime());
			familia.setResideDesdeIsSet(true);
		}
		List<FamiliaRowThrift> familias = new ArrayList<FamiliaRowThrift>();
		familias.add(familia);
		cadastroDomiciliarThrift.setFamilias(familias);

		if (cad.getDtEnvio() != null) {
			cadastroDomiciliarThrift.setFichaAtualizada(true);
			cadastroDomiciliarThrift.setFichaAtualizadaIsSet(true);
		}
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
		cadastroDomiciliarThrift.setTpCdsOrigemIsSet(true);
		cadastroDomiciliarThrift.setTpCdsOrigem(3);

		return cadastroDomiciliarThrift;
	}

}
