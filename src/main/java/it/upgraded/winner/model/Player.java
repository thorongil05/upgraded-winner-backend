package it.upgraded.winner.model;

public class Player {

    private String name;
    private Role role;
    private String team;

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public Player setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getTeam() {
        return team;
    }

    public Player setTeam(String team) {
        this.team = team;
        return this;
    }

}
