package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusCadastroDomiciliar;
import esaude.util.HibernateUtil;


public class EsusCadastroDomiciliarDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public EsusCadastroDomiciliarDao() {
		
		sessionFactory = HibernateUtil.getSessionFactory(); 
	}

	public List<EsusCadastroDomiciliar> findNaoEnviados() {
		Transaction tx = sessionFactory.openSession()
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusCadastroDomiciliar cd  where st_envio is null or st_envio=0");
		List<EsusCadastroDomiciliar> lista = query.list();
		tx.commit();

		return lista;
	}
	
	public void atualiza(EsusCadastroDomiciliar entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.beginTransaction().commit();
		
		
	}

}
