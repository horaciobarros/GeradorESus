package esaude.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import esaude.controller.Controller;

public class HibernateUtil {
	static Logger log = Logger.getLogger(HibernateUtil.class.getName());

	private static final SessionFactory sessionFactory = buildSessionFactory();

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {

			File file = new File("hibernate.cfg.xml");
			if (!file.exists()) {
				JOptionPane
						.showMessageDialog(null,
								"arquivo de configuração do Banco de Dados não encontrado!");
				log.error("arquivo de configuração do Banco de Dados não encontrado!");
				throw new ExceptionInInitializerError();
			} else {
				AnnotationConfiguration configuration = new AnnotationConfiguration();
				configuration
						.addAnnotatedClass(esaude.model.EsusTipoatividadecoletiva.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusCadastroDomiciliar.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusAbastecimentodeagua.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusCondicaodeposseeusodaterra.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusDestinodolixo.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusFormadeescoamentodobanheiroousanitario.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusLocalizacaodamoradia.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusMaterialpredominantenaconstrucao.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusSituacaodemoradia.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusTipodeacessoaodomicilio.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusTipodedomicilio.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusTratamentodeaguanodomicilio.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusRegistro.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusAtividadeColetiva.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusTipoatividadecoletiva.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusAtividadeColetivaParticipantes.class);
				configuration
						.addAnnotatedClass(esaude.model.EsusAtividadeColetivaProfissional.class);
				configuration.addAnnotatedClass(esaude.model.PProntuario.class);
				configuration
						.addAnnotatedClass(esaude.model.CnesProfissionais.class);
				configuration.addAnnotatedClass(esaude.model.FrUsuario.class);
				configuration
						.addAnnotatedClass(esaude.model.CnesEstabelecimentos.class);
				configuration.addAnnotatedClass(esaude.model.PMunicipio.class);
				configuration
						.addAnnotatedClass(esaude.model.PNacionalidade.class);
				configuration
						.addAnnotatedClass(esaude.model.PNacionalidade.class);
				configuration.addAnnotatedClass(esaude.model.PRacaCor.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusCadastroIndividual.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusTemposituacaoderua.class);
				
				configuration
				.addAnnotatedClass(esaude.model.PProntuario.class);

				configuration
				.addAnnotatedClass(esaude.model.FrUsuario.class);
				
				configuration
				.addAnnotatedClass(esaude.model.SisRegistro.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusPublicoalvo.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtividadeColetivaPublico.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtividadeColetivaTemas.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusTemasparareuniao.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusCadastroIndividualDeficiencia.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusDeficienciacidadao.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusVisitaDomiciliar.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusSexo.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusVisitaDomiciliarMotivovisita.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusTurno.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusDesfecho.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusMotivovisita.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoIndividual.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusExames.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusTipodeatendimento.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoIndividualExames.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoIndividualExamesSia.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAleitamentomaterno.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusLocaldeatendimento.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusPraticasintegrativascomplementares.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusProced.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoOdontologico.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoOdontologicoConduta.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoOdontologicoEncam.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoOdontologicoFornecimento.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoOdontologicoVigilancia.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusTipodeconsultaodonto.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusCondutaencaminhamentoodonto.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoOdontologicoFornecimento.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusFornecimentoodonto.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusVigilanciaemsaudebucal.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusFichaProcedimento.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusLocaldeatendimento.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusTurno.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoIndividualCiap.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoOdontologicoCiap.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtendimentoIndividualCondicaoaval.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusCondicaoavaliada.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusPraticastemasparasaude.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusAtividadeColetivaPraticatemas.class);
				
				configuration
				.addAnnotatedClass(esaude.model.PProntuarioCns.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusMotivosaida.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusConsumoAlimentar.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusConsumoAlimentarRespostas.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusQstRespostas.class);
				
				configuration
				.addAnnotatedClass(esaude.model.EsusQstPergunta.class);
				
				SessionFactory sessionFactory = configuration.configure(file)
						.buildSessionFactory();
				return sessionFactory;
			}
		} catch (Throwable ex) {
			JOptionPane.showMessageDialog(null,
					"Erro em configuração do banco! Detalhes no log da aplicação");
			log.error("Erro em configuração do banco!" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}
