package classes;

/*
 * INIZIALIZZARE IL PUNTATORE NEL COSTRUTTORE PRIVATE
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class GraphDb2{

    private static GraphDb2 graphDb2 = null; // ISTANZA DELLA STESSA CLASSE CHE CONTIENE I DATI, INIZIALMENTE A NULL

    private static ArrayList<File> percorsi = new ArrayList<File>();

    private static ArrayList<GraphDatabaseService> db = new ArrayList<GraphDatabaseService>();

    public static GraphDatabaseService getDb(File percorso){

        if( !graphDb2.percorsi.contains(percorso) ){ // SE L'ARRAY NON CONTIENE IL PERCORSO

            GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(percorso); // cREO IL PUNTATORE AL DB

            graphDb2.db.add(db); // AGGIUNGO IL PUNTATORE ALL'ARRAY DB

            graphDb2.percorsi.add(percorso); // AGGIUNGO IL FILE PERCORSO ALL'ARRAY PERCORSI

            System.out.println("non trovato il database, provvedo a crearlo");

            System.out.println("numero File presenti al momento: " + graphDb2.percorsi.size());

            System.out.println("numero puntatori presenti al momento: " + graphDb2.db.size() + "\n");

            return db;
        }
        else{ // SE HO GIA CREATO IL PUNTATORE AL DATABASE PERCORSO

            System.out.println("trovato il database, lo passo");

            System.out.println("numero File presenti al momento: " + graphDb2.percorsi.size());

            System.out.println("numero puntatori presenti al momento: " + graphDb2.db.size() + "\n");

            int count = 0; // INIZIALIZZO UN CONTATORE

            while(count < graphDb2.percorsi.size() ){ // CICLO PER SCORRERE L'ARRAY DEI PERCORSI

                if( ( graphDb2.percorsi.get(count) ).equals(percorso) ) break; // CONFRONTO

                count++; // EVIDENTEMENTE NON HO TROVATO IL FILE DESIDERATO, QUINDI INCREMENTO COUNT
            }

            return graphDb2.db.get(count); // EVIDENTEMENTE HO TROVATO IL PERCORSO, GLI INDICI DEI DUE ARRAY (PERCORSI E DB ) COINCIDONO, QUINDI PASSO IL PUNTATORE AL DB CHE CORRISPONDE AL DATABASE DEL PERCORSO POASSATOMI
        }
    }

}
