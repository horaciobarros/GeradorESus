package esaude.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusFichaProcedimento;
import esaude.model.EsusFichaProcedimentoAtendimento;
import esaude.util.HibernateUtil;

public class EsusFichaProcedimentoDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;
	Session session;

	public EsusFichaProcedimentoDao() {

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	public List<EsusFichaProcedimento> findNaoEnviados() {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusFichaProcedimento fp  "
								+ "where fp.stEnvio is null or fp.stEnvio=0");
		List<EsusFichaProcedimento> lista = query.list();
		tx.commit();
		
		return (List<EsusFichaProcedimento>) lista;
	}

	public void atualiza(EsusFichaProcedimento entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("update EsusFichaProcedimento set stEnvio=:stEnvio"
				+ ", dtEnvio=:dtEnvio, uuid=:uuid where id = :id");
		query.setInteger("stEnvio", 1);
		query.setLong("id", entity.getId());
		query.setDate("dtEnvio", entity.getDtEnvio());
		query.setString("uuid", entity.getUuid());
		query.executeUpdate();
		session.beginTransaction().commit();
		session.close();
	}
	
	public List<EsusFichaProcedimentoAtendimento> findAtendimentos(Long id) {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusFichaProcedimentoAtendimento fpa join fpa.esusFichaProcedimento fp "
						+ " join fpa.pProntuario pp join fpa.esusTurno t join fpa.esusLocaldeatendimento "
								+ "where fp.id = " + id);
		List<EsusFichaProcedimentoAtendimento> lista = query.list();
		tx.commit();
		
		return (List<EsusFichaProcedimentoAtendimento>) lista;
	}

	

}
