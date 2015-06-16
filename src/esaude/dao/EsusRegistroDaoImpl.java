package esaude.dao;

import java.io.*;
import java.util.*;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import esaude.model.EsusRegistro;
import esaude.util.Util;
import esaude.dao.*;

public class EsusRegistroDaoImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	StringBuilder hql;
	
	public EsusRegistroDaoImpl() {
		sessionFactory = Util.getSessionFactory(); 
	}

	public EsusRegistro buscaEsusRegistro() {
		Transaction tx = sessionFactory.openSession()
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusRegistro e  ");
		return (EsusRegistro) query.uniqueResult();
	}
}