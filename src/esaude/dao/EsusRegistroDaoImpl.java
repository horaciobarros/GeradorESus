package esaude.dao;

import java.io.*;
import java.util.*;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import esaude.model.EsusRegistro;
import esaude.dao.*;

public class EsusRegistroDaoImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	StringBuilder hql;

	public EsusRegistro buscaEsusRegistro() {
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from EsusRegistro e where e.id = :pId ");
		return (EsusRegistro) query.uniqueResult();
	}
}