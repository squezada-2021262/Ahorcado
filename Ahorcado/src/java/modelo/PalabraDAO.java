package modelo;

import config.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PalabraDAO {

    private Connection cn;
    private CallableStatement cs;
    private ResultSet rs;
    private final Conexion con = new Conexion();

    public List<Palabra> listarPalabras() {
        List<Palabra> lista = new ArrayList<>();
        String sql = "{CALL sp_ListarPalabras()}";
        try {
            cn = con.Conexion();
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                Palabra p = new Palabra();
                p.setCodigoPalabra(rs.getInt("codigoPalabra"));
                p.setPalabra(rs.getString("Palabra"));
                p.setPista1(rs.getString("Pista1"));
                p.setPista2(rs.getString("Pista2"));
                p.setPista3(rs.getString("Pista3"));
                lista.add(p);
            }
        } catch (SQLException e) {
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
        return lista;
    }

    public Palabra obtenerPalabraAleatoria() {
        Palabra palabra = null;
        String sql = "{CALL sp_ObtenerPalabraAleatoria()}";
        try {
            cn = con.Conexion();
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            if (rs.next()) {
                palabra = new Palabra();
                palabra.setCodigoPalabra(rs.getInt("codigoPalabra"));
                palabra.setPalabra(rs.getString("Palabra"));
                palabra.setPista1(rs.getString("Pista1"));
                palabra.setPista2(rs.getString("Pista2"));
                palabra.setPista3(rs.getString("Pista3"));
                System.out.println("Palabra obtenida: " + palabra.getPalabra()); // Depuración
            } else {
                System.out.println("No se encontraron palabras en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener palabra aleatoria: " + e.getMessage());
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
        return palabra;
    }
}