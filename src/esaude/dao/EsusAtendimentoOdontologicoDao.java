package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusAtendimentoOdontologico;
import esaude.model.EsusAtendimentoOdontologicoEncam;
import esaude.model.EsusAtendimentoOdontologicoVigilancia;
import esaude.model.EsusCondutaencaminhamentoodonto;
import esaude.model.EsusVigilanciaemsaudebucal;
import esaude.util.HibernateUtil;

public class EsusAtendimentoOdontologicoDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;
	Session session;

	public EsusAtendimentoOdontologicoDao() {

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	public List<EsusAtendimentoOdontologico> findNaoEnviados() {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusAtendimentoOdontologico ao left join fetch ao.esusTipodeconsultaodonto t left join fetch ao.esusTurno turno "
								+ "where ao.stEnvio is null or ao.stEnvio=0");
		List<EsusAtendimentoOdontologico> lista = query.list();
		tx.commit();
		
		return (List<EsusAtendimentoOdontologico>) lista;
	}

	public void atualiza(EsusAtendimentoOdontologico entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("update EsusAtendimentoOdontologico set stEnvio=:stEnvio, dtEnvio=:dtEnvio, "
				+ "uuid=:uuid where id = :id");
		query.setInteger("stEnvio", 1);
		query.setLong("id", entity.getId());
		query.setDate("dtEnvio", entity.getDtEnvio());
		query.setString("uuid", entity.getUuid());
		query.executeUpdate();
		session.beginTransaction().commit();
		session.close();
	}
	
	public List<EsusAtendimentoOdontologicoEncam> findEncaminhamentoOdontologico(EsusAtendimentoOdontologico cad) {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusAtendimentoOdontologicoEncam e join fetch e.esusAtendimentoOdontologico ao "
						+ "join fetch e.esusCondutaencaminhamentoodonto enc "
								+ "where ao.id = " + cad.getId());
		List<EsusAtendimentoOdontologicoEncam> lista = query.list();
		tx.commit();
		
		return (List<EsusAtendimentoOdontologicoEncam>) lista;
	}
	
	public List<EsusAtendimentoOdontologicoVigilancia> findVigilancia(EsusAtendimentoOdontologico cad) {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusAtendimentoOdontologicoVigilancia v join fetch v.esusAtendimentoOdontologico ao "
						+ "join fetch v.esusVigilanciaemsaudebucal vb "
								+ "where ao.id = " + cad.getId());
		List<EsusAtendimentoOdontologicoVigilancia> lista = query.list();
		tx.commit();
		
		return (List<EsusAtendimentoOdontologicoVigilancia>) lista;
	}
	
}
