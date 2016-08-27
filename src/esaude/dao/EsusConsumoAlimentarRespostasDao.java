package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusConsumoAlimentarRespostas;
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
						"from EsusConsumoAlimentarRespostas car join fetch car.esusConsumoAlimentar a "
						+ " left outer join fetch car.esusQstPergunta p  join fetch car.esusQstRespostas r where a.id = " 
								+ id + " and car.esusQstRespostas is not null order by car.esusQstPergunta.id ");
		List<EsusConsumoAlimentarRespostas> lista = query.list();
		tx.commit();

		return lista;
	}

}
