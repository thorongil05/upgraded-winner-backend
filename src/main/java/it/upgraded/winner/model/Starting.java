package it.upgraded.winner.model;

import java.util.List;
import java.util.ArrayList;

public class Starting {

    private List<Player> startingEleven = new ArrayList<>();
    private List<Player> bench = new ArrayList<>();

    public List<Player> getStartingEleven() {
        return startingEleven;
    }

    public void setStartingEleven(List<Player> startingEleven) {
        this.startingEleven = startingEleven;
    }

    public List<Player> getBench() {
        return bench;
    }

    public void setBench(List<Player> bench) {
        this.bench = bench;
    }

}
