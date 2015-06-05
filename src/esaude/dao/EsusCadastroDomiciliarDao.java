package esaude.dao;

import esaude.model.*;

import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


public class EsusCadastroDomiciliarDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public EsusCadastroDomiciliarDao() {
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(); 
	}

	public List<EsusCadastroDomiciliar> findNaoEnviados() {
		Transaction tx = sessionFactory.openSession()
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusCadastroDomiciliar cd  ");
		List<EsusCadastroDomiciliar> lista = query.list();
		tx.commit();

		return lista;
	}

}
