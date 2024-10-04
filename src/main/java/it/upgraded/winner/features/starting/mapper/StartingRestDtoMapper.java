package it.upgraded.winner.features.starting.mapper;

import it.upgraded.winner.features.starting.rest.StartingPlayerRestDto;
import it.upgraded.winner.features.starting.rest.StartingRestDto;
import it.upgraded.winner.model.Player;
import it.upgraded.winner.model.Role;
import it.upgraded.winner.model.Starting;

public class StartingRestDtoMapper {

  private StartingRestDtoMapper() {
  }

  public static Starting map(StartingRestDto startingRestDto) {
    Starting starting = new Starting();
    startingRestDto.getStartingEleven()
        .forEach(playerDto -> starting.getStartingEleven().add(mapDtoToPlayer(playerDto)));
    startingRestDto.getBench().forEach(playerDto -> starting.getBench().add(mapDtoToPlayer(playerDto)));
    return starting;
  }

  static Player mapDtoToPlayer(StartingPlayerRestDto playerDto) {
    Player player = new Player();
    player.setName(playerDto.getName());
    player.setRole(Role.fromString(playerDto.getRole()));
    player.setTeam(playerDto.getTeam());
    return player;
  }

}
