package it.upgraded.winner.features.starting;

import it.upgraded.winner.features.starting.rest.ValidationResult;
import it.upgraded.winner.model.Player;
import it.upgraded.winner.model.Role;
import it.upgraded.winner.model.Starting;
import it.upgraded.winner.utils.logging.Logger;
import it.upgraded.winner.utils.logging.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@ApplicationScoped
public class StartingService {

    private final Logger logger;

    @Inject
    public StartingService(LoggerFactory loggerFactory) {
        this.logger = loggerFactory.getLogger(StartingService.class);
    }

    public ValidationResult validate(Starting starting) {
        List<String> validationErrorList = new ArrayList<>();
        List<Player> allPlayers = Stream.concat(starting.getStartingEleven().stream(), starting.getBench().stream())
                .toList();
        if (allPlayers.size() != 17) {
            validationErrorList.add("Total number of players is wrong");
        }
        Map<String, Integer> teamsMap = extractTeamsMap(allPlayers);
        teamsMap.forEach((team, counter) -> {
            if (counter > 1) {
                validationErrorList.add(String.format("%s has %s players", team, counter));
            }
        });
        RoleInfo roleInfo = extractRoleInfo(starting.getStartingEleven());
        if (roleInfo.getGoalkeeper() != 1) {
            validationErrorList.add("Only one goalkeeper is accepted");
        }
        if (roleInfo.getTotal() != 11) {
            validationErrorList.add("Number of starting XI is wrong");
        }
        if (roleInfo.getDefenders() == 4
                && !isValidFourDefendersModule(roleInfo.getMidfielders(), roleInfo.getStrickers())) {
            validationErrorList.add("With 4 defenders there could be only 4-3-3, 4-4-2 and 4-5-1");
        }
        if (roleInfo.getDefenders() == 3
                && !isValidThreeDefendersModule(roleInfo.getMidfielders(), roleInfo.getStrickers())) {
            validationErrorList.add("With 3 defenders there could be only 3-5-2, 3-4-3");
        }
        return ValidationResult.of(validationErrorList);
    }

    private Map<String, Integer> extractTeamsMap(List<Player> allPlayers) {
        Map<String, Integer> teamsMap = new HashMap<>();
        allPlayers.stream().map(Player::getTeam).forEach(team -> {
            Integer current = teamsMap.getOrDefault(team, 0);
            teamsMap.put(team, current + 1);
        });
        logger.info().object("Teams map", teamsMap).end();
        return teamsMap;
    }

    private RoleInfo extractRoleInfo(List<Player> startingEleven) {
        EnumMap<Role, Integer> rolesMap = new EnumMap<>(Role.class);
        startingEleven.stream().map(Player::getRole).forEach(role -> {
            Integer current = rolesMap.getOrDefault(role, 0);
            rolesMap.put(role, current + 1);
        });
        logger.info().object("Roles map", rolesMap).end();
        Integer goalkeepers = rolesMap.getOrDefault(Role.GOALKEEPER, 0);
        Integer defenders = rolesMap.getOrDefault(Role.DEFENDER, 0);
        Integer midfielders = rolesMap.getOrDefault(Role.MIDFIELDER, 0);
        Integer strickers = rolesMap.getOrDefault(Role.STRICKER, 0);
        return new RoleInfo(goalkeepers, defenders, midfielders, strickers);
    }

    boolean isValidFourDefendersModule(int midfielders, int strickers) {
        if (midfielders == 3 && strickers == 3)
            return true;
        if (midfielders == 4 && strickers == 2)
            return true;
        return (midfielders == 5 && strickers == 1);
    }

    boolean isValidThreeDefendersModule(int midfielders, int strickers) {
        if (midfielders == 5 && strickers == 2)
            return true;
        return (midfielders == 4 && strickers == 3);
    }

    class RoleInfo {
        private int goalkeeper;
        private int defenders;
        private int midfielders;
        private int strickers;

        public RoleInfo(int goalkeeper, int defenders, int midfielders, int strickers) {
            this.goalkeeper = goalkeeper;
            this.defenders = defenders;
            this.midfielders = midfielders;
            this.strickers = strickers;
        }

        public int getGoalkeeper() {
            return goalkeeper;
        }

        public int getDefenders() {
            return defenders;
        }

        public int getMidfielders() {
            return midfielders;
        }

        public int getStrickers() {
            return strickers;
        }

        public int getTotal() {
            return goalkeeper + defenders + midfielders + strickers;
        }
    }

}
