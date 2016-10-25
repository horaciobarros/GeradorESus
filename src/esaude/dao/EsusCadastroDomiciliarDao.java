package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusCadastroDomiciliar;
import esaude.model.EsusCadastroDomiciliarFamilia;
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
				"from EsusCadastroDomiciliar cd JOIN fetch cd.pProntuario pp left join fetch cd.esusSituacaodemoradia "
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
	
	public List<EsusCadastroDomiciliar> findAnterioresMesmoProntuario(Long coProntuario, Long idAtual) {
		Transaction tx = session
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusCadastroDomiciliar cd  "
				+ " where cd.idProntuarioResponsavel = " + coProntuario + " and id != " + idAtual);
		List<EsusCadastroDomiciliar> lista = query.list();
		tx.commit();

		return lista;
	}
	
	public EsusCadastroDomiciliar findById(Long id) {
		Transaction tx = session
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusCadastroDomiciliar cd   "
				+ " where cd.id = " + id);
		List<EsusCadastroDomiciliar> lista = query.list();
		tx.commit();

		return lista.get(0);
	}

	public List<EsusCadastroDomiciliarFamilia> findFamilias(Long id) {
		Transaction tx = session
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusCadastroDomiciliarFamilia f  join fetch f.esusCadastroDomiciliar "
				+ " where f.esusCadastroDomiciliar.id = " + id );
		List<EsusCadastroDomiciliarFamilia> lista = query.list();
		tx.commit();

		return lista;
	}

	public List<EsusCadastroDomiciliar>  findNaoEnviadosSemIdOrigem() {
		Transaction tx = session
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusCadastroDomiciliar cd left join fetch cd.esusSituacaodemoradia "
				+ "left join fetch cd.esusAbastecimentodeagua where (cd.stEnvio is null or cd.stEnvio=0) and cd.idOrigem is null ");
		List<EsusCadastroDomiciliar> lista = query.list();
		tx.commit();

		return lista;
	}
	
	public List<EsusCadastroDomiciliar>  findRegistrosComIdOrigem() {
		Transaction tx = session
				.beginTransaction();
		Query query = sessionFactory.openSession().createQuery(
				"from EsusCadastroDomiciliar cd left join fetch cd.esusSituacaodemoradia "
				+ "left join fetch cd.esusAbastecimentodeagua where cd.idOrigem is not null ");
		List<EsusCadastroDomiciliar> lista = query.list();
		tx.commit();

		return lista;
	}
	

}
