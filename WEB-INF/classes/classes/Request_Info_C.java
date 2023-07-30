package classes;

import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.http.*;

public class Request_Info_C extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String name = request.getParameter("name");
        String first_node = request.getParameter("A_node");
        String second_node = request.getParameter("B_node");
        int x = Integer.parseInt(first_node);
        int y = Integer.parseInt(second_node);

        /*Ho intenzione di passare a traverser hashmap per memorizzare nodi iterazione per iterazione e
        *   passare i due nodi sotto forma di stringa*/

        /*Effettuo la connessione come in tutti gli altri casi*/
        File directory = new File("C:\\apache-tomcat-8.5.11\\webapps\\ROOT\\databases\\"+name);


        Traversal t = new Traversal(directory, x, y);

        PrintWriter out = response.getWriter();
        out.print("<html><head><link href=\"ppc_style_B.css\" rel=\"stylesheet\" type=\"text/css\"></head><body>");
        out.print("<h4>somma attributi totali nodi"+t.mapNodi+"</h4>");
        out.print("<h4>somma attributi totali archi:"+t.mapArchi+"</h4>");
        out.print("<h4>millisecondi:"+t.startTime+"</h4>");
        out.print("<h4>percorso:"+t.percorso+"</h4>");

        out.print(" <a  href=\"http://localhost:8080/Request_Info_B?recupera+albero=recupera+albero\">back</a></body>\n"
                + "</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy(){}
}