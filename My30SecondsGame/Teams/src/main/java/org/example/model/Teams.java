package org.example.model;


import java.util.Collection;

public interface Teams {

    Collection<String> names();

    Collection<Team> ColourIn(String name );

    int size();

}
