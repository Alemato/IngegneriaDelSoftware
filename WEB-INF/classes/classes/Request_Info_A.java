package classes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Request_Info_A extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException{
        /*viene creato un file di testo in cui vengono salvati tutti i nomi degli alberi creati per poi essere
          recuperati in un secondo momento. Creazione del file di testo tramite newFile e poi aggiungere i nomi degli
          alberi con File e poi aggiunta dei nomi tramite metodi. Il file di testo si chiama "bucket"*/

        //Ricezione nome albero
        String name_tree = request.getParameter("nomeAlbero");

        File directory = new File("C:\\apache-tomcat-8.5.11\\webapps\\ROOT\\databases\\"+name_tree);

        if(!(directory.exists())) {
        /*
        GraphDatabaseFactory factory = new GraphDatabaseFactory();
        GraphDatabaseService db = factory.newEmbeddedDatabase(directory);
        */
        /*Split size e depth dell'albero*/
            String split_size = request.getParameter("splitSize");
            String depth = request.getParameter("depth");
            int i = Integer.parseInt(split_size);        //split_size
            int j = Integer.parseInt(depth);            //depth


            //Passaggio parametri nodi
            String[] node_attributes = request.getParameterValues("nodo");
            String[] max_node = request.getParameterValues("maxnodo");
            String[] min_node = request.getParameterValues("minnodo");

            //Passaggi parametri archi
            String[] edge_attributes = request.getParameterValues("arco");
            String[] max_edge = request.getParameterValues("maxarco");
            String[] min_edge = request.getParameterValues("minarco");

            GenerateTree tree = new GenerateTree(i, j, directory, node_attributes, min_node, max_node, edge_attributes, min_edge, max_edge);

            int tot = tree.calcoloNumeroNodi(i, j);

            //Prova di scrittura su file dei nomi dell'albero

            File bucket = new File("C:\\apache-tomcat-8.5.11\\webapps\\ROOT\\bucket.txt");
            BufferedWriter bw = null;
            FileWriter fw = null;

            try {
                fw = new FileWriter(bucket.getAbsolutePath(), true);
                bw = new BufferedWriter(fw);

                bw.write(name_tree + "," + split_size + "," + depth + "," + tot + ",");
                bw.newLine();

                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String feedback = "albero creato";
            PrintWriter out = response.getWriter();
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
            out.print(docType + "<html><head><link href=\"ppc_style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><h4>"+feedback + " </h4><a  href=\"http://localhost:8080/crea_albero.html?crea+albero=crea+albero\">back</a></body>\n"
                    + "</html>");
        }   else    {
            PrintWriter out = response.getWriter();
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
            out.print(docType+"<html><head><link href=\"ppc_style.css\" rel=\"stylesheet\" type=\"text/css\"></head>" +
                    "<body><h4 id=\"response\">"+"Il nome dell'albero che si vuole creare e' stato utilizzato.utilizzare un altro nome"+"</h4><br><a  href=\"http://localhost:8080/crea_albero.html?crea+albero=crea+albero\">back</a></body>\n"
                    + "</html>");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy(){}
}