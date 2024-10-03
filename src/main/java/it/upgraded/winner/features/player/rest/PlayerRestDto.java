package it.upgraded.winner.features.player.rest;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema
public class PlayerRestDto {

    private String name;
    private String role;
    private String team;

    @Schema(title = "name", example = "Name Surname")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Schema(title = "role", example = "DEFENDER")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Schema(title = "team", example = "REAL MADRID")
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

}
