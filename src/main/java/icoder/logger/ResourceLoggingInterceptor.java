package iconfig.logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import icoder.RestApplication;
import icoder.helpers.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class ResourceLoggingInterceptor implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceLoggingInterceptor.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (LOGGER.isDebugEnabled()) {
            ObjectNode result = Json.newObject()
                    .put("Path", requestContext.getUriInfo().getPath(false))
                    .put("Method", requestContext.getMethod())
                    .put("Query parameters", requestContext.getUriInfo().getQueryParameters().toString())
                    .put("Path parameters", requestContext.getUriInfo().getPathParameters().toString());
            LOGGER.debug(RestApplication.ENV_BRANCH, Json.toString(result));
        }
    }
}
