package icoder.services;

import icoder.DBs;
import icoder.entities.Client;
import icoder.providers.DatabaseConnectionProvider;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


abstract class AbstractService<E> {

  @Inject
  DatabaseConnectionProvider databaseConnectionProvider;

  abstract Class<E> getEntityClass();

  public List<E> getAll(DBs database) {
    EntityManager entityManager = databaseConnectionProvider.getEntityManager(database);
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<E> c = cb.createQuery(getEntityClass());
    c.from(getEntityClass());
    return entityManager.createQuery(c).getResultList();
  }
}
