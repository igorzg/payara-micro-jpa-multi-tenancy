package icoder.providers;

import icoder.DBs;
import icoder.exceptions.DatabaseException;
import icoder.exceptions.Errors;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;


@Stateless(name = "DatabaseConnectionProvider")
public class DatabaseConnectionProvider {

    @Resource
    private EJBContext context;

    public EntityManager getEntityManager(DBs country) {
        try {
            return (EntityManager) context.lookup("em/" + country.toString());
        } catch (Exception e) {
            throw new DatabaseException(Errors.INVALID_COUNTRY + ":" + country, e);
        }
    }
}
