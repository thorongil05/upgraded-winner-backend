package it.upgraded.winner.features.player;

import java.util.List;

import org.jboss.logging.Logger;

import it.upgraded.winner.features.player.rest.PlayerRestDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/players")
public class PlayerResource {

    private static final Logger logger = Logger.getLogger(PlayerResource.class);
    private final PlayerService playerService;

    @Inject
    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PlayerRestDto player) {
        try {
            logger.info(player);
            playerService.savePlayer(player);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Error creating player", e);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("retrieve")
    public Response retrieve() {
        List<PlayerRestDto> playerList = playerService.fetchPlayerByName();
        return Response.ok(playerList).build();
    }

}
