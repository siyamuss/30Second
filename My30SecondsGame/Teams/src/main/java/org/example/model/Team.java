package org.example.model;

public class Team implements Comparable<Team>{

    private final String name;

    private final String colour;

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public Team(String value, String value1) {
        name = value;
        colour = value1;
    }

    @Override
    public int compareTo(Team o) {
        return 0;
    }

    @Override
    public String toString(){
        return "Team{"
                + getName()
                + ", "
                + getColour()
                + "}";
    }
}
