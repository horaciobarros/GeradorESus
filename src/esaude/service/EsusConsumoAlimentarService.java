package esaude.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.RespostaUnicaEscolhaEnumThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusConsumoAlimentarDao;
import esaude.dao.EsusConsumoAlimentarRespostasDao;
import esaude.model.EsusConsumoAlimentar;
import esaude.model.EsusConsumoAlimentarRespostas;
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
			JOptionPane.showMessageDialog(null, "Esus registro não encontrado");
			throw e;
		}
		SisRegistroService sisRegistroService = new SisRegistroService();
		try {
			sisRegistro = sisRegistroService.buscaSisRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sisregistro não encontrado");
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

				// Passo 3: coletar as informaÃ§Ãµes do envio
				informacoesEnvioDto.setTipoDadoSerializado(6L); // importante,
																// aqui
																// identifica
																// qual tipo
																// de ficha
																// está
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
				log.error("Data de atendimento inválida ou nula. Id:" + cad.getId() + " -- " + e.getMessage());
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
			c.setIdentificacaoUsuario(cad.getPProntuario().getNomeSocial());
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
				c.setPerguntasQuestionarioCriancasMenoresSeisMeses(perguntas1);
				c.setPerguntasQuestionarioCriancasMenoresSeisMesesIsSet(true);
				
			} else if (meses <= 23) {
				List<PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift> perguntas2 = buscaPerguntaQuestionarioCriancas6a23(cad);
				c.setPerguntasQuestionarioCriancasDeSeisVinteTresMeses(perguntas2);
				c.setPerguntasQuestionarioCriancasDeSeisVinteTresMesesIsSet(true);
				
			} else if (meses >= 24) {
				List<PerguntaQuestionarioCriancasComMaisDoisAnosThrift> perguntas3 = buscaPerguntaQuestionarioCriancas2Anos(cad);
				c.setPerguntasQuestionarioCriancasComMaisDoisAnos(perguntas3);
				c.setPerguntasQuestionarioCriancasComMaisDoisAnosIsSet(true);
				
			}

		} catch (Exception e) {

		}

		try {
			c.setTpCdsOrigem(3);
			c.setTpCdsOrigemIsSet(true);
		} catch (Exception e) {

		}

		return c;
	}


	
	private int getQtdeMeses(Date dtAtendimento, Date dtNascimento) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		// vamos obter a diferença de meses
		long segundos = (dtAtendimento.getTime() - dtNascimento.getTime()) / 1000;
		int meses = (int) Math.floor(segundos / 2592000);
		System.out.println("Datas:" + dateFormat.format(dtAtendimento) + " - " + dateFormat.format(dtNascimento) + " = " + meses);
		return meses;
	}

	private List<PerguntaQuestionarioCriancasMenoresSeisMesesThrift> buscaPerguntaQuestionarioCriancas1(
			EsusConsumoAlimentar cad) {

		List<PerguntaQuestionarioCriancasMenoresSeisMesesThrift> lista = new ArrayList<PerguntaQuestionarioCriancasMenoresSeisMesesThrift>();

		for (EsusConsumoAlimentarRespostas resposta : esusConsumoAlimentarRespostasDaodao.findRespostas(cad.getId())) {

			PerguntaQuestionarioCriancasMenoresSeisMesesThrift respostaThrift = new PerguntaQuestionarioCriancasMenoresSeisMesesThrift();
			respostaThrift.setPergunta(PerguntaCriancasMenoresSeisMesesEnumThrift
					.findByValue(resposta.getEsusQstPergunta().getCoQstPergunta().intValue()));
			respostaThrift.setPerguntaIsSet(true);
			respostaThrift.setRespostaUnicaEscolha(
					RespostaUnicaEscolhaEnumThrift.findByValue(resposta.getEsusQstRespostas().getId().intValue()));
			respostaThrift.setRespostaUnicaEscolhaIsSet(true);
			lista.add(respostaThrift);

		}

		return lista;
	}
	
	private List<PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift> buscaPerguntaQuestionarioCriancas6a23(
			EsusConsumoAlimentar cad) {

		List<PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift> lista = new ArrayList<PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift>();

		for (EsusConsumoAlimentarRespostas resposta : esusConsumoAlimentarRespostasDaodao.findRespostas(cad.getId())) {

			PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift respostaThrift = new PerguntaQuestionarioCriancasDeSeisVinteTresMesesThrift();
			respostaThrift.setPergunta(PerguntaCriancasDeSeisVinteTresMesesEnumThrift
					.findByValue(resposta.getEsusQstPergunta().getCoQstPergunta().intValue()));
			respostaThrift.setPerguntaIsSet(true);
			respostaThrift.setRespostaUnicaEscolha(
					RespostaUnicaEscolhaEnumThrift.findByValue(resposta.getEsusQstRespostas().getId().intValue()));
			respostaThrift.setRespostaUnicaEscolhaIsSet(true);
			lista.add(respostaThrift);

		}

		return lista;
	}
	
	private List<PerguntaQuestionarioCriancasComMaisDoisAnosThrift> buscaPerguntaQuestionarioCriancas2Anos(
			EsusConsumoAlimentar cad) {
		List<PerguntaQuestionarioCriancasComMaisDoisAnosThrift> lista = new ArrayList<PerguntaQuestionarioCriancasComMaisDoisAnosThrift>();

		for (EsusConsumoAlimentarRespostas resposta : esusConsumoAlimentarRespostasDaodao.findRespostas(cad.getId())) {

			PerguntaQuestionarioCriancasComMaisDoisAnosThrift respostaThrift = new PerguntaQuestionarioCriancasComMaisDoisAnosThrift();
			respostaThrift.setPergunta(PerguntaCriancasComMaisDoisAnosEnumThrift
					.findByValue(resposta.getEsusQstPergunta().getCoQstPergunta().intValue()));
			respostaThrift.setPerguntaIsSet(true);
			respostaThrift.setRespostaUnicaEscolha(
					RespostaUnicaEscolhaEnumThrift.findByValue(resposta.getEsusQstRespostas().getId().intValue()));
			respostaThrift.setRespostaUnicaEscolhaIsSet(true);
			lista.add(respostaThrift);

		}

		return lista;
	}


}
