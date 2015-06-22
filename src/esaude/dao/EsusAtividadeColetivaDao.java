package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusAtividadeColetiva;
import esaude.util.HibernateUtil;


public class EsusAtividadeColetivaDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public EsusAtividadeColetivaDao() {
		
		sessionFactory = HibernateUtil.getSessionFactory(); 
	}

	public List<EsusAtividadeColetiva> findNaoEnviados() {
		Transaction tx = sessionFactory.openSession()
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusAtividadeColetiva ac  where st_envio is null or st_envio=0");
		List<EsusAtividadeColetiva> lista = query.list();
		tx.commit();

		return lista;
	}
	
	public void atualiza(EsusAtividadeColetiva entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.beginTransaction().commit();
		
		
	}

}
