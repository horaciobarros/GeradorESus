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
	static Logger log = Logger.getLogger(HibernateUtil.class
			.getName());

	private static final SessionFactory sessionFactory = buildSessionFactory();

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {

			File file = new File("hibernate.cfg.xml");
			if (!file.exists()) {
				JOptionPane.showMessageDialog(null, "arquivo de configuração do Banco de Dados não encontrado!");
				log.error("arquivo de configuração do Banco de Dados não encontrado!");
				throw new ExceptionInInitializerError();
			} else {
				AnnotationConfiguration configuration = new AnnotationConfiguration();
				SessionFactory sessionFactory = configuration.configure(
					file).buildSessionFactory();
				return sessionFactory;
			}
		} catch (Throwable ex) {
			JOptionPane.showMessageDialog(null, "Erro em configuração do banco!");
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
