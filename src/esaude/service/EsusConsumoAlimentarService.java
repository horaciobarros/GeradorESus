package esaude.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.FichaConsumoAlimentarThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.PerguntaCriancasComMaisDoisAnosEnumThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.PerguntaCriancasDeSeisVinteTresMesesEnumThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.PerguntaCriancasMenoresSeisMesesEnumThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.PerguntaQuestionarioCriancasComMaisDoisAnosThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.PerguntaQuestionarioCriancasMenoresSeisMesesThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.RespostaMultiplaEscolhaEnumThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.RespostaUnicaEscolhaEnumThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusConsumoAlimentarDao;
import esaude.dao.EsusConsumoAlimentarRespostasDao;
import esaude.model.EsusConsumoAlimentar;
import esaude.model.EsusConsumoAlimentarRespostas;
import esaude.model.EsusQstPergunta;
import esaude.model.EsusRegistro;
import esaude.model.SisRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

/**
 * @author Fernando Werneck - 02/05/2016 Analista Desenvolvedor
 *         fernandowtb@hotmail.com www.jwaysistemas.com.br (31) 98594-8242
 */
public class EsusConsumoAlimentarService extends MasterService {
	static Logger log = Logger.getLogger(EsusConsumoAlimentarService.class.getName());

	private EsusConsumoAlimentarDao dao = new EsusConsumoAlimentarDao();
	private EsusRegistro esusRegistro = new EsusRegistro();
	private SisRegistro sisRegistro = new SisRegistro();
	private MasterService masterService = new MasterService();
	private EsusConsumoAlimentarRespostasDao esusConsumoAlimentarRespostasDaodao = new EsusConsumoAlimentarRespostasDao();

