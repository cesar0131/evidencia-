import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Doctor {
    private String idDoctor;
    private String nombre;
    private String especialidad;
    private List<Cita> citasAsignadas;

    public Doctor(String idDoctor, String nombre, String especialidad) {
        this.idDoctor = idDoctor;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.citasAsignadas = new ArrayList<>();
    }

         public String getIdDoctor() {
        return idDoctor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public List<Cita> getCitasAsignadas() {
        return citasAsignadas;
    }


    public void agregarCita(Cita cita) {
        this.citasAsignadas.add(cita);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(idDoctor, doctor.idDoctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDoctor);
    }

    @Override
    public String toString() {
        return "Dr. " + nombre + " (" + especialidad + ", ID: " + idDoctor + ")";
    }
}