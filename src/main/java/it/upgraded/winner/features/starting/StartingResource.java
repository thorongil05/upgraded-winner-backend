package it.upgraded.winner.features.starting;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.upgraded.winner.features.starting.mapper.StartingRestDtoMapper;
import it.upgraded.winner.features.starting.rest.StartingRestDto;
import it.upgraded.winner.features.starting.rest.ValidationResult;
import it.upgraded.winner.model.Starting;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/v1/startings")
public class StartingResource {

    private final Logger logger = Logger.getLogger(StartingResource.class);
    private final StartingService startingService;
    private final ObjectMapper objectMapper;

    @Inject
    public StartingResource(StartingService startingService, ObjectMapper objectMapper) {
        this.startingService = startingService;
        this.objectMapper = objectMapper;
    }

    @POST
    @Path("validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validate(StartingRestDto startingRestDto) {
        try {
            logger.info("Received request" + objectMapper.writeValueAsString(startingRestDto));
            Starting starting = StartingRestDtoMapper.map(startingRestDto);
            ValidationResult result = startingService.validate(starting);
            return Response.ok(result).build();
        } catch (Exception e) {
            logger.error("Error creating player", e);
            return Response.serverError().build();
        }
    }

}
