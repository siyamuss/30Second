package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.example.model.Team;
import org.example.model.Teams;
import picocli.CommandLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;

@CommandLine.Command( name = "TeamService", mixinStandardHelpOptions = true )
public class TeamService implements Runnable {

    public static final int DEFAULT_PORT = 7090;

    private Javalin server;
    private File dataFile;
    private Teams teams;

    public static void main(String[] args) throws FileNotFoundException {
        final TeamService service = new TeamService().initialise();
        final int exitCode = new CommandLine( service ).execute( args );
    }

    private TeamService initialise() {
        teams = initTeamDb();
        server = initHttpServer();
        return this;

    }

    File dataFile(){
        return dataFile != null
                ? dataFile
                : new File( "C:\\Users\\Administrator\\Documents\\student\\demoGame30seconds\\NamesOfTeams.csv" );

    }

    private Javalin initHttpServer() {
        return Javalin.create()
                .get( "/teams", ctx -> ctx.json( teams.names() ))
                .get( "/team/{name}", this::getTeam );

    }

    private Context getTeam(Context context) {
        final String team = context.pathParam( "name" );
        final Collection<Team> towns = teams.ColourIn(team);
        return context.json( towns );
    }

    private Teams initTeamDb() {
        try{
           return new readCsvFile().readCsvFile( dataFile() );
        } catch (Exception e) {
            dataFile = new File( "C:\\Users\\Administrator\\Documents\\student\\My30SecondsGame\\NamesOfTeams.csv" );
            return initTeamDb();
        }
    }

    @Override
    public void run() {
        this.server.start( DEFAULT_PORT );
    }
}
