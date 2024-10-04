package it.upgraded.winner.features.starting;

import org.jboss.logging.Logger;

import it.upgraded.winner.features.player.rest.PlayerRestDto;
import it.upgraded.winner.features.starting.mapper.StartingRestDtoMapper;
import it.upgraded.winner.features.starting.rest.StartingRestDto;
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
@Path("/startings")
public class StartingResource {

    private final Logger logger = Logger.getLogger(StartingResource.class);
    private final StartingService startingService;

    @Inject
    public StartingResource(StartingService startingService) {
        this.startingService = startingService;
    }

    @POST
    @Path("validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(StartingRestDto startingRestDto) {
        try {
            Starting starting = StartingRestDtoMapper.map(startingRestDto);
            ValidationResult result = startingService.validate(starting);
            return Response.ok(result).build();
        } catch (Exception e) {
            logger.error("Error creating player", e);
            return Response.serverError().build();
        }
    }

}
