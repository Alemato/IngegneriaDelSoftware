package classes;

/*
 * NON FINISCE I NODI, NE CREA UN NUMERO IN BASE TOT MA NN FA GLI ULTIMI
 */

import java.io.File;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class GenerateTree{

    GraphDatabaseService graphDb;

    int numeroNodiDaCreare;  // NUMERO DEI NODI DA CREARE IN TOTALE
    int numeroNodiCreati;
    final int numeroTrancheNodi = 50000;

    int numeroRelazioniDaCreare;
    int numeroRelazioniCreate;
    final int numeroTrancheRelazioni = 50000;
    int countSplitSize; // SE IL countSplitSize E' UGUALE ALLO splitSize VA INCREMENTATO IL CONTATORE DEL PADRE E RIMESSO A ZERO countSplitSize
    int countNodoPadre;
    int countNodoFiglio;

    int numeroNodiDaAssegnare;  // NUMERO DEI NODI A CUI ATTRBUIRE GLI ATTRIBUTI
    int numeroNodiAssegnati; // NUMERO DEI NODI ASSEGNATI FINO AD ORA

    int numeroRelazioniAssegnate;
    int numeroRelazioniDaAssegnare;


    File percorso;

    public GenerateTree( int splitSize, int dept, File percorso, String nomiAttributiNodi[],String minAttributiNodi[], String maxAttributiNodi[], String nomiAttributiRelazioni[], String minAttributiRelazioni[], String maxAttributiRelazioni[]){

        //CREAZIONE COLLEGAMENTO
        this.graphDb = connection(percorso);

        //ASSEGNAZIONE numeroNodiDaCreare E numeroRelazioniDaCreare
        this.numeroNodiDaCreare = calcoloNumeroNodi(splitSize, dept);
        this.numeroNodiCreati = 0;

        this.numeroRelazioniDaCreare = numeroNodiDaCreare - 1;
        this.numeroRelazioniCreate=0;
        this.countSplitSize = 0;
        this.countNodoPadre = 0;
        this.countNodoFiglio = 1;

        this.numeroNodiDaAssegnare = this.numeroNodiDaCreare;
        this.numeroNodiAssegnati = 0;

        this.numeroRelazioniDaAssegnare = this.numeroRelazioniDaCreare;
        this.numeroRelazioniAssegnate = 0;



		/*
		 * CREAZIONE NODI    CREAZIONE NODI    CREAZIONE NODI    CREAZIONE NODI    CREAZIONE NODI    CREAZIONE NODI    CREAZIONE NODI    CREAZIONE NODI    CREAZIONE NODI    CREAZIONE NODI    CREAZIONE NODI
		 *
		 *
		 *
		 */
        while( this.numeroNodiCreati < this.numeroNodiDaCreare ){

            GenerateNode createNode = new GenerateNode( graphDb, numeroTrancheNodi, numeroNodiCreati, numeroNodiDaCreare);
            this.numeroNodiCreati = createNode.numeroNodiCreati;
        }

        if(this.numeroNodiCreati != this.numeroNodiDaCreare ) System.out.println("ERRORE, creati: " + numeroNodiCreati + ", numero nodi da creare: " + numeroNodiDaCreare);

        System.out.println( "creati " + numeroNodiCreati + " nodi" );


		/*
		 * CREAZIONE ARCHI   CREAZIONE ARCHI   CREAZIONE ARCHI   CREAZIONE ARCHI   CREAZIONE ARCHI   CREAZIONE ARCHI   CREAZIONE ARCHI   CREAZIONE ARCHI   CREAZIONE ARCHI    CREAZIONE ARCHI    CREAZIONE ARCHI
		 *
		 *
		 *
		 */

        while( this.numeroRelazioniCreate < this.numeroRelazioniDaCreare ){

            GenerateRelationship createRelationship = new GenerateRelationship(graphDb, numeroRelazioniDaCreare, numeroRelazioniCreate, numeroTrancheRelazioni, countNodoPadre, countNodoFiglio, splitSize , countSplitSize);

            this.numeroRelazioniCreate = createRelationship.numeroRelazioniCreate;
            // numeroTrancheRelazioni E' SEMPRE LO STESSO

            this.countNodoPadre = createRelationship.countNodoPadre;
            this.countNodoFiglio = createRelationship.countNodoFiglio;

            //splitSize  E' SEMPRE LO STESSO
            this.countSplitSize = createRelationship.countSplitSize;

        }

		/*
		 * ASSEGNAZIONE NODI     ASSEGNAZIONE NODI     ASSEGNAZIONE NODI     ASSEGNAZIONE NODI     ASSEGNAZIONE NODI     ASSEGNAZIONE NODI     ASSEGNAZIONE NODI     ASSEGNAZIONE NODI     ASSEGNAZIONE NODI     ASSEGNAZIONE NODI
		 *
		 *
		 *
		 */

        while( this.numeroNodiAssegnati < this.numeroNodiDaAssegnare ){

            AssegnaAttributiNodi assegnaNodi = new AssegnaAttributiNodi( graphDb, numeroNodiDaAssegnare, numeroNodiAssegnati, numeroTrancheNodi , nomiAttributiNodi, minAttributiNodi, maxAttributiNodi );
            this.numeroNodiAssegnati = assegnaNodi.numeroNodiAssegnati;
        }

        System.out.println( "assegnati " + numeroNodiAssegnati + " nodi" );



		/*
		 * ASSEGNAZIONE RELAZIONI     ASSEGNAZIONE RELAZIONI      ASSEGNAZIONE RELAZIONI      ASSEGNAZIONE RELAZIONI      ASSEGNAZIONE RELAZIONI      ASSEGNAZIONE RELAZIONI      ASSEGNAZIONE RELAZIONI
		 *
		 *
		 *
		 */

        while( this.numeroRelazioniAssegnate < this.numeroRelazioniDaAssegnare ){

            AssegnaAttributiRelazioni assegnaRelazioni = new AssegnaAttributiRelazioni( graphDb, numeroRelazioniDaAssegnare, numeroRelazioniAssegnate, numeroTrancheRelazioni , nomiAttributiRelazioni, minAttributiRelazioni, maxAttributiRelazioni );
            this.numeroRelazioniAssegnate = assegnaRelazioni.numeroRelazioniAssegnate;
        }

        System.out.println( "assegnate " + numeroRelazioniAssegnate + " relazioni" );


        //CHIUDO LA CONNESSIONE
        graphDb.shutdown();
    }

    public GraphDatabaseService connection( File percorso){
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb = dbFactory.newEmbeddedDatabase(percorso);
        return graphDb;
    }

    // METODO CALCOLO NUMERO DEI NODI DELL'ALBERO
    public int calcoloNumeroNodi( int splitSize, int dept ){

        int totale = 1;
        int numeroNodiLivelloI = 1; // IN TEORIA DOVREI COMINCIARE A CONTARE DAL SECONDO LIVELLO VISTO CHE IL PRIMO HA SEMPRE VALORE 1

        for ( int i = 2; i <= dept; i++ ){ // PARTO DAL SECONDO LIVELLO

            numeroNodiLivelloI = numeroNodiLivelloI * splitSize;
            totale = totale + numeroNodiLivelloI;
        }
        System.out.println("nodi da creare: " + totale);
        return totale;
    }

    // METODO CALCOLO NUMERO DEI NODI DELL'ALBERO
    public int calcoloNumeroNodiPadre( int splitSize, int dept ){

        int totale = 1;
        int numeroNodiLivelloI = 1;

        for ( int i = 1; i < (dept - 1 ); i++ ){

            numeroNodiLivelloI = numeroNodiLivelloI * splitSize;
            totale = totale + numeroNodiLivelloI;
        }
        return totale;
    }

}