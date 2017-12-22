package icoder;

import icoder.setup.IntegrationTestSetup;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Integration
 *
 * @since 1.0
 */
@RunWith(Arquillian.class)
public class StatusResourceIntegrationTest extends IntegrationTestSetup {

    @Test
    public void checkStatus(@ArquillianResource URL baseURL) throws Exception {
        Response result = ClientBuilder.newClient().target(baseURL.toURI()).request().get();
        assertThat(result.getStatus(), is(200));
    }

}