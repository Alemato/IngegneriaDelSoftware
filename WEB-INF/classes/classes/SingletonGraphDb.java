package classes;

//CREARE UN OGGETTO CHE CONTIENE IL FILE E IL PUNTATORE CORRISPONDENTE

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class SingletonGraphDb{

    //VARIABILI DI ISATANZA
    private static SingletonGraphDb instance = null; // ISTANZA DELLA STESSA CLASSE CHE CONTIENE I DATI, INIZIALMENTE A NULL

    private ArrayList<Database> databaseList = new ArrayList<Database>();


    //COSTRUTTORE
    private SingletonGraphDb(){

    }

    //METODI
    public static GraphDatabaseService getDb( File percorso){

        if( instance == null ) instance = new SingletonGraphDb(); // SE L'ISTANZA E' A NULL(QUINDI E' LA PRIMA VOLTA CHE LA USO) LA INIZIALIZZO

        if( !controllaEsistenzaFile( instance.databaseList, percorso ) ){ // SE L'ARRAY NON CONTIENE IL PERCORSO....

            System.out.println("non trovato il database, provvedo a crearlo");

            //CREO ED AGGIUNGO IL DATABASE
            instance.databaseList.add( new Database(percorso) ); // AGGIUNGO IL PERCORSO

            int x = instance.databaseList.size() - 1; // CALCOLO L'INDICE DEL DATABASE

            System.out.println("incrementato il contatore");

            instance.databaseList.get( x ).increaseReaderCount(); // INCREASE, INCREMENTO IL CONTATORE DEGLI UTILIZZATORI PRIMA DI PASSARE IL DB

            return instance.databaseList.get( x ).getDb(); // RESTITUISCO IL PUNTATORE AL DATABASE
        }

        else{ // SE HO GIA CREATO IL PUNTATORE AL DATABASE PERCORSO

            System.out.println("trovato il database, lo passo");

            System.out.println("numero File presenti al momento: " + instance.databaseList.size());

            System.out.println("numero puntatori presenti al momento: " +  instance.databaseList.size() + "\n");

            int count = 0; // INIZIALIZZO UN CONTATORE

            while(count < instance.databaseList.size() ){ // CICLO PER SCORRERE L'ARRAY DEI PERCORSI

                if( ( instance.databaseList.get(count).getFile() ).equals(percorso) ) break; // CONFRONTO

                count++; // EVIDENTEMENTE NON HO TROVATO IL FILE DESIDERATO, QUINDI INCREMENTO COUNT
            }

            System.out.println("incrementato il contatore");

            instance.databaseList.get( count ).increaseReaderCount(); // INCREASE, INCREMENTO IL CONTATORE DEGLI UTILIZZATORI PRIMA DI PASSARE IL DB

            return instance.databaseList.get(count).getDb(); // EVIDENTEMENTE HO TROVATO IL PERCORSO, GLI INDICI DEI DUE ARRAY (PERCORSI E DB ) COINCIDONO, QUINDI PASSO IL PUNTATORE AL DB CHE CORRISPONDE AL DATABASE DEL PERCORSO POASSATOMI
        }

    }

    private static boolean controllaEsistenzaFile( ArrayList<Database> databaseList, File route ){

        for( int i = 0; i < databaseList.size(); i++ ){

            if( databaseList.get(i).getFile().equals(route) ){
                return true;
            }
        }
        return false;
    }

    public static void decreaseCount( File route){


        int count = 0; // INIZIALIZZO UN CONTATORE

        while(count < instance.databaseList.size() ){ // CICLO PER SCORRERE L'ARRAY DEI PERCORSI

            if( ( instance.databaseList.get(count).getFile() ).equals(route) ) break; // CONFRONTO

            count++; // EVIDENTEMENTE NON HO TROVATO IL FILE DESIDERATO, QUINDI INCREMENTO COUNT
        }

        instance.databaseList.get(count).decreaseReaderCount(); // DECREASE

        System.out.println("decrementato il contatore");

        if ( instance.databaseList.get(count).getDeleteRequest() == true && instance.databaseList.get(count).getReaderCount() == 0 ){

            //CANCELLARE DAL FILE DI TESTO IL DATABASE

            //CANCELLO IL PUNTATORE
            System.out.println("cancello il puntatore ");
            instance.databaseList.remove(count);

            // ELIMINO IL DATABASE
            System.out.println("elimino il database");
            new Delete2(route);

        }
    }

    public static boolean requestDelete( File route){ // TORNA TRUE SE HO ELIMINATO SUBITO IL DATABASE

        int count = 0; // INIZIALIZZO UN CONTATORE

        while(count < instance.databaseList.size() ){ // CICLO PER SCORRERE L'ARRAY DEI PERCORSI

            if( ( instance.databaseList.get(count).getFile() ).equals(route) ) break; // CONFRONTO

            count++; // EVIDENTEMENTE NON HO TROVATO IL FILE DESIDERATO, QUINDI INCREMENTO COUNT
        }

        System.out.println("setto deleteRequest a true ");
        instance.databaseList.get( count ).setDeleteRequest();

        if( instance.databaseList.get( count ).getDeleteRequest() == true && instance.databaseList.get( count ).getReaderCount() == 0 ){ //ESEGUO IL CONTROLLO PER SAPERE SE POSSO CANCELLARE SUBITO IL DATABASE

            //CANCELLARE DAL FILE DI TESTO IL DATABASE

            //CANCELLO IL PUNTATORE
            System.out.println("cancello il puntatore ");
            instance.databaseList.remove(count);

            // ELIMINO IL DATABASE
            System.out.println("elimino il database");
            new Delete2(route);

            return true; // RESTITUISCO TRUE PER DIRE CHE HO ELIMINATO IL DATABASE
        }
        else return false; // RESTITUISCO FALSE PER DIRE CHE NON HO ELIMINATO ANCORA IL DATABASE

    }
}