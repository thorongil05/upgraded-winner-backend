package it.upgraded.winner.features.starting.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.upgraded.winner.features.starting.rest.StartingPlayerRestDto;
import it.upgraded.winner.model.Player;
import it.upgraded.winner.model.Role;

class StartingRestDtoMapperTest {

  @Test
  void testMapDtoToPlayer_wrongRole() {
    StartingPlayerRestDto playerRestDto = new StartingPlayerRestDto();
    playerRestDto.setName("Buffon");
    playerRestDto.setRole("Wrong Role");
    playerRestDto.setTeam("Juventus");
    Player player = StartingRestDtoMapper.mapDtoToPlayer(playerRestDto);
    Assertions.assertEquals("Buffon", player.getName());
    Assertions.assertEquals(Role.UNDEF, player.getRole());
    Assertions.assertEquals("Juventus", player.getTeam());
  }
}
