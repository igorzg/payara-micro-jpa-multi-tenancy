package icoder.providers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jaxrs.Jaxrs2TypesModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import icoder.config.Config;
import icoder.config.IConfig;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * JsonProvider ObjectMapper resolver
 *
 * @since 1.0
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JsonProvider implements ContextResolver<ObjectMapper> {

  static {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new Jdk8Module());
    mapper.registerModule(new JavaTimeModule());
    mapper.registerModule(new Jaxrs2TypesModule());
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.configure(SerializationFeature.CLOSE_CLOSEABLE, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    defaultObjectMapper = mapper;
  }

  private static final ObjectMapper defaultObjectMapper;

  public static ObjectMapper mapper() {
    return defaultObjectMapper;
  }

  @Inject
  @IConfig
  Config config;

  @Override
  public ObjectMapper getContext(Class<?> type) {
    if (config.isDevelopment()) {
      mapper().enable(SerializationFeature.INDENT_OUTPUT);
    }
    return mapper();
  }

}
