package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusAtendimentoOdontologico;
import esaude.util.HibernateUtil;

public class EsusAtendimentoOdontologicoDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;
	Session session;

	public EsusAtendimentoOdontologicoDao() {

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	public List<EsusAtendimentoOdontologico> findNaoEnviados() {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusAtendimentoOdontologico ao "
								+ "where ai.stEnvio is null or ai.stEnvio=0");
		List<EsusAtendimentoOdontologico> lista = query.list();
		tx.commit();
		
		return (List<EsusAtendimentoOdontologico>) lista;
	}

	public void atualiza(EsusAtendimentoOdontologico entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("update EsusAtendimentoOdontologico set stEnvio=:stEnvio, dtEnvio=:dtEnvio where id = :id");
		query.setInteger("stEnvio", 1);
		query.setLong("id", entity.getId());
		query.setDate("dtEnvio", entity.getDtEnvio());
		query.executeUpdate();
		session.beginTransaction().commit();
		session.close();
	}
	
}
