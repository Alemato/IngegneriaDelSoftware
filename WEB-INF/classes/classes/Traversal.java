package classes;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.cypher.internal.compiler.v2_3.pipes.matching.Relationships;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.traversal.Evaluators;

public class Traversal{

    //VAR DI ISTANZA
    long startTime;

    GraphDatabaseService graphDb;

    int nodoIniziale; // ID DEL NODO DA CUI INIZIA L'ATTRAVERSAMENTO
    int nodoFinale; // ID DEL NODO IN CUI FINISCE L'ATTRAVERSAMENTO

    Map mapNodi;
    Map mapArchi;

    String output;
    String percorso;
    int count; // CONTEGGIO DELLE ITERAZIONI

    int numeroNodiAlbero;


    //COSTRUTTORE
    public Traversal( File route, int nodo1, int nodo2){

        //INIZIALIZZAZIONI VARIABILI DI ISTANZA

        //INIZIALIZZAZIONE CONTATORE DEL TEMPO
        startTime = System.currentTimeMillis(); // INIZIO CONTO TEMPO

        //INIZIALIZZAZIONE COLLEGAMENTO DATABASE
        this.graphDb = SingletonGraphDb.getDb(route);

        // INIZIALIZZAZIONE nodo1 E nodo2
        if( nodo1 >= nodo2){
            this.nodoIniziale = nodo1;
            this.nodoFinale = nodo2;
        }
        else{
            this.nodoIniziale = nodo2;
            this.nodoFinale = nodo1;
        }

        //INIZIALIZZAZIONE Map
        this.mapNodi = null;
        this.mapArchi = null;

        this.percorso = "";


        //INIZIALIZZAZIONE OUTPUT
        this.output = "";

        //INIZIALIZZAZIONE COUNT
        this.count = 0;



        //CONTROLLO ESISTENZA NODI, SE ESISTONO ENTRAMBI ESEGUE ALTRIMENTI CONTINUA DOPO L'ELSE
        if( controlloEsistenzaNodi( graphDb, nodo1, nodo2 ) ){

            System.out.println("esistono entrambi i nodi");



            // INIZIO TRANSIZIONE
            try (Transaction tx = graphDb.beginTx()) {

                for ( Path position : graphDb.traversalDescription()
                        .relationships( RelationshipType.withName("RELAZIONE"), Direction.OUTGOING)// NAVIGO TRA RELAZIONE nella direzione in cui va la relazione		        .
                        .evaluator( Evaluators.all() ) // INDICA QUANDO SI DEVE FERMARE
                        .traverse( graphDb.getNodeById(nodoIniziale) ) // NODO DI PARTENZA
                        )

                {// INIZIO CORPO DEL FOR




                    // CASO IN CUI NON TROVO IL NODO, OVVERO NON SI TROVA SULLO SESSO PERCORSO
                    if( position.endNode().getId() == 0 && nodoFinale != 0){

                        output = "non ho trovato uno dei due nodi sul percorso";

                        percorso = position + "\n";

                        break;
                    }





                    // CASO IN CUI TROVO IL NODO
                    if( position.endNode().getId() == nodoFinale){
                        output = "i nodi sono sullo stesso percorso ";

                        if(count != 0){ //

                            //SOMMA DELLE MAPPE DELL'ULTIMA RELAZIONE
                            Map map = position.lastRelationship().getAllProperties();// mappa dell'ultima relazione
                            if( mapArchi == null) mapArchi = map; // SE E' LA PRIMA VOLTA CHE RIEMPIO LA MIA MAP, TANTO VALE CHE LA RENDO UGUALE A QUELLA A CUI DOVREI SOMMARLA
                            else sommaMap( mapArchi, map);

                        }

                        Map map = position.endNode().getAllProperties();// mappa dell'ultimo nodo
                        if( mapNodi == null) mapNodi = map; // SE E' LA PRIMA VOLTA CHE RIEMPIO LA MIA MAP, TANTO VALE CHE LA RENDO UGUALE A QUELLA A CUI DOVREI SOMMARLA
                        else sommaMap( mapNodi, map);


                        percorso = position + "\n";

                        break; // ESCO DAL CICLO FOR
                    }
                    // FINE CASO IN CUI TROVO IL NODO

                    percorso = position + "\n";









                    // SE SONO ARRIVATO A QUESTO PUNTO NON HO RAGGIUNTO IL NODO RADICE E NON HO TROVATO IL SECONDO NODO CERCATO

                    //SOMMA MAPPA RELAZIONE
                    if(count != 0){ // SOMMA DELLE MAPPE DELLE RELAZIONI, AVVIENE SOLO SE NON E' IL PRIMO NODO CHE VISITO IN QUANTO NON ESISTE L'ARCO PRECEDENTE

                        Map map = position.lastRelationship().getAllProperties();// mappa dell'ultima relazione
                        if( mapArchi == null) mapArchi = map;
                        else sommaMap( mapArchi, map);

                    }

                    count++;

                    //SOMMA MAPPA NODO
                    Map map = position.endNode().getAllProperties();// mappa dell'ultimo nodo
                    if( mapNodi == null) mapNodi = map;
                    else sommaMap( mapNodi, map);










                }// FINE CORPO FOR

                long endTime = System.currentTimeMillis();
                startTime = (endTime - startTime);
            }//FINE TRANSAZIONE

        }
        else{
            this.output = "uno dei due nodi non trovati";
            System.out.println(output);

            long endTime = System.currentTimeMillis();
            startTime = (endTime - startTime);
        }
        // FINE TRANSIZIONE

        SingletonGraphDb.decreaseCount(route);// UNA VOLTA FINITA LA RACCOLTA DEI DATI DA PARTE DELL'INTERROGAZIONE DECREMENTO L'INTERROGAZIONE
    }
    //FINE COSTRUTTORE

    public static void sommaMap( Map map1, Map map2){

        Object[] chiavi1 = map1.keySet().toArray();//array contenente le chiavi delle mappe


        for( int i = 0; i<map1.size(); i++){

            int x =(int) map1.get( (String)chiavi1[i] ) + (int) map2.get( (String)chiavi1[i] ); // sommo dentro x il valore della chiave i di map1 e di map2

            map1.put((String)chiavi1[i], x);
        }
    }

    public static void sommaMap2( Map map1, Map map2){

        Object[] chiavi1 = map1.keySet().toArray();//array contenente le chiavi delle mappe

        for( int i = 0; i<map1.size(); i++){

            int x =(int) map1.get( (String)chiavi1[i] ) + (int) map2.get( (String)chiavi1[i] ); // sommo dentro x il valore della chiave i di map1 e di map2

            map1.put((String)chiavi1[i], x);
        }
    }


    public static boolean controlloEsistenzaNodi( GraphDatabaseService graphDb, int nodo1, int nodo2 ){

        int numeroNodiAlbero = 0;

        try (Transaction tx = graphDb.beginTx()) {

            ResourceIterable<Node> iterable =  graphDb.getAllNodes();
            ResourceIterator<Node> iterator = iterable.iterator();

            while ( iterator.hasNext() ) {

                Object item = iterator.next();

                numeroNodiAlbero++;
            }
        }
        numeroNodiAlbero = numeroNodiAlbero - 1;

        System.out.println("trovati: " + numeroNodiAlbero + " nodi");

        if ( nodo1 <= numeroNodiAlbero && nodo2 <= numeroNodiAlbero) return true; // SE ENTRAMBI I NODI ESISTONO TORNA TRUE, ALTRIMENTI FALSE
        else return false;
    }




}