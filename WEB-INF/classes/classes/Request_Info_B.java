package classes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.servlet.*;
import javax.servlet.http.*;

public class Request_Info_B extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException{
        //prova di creazione di tabella riempita con i nomi dei db disponibili
        PrintWriter out = response.getWriter();

        File bucket = new File("C:\\apache-tomcat-8.5.11\\webapps\\ROOT\\bucket.txt");
        Scanner sc = new Scanner(bucket);
        sc.useDelimiter(",");
        long count = Files.lines(Paths.get(String.valueOf(bucket))).count();
        String [][] a = new String[(int)count][4];


            for (int i = 0; i < count; i++) {
                for (int y = 0; y < 4; y++) {
                    String string = sc.next();
                    a[i][y] = string;
                }
            }
        sc.close();

        out.print("<html>\n" +
                "\t<head>\n" +
                "\t\t<meta charset=\"utf-8\">\n" +
                "\t\t<link href=\"ppc_style_B.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\t\t<link rel=\"shotcut icon\" href=\"logo1.png\"/>\n" +
                "\t\t<script type=\"text/javascript\" src=\"script.js\"></script>\n"+
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<form id=\"requisiti_B\" action=\"Request_Info_C\" method=\"POST\"><div class=\"container\">\n" +
                "\t\t<div class=\"division\" id=\"division\">\n" +
                "\t\t\t<a href=\"index.html\"><img src=\"logo1.png\"></a>\n" +
                "\t\t</div >\n" +
                "\t\t<div id=\"vertical_B\">\n" +
                "\t\t\t<table id=\"table_B\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<th id=\"h\">nome</th>\n" +
                "\t\t\t\t\t<th id=\"h\">split size</th>\n" +
                "\t\t\t\t\t<th id=\"h\">depth</th>\n" +
                "\t\t\t\t\t<th id=\"h\">num. nodi</th>\n" +
                "\t\t\t\t</tr>");

        int cont = 0;
        for(int i = 0; i < count; i++){
            out.print("<tr class=\"prime\" id=\"row\">");
            for(int y = 0; y < 4; y++) {
                out.print("<td  class=\"cell\" id='" + i + "' onclick=\"appendRow()\">"+ a[i][y]+ "</td>");
            }
            out.print("</tr>");
            cont = i;
        }

        if(a.length == 0){
            cont = -1;
        }
        out.print("<tr id=\"ghost\"><td id=\"hollow\">" + cont + "</td></tr>");

        out.print("</table>\n" +
                "\t\t</div>\n" +
                "\t\t<div id=\"vertical_B\">\n" +
                "\t\t\t<label for=\"name\" class=\"label_C\" id=\"F\"><strong>Inserire nome albero</strong></label> \n" +
                "\t\t\t<input type=\"text\" name=\"name\" id=\"name\" placeholder=\"inserire nome albero\"> \n" +
                "\t\t\t<label for=\"A_node\" class=\"label_C\" id=\"G\"><strong>Primo nodo</strong></label> \n" +
                "\t\t\t<input type=\"text\" name=\"A_node\" id=\"A_node\" placeholder=\"inserire primo nodo\"> \n" +
                "\t\t\t<label for=\"B_node\" class=\"label_C\" id=\"I\"><strong>Secondo nodo</strong></label> \n" +
                "\t\t\t<input type=\"text\" name=\"B_node\" id=\"B_node\" placeholder=\"inserire secondo nodo\"> \n" +
                "\t\t\t<input type=\"submit\" value=\"submit\" id=\"sub\" onclick=\"return verify_B()\"> <br>\n" +
                "\t\t\t\n" +
                "\t\t\t<label for=\"tree_del\" class=\"label_C\" id=\"L\"><strong>Cancella albero</strong></label> \n" +
                "\t\t\t<input type=\"text\" name=\"tree_del\" id=\"quary\" placeholder=\"inserire albero da cancellare\"> \n" +
                "\t\t\t<input type=\"submit\" value=\"delete\" id=\"sub\" onclick=\"return verify_C()\">\n" +
                "\t\t</div>\n" +
                "\t\t</div></form>\n" +
                "\t<div id=\"alert\">\n" +
                "\t\t<div id=\"alertmessage\"></div>\n"+
                "\t</div>\n"+
                "\t</body>\n"+
                "</html>");

        out.close();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy(){}
}