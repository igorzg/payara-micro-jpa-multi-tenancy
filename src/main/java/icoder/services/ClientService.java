package icoder.services;

import icoder.DBs;
import icoder.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.UUID;


@Stateless
public class ClientService extends AbstractService<Client> {

  @Override
  Class<Client> getEntityClass() {
    return Client.class;
  }


  public void createData(DBs database) {
    for (int i = 0; i < 10; i++) {
      Client client = new Client();
      client.setName("name_" + database.toString() + "_" + i + "_" + UUID.randomUUID().toString());
      EntityManager entityManager = databaseConnectionProvider.getEntityManager(database);
      entityManager.persist(client);
    }
  }

}
