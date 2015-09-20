package esaude.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusAtividadeColetiva;
import esaude.model.PProntuario;
import esaude.model.PProntuarioCns;
import esaude.util.HibernateUtil;

public class Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;
	Session session;

	public Dao() {

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	public void cancelaEnvio(String nomeEntidade, Date dataGeracao) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("update " + nomeEntidade + " set stEnvio = 0, dtEnvio = null, uuid = null "
				+ " where dtEnvio = :dtEnvio");
		query.setDate("dtEnvio", dataGeracao);
		query.executeUpdate();
		session.beginTransaction().commit();
		session.close();
	}
	
	public void ativaCns(PProntuario p) {
		
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"select cns from PProntuarioCns cns join fetch cns.pProntuario "
								+ "where cns.pProntuario.id = " + p.getCoProntuario());
		List<PProntuarioCns> lista = query.list();
		tx.commit();
		p.setFilhos(lista);
		
	}
	
}