package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.gov.saude.esus.cds.transport.generated.thrift.atividadecoletiva.ParticipanteRowItemThrift;
import esaude.model.EsusAtividadeColetiva;
import esaude.model.EsusAtividadeColetivaParticipantes;
import esaude.model.EsusAtividadeColetivaProfissional;
import esaude.model.EsusAtividadeColetivaPublico;
import esaude.model.EsusAtividadeColetivaTemas;
import esaude.model.PProntuario;
import esaude.util.HibernateUtil;

public class EsusAtividadeColetivaDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public EsusAtividadeColetivaDao() {

		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public List<EsusAtividadeColetiva> findNaoEnviados() {
		Transaction tx = sessionFactory.openSession().beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"select ac from EsusAtividadeColetiva ac left join ac.esusTipoatividadecoletiva "
								+ "where ac.stEnvio is null or ac.stEnvio=0");
		List<EsusAtividadeColetiva> lista = query.list();
		tx.commit();

		return (List<EsusAtividadeColetiva>) lista;
	}

	public void atualiza(EsusAtividadeColetiva entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("update EsusAtividadeColetiva set stEnvio=:stEnvio, dtEnvio=:dtEnvio where id = :id");
		query.setInteger("stEnvio", 1);
		query.setLong("id", entity.getId());
		query.setDate("dtEnvio", entity.getDtEnvio());
		query.executeUpdate();
		session.beginTransaction().commit();

		
		
	}

	public List<EsusAtividadeColetivaParticipantes> findParticipantes(Long id) {
		Transaction tx = sessionFactory.openSession().beginTransaction();
		Query query = sessionFactory
				.openSession()
				.createQuery(
						"from EsusAtividadeColetivaParticipantes pa where pa.esusAtividadeColetiva.id = "
								+ id);
		List<EsusAtividadeColetivaParticipantes> lista = query.list();
		tx.commit();

		return lista;
	}

	public List<EsusAtividadeColetivaProfissional> findProfissionais(Long id) {
		Transaction tx = sessionFactory.openSession().beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusAtividadeColetivaProfissional ap  where ap.esusAtividadeColetiva.id = "
						+ id);
		List<EsusAtividadeColetivaProfissional> lista = query.list();
		tx.commit();

		return lista;
	}
	
	public List<EsusAtividadeColetivaTemas> findTemas(Long id) {
		Transaction tx = sessionFactory.openSession().beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusAtividadeColetivaTemas at join fetch at.esusTemasparareuniao where at.esusAtividadeColetiva.id = "
						+ id);
		List<EsusAtividadeColetivaTemas> lista = query.list();
		tx.commit();

		return lista;
	}

	public List<EsusAtividadeColetivaPublico> findPublico(Long id) {
		Transaction tx = sessionFactory.openSession().beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusAtividadeColetivaPublico ap join fetch ap.esusPublicoalvo where ap.esusAtividadeColetiva.id = "
						+ id);
		List<EsusAtividadeColetivaPublico> lista = query.list();
		tx.commit();

		return lista;
	}
	
	

}
