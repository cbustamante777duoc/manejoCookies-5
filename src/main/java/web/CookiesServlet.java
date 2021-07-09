package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookiesServlet")
public class CookiesServlet extends HttpServlet {
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
     //suponemos que el usuario entra por primera vez al sitio web
     boolean nuevoUsuario = true;
     String mensaje = null;
     
     //obtenemos el arreglo de las cookies
     Cookie[] cookies = request.getCookies();
     
     //busca si existe una cookies con anterioridad
     
        if (cookies != null) {
            
            for (Cookie item : cookies) {
                
                if (item.getName().equals("visitanteRecurrente") && item.getValue().equals("si")) {
                    //si ya existe la cookies entonce es un usuario recurrrente
                    
                    nuevoUsuario = false;
                    break;
                }
            }
        }
     
        if (nuevoUsuario) {
            //creamos la cookie con los valores
            Cookie visitanteCookie = new Cookie("visitanteRecurrente","si");
            response.addCookie(visitanteCookie);
            mensaje = "Gracias por visitar este sitio por primera vez";
        }else{
            mensaje = "Gracias por visitar nuestro sitio nuevamente";
        }
     
        response.setContentType("text/html; charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        out.print("mensaje "+mensaje);
        out.close();
     }
}
