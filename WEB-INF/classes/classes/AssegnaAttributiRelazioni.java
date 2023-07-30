package classes;

import java.util.concurrent.ThreadLocalRandom;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class AssegnaAttributiRelazioni{

    int numeroRelazioniAssegnate;

    public AssegnaAttributiRelazioni( GraphDatabaseService graphDb, int numeroRelazioniDaAssegnare, int numeroRelazioniAssegnate, int numeroTrancheRelazioni , String nomiAttributiRelazioni[],String minAttributiRelazioni[],String maxAttributiRelazioni[] ){

        this.numeroRelazioniAssegnate = numeroRelazioniAssegnate;

        try ( Transaction tx = graphDb.beginTx() ){

            for ( int i = 0; i < numeroTrancheRelazioni && numeroRelazioniDaAssegnare > this.numeroRelazioniAssegnate; i++ ){ // CICLO PER SCORRERE LE RELAZIONI IN BASE ALL'ID

                for( int e = 0; e < nomiAttributiRelazioni.length; e++ ){ // CICLO PER SCORRERE GLI ATTRIBUTI

                    int x = ThreadLocalRandom.current().nextInt(Integer.parseInt(minAttributiRelazioni[e]), Integer.parseInt(maxAttributiRelazioni[e] + 1));
                    graphDb.getRelationshipById(this.numeroRelazioniAssegnate).setProperty(nomiAttributiRelazioni[e], x );
                }

                this.numeroRelazioniAssegnate++;
            }
            System.out.println( "assegnate " + this.numeroRelazioniAssegnate + " relazioni");
            tx.success();
        }
    }
}