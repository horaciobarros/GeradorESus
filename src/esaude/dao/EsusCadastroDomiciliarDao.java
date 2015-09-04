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
	private Session session;

	public EsusCadastroDomiciliarDao() {
		
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	public List<EsusCadastroDomiciliar> findNaoEnviados() {
		Transaction tx = session
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusCadastroDomiciliar cd left join fetch cd.esusSituacaodemoradia "
				+ "left join fetch cd.esusAbastecimentodeagua where cd.stEnvio is null or cd.stEnvio=0");
		List<EsusCadastroDomiciliar> lista = query.list();
		tx.commit();

		return lista;
	}
	
	public void atualiza(EsusCadastroDomiciliar entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.beginTransaction().commit();
		session.close();
		
		
	}

}