	public List<DadoTransporteThrift> buscaRegistros() {
		EsusRegistroServiceImpl esusRegistroService = new EsusRegistroServiceImpl();
		try {
			esusRegistro = esusRegistroService.buscaEsusRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Esus registro n�o encontrado");
			throw e;
		}
		SisRegistroService sisRegistroService = new SisRegistroService();
		try {
			sisRegistro = sisRegistroService.buscaSisRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sisregistro n�o encontrado");
			throw e;
		}

		log.info(new Date() + " -- Gerando Consumo Alimentar -------");
		TelaPrincipal.enviaLog(new Date() + " -- Gerando Consumo Alimentar -------");

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		for (EsusConsumoAlimentar cad : dao.findNaoEnviados()) {
			try {
				FichaConsumoAlimentarThrift thriftConsumoAlimentar = converterParaThrift(cad);

				InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

				byte[] dadoSerializado;

				// Passo 2: serializar o thrift
				dadoSerializado = ThriftSerializer.serialize(thriftConsumoAlimentar);

				// Passo 3: coletar as informações do envio
				informacoesEnvioDto.setTipoDadoSerializado(12L); // importante,
																	// aqui
																	// identifica
																	// qual tipo
																	// de ficha
																	// est�
																	// sendo
																	// enviado
				informacoesEnvioDto.setDadoSerializado(dadoSerializado);
				informacoesEnvioDto.setUuidDadoSerializado(thriftConsumoAlimentar.getUuidFicha());
				informacoesEnvioDto.setIneDadoSerializado(cad.getIneEquipe());
				informacoesEnvioDto.setCnesDadoSerializado(cad.getCnesUnidade());
				informacoesEnvioDto.setCodIbge(sisRegistro.getCidadeIbge());

				DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio.getInfoInstalacao(informacoesEnvioDto,
						esusRegistro);

				dados.add(dadoTransporteThrift);
				cad.setDtEnvio(new Date());
				cad.setStEnvio(Long.valueOf(1));
				dao.atualiza(cad);

				log.info(new Date() + " -- Gerando Consumo alimentar  --> " + cad.getId() + " - "
						+ thriftConsumoAlimentar.getUuidFicha());
				System.out.println("Gerando Consumo alimentar --> " + cad.getId());

			} catch (JDBCConnectionException e) {
				log.error(new Date() + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}

		log.info(new Date() + " -- Consumo alimentar - fichas geradas ----" + dados.size());

		return dados;
	}

	private FichaConsumoAlimentarThrift converterParaThrift(EsusConsumoAlimentar cad) {
		FichaConsumoAlimentarThrift c = new FichaConsumoAlimentarThrift();

		try {
			c.setUuidFichaIsSet(true);
			c.setUuidFicha(masterService.gerarUuid(cad.getCnesUnidade()));
			cad.setUuid(c.getUuidFicha());

		} catch (Exception e) {

		}

		try {
			UnicaLotacaoHeaderThrift unicaLotacao = new UnicaLotacaoHeaderThrift();
			unicaLotacao.setCboCodigo_2002(cad.getCboProfissional());
			unicaLotacao.setCboCodigo_2002IsSet(true);
			unicaLotacao.setCnes(cad.getCnesUnidade());
			unicaLotacao.setCnesIsSet(true);
			unicaLotacao.setProfissionalCNS(cad.getCnsProfissional());
			unicaLotacao.setProfissionalCNSIsSet(true);
			try {
				unicaLotacao.setDataAtendimento(cad.getDtAtendimento().getTime());
				unicaLotacao.setDataAtendimentoIsSet(true);
			} catch (Exception e) {
				log.error("Data de atendimento inv�lida ou nula. Id:" + cad.getId() + " -- " + e.getMessage());
				e.printStackTrace();
			}
			unicaLotacao.setCodigoIbgeMunicipio(sisRegistro.getCidadeIbge());
			unicaLotacao.setCodigoIbgeMunicipioIsSet(true);
			c.setHeaderTransport(unicaLotacao);
		} catch (Exception e) {

		}

		try {
			dao.ativaCns(cad.getPProntuario());
			c.setNumeroCartaoSus(cad.getPProntuario().getCoNumeroCartao());
			c.setNumeroCartaoSusIsSet(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			c.setIdentificacaoUsuario(cad.getPProntuario().getNoUsuario());
			c.setIdentificacaoUsuarioIsSet(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			c.setDataNascimento(cad.getDtNascimento().getTime());
			c.setDataNascimentoIsSet(true);
		} catch (Exception e) {

		}

		try {
			c.setSexo(Long.valueOf(cad.getPProntuario().getCoSexo()));
			c.setSexoIsSet(true);
		} catch (Exception e) {

		}

		try {
			c.setLocalAtendimento(cad.getEsusLocaldeatendimento().getId());
			c.setLocalAtendimentoIsSet(true);
		} catch (Exception e) {

		}

		try {

			int meses = getQtdeMeses(cad.getDtAtendimento(), cad.getDtNascimento());

			if (meses < 6) {
				List<PerguntaQuestionarioCriancasMenoresSeisMesesThrift> perguntas1 = buscaPerguntaQuestionarioCriancas1(
						cad);
				if (perguntas1 != null && perguntas1.size() > 0) {
					c.setPerguntasQuestionarioCriancasMenoresSeisMeses(perguntas1);
					c.setPerguntasQuestionarioCriancasMenoresSeisMesesIsSet(true);
				} else {
					c.setPerguntasQuestionarioCriancasMenoresSeisMesesIsSet(false);
				}

			} else if (meses <= 23) {
				List<PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift> perguntas2 = buscaPerguntaQuestionarioCriancas6a23(
						cad);
				if (perguntas2 != null && perguntas2.size() > 0) {
					c.setPerguntasQuestionarioCriancasDeSeisVinteTresMeses(perguntas2);
					c.setPerguntasQuestionarioCriancasDeSeisVinteTresMesesIsSet(true);
				} else {
					c.setPerguntasQuestionarioCriancasDeSeisVinteTresMesesIsSet(false);
				}

			} else if (meses >= 24) {
				List<PerguntaQuestionarioCriancasComMaisDoisAnosThrift> perguntas3 = buscaPerguntaQuestionarioCriancas2Anos(
						cad);
				if (perguntas3 != null && perguntas3.size() > 0) {
					c.setPerguntasQuestionarioCriancasComMaisDoisAnos(perguntas3);
					c.setPerguntasQuestionarioCriancasComMaisDoisAnosIsSet(true);
				} else {
					c.setPerguntasQuestionarioCriancasComMaisDoisAnosIsSet(false);
				}

			}

		} catch (Exception e) {

		}

		try {
			c.setTpCdsOrigem(3);
			c.setTpCdsOrigemIsSet(true);
		} catch (Exception e) {

		}

		try {
			Long sexo = new Long(0);
			if (cad.getPProntuario().getCoSexo().equals("M")) {
				sexo = Long.valueOf(0);
			} else {
				sexo = Long.valueOf(1);
			}
			c.setSexo(sexo);
			c.setSexoIsSet(true);
		} catch (Exception e) {
			c.setSexoIsSet(false);
		}

		return c;
	}

	private int getQtdeMeses(Date dtAtendimento, Date dtNascimento) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// vamos obter a diferen�a de meses
		long segundos = (dtAtendimento.getTime() - dtNascimento.getTime()) / 1000;
		int meses = (int) Math.floor(segundos / 2592000);
		System.out.println(
				"Datas:" + dateFormat.format(dtAtendimento) + " - " + dateFormat.format(dtNascimento) + " = " + meses);
		return meses;
	}

	private List<PerguntaQuestionarioCriancasMenoresSeisMesesThrift> buscaPerguntaQuestionarioCriancas1(
			EsusConsumoAlimentar cad) {

		List<PerguntaQuestionarioCriancasMenoresSeisMesesThrift> lista = new ArrayList<PerguntaQuestionarioCriancasMenoresSeisMesesThrift>();
		try {

			for (EsusConsumoAlimentarRespostas resposta : esusConsumoAlimentarRespostasDaodao
					.findRespostas(cad.getId())) {

				PerguntaQuestionarioCriancasMenoresSeisMesesThrift respostaThrift = new PerguntaQuestionarioCriancasMenoresSeisMesesThrift();
				respostaThrift.setPergunta(PerguntaCriancasMenoresSeisMesesEnumThrift
						.findByValue(resposta.getEsusQstPergunta().getCoQstPergunta().intValue()));
				respostaThrift.setPerguntaIsSet(true);
				respostaThrift.setRespostaUnicaEscolha(
						RespostaUnicaEscolhaEnumThrift.findByValue(resposta.getEsusQstRespostas().getId().intValue()));
				respostaThrift.setRespostaUnicaEscolhaIsSet(true);

				lista.add(respostaThrift);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	private List<PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift> buscaPerguntaQuestionarioCriancas6a23(
			EsusConsumoAlimentar cad) {

		List<PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift> lista = new ArrayList<PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift>();
		try {

			for (EsusConsumoAlimentarRespostas resposta : esusConsumoAlimentarRespostasDaodao
					.findRespostas(cad.getId())) {

				PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift respostaThrift = new PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift();
				respostaThrift.setPergunta(PerguntaCriancasDeSeisVinteTresMesesEnumThrift
						.findByValue(resposta.getEsusQstPergunta().getCoQstPergunta().intValue()));
				respostaThrift.setPerguntaIsSet(true);

				respostaThrift.setRespostaUnicaEscolha(
						RespostaUnicaEscolhaEnumThrift.findByValue(resposta.getEsusQstRespostas().getId().intValue()));
				respostaThrift.setRespostaUnicaEscolhaIsSet(true);

				lista.add(respostaThrift);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	private List<PerguntaQuestionarioCriancasComMaisDoisAnosThrift> buscaPerguntaQuestionarioCriancas2Anos(
			EsusConsumoAlimentar cad) {

		List<PerguntaQuestionarioCriancasComMaisDoisAnosThrift> lista = new ArrayList<PerguntaQuestionarioCriancasComMaisDoisAnosThrift>();

		try {

			Map<EsusQstPergunta, List<EsusConsumoAlimentarRespostas>> respostasMap = new HashMap<EsusQstPergunta, List<EsusConsumoAlimentarRespostas>>();

			List<EsusConsumoAlimentarRespostas> listaAux;

			for (EsusConsumoAlimentarRespostas resposta : esusConsumoAlimentarRespostasDaodao
					.findRespostas(cad.getId())) {

				if (!respostasMap.containsKey(resposta.getEsusQstPergunta())) {
					listaAux = new ArrayList<EsusConsumoAlimentarRespostas>();
					listaAux.add(resposta);
					respostasMap.put(resposta.getEsusQstPergunta(), listaAux);

				} else {
					listaAux = respostasMap.get(resposta.getEsusQstPergunta());
					listaAux.add(resposta);
					respostasMap.put(resposta.getEsusQstPergunta(), listaAux);
				}

			}

			for (Map.Entry<EsusQstPergunta, List<EsusConsumoAlimentarRespostas>> entry : respostasMap.entrySet()) {

				EsusQstPergunta key = entry.getKey();
				List<EsusConsumoAlimentarRespostas> rValues = entry.getValue();

				PerguntaQuestionarioCriancasComMaisDoisAnosThrift perguntaThrift = new PerguntaQuestionarioCriancasComMaisDoisAnosThrift();

				perguntaThrift.setPergunta(
						PerguntaCriancasComMaisDoisAnosEnumThrift.findByValue(key.getCoQstPergunta().intValue()));
				perguntaThrift.setPerguntaIsSet(true);

				// as respostas
				
				if (rValues.size() > 1) { // multipla escolha
					List<RespostaMultiplaEscolhaEnumThrift> respostaMultipla = new ArrayList<RespostaMultiplaEscolhaEnumThrift>();
					
					for (EsusConsumoAlimentarRespostas resposta : rValues) {
						if (resposta.getEsusQstRespostas() == null || resposta.getEsusQstRespostas().getId() == null) {
							continue;
						}
						
						RespostaMultiplaEscolhaEnumThrift th = RespostaMultiplaEscolhaEnumThrift
								.findByValue(resposta.getEsusQstRespostas().getId().intValue());
						
						if (th == null || th.getValue() == 0) {
							continue;
						}
						respostaMultipla.add(th);
					}
					
					perguntaThrift.setRespostaMultiplaEscolha(respostaMultipla);
					perguntaThrift.setRespostaMultiplaEscolhaIsSet(true);
					
					System.out.println("gravando respostas multiplas:" + respostaMultipla.size());

				} else {
					
					perguntaThrift.setRespostaUnicaEscolha(RespostaUnicaEscolhaEnumThrift
							.findByValue(rValues.get(0).getEsusQstRespostas().getId().intValue()));
					perguntaThrift.setRespostaUnicaEscolhaIsSet(true);
				}
				
				if ((perguntaThrift.getRespostaMultiplaEscolha() == null || perguntaThrift.getRespostaMultiplaEscolha().isEmpty())
					&& perguntaThrift.getRespostaUnicaEscolha() == null) {
					continue;
				}
				
				
				
				lista.add(perguntaThrift);
			}


		} catch (

		Exception e) {
			e.printStackTrace();
		}
		
		if (cad.getPProntuario().getCoProntuario() == 7063) {
			System.out.println("gravando marlene" );

		}
		
		return lista;
	}

}
