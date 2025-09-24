package modelo;

public class Usuario {
    private int codigoUsuario;
    private String correoUsuario;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(int codigoUsuario, String correoUsuario, String contrasena) {
        this.codigoUsuario = codigoUsuario;
        this.correoUsuario = correoUsuario;
        this.contrasena = contrasena;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigoUsuario=" + codigoUsuario + ", correoUsuario=" + correoUsuario + ", contrasena=" + contrasena + '}';
    }
    
}