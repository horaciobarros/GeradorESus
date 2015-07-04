package esaude.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.SisRegistro;
import esaude.util.HibernateUtil;

public class SisRegistroDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	StringBuilder hql;
	
	public SisRegistroDao() {
		sessionFactory = HibernateUtil.getSessionFactory(); 
	}

	public SisRegistro buscaSisRegistro() {
		Transaction tx = sessionFactory.openSession()
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from SisRegistro s  ");
		return (SisRegistro) query.uniqueResult();
	}
}