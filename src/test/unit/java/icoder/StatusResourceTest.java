package icoder;

import com.fasterxml.jackson.databind.node.ObjectNode;
import icoder.config.Config;
import icoder.helpers.Json;
import icoder.resources.StatusResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @since 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class StatusResourceTest {

  @Mock
  Config config;

  @InjectMocks
  StatusResource statusResource;

  @Test
  public void t1Status() {
    when(config.toString("applicationName")).thenReturn("icoder");
    when(config.toString("version")).thenReturn("1.0.0");
    when(config.toString("environment")).thenReturn("test");

    ObjectNode node = Json.newObject()
        .put("message",  "Service is up and running")
        .put("applicationName",  config.toString("applicationName"))
        .put("env",  config.toString("version") + ":" + config.toString("environment"));

    assertEquals(statusResource.status().get(0), node.get(0));
    assertEquals(statusResource.status().get(1), node.get(1));
    assertEquals(statusResource.status().get(2), node.get(2));
  }
}
