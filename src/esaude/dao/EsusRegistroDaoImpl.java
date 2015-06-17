package esaude.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusRegistro;
import esaude.util.HibernateUtil;

public class EsusRegistroDaoImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	StringBuilder hql;
	
	public EsusRegistroDaoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory(); 
	}

	public EsusRegistro buscaEsusRegistro() {
		Transaction tx = sessionFactory.openSession()
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusRegistro e  ");
		return (EsusRegistro) query.uniqueResult();
	}
}