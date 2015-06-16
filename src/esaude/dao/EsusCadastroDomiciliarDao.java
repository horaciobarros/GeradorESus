package esaude.dao;

import esaude.model.*;
import esaude.util.HibernateUtil;
import esaude.util.Util;

import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


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
				"from EsusCadastroDomiciliar cd  where dt_envio is null");
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
