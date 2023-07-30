package classes;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

public class Database{

    //VARIABLE ISTANCE
    private File route;

    private GraphDatabaseService db;

    private int readerCount;

    private boolean deleteRequest;


    //COSTRUCTOR
    public Database( File route ){

        this.route = route;
        this.db = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder( route )
                .setConfig( GraphDatabaseSettings.read_only, "true" )
                .newGraphDatabase();

        this.readerCount = 0;
        this.deleteRequest = false;
    }

    //METODI
    public void increaseReaderCount(){
        this.readerCount = this.readerCount + 1;
    }

    public void decreaseReaderCount(){
        this.readerCount = this.readerCount - 1;
    }

    public void setDeleteRequest(){
        this.deleteRequest = true;
    }

    public boolean equals( Database database){ // IL METODO EQUAL CONFRONTA SOLO LA ROUTE DELL' ISTANZA IN QUANTO DA QUELLO SI DETERMINA IL COLLEGAMENTO AL DATABASE
        if( this.route == database.route) return true;
        else return false;
    }

    //METODI GET
    public File getFile(){
        return this.route;
    }

    public GraphDatabaseService getDb(){
        return this.db;
    }

    public int getReaderCount(){
        return this.readerCount;
    }

    public boolean getDeleteRequest(){
        return this.deleteRequest;
    }

}