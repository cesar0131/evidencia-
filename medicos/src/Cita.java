import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class Cita {
    private String idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private Doctor doctor;
    private Paciente paciente;

        public Cita(String idCita, LocalDate fecha, LocalTime hora, Doctor doctor, Paciente paciente) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.doctor = doctor;
        this.paciente = paciente;
    }


            public String getIdCita() {
        return idCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

        public LocalTime getHora() {
        return hora;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
             }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return Objects.equals(idCita, cita.idCita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCita);
    }

    @Override
    public String toString() {
        return "Cita ID: " + idCita + " - Fecha: " + fecha + " Hora: " + hora + "\n" +
                "  Doctor: " + doctor.getNombre() + "\n" +
                "  Paciente: " + paciente.getNombre();
    }
}
