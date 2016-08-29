package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import esaude.model.EsusCadastroDomiciliarFamilia;
import esaude.model.EsusRegistro;
import esaude.model.SisRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

public class EsusCadastroDomiciliarService {
	static Logger log = Logger.getLogger(EsusCadastroDomiciliarService.class.getName());
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
		TelaPrincipal.enviaLog(new Date() + " -- Gerando Cadastro domiciliar -------");

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		mapeiaSituacaoMoradia();
		try {

			for (EsusCadastroDomiciliar cad : dao.findNaoEnviados()) {
				try {
					CadastroDomiciliarThrift thriftCadastroDomiciliar = converterParaThrift(cad);

					InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

					byte[] dadoSerializado;

					// Passo 2: serializar o thrift
					dadoSerializado = ThriftSerializer.serialize(thriftCadastroDomiciliar);

					// Passo 3: coletar as informaÃ§Ãµes do envio
					informacoesEnvioDto.setTipoDadoSerializado(3l);
					informacoesEnvioDto.setDadoSerializado(dadoSerializado);
					informacoesEnvioDto.setUuidDadoSerializado(thriftCadastroDomiciliar.getUuid());
					if (cad.getId() == null) {
						log.info("UuidDadoSerializado inexistente para o endereço: " + cad.getDsComplemento());
					}
					informacoesEnvioDto.setIneDadoSerializado(cad.getIneEquipe());
					informacoesEnvioDto.setCnesDadoSerializado(cad.getCnesUnidade());
					informacoesEnvioDto.setCodIbge(sisRegistro.getCidadeIbge());

					// Passo 4: preencher o thrift de transporte com as
					// informadosÃ§Ãµeso
					// coletadas;
					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio.getInfoInstalacao(informacoesEnvioDto,
							esusRegistro);
					if (dadoTransporteThrift.getUuidDadoSerializado() == null) {
						log.info("UuidDadoSerializado inexistente para o complemento: " + cad.getDsComplemento());
					}

					dados.add(dadoTransporteThrift);
					log.info(new Date() + " -- Gerando cadastro Domiciliar --> " + cad.getId() + " --- "
							+ thriftCadastroDomiciliar.getUuid());
					System.out.println("Gerando cadastro Domiciliar --> " + cad.getId() + " - "
							+ thriftCadastroDomiciliar.getUuid() + " - " + cad.getUuid());

					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);

				} catch (JDBCConnectionException e) {
					log.info("Erro de conexão");
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date() + " - Erro de conexão" + e.getStackTrace());
				} catch (Exception e) {
					log.info("Erro na gravação do registro - " + e.getStackTrace());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date() + " - " + e.getStackTrace());
				}
			}
			log.info(new Date() + " -- Cadastro domiciliar - fichas geradas ----" + dados.size());

		} catch (JDBCConnectionException e) {
			log.error(new Date() + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return dados;
	}

	private CadastroDomiciliarThrift converterParaThrift(EsusCadastroDomiciliar cad) {
		CadastroDomiciliarThrift cadastroDomiciliarThrift = new CadastroDomiciliarThrift();

		cadastroDomiciliarThrift.setUuid(masterService.gerarUuid(cad.getCnesUnidade()));
		cadastroDomiciliarThrift.setUuidIsSet(true);

		if (cad.getIdOrigem() == null) {
			cadastroDomiciliarThrift.setFichaAtualizada(false);

		} else {
			EsusCadastroDomiciliar cadFichaOrigem;
			try {
				cadFichaOrigem = dao.findById(cad.getIdOrigem());
				cadastroDomiciliarThrift.setUuidFichaOriginadora(cadFichaOrigem.getUuid());
				cadastroDomiciliarThrift.setUuidFichaOriginadoraIsSet(true);
				cadastroDomiciliarThrift.setFichaAtualizada(true);
			} catch (Exception e) {
				cadastroDomiciliarThrift.setFichaAtualizada(false);
			}
		}
		
		cadastroDomiciliarThrift.setFichaAtualizadaIsSet(true);

		cad.setUuid(cadastroDomiciliarThrift.getUuid());

		cadastroDomiciliarThrift.setAnimaisNoDomicilio(null);
		cadastroDomiciliarThrift.setAnimaisNoDomicilioIsSet(true);

		CondicaoMoradiaThrift condicaoMoradia = new CondicaoMoradiaThrift();
		try {
			condicaoMoradia.setAbastecimentoAgua(cad.getEsusAbastecimentodeagua().getId());
			condicaoMoradia.setAbastecimentoAguaIsSet(true);
		} catch (Exception e) {
			condicaoMoradia.setAbastecimentoAguaIsSet(false);
		}
		try {
			condicaoMoradia.setDestinoLixo(cad.getEsusDestinodolixo().getId());
			condicaoMoradia.setDestinoLixoIsSet(true);
		} catch (Exception e) {
			condicaoMoradia.setDestinoLixoIsSet(false);
		}

		try {

			condicaoMoradia.setLocalizacao(cad.getEsusLocalizacaodamoradia().getId());
			condicaoMoradia.setLocalizacaoIsSet(true);
		} catch (Exception e) {
			condicaoMoradia.setLocalizacaoIsSet(false);
		}

		try {
			condicaoMoradia.setNuMoradores(cad.getQuantidadeMoradores().toString());
			condicaoMoradia.setNuMoradoresIsSet(true);
		} catch (Exception e) {
			condicaoMoradia.setNuMoradoresIsSet(false);
		}

		try {
			condicaoMoradia
					.setSituacaoMoradiaPosseTerra(situacaoMoradia.get(cad.getEsusCondicaodeposseeusodaterra().getId()));
			condicaoMoradia.setSituacaoMoradiaPosseTerraIsSet(true);

		} catch (Exception e) {
			condicaoMoradia.setSituacaoMoradiaPosseTerra(82);
			condicaoMoradia.setSituacaoMoradiaPosseTerraIsSet(true);
		}
		try {
			condicaoMoradia.setFormaEscoamentoBanheiro(cad.getEsusFormadeescoamentodobanheiroousanitario().getId());
			condicaoMoradia.setFormaEscoamentoBanheiroIsSet(true);

		} catch (Exception e) {
			condicaoMoradia.setFormaEscoamentoBanheiroIsSet(false);

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
		endereco.setComplementoIsSet(true);
		endereco.setNomeLogradouro(cad.getNoLogradouro());
		endereco.setNomeLogradouroIsSet(true);
		endereco.setNumero(cad.getNuDomicilio());
		if (cad.getNuDomicilio() == null) {
			endereco.setNumero("SN");
		}
		endereco.setNumeroIsSet(true);
		endereco.setTelReferencial(cad.getNuFoneReferencia());
		endereco.setTelReferencialIsSet(true);
		endereco.setTelResidencial(cad.getNuFoneResidencia());
		endereco.setTelResidencialIsSet(true);
		try {
			endereco.setTipoLogradouroNumeroDne("081");
			endereco.setTipoLogradouroNumeroDneIsSet(true);
		} catch (Exception e) {
			endereco.setTipoLogradouroNumeroDne("081");
			endereco.setTipoLogradouroNumeroDneIsSet(true);
		}
		endereco.setNumeroDneUf(cad.getCoUf() + "");
		endereco.setNumeroDneUfIsSet(true);
		cadastroDomiciliarThrift.setEnderecoLocalPermanencia(endereco);

		List<FamiliaRowThrift> familias = buscaFamilias(cad);
		cadastroDomiciliarThrift.setFamilias(familias);
		cadastroDomiciliarThrift.setFamiliasIsSet(true);

		if (cad.getQuantidadeAnimais() != null) {
			cadastroDomiciliarThrift.setQuantosAnimaisNoDomicilio(Long.toString(cad.getQuantidadeAnimais()));
			cadastroDomiciliarThrift.setQuantosAnimaisNoDomicilioIsSet(true);

			if (cad.getQuantidadeAnimais() > 0) {
				cadastroDomiciliarThrift.setStAnimaisNoDomicilio(true);
			} else {
				cadastroDomiciliarThrift.setStAnimaisNoDomicilio(false);
			}
			cadastroDomiciliarThrift.setStAnimaisNoDomicilioIsSet(true);// ??
																		// TODO
		} else {
			cadastroDomiciliarThrift.setQuantosAnimaisNoDomicilioIsSet(false);
			cadastroDomiciliarThrift.setStAnimaisNoDomicilioIsSet(false);// ??
																			// TODO
		}

		cadastroDomiciliarThrift.setStatusTermoRecusaCadastroDomiciliarAtencaoBasicaIsSet(false);
		cadastroDomiciliarThrift.setStatusTermoRecusaCadastroDomiciliarAtencaoBasica(cad.getStRecusaCadastro());
		cadastroDomiciliarThrift.setTpCdsOrigemIsSet(true);
		cadastroDomiciliarThrift.setTpCdsOrigem(3);

		return cadastroDomiciliarThrift;
	}

	private List<FamiliaRowThrift> buscaFamilias(EsusCadastroDomiciliar cad) {
		List<FamiliaRowThrift> lista = new ArrayList<FamiliaRowThrift>();

		for (EsusCadastroDomiciliarFamilia familiaAux : dao.findFamilias(cad.getId())) {
			FamiliaRowThrift familia = new FamiliaRowThrift();
			if (cad.getQtMembrosFamilia() != null) {
				familia.setNumeroMembrosFamilia(Integer.parseInt(Long.toString(cad.getQtMembrosFamilia())));
				familia.setNumeroMembrosFamiliaIsSet(true);
			}
			familia.setNumeroCnsResponsavel(cad.getCnsProfissional());
			familia.setNumeroCnsResponsavelIsSet(true);
			try {
				familia.setRendaFamiliar(cad.getCoCdsRendaFamiliar());
				familia.setRendaFamiliarIsSet(true);
			} catch (Exception e) {

			}
			if (cad.getDtMudanca() != null) {
				familia.setResideDesde(cad.getDtMudanca().getTime());
				familia.setResideDesdeIsSet(true);
			}
			familia.setNumeroProntuario(familiaAux.getNumProntuarioFamiliar());
			familia.setNumeroProntuarioIsSet(true);
			lista.add(familia);
		}
		return lista;

	}

	Map<Long, Long> situacaoMoradia;

	public void mapeiaSituacaoMoradia() {
		situacaoMoradia = new HashMap<Long, Long>();
		situacaoMoradia.put(Long.valueOf("101"), Long.valueOf("75"));
		situacaoMoradia.put(Long.valueOf("102"), Long.valueOf("76"));
		situacaoMoradia.put(Long.valueOf("103"), Long.valueOf("77"));
		situacaoMoradia.put(Long.valueOf("104"), Long.valueOf("78"));
		situacaoMoradia.put(Long.valueOf("105"), Long.valueOf("79"));
		situacaoMoradia.put(Long.valueOf("106"), Long.valueOf("80"));
		situacaoMoradia.put(Long.valueOf("107"), Long.valueOf("81"));
		situacaoMoradia.put(Long.valueOf("108"), Long.valueOf("82"));
	}
}
