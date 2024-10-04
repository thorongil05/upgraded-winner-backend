package it.upgraded.winner.features.starting;

import it.upgraded.winner.features.starting.rest.ValidationResult;
import it.upgraded.winner.model.Player;
import it.upgraded.winner.model.Role;
import it.upgraded.winner.model.Starting;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.stream.*;
import java.util.Map;

@ApplicationScoped
public class StartingService {

    public ValidationResult validate(Starting starting) {
        List<String> validationErrorList = new ArrayList<>();
        List<Player> allPlayers = Stream.concat(starting.getStartingEleven().stream(), starting.getBench().stream())
                .toList();
        if (allPlayers.size() != 17) {
            validationErrorList.add("Total number of players is wrong");
        }
        Map<Role, Integer> startingElevenRolesMap = extractRolesMap(starting.getStartingEleven());
        Integer goalkeepers = startingElevenRolesMap.getOrDefault(Role.GOALKEEPER, 0);
        Integer defenders = startingElevenRolesMap.getOrDefault(Role.DEFENDER, 0);
        Integer midfielders = startingElevenRolesMap.getOrDefault(Role.MIDFIELDER, 0);
        Integer strickers = startingElevenRolesMap.getOrDefault(Role.STRICKER, 0);
        Integer total = goalkeepers + defenders + midfielders + strickers;
        if (goalkeepers != 1) {
            validationErrorList.add("Only one goalkeeper is accepted");
        }
        if (total != 11) {
            validationErrorList.add("Number of starting XI is wrong");
        }
        if (defenders == 4) {
            boolean isValid = (midfielders == 3 && strickers == 3) || (midfielders == 4 && strickers == 2)
                    || (midfielders == 5 && strickers == 1);
            if (!isValid)
                validationErrorList.add("With 4 defenders there could be only 4-3-3, 4-4-2 and 4-5-1");
        }
        if (defenders == 3) {
            boolean isValid = (midfielders == 5 && strickers == 2) || (midfielders == 4 && strickers == 3);
            if (!isValid)
                validationErrorList.add("With 3 defenders there could be only 3-5-2, 3-4-3");
        }
        return ValidationResult.of(validationErrorList);
    }

    private Map<Role, Integer> extractRolesMap(List<Player> startingEleven) {
        EnumMap<Role, Integer> rolesMap = new EnumMap<>(Role.class);
        startingEleven.stream().map(Player::getRole).forEach(role -> {
            Integer current = rolesMap.getOrDefault(role, 0);
            rolesMap.put(role, current + 1);
        });
        return rolesMap;
    }

}
