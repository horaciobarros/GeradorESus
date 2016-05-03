package esaude.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusConsumoAlimentar;
import esaude.model.EsusConsumoAlimentarRespostas;
import esaude.model.PProntuario;
import esaude.util.HibernateUtil;

public class EsusConsumoAlimentarRespostasDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;
	private Session session;

	public EsusConsumoAlimentarRespostasDao() {

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	public List<EsusConsumoAlimentarRespostas> findRespostas(Long id) {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusConsumoAlimentarRespostas car inner join car.esusConsumoAlimentar a "
						+ " inner join car.esusQstPergunta p inner join car.esusQstRespostas r where a.id = " 
								+ id);
		List<EsusConsumoAlimentarRespostas> lista = query.list();
		tx.commit();

		return lista;
	}

}
