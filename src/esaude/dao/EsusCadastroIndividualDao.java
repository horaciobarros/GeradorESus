package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusCadastroIndividual;
import esaude.util.HibernateUtil;

public class EsusCadastroIndividualDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public EsusCadastroIndividualDao() {

		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public List<EsusCadastroIndividual> findNaoEnviados() {
		Transaction tx = sessionFactory.openSession().beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusCadastroIndividual ci  left outer join fetch ci.pProntuario pp "
						+ " left outer join fetch pp.pNacionalidade pn "
						+ " left outer join fetch pp.pMunicipio pm "
								+ "where ci.stEnvio is null or ci.stEnvio=0");
		List<EsusCadastroIndividual> lista = query.list();
		tx.commit();

		return (List<EsusCadastroIndividual>) lista;
	}

	public void atualiza(EsusCadastroIndividual entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("update EsusCadastroIndividual set stEnvio=:stEnvio, dtEnvio=:dtEnvio where id = :id");
		query.setInteger("stEnvio", 1);
		query.setLong("id", entity.getId());
		query.setDate("dtEnvio", entity.getDtEnvio());
		query.executeUpdate();
		session.beginTransaction().commit();

		
		
	}

}
