package org.example;


import org.example.memory.TeamsDb;
import org.example.model.Team;
import org.example.model.Teams;

import java.io.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class readCsvFile {

    private static final Set<String> WANTED_FEATURES = Set.of(
            "Red".toLowerCase(),
            "Blue".toLowerCase(),
            "Brown".toLowerCase(),
            "Yellow".toLowerCase(),
            "Green".toLowerCase()
    );

    public Teams readCsvFile(File csvFile) throws FileNotFoundException {
        LineNumberReader lineNumberReader = new LineNumberReader( new FileReader(csvFile));
        try( final LineNumberReader in = Objects.requireNonNull( lineNumberReader )){
            in.readLine();
            final Teams db = parseDataLines( in );
            in.getLineNumber();
            return db;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Teams parseDataLines(final LineNumberReader in) throws IOException {
        final Set<Team> allTeams = in.lines()
                .map( this::splitLineIntoValues )
                .filter(this::isLineAWantedFeature )
                .map( this::asTeam )
                .collect( Collectors.toSet() );
        return new TeamsDb( allTeams ) {
        };
    }

    Team asTeam(String[] values ) {
        return new Team( values[0], values[1]);
    }

    private boolean isLineAWantedFeature(String[] o) {
        if( o.length >= 1 && o.length <= 2){
            if(!o[0].contains("\"") && o[1].length() > 1){
                return WANTED_FEATURES.contains( o[1].toLowerCase() );
            }
        }
        return false;
    }

    String[] splitLineIntoValues(String s) {
        return s.trim().split(",");
    }
}
