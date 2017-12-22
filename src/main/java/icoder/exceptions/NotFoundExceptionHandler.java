package icoder.exceptions;

import icoder.RestApplication;
import icoder.helpers.Json;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    private static final Logger log = LogManager.getLogger(NotFoundExceptionHandler.class);

    @Context
    HttpServletRequest request;

    @Override
    public Response toResponse(NotFoundException e) {
        log.warn(RestApplication.ENV_BRANCH.toString(), "NotFoundException: {}, remoteAddr: {}, remoteHost:{}, remotePort: {}",
                request.getRequestURI(), request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort());

        Response response = e.getResponse();
        return Response.status(response.getStatus())
                .entity(Json.newObject().put("error", e.getMessage()))
                .build();
    }
}
