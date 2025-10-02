import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paciente {
    private String idPaciente;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String telefono;
    private List<Cita> historialCitas;

    public Paciente(String idPaciente, String nombre, LocalDate fechaNacimiento) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;

        this.historialCitas = new ArrayList<>();
    }


    public String getIdPaciente() {
        return idPaciente;
    }

            public String getNombre() {
         return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }



            public List<Cita> getHistorialCitas() {
        return historialCitas;
    }


        public void agregarCita(Cita cita) {
        this.historialCitas.add(cita);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(idPaciente, paciente.idPaciente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaciente);
    }

    @Override
    public String toString() {
        return "Paciente: " + nombre + " (ID: " + idPaciente + ", Teléfono: " + telefono + ")";
    }
}