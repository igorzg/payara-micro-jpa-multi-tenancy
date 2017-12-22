package icoder.resources;

import com.fasterxml.jackson.databind.node.ObjectNode;
import icoder.config.Config;
import icoder.config.IConfig;
import icoder.helpers.Json;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * StatusResource controller
 *
 * @since 1.0
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class StatusResource {

  @Inject
  @IConfig
  Config config;

  @GET
  public ObjectNode status() {
    return Json.newObject()
        .put("message",  "Service is up and running")
        .put("applicationName",  config.toString("applicationName"))
        .put("env",  config.toString("version") + ":" + config.toString("environment"));
  }

}
