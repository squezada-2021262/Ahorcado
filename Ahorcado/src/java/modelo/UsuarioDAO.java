package modelo;

import config.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private Connection cn;
    private CallableStatement cs;
    private ResultSet rs;
    private final Conexion con = new Conexion();

    public Usuario validar(String correo, String contrasena) {
        Usuario user = null;
        String sql = "{CALL sp_ValidarUsuario(?, ?)}";
        try {
            cn = con.Conexion();
            if (cn == null) {
                System.out.println("Error: No se pudo establecer la conexión a la base de datos.");
                return null;
            }
            cs = cn.prepareCall(sql);
            cs.setString(1, correo);
            cs.setString(2, contrasena);
            rs = cs.executeQuery();
            if (rs.next()) {
                user = new Usuario();
                user.setCodigoUsuario(rs.getInt("codigoUsuario"));
                user.setCorreoUsuario(rs.getString("correoUsuario"));
                user.setContrasena(rs.getString("contrasena"));
            }
        } catch (SQLException e) {
            System.out.println("Error al validar el usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return user;
    }
}