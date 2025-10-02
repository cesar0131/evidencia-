import java.util.Objects;


public class Administrador {
    private String identificador;
    private String contrasena;

         public Administrador(String identificador, String contrasena) {
        this.identificador = identificador;
        this.contrasena = contrasena;
        }


    public String getIdentificador() {
        return identificador;
    }

        public String getContrasena() {
        return contrasena;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrador that = (Administrador) o;
        return Objects.equals(identificador, that.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public String toString() {
        return "Administrador [identificador=" + identificador + "]";
    }
}