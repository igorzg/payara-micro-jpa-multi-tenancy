package icoder.features;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 * Enable jackson feature
 * @since 1.0
 */
public class JacksonFeature implements Feature {

  private final static String JSON_FEATURE = JacksonFeature.class.getSimpleName();

  static String getPropertyNameForRuntime(String key, RuntimeType runtimeType) {
    if (runtimeType != null && key.startsWith("jersey.config")) {
      RuntimeType[] types = RuntimeType.values();
      for (RuntimeType type : types) {
        if (key.startsWith("jersey.config." + type.name().toLowerCase())) {
          return key;
        }
      }
      return key.replace("jersey.config", "jersey.config." + runtimeType.name().toLowerCase());
    }
    return key;
  }

  @Override
  public boolean configure(FeatureContext context) {

    context.property( "jersey.config.server.disableMoxyJson", true);

    final Configuration config = context.getConfiguration();
    // Disable other JSON providers.
    context.property(getPropertyNameForRuntime("jersey.config.jsonFeature", config.getRuntimeType()), JSON_FEATURE);
    // Register Jackson.
    if (!config.isRegistered(JacksonJaxbJsonProvider.class)) {
      // add the default Jackson exception mappers
      context.register(JsonParseExceptionMapper.class);
      context.register(JsonMappingExceptionMapper.class);
      context.register(JacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
    }
    return true;
  }
}
