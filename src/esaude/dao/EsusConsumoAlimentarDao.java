package esaude.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import esaude.model.EsusConsumoAlimentar;
import esaude.util.HibernateUtil;

/**
 * @author Fernando Werneck - 02/05/2016
 * Analista Desenvolvedor
 * fernandowtb@hotmail.com
 * www.jwaysistemas.com.br
 * (31) 98594-8242
 */
public class EsusConsumoAlimentarDao extends Dao {
	 
		StringBuilder hql;
		private SessionFactory sessionFactory;
		private Session session;

		public EsusConsumoAlimentarDao() {

			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
		}

		public List<EsusConsumoAlimentar> findNaoEnviados() {
			Transaction tx = session.beginTransaction();
			Query query = sessionFactory
					.openSession()
					.createQuery(
							"select vd from EsusConsumoAlimentar ca  "
									+ " where vd.stEnvio is null or vd.stEnvio=0");
			List<EsusConsumoAlimentar> lista = query.list();
			tx.commit();

			return lista;
		}

		public void atualiza(EsusConsumoAlimentar entity) {
			Transaction tx = session.beginTransaction();
			session.beginTransaction();
			
			Query query = session.createQuery("update EsusConsumoAlimentar set stEnvio=:stEnvio, dtEnvio=:dtEnvio, "
					+ "uuid=:uuid where id = :id");
			query.setInteger("stEnvio", 1);
			query.setLong("id", entity.getId());
			query.setDate("dtEnvio", entity.getDtEnvio());
			query.setString("uuid", entity.getUuid());
			query.executeUpdate();
			session.beginTransaction().commit();
		}
		
}
