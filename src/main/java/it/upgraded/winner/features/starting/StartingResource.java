package it.upgraded.winner.features.starting;

import it.upgraded.winner.features.starting.mapper.StartingRestDtoMapper;
import it.upgraded.winner.features.starting.rest.StartingRestDto;
import it.upgraded.winner.features.starting.rest.ValidationResult;
import it.upgraded.winner.model.Starting;
import it.upgraded.winner.utils.logging.Logger;
import it.upgraded.winner.utils.logging.LoggerFactory;
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

    private final Logger logger;
    private final StartingService startingService;

    @Inject
    public StartingResource(StartingService startingService, LoggerFactory loggerFactory) {
        this.startingService = startingService;
        this.logger = loggerFactory.getLogger(StartingResource.class);
    }

    @POST
    @Path("validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validate(StartingRestDto startingRestDto) {
        try {
            logger.info().object("Received request", startingRestDto).end();
            Starting starting = StartingRestDtoMapper.map(startingRestDto);
            ValidationResult result = startingService.validate(starting);
            return Response.ok(result).build();
        } catch (Exception e) {
            logger.error().token("Error creating player", e.getMessage());
            return Response.serverError().build();
        }
    }

}
