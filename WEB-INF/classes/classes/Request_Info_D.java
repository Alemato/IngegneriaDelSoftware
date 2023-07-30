package classes;


import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Request_Info_D extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("tree_del");
        File file = new File("C:\\apache-tomcat-8.5.11\\webapps\\ROOT\\databases\\"+name);
        PrintWriter out = response.getWriter();

        if (!(file.exists())) {
            out.print("<html><head><link href=\"ppc_style_B.css\" rel=\"stylesheet\" type=\"text/css\"></head>");
            out.print("<body><h4>L'albero che si vuole cancellare non esiste all'interno del database</h4><br><a  href=\"http://localhost:8080/Request_Info_B?recupera+albero=recupera+albero\">back</a></html>");
            out.print("</html>");
        }   else {
            SingletonGraphDb.requestDelete(file);

            File bucket = new File("C:\\apache-tomcat-8.5.11\\webapps\\ROOT\\bucket.txt");
            List<String> lines = FileUtils.readLines(bucket);
            List<String> updatedLines = lines.stream().filter(s -> !s.contains(name)).collect(Collectors.toList());
            FileUtils.writeLines(bucket, updatedLines, false);

            String feedback = "albero cancellato";
            out.print("<html><head><link href=\"ppc_style_B.css\" rel=\"stylesheet\" type=\"text/css\"></head>");
            out.print("<body><h4>" + feedback + "</h4> <a  href=\"http://localhost:8080/Request_Info_B?recupera+albero=recupera+albero\">back</a></body>\n");
            out.print("</html>");
        }
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public void destroy(){}
}
