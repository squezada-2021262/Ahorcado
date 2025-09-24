package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.UsuarioDAO;

@WebServlet(name = "Validar", urlPatterns = {"/Validar"})
public class Validar extends HttpServlet {
    
    UsuarioDAO udao = new UsuarioDAO();
    Usuario u = new Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Login")) {
            // CORRECCIÓN: Se cambió "correo" por "usuario"
            String correo = request.getParameter("usuario"); 
            String contrasena = request.getParameter("contrasena");
            u = udao.validar(correo, contrasena);
            if (u != null) {
                HttpSession session = request.getSession();
                session.setAttribute("logueado", true);
                session.setAttribute("usuario", u);
                request.getRequestDispatcher("Controlador?menu=Juego&accion=Jugar").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
        return "Servlet para validar el usuario";
    }
}