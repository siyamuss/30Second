package org.example.memory;


import org.example.model.Team;
import org.example.model.Teams;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class TeamsDb implements Teams {

    private Set<Team> teams;
    public TeamsDb(Set<Team> allTowns) {
        teams = allTowns;
    }


    @Override
    public Collection<String> names() {
        return teams.parallelStream()
                .map( team -> team.getName() )
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Team> ColourIn( String name ) {
        return teams.parallelStream()
                .filter( aColour -> aColour.getName().equals( name ) )
                .collect( Collectors.toSet() );
    }

    @Override
    public int size() {
        return teams.size();
    }
}
