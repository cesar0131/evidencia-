import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator; // Para ordenar citas al mostrar


public class SistemaGestionCitas {

    private List<Administrador> administradores;
    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;

    private Administrador sesionActivaAdmin;

    public SistemaGestionCitas() {
        this.administradores = new ArrayList<>();
        this.doctores = new ArrayList<>();
        this.pacientes = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.sesionActivaAdmin = null;


        darDeAltaAdministrador("admin", "Gatos123");
    }

    //
    public Administrador darDeAltaAdministrador(String identificador, String contrasena) {
        for (Administrador admin : administradores) {
            if (admin.getIdentificador().equals(identificador)) {
                System.out.println(" Error: El identificador '" + identificador + "' ya está en uso para un administrador.");
                return null;
            }
        }
        Administrador nuevoAdmin = new Administrador(identificador, contrasena);
        administradores.add(nuevoAdmin);
        System.out.println("(+) Administrador '" + identificador + "' dado de alta exitosamente.");
        return nuevoAdmin;
    }

    public boolean autenticarAdministrador(String identificador, String contrasena) {
        for (Administrador admin : administradores) {
            if (admin.getIdentificador().equals(identificador) && admin.getContrasena().equals(contrasena)) {
                this.sesionActivaAdmin = admin;
                System.out.println("\n--- Autenticación exitosa para el administrador: " + identificador + " ---\n");
                return true;
            }
        }
        System.out.println("(!) Error de autenticación: Identificador o contraseña incorrectos.");
        return false;
    }

    public void cerrarSesionAdministrador() {
        if (sesionActivaAdmin != null) {
            System.out.println("--- Sesión del administrador '" + sesionActivaAdmin.getIdentificador() + "' cerrada. ---\n");
            sesionActivaAdmin = null;
        } else {
            System.out.println(" No hay una sesión de administrador activa.");
        }
    }

    public boolean verificarSesionAdmin() {
        if (sesionActivaAdmin == null) {
            System.out.println(" Acceso denegado: Se requiere una sesión de administrador activa.");
            return false;
        }
        return true;
    }

    // --- Métodos para la gestión de doctores ---
    public Doctor darDeAltaDoctor(String idDoctor, String nombre, String especialidad) {
        if (!verificarSesionAdmin()) {
            return null;
                }
        for (Doctor doc : doctores) {
            if (doc.getIdDoctor().equals(idDoctor)) {
                System.out.println("(!) Error: Ya existe un doctor con el ID '" + idDoctor + "'.");
                return null;
            }
         }
        Doctor nuevoDoctor = new Doctor(idDoctor, nombre, especialidad);
        doctores.add(nuevoDoctor);
        System.out.println("(+) Doctor '" + nombre + "' (" + especialidad + ") dado de alta con ID: " + idDoctor + ".");
        return nuevoDoctor;
         }

    // --- Métodos para la gestión de pacientes ---
    public Paciente darDeAltaPaciente(String idPaciente, String nombre, LocalDate fechaNacimiento ) {
        if (!verificarSesionAdmin()) {
            return null;
        }
            for (Paciente pac : pacientes) {
                if (pac.getIdPaciente().equals(idPaciente)) {
                System.out.println("(!) Error: Ya existe un paciente con el ID '" + idPaciente + "'.");
                return null;
            }
        }
        Paciente nuevoPaciente = new Paciente(idPaciente, nombre, fechaNacimiento);
        pacientes.add(nuevoPaciente);
        System.out.println("(+) Paciente '" + nombre + "' dado de alta con ID: " + idPaciente + ".");
        return nuevoPaciente;
    }


    public Cita crearCita(String idCita, LocalDate fecha, LocalTime hora, String idDoctor, String idPaciente) {
        if (!verificarSesionAdmin()) {
            return null;
        }
        for (Cita c : citas) {
            if (c.getIdCita().equals(idCita)) {
                System.out.println("(!) Error: Ya existe una cita con el ID '" + idCita + "'.");
                return null;
            }
        }

        Doctor doctorEncontrado = null;
        for (Doctor doc : doctores) {
            if (doc.getIdDoctor().equals(idDoctor)) {
                doctorEncontrado = doc;
                break;
            }
        }

        Paciente pacienteEncontrado = null;
        for (Paciente pac : pacientes) {
            if (pac.getIdPaciente().equals(idPaciente)) {
                pacienteEncontrado = pac;
                break;
            }
        }

        if (doctorEncontrado == null) {
            System.out.println(" Error: Doctor con ID '" + idDoctor + "' no encontrado.");
            return null;
        }
        if (pacienteEncontrado == null) {
            System.out.println("Error: Paciente con ID '" + idPaciente + "' no encontrado.");
            return null;
        }

        Cita nuevaCita = new Cita(idCita, fecha, hora, doctorEncontrado, pacienteEncontrado);
        citas.add(nuevaCita);
        doctorEncontrado.agregarCita(nuevaCita);
        pacienteEncontrado.agregarCita(nuevaCita);

        System.out.println("( Cita '" + idCita + "' creada para " + fecha + " a las " + hora +
                " con Dr. " + doctorEncontrado.getNombre() + " y Paciente " + pacienteEncontrado.getNombre() + ".");
        return nuevaCita;
    }


    public void mostrarDoctores() {
        System.out.println("\n--- Doctores Registrados ---");
        if (doctores.isEmpty()) {
            System.out.println("No hay doctores registrados.");
            return;
        }
        doctores.sort(Comparator.comparing(Doctor::getNombre));
        for (Doctor doctor : doctores) {
            System.out.println(doctor);
            if (!doctor.getCitasAsignadas().isEmpty()) {
                System.out.println("  Citas asignadas:");

                doctor.getCitasAsignadas().stream()
                        .sorted(Comparator.comparing(Cita::getFecha).thenComparing(Cita::getHora))
                        .forEach(cita ->
                                System.out.println("    - ID Cita: " + cita.getIdCita() +
                                        ", Fecha: " + cita.getFecha() +
                                        ", Hora: " + cita.getHora() +
                                        ", Paciente: " + cita.getPaciente().getNombre())
                        );
            } else {
                System.out.println("  Sin citas asignadas.");
            }
        }
    }

    public void mostrarPacientes() {
        System.out.println("\n--- Pacientes registrados ---");
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        pacientes.sort(Comparator.comparing(Paciente::getNombre));
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
            if (!paciente.getHistorialCitas().isEmpty()) {
                System.out.println("  Historial de citas:");

                paciente.getHistorialCitas().stream()
                        .sorted(Comparator.comparing(Cita::getFecha).thenComparing(Cita::getHora))
                        .forEach(cita ->
                                System.out.println("    - ID Cita: " + cita.getIdCita() +
                                        ", Fecha: " + cita.getFecha() +
                                        ", Hora: " + cita.getHora() +
                                        ", Doctor: " + cita.getDoctor().getNombre())
                        );
            } else {
                System.out.println("  Sin historial de citas.");
            }
        }
    }

    public void mostrarCitas() {
        System.out.println("\n--- Citas Agendadas ---");
        if (citas.isEmpty()) {
            System.out.println("No hay citas agendadas.");
            return;
        }

        citas.stream()
                .sorted(Comparator.comparing(Cita::getFecha).thenComparing(Cita::getHora))
                .forEach(System.out::println);
    }
}