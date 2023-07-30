package classes;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

public class GenerateRelationship{

    int numeroRelazioniCreate;
    int countSplitSize;
    int countNodoPadre;
    int countNodoFiglio;

    public GenerateRelationship(GraphDatabaseService graphDb, int numeroRelazioniDaCreare, int numeroRelazioniCreate, int numeroTrancheRelazioni, int countNodoPadre, int countNodoFiglio, int splitSize , int countSplitSize){

        // graphDb NON SERVE INIZIALIZZARLO

        // numeroRelazioniDaCreare E' SEMPRE LO STESSO
        this.numeroRelazioniCreate = numeroRelazioniCreate;
        // numeroTrancheRelazioni E' SEMPRE LO STESSO

        this.countNodoPadre = countNodoPadre;
        this.countNodoFiglio = countNodoFiglio;

        //splitSize  E' SEMPRE LO STESSO
        this.countSplitSize = countSplitSize;

        try ( Transaction tx = graphDb.beginTx() ){
            for( int i = 0; i<numeroTrancheRelazioni && numeroRelazioniDaCreare > this.numeroRelazioniCreate; i++){

                if( this.countSplitSize >= splitSize){
                    this.countNodoPadre++;
                    this.countSplitSize = 0;
                }

                graphDb.getNodeById(this.countNodoFiglio).createRelationshipTo( graphDb.getNodeById(this.countNodoPadre) , RelationshipType.withName("RELAZIONE"));

                this.numeroRelazioniCreate++;
                this.countNodoFiglio++;
                this.countSplitSize++;
            }
            tx.success();
        }
        System.out.println( "generate " + this.numeroRelazioniCreate + " relazioni");
    }

    public void creaSottoAlbero(GraphDatabaseService graphDb ,int splitSize){ // il metodo si occupera di: creare i figli e associarli al padre, incrementare il contatore dei figli

        for ( int i=1; i<=splitSize; i++){

            graphDb.getNodeById(countNodoPadre).createRelationshipTo( graphDb.getNodeById(countNodoFiglio) , RelationshipType.withName("RELAZIONE"));

            countNodoFiglio++;
        }
    }

    //METODO CALCOLO NUMERO NODI SU UN LIVELLO
    public int countNumNodeLivel(int level, int splitSize){

        int resoult=1;

        if ( level == 1 ) return 1;

        else{

            for ( int i=2; i<=level;i++){

                resoult = resoult*splitSize;
            }
            return resoult;
        }
    }
}