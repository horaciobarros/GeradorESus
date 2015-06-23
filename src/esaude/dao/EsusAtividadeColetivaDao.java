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
import esaude.util.HibernateUtil;


public class EsusAtividadeColetivaDao extends Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public EsusAtividadeColetivaDao() {
		
		sessionFactory = HibernateUtil.getSessionFactory(); 
	}

	public List<EsusAtividadeColetiva> findNaoEnviados() {
		Transaction tx = sessionFactory.openSession()
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusAtividadeColetiva ac left join ac.tipoAtividadeColetiva "
				+ "where ac.st_envio is null or ac.st_envio=0");
		List<EsusAtividadeColetiva> lista = query.list();
		tx.commit();

		return lista;
	}
	
	public void atualiza(EsusAtividadeColetiva entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.beginTransaction().commit();
		
		
	}

	public List<EsusAtividadeColetivaParticipantes> findParticipantes(Long id) {
		Transaction tx = sessionFactory.openSession()
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusAtividadeColetivaParticipantes ap  left join ap.pprontuario where ap.atividadeColetiva.id = " + id);
		List<EsusAtividadeColetivaParticipantes> lista = query.list();
		tx.commit();

		return lista;
	}

	public List<EsusAtividadeColetivaProfissional> findProfissionais(Long id) {
		Transaction tx = sessionFactory.openSession()
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusAtividadeColetivaProfissional ap  where ap.atividadeColetiva = " + id);
		List<EsusAtividadeColetivaProfissional> lista = query.list();
		tx.commit();

		return lista;
	}

}
