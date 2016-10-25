package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusAtividadeColetivaParticipantes;
import esaude.model.EsusVisitaDomiciliar;
import esaude.model.EsusVisitaDomiciliarMotivovisita;
import esaude.util.HibernateUtil;

public class EsusVisitaDomiciliarDao extends Dao {
 
	StringBuilder hql;
	private SessionFactory sessionFactory;
	private Session session;

	public EsusVisitaDomiciliarDao() {

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	public List<EsusVisitaDomiciliar> findNaoEnviados() {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"select vd from EsusVisitaDomiciliar vd JOIN fetch vd.pProntuario pp "
						+ " left join vd.esusSexo "
						+ " left join vd.pProntuario "
						+ " left join vd.esusDesfecho "
								+ " where vd.stEnvio is null or vd.stEnvio=0");
		List<EsusVisitaDomiciliar> lista = query.list();
		tx.commit();

		return (List<EsusVisitaDomiciliar>) lista;
	}

	public void atualiza(EsusVisitaDomiciliar entity) {
		Transaction tx = session.beginTransaction();
		session.beginTransaction();
		
		Query query = session.createQuery("update EsusVisitaDomiciliar set stEnvio=:stEnvio, dtEnvio=:dtEnvio, "
				+ "uuid=:uuid where id = :id");
		query.setInteger("stEnvio", 1);
		query.setLong("id", entity.getId());
		query.setDate("dtEnvio", entity.getDtEnvio());
		query.setString("uuid", entity.getUuid());
		query.executeUpdate();
		session.beginTransaction().commit();
	}
	
	public List<EsusVisitaDomiciliarMotivovisita> findMotivos(Long id) {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusVisitaDomiciliarMotivovisita vdm where vdm.esusVisitaDomiciliar.id = "
								+ id);
		List<EsusVisitaDomiciliarMotivovisita> lista = query.list();
		tx.commit();

		return lista;

	}


}
