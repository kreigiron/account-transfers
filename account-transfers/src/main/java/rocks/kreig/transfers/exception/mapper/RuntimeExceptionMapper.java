package rocks.kreig.transfers.exception.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    private static final Logger logger = LoggerFactory.getLogger(RuntimeExceptionMapper.class);

    @Override
    public Response toResponse(final RuntimeException e) {
        logger.warn(e.getMessage(), e);

        if(e instanceof IllegalArgumentException) {
            return Response.status(Response.Status.PRECONDITION_REQUIRED).entity(e.getMessage()).build();
        }

        if(e instanceof IllegalStateException) {
            return Response.status(Response.Status.PRECONDITION_REQUIRED).entity(e.getMessage()).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
