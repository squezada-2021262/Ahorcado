package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Palabra;
import modelo.PalabraDAO;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {
    
    PalabraDAO pdao = new PalabraDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu != null && menu.equalsIgnoreCase("Juego")) {
            if ("Jugar".equals(accion)) {
                // Obtener una nueva palabra aleatoria
                Palabra palabraAleatoria = pdao.obtenerPalabraAleatoria();
                if (palabraAleatoria != null) {
                    // Actualizar la sesión con la nueva palabra
                    HttpSession session = request.getSession();
                    session.setAttribute("palabraJuego", palabraAleatoria);
                    System.out.println("Palabra guardada en sesión: " + palabraAleatoria.getPalabra()); // Depuración
                    request.getRequestDispatcher("ahorcado.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "No hay palabras para jugar. Contacte al administrador.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else if ("ObtenerPalabra".equals(accion)) {
                // Obtener la palabra de la sesión
                HttpSession session = request.getSession();
                Palabra palabra = (Palabra) session.getAttribute("palabraJuego");
                if (palabra != null) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    // Serialización manual a JSON
                    String json = "{";
                    json += "\"codigoPalabra\":" + palabra.getCodigoPalabra() + ",";
                    json += "\"palabra\":\"" + palabra.getPalabra() + "\",";
                    json += "\"pista1\":\"" + palabra.getPista1() + "\",";
                    json += "\"pista2\":\"" + palabra.getPista2() + "\",";
                    json += "\"pista3\":\"" + palabra.getPista3() + "\"";
                    json += "}";
                    out.print(json);
                    out.flush();
                    System.out.println("Palabra enviada como JSON: " + palabra.getPalabra()); // Depuración
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\":\"No hay palabra disponible\"}");
                }
            } else {
                request.getRequestDispatcher("ahorcado.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador principal para el juego Ahorcado";
    }
}