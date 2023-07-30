package classes;

import java.util.concurrent.ThreadLocalRandom;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class AssegnaAttributiNodi{

    int numeroNodiAssegnati;

    public AssegnaAttributiNodi( GraphDatabaseService graphDb, int numeroNodiDaAssegnare, int numeroNodiAssegnati, int numeroTrancheNodi , String nomiAttributiNodi[],String minAttributiNodi[], String maxAttributiNodi[] ){

        this.numeroNodiAssegnati = numeroNodiAssegnati;

        try ( Transaction tx = graphDb.beginTx() ){



            for ( int i = 0; i < numeroTrancheNodi && numeroNodiDaAssegnare > this.numeroNodiAssegnati; i++ ){ // CICLO PER SCORRERE I NODI IN BASE ALL'ID


                for( int e = 0; e < nomiAttributiNodi.length; e++ ){ // CICLO PER SCORRERE GLI ATTRIBUTI

                    int x = ThreadLocalRandom.current().nextInt(Integer.parseInt(minAttributiNodi[e]), Integer.parseInt(maxAttributiNodi[e] + 1));
                    graphDb.getNodeById(this.numeroNodiAssegnati).setProperty(nomiAttributiNodi[e], x );
                }

                this.numeroNodiAssegnati++;
            }

            System.out.println( "assegnati " + this.numeroNodiAssegnati + " nodi");
            tx.success();
        }
    }
}