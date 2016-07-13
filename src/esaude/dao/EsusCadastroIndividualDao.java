package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusAtividadeColetivaPublico;
import esaude.model.EsusCadastroIndividual;
import esaude.model.EsusCadastroIndividualDeficiencia;
import esaude.model.EsusCadastroIndividualHigienepessoalsituacaorua;
import esaude.util.HibernateUtil;

public class EsusCadastroIndividualDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;
	Session session;

	public EsusCadastroIndividualDao() {

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}

	public List<EsusCadastroIndividual> findNaoEnviados() {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusCadastroIndividual ci JOIN fetch ci.pProntuario pp "
						+ " left outer join fetch pp.pNacionalidade pn "
						+ " left outer join fetch pp.pMunicipio pm "
						+ " left outer join fetch ci.esusMotivosaida "
								+ "where ci.stEnvio is null or ci.stEnvio=0");
		//query.setMaxResults(1);
		List<EsusCadastroIndividual> lista = query.list();
		tx.commit();
		
		return (List<EsusCadastroIndividual>) lista;
	}

	public void atualiza(EsusCadastroIndividual entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("update EsusCadastroIndividual set stEnvio=:stEnvio, dtEnvio=:dtEnvio, uuid=:uuid where id = :id");
		query.setInteger("stEnvio", 1);
		query.setLong("id", entity.getId());
		query.setDate("dtEnvio", entity.getDtEnvio());
		query.setString("uuid", entity.getUuid());
		query.executeUpdate();
		session.beginTransaction().commit();
		session.close();
	}
	
	public List<EsusCadastroIndividualDeficiencia> findDeficiencias(Long id) {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusCadastroIndividualDeficiencia cid join fetch cid.esusDeficienciacidadao where cid.esusCadastroIndividual.id = "
						+ id);
		List<EsusCadastroIndividualDeficiencia> lista = query.list();
		tx.commit();
		return lista;
	}
	
	public List<EsusCadastroIndividualHigienepessoalsituacaorua> findHigiene(Long id) {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusCadastroIndividualHigienepessoalsituacaorua h join fetch h.esusAcessohigiene where h.esusCadastroIndividual.id = "
						+ id);
		List<EsusCadastroIndividualHigienepessoalsituacaorua> lista = query.list();
		tx.commit();

		return lista;
	}

	public List<EsusCadastroIndividual> findAnterioresMesmoProntuario(Long coProntuario, Long idAtual) {
		Transaction tx = session.beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusCadastroIndividual ci JOIN fetch ci.pProntuario pp "
								+ "where pp.coProntuario = " + coProntuario + " and ci.id != " + idAtual);
		//query.setMaxResults(1);
		List<EsusCadastroIndividual> lista = query.list();
		tx.commit();
		
		return (List<EsusCadastroIndividual>) lista;
	}

}
