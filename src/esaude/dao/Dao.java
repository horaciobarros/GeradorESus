package esaude.dao;

import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		
		Query query = session.createQuery("update " + nomeEntidade + " set stEnvio = 0 "
				+ " where dtEnvio = :dtEnvio");
		query.setDate("dtEnvio", dataGeracao);
		query.executeUpdate();
		session.beginTransaction().commit();
		session.close();
	}
	
}