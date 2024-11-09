package it.upgraded.winner.features.starting;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.upgraded.winner.features.starting.rest.ValidationResult;
import it.upgraded.winner.model.Player;
import it.upgraded.winner.model.Role;
import it.upgraded.winner.model.Starting;
import it.upgraded.winner.utils.logging.LoggerFactoryMockProvider;

class StartingServiceTest {

  private static StartingService startingService;

  @BeforeAll
  static void setup() {
    startingService = new StartingService(LoggerFactoryMockProvider.provide());
  }

  @Test
  void testValidation() {
    Starting starting = new Starting();
    starting.setStartingEleven(List.of(new Player().setName("Buffon").setRole(Role.GOALKEEPER).setTeam("Juventus"),
        new Player().setName("Cordoba").setRole(Role.DEFENDER).setTeam("Inter"),
        new Player().setName("Costacurta").setRole(Role.DEFENDER).setTeam("Milan"),
        new Player().setName("Stam").setRole(Role.DEFENDER).setTeam("Lazio"),
        new Player().setName("Materazzi").setRole(Role.DEFENDER).setTeam("Perugia"),
        new Player().setName("Veron").setRole(Role.MIDFIELDER).setTeam("Parma"),
        new Player().setName("Italiano").setRole(Role.MIDFIELDER).setTeam("Verona"),
        new Player().setName("Doni").setRole(Role.MIDFIELDER).setTeam("Atalanta"),
        new Player().setName("Totti").setRole(Role.STRICKER).setTeam("Roma"),
        new Player().setName("Iaquinta").setRole(Role.STRICKER).setTeam("Udinese"),
        new Player().setName("Cozza").setRole(Role.STRICKER).setTeam("Reggina")));
    starting.setBench(List.of(new Player().setName("Mensah").setRole(Role.DEFENDER).setTeam("Chievo Verona"),
        new Player().setName("Juarez").setRole(Role.DEFENDER).setTeam("Como"),
        new Player().setName("Amoroso").setRole(Role.MIDFIELDER).setTeam("Bologna"),
        new Player().setName("Appiah").setRole(Role.MIDFIELDER).setTeam("Brescia"),
        new Player().setName("Rocchi").setRole(Role.STRICKER).setTeam("Empoli"),
        new Player().setName("Vignaroli").setRole(Role.STRICKER).setTeam("Modena")));
    ValidationResult result = startingService.validate(starting);
    Assertions.assertTrue(result.isValid());
  }

  @Test
  void testValidation_TwoPlayersSameTeam() {
    Starting starting = new Starting();
    starting.setStartingEleven(List.of(new Player().setName("Buffon").setRole(Role.GOALKEEPER).setTeam("Juventus"),
        new Player().setName("Cordoba").setRole(Role.DEFENDER).setTeam("Inter"),
        new Player().setName("Costacurta").setRole(Role.DEFENDER).setTeam("Milan"),
        new Player().setName("Stam").setRole(Role.DEFENDER).setTeam("Lazio"),
        new Player().setName("Materazzi").setRole(Role.DEFENDER).setTeam("Perugia"),
        new Player().setName("Veron").setRole(Role.MIDFIELDER).setTeam("Parma"),
        new Player().setName("De Rossi").setRole(Role.MIDFIELDER).setTeam("Roma"),
        new Player().setName("Doni").setRole(Role.MIDFIELDER).setTeam("Atalanta"),
        new Player().setName("Totti").setRole(Role.STRICKER).setTeam("Roma"),
        new Player().setName("Iaquinta").setRole(Role.STRICKER).setTeam("Udinese"),
        new Player().setName("Cozza").setRole(Role.STRICKER).setTeam("Reggina")));
    starting.setBench(List.of(new Player().setName("Mensah").setRole(Role.DEFENDER).setTeam("Chievo Verona"),
        new Player().setName("Juarez").setRole(Role.DEFENDER).setTeam("Como"),
        new Player().setName("Amoroso").setRole(Role.MIDFIELDER).setTeam("Bologna"),
        new Player().setName("Appiah").setRole(Role.MIDFIELDER).setTeam("Brescia"),
        new Player().setName("Rocchi").setRole(Role.STRICKER).setTeam("Empoli"),
        new Player().setName("Vignaroli").setRole(Role.STRICKER).setTeam("Modena")));
    ValidationResult result = startingService.validate(starting);
    Assertions.assertFalse(result.isValid());
  }

}
