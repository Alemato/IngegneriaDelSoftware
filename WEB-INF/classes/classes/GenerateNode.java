package classes;

/*
 *  VA ESEGUITO IN UNA TRANSAZIONE!!!
 *
 *  l'idea e di creare a Tranche i nodi e gli archi i modo da non riempire l'heap
 */



import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;

public class GenerateNode{
    int numeroNodiCreati;
    public GenerateNode( GraphDatabaseService graphDb, int numeroTrancheNodi, int numeroNodiCreati, int numeroNodiDaCreare){
        this.numeroNodiCreati = numeroNodiCreati;
        try ( Transaction tx = graphDb.beginTx() ){
            for ( int i = 0; i < numeroTrancheNodi && numeroNodiDaCreare > this.numeroNodiCreati; i++){ // CONDIZIONE: SE i E' MINORE DELLA TRANCHE DEI NODI E IL NUMERO DEI NODI DA CREARE E' ANCORA MAGGIORE AL NUMERO DI NODI CREATI
                graphDb.createNode(Label.label("nodo"));
                this.numeroNodiCreati++;
            }
            tx.success();
        }
        System.out.println( "generati " + this.numeroNodiCreati + " nodi");
    }
}

