package esaude.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusAtendimentoIndividual;
import esaude.model.EsusAtendimentoIndividualExames;
import esaude.model.EsusAtendimentoIndividualExamesSia;
import esaude.model.EsusExames;
import esaude.util.HibernateUtil;

public class EsusAtendimentoIndividualDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;
	Session session;

	public EsusAtendimentoIndividualDao() {

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	public List<EsusAtendimentoIndividual> findNaoEnviados() {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusAtendimentoIndividual ai  left outer join fetch ai.pProntuario pp "
						+ " left outer join fetch pp.pNacionalidade pn "
						+ " left outer join fetch pp.pMunicipio pm "
						+ " left outer join fetch ai.esusLocaldeatendimento "
						+ " left outer join fetch ai.esusTurno "
						+ " left outer join fetch ai.esusTipodeatendimento "
						+ " left outer join fetch ai.esusAleitamentomaterno "
						+ " left outer join fetch ai.esusPraticasintegrativascomplementares "
								+ "where ai.stEnvio is null or ai.stEnvio=0");
		List<EsusAtendimentoIndividual> lista = query.list();
		tx.commit();
		
		return (List<EsusAtendimentoIndividual>) lista;
	}

	public void atualiza(EsusAtendimentoIndividual entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("update EsusAtendimentoIndividual set stEnvio=:stEnvio"
				+ ", dtEnvio=:dtEnvio, uuid=:uuid where id = :id");
		query.setInteger("stEnvio", 1);
		query.setLong("id", entity.getId());
		query.setDate("dtEnvio", entity.getDtEnvio());
		query.setString("uuid", entity.getUuid());
		query.executeUpdate();
		session.beginTransaction().commit();
		session.close();
	}
	
	public List<EsusAtendimentoIndividualExames> findExames(EsusAtendimentoIndividual cad) {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusAtendimentoIndividualExames e  left outer join fetch e.esusExames"
						+ " left outer join fetch e.esusAtendimentoIndividual  "
								+ "where e.esusAtendimentoIndividual.id = " + cad.getId());
		List<EsusAtendimentoIndividualExames> lista = query.list();
		tx.commit();
		
		return lista;
	}


}
