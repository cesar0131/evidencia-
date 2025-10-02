import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ProgramaCitasMedicas {

    private static SistemaGestionCitas sistema = new SistemaGestionCitas();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- BIENVENIDO AL SISTEMA DE DE CITAS MÉDICAS ---");
            System.out.println("1. Iniciar sesión como administrador");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    iniciarSesionAdmin();
                    break;
                case 2:
                    salir = true;
                    System.out.println(" ¡Hasta pronto!");
                    break;
                default:
                    System.out.println(" Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close(); // Cerrar el scanner al finalizar el programa
    }

    private static void iniciarSesionAdmin() {
        System.out.println("\n--- INICIO DE SESIÓN ADMINISTRADOR ---");
        System.out.print("Identificador: ");
        String identificador = scanner.next();
        System.out.print("Contraseña: ");
        String contrasena = scanner.next();

        if (sistema.autenticarAdministrador(identificador, contrasena)) {
            mostrarMenuAdministrador();
        } else {
            System.out.println("No se pudo iniciar sesión.");
        }
    }

    private static void mostrarMenuAdministrador() {
        boolean sesionActiva = true;
        while (sesionActiva) {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("Dar de alta Doctor 1");
            System.out.println(" Dar de alta Paciente 2");
            System.out.println(" Crear Cita 3");
            System.out.println(" Mostrar Doctores 4");
            System.out.println("mostrar Pacientes 5");
            System.out.println("Mostrar Citas 5");
            System.out.println(" Cerrar sesión 7");
            System.out.print("Selecciona una opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    darDeAltaDoctorConsola();
                    break;
                case 2:
                    darDeAltaPacienteConsola();
                    break;
                case 3:
                    crearCitaConsola();
                    break;
                case 4:
                    sistema.mostrarDoctores();
                    break;
                case 5:
                    sistema.mostrarPacientes();
                    break;
                case 6:
                    sistema.mostrarCitas();
                    break;
                case 7:
                    sistema.cerrarSesionAdministrador();
                    sesionActiva = false;
                    break;
                default:
                    System.out.println(" Opción no válida Intente de nuevo.");
            }
        }
    }

    private static void darDeAltaDoctorConsola() {
        System.out.println("\n--- DAR DE ALTA DOCTOR ---");
        System.out.print("ID del Doctor: ");
        String idDoctor = scanner.next();
        scanner.nextLine();
        System.out.print("Nombre del Doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("especialidad: ");
        String especialidad = scanner.nextLine();


        sistema.darDeAltaDoctor(idDoctor, nombre, especialidad);
     }

    private static void darDeAltaPacienteConsola() {
        System.out.println("\n--- DAR DE ALTA PACIENTE ---");
        System.out.print("ID del Paciente: ");
        String idPaciente = scanner.next();
        scanner.nextLine();

        System.out.print("Nombre del paciente: ");
        String nombre = scanner.nextLine();

        LocalDate fechaNacimiento = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
            String fechaStr = scanner.next();
            try {
                fechaNacimiento = LocalDate.parse(fechaStr);
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println(" Formato de fecha incorrecto. ");
            }
        }


        sistema.darDeAltaPaciente(idPaciente, nombre, fechaNacimiento);
    }

    private static void crearCitaConsola() {
        System.out.println("\n--- CREAR CITA ---");
        System.out.print("ID de la Cita: ");
        String idCita = scanner.next();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        LocalDate fechaCita = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            System.out.print("Fecha de la cita (aaaa-mm-dd): ");
            String fechaStr = scanner.next();
            try {
                fechaCita = LocalDate.parse(fechaStr);
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("( Formato de fecha incorrecto");
            }
        }
        scanner.nextLine();

        LocalTime horaCita = null;
        boolean horaValida = false;
        while (!horaValida) {
            System.out.print("Hora de la Cita (HH:MM): ");
            String horaStr = scanner.next();
            try {
                horaCita = LocalTime.parse(horaStr);
                horaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println(" Formato de hora incorrecto");
            }
        }
        scanner.nextLine();

        System.out.print("ID del Doctor para la cita: ");
        String idDoctor = scanner.next();
        System.out.print("ID del Paciente para la cita: ");
        String idPaciente = scanner.next();
        scanner.nextLine();

        sistema.crearCita(idCita, fechaCita, horaCita, idDoctor, idPaciente);
    }

    // Helper para leer opciones de menú y manejar errores de entrada
    private static int leerOpcion() {
        while (true) {
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();
                return opcion;
            } catch (InputMismatchException e) {
                System.out.println(" Entrada no válida ingrese un número.");
                scanner.nextLine();
                System.out.print("Seleccione una opción: ");
            }
        }
    }
}