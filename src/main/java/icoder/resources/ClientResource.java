package icoder.resources;

import icoder.DBs;
import icoder.entities.Client;
import icoder.providers.DatabaseConnectionProvider;
import icoder.services.ClientService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/{dba}/client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource {

  @PathParam("dba")
  DBs database;

  @Inject
  ClientService clientService;

  @GET
  public List<Client> listAll() {
    return clientService.getAll(database);
  }

  @GET
  @Path("dummy")
  public String createData() {
    clientService.createData(database);
    return "CREATED";
  }
}
