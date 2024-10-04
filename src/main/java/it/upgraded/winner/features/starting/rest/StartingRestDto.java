package it.upgraded.winner.features.starting.rest;

import java.util.List;

public class StartingRestDto {

    private List<StartingPlayerRestDto> startingEleven;
    private List<StartingPlayerRestDto> bench;

    public List<StartingPlayerRestDto> getStartingEleven() {
        return startingEleven;
    }

    public void setStartingEleven(List<StartingPlayerRestDto> startingEleven) {
        this.startingEleven = startingEleven;
    }

    public List<StartingPlayerRestDto> getBench() {
        return bench;
    }

    public void setBench(List<StartingPlayerRestDto> bench) {
        this.bench = bench;
    }

}
