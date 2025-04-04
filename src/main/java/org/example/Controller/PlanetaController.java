package org.example.Controller;
import org.example.Entity.Nave;
import org.example.Entity.Planeta;
import org.example.Entity.SistemaEstelar;
import org.example.Repository.NaveRepository;
import org.example.Repository.PlanetaRepository;
import org.example.Repository.SistemaEstelarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;
/**
 * Controlador para gestionar los planetas dentro de un sistema estelar.
 * Permite la creación, listado, eliminación y asignación de planetas a naves.
 */
@Component
public class PlanetaController {

    @Autowired
    private SistemaEstelarRepository sistemaEstelarRepository;

    @Autowired
    private PlanetaRepository planetaRepository;

    @Autowired
    private NaveRepository naveRepository;

    /**
     * Metodo para gestionar los planetas mediante un menú interactivo.
     *
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    public void gestionarPlanetas(Scanner scanner) {
        int opcion=0;
        while (opcion!=6) {
            System.out.println("\nGestionar Planetas:");
            System.out.println("1. Crear Planeta");
            System.out.println("2. Listar Planetas");
            System.out.println("3. Borrar Planeta");
            System.out.println("4. Asignar Planeta a nave");
            System.out.println("5. Ver las naves que tiene este planeta");
            System.out.println("6. Cambiar el sistema estelar");
            System.out.println("7. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer
            switch (opcion) {
                case 1:
                    crearPlaneta(scanner);
                    break;
                case 2:
                    listarPlanetas();
                    break;
                case 3:
                    borrarPlaneta(scanner);
                    break;
                case 4:
                    asignarPlanetaANaves(scanner);
                    break;
                case 5:
                    mostrarNavesDePlaneta(scanner);
                    break;
                case 6:
                    cambiarSistemaDePlaneta(scanner);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    /**
     * Crea un nuevo planeta dentro de un sistema estelar existente.
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    private void crearPlaneta(Scanner scanner) {
        System.out.println("Introduce el ID del sistema estelar al que pertenece:");
        Long idSistema = scanner.nextLong();
        scanner.nextLine();
        SistemaEstelar sistemaEstelar = sistemaEstelarRepository.findById(idSistema).orElse(null);
        if (sistemaEstelar == null) {
            System.out.println("No existe el sistema estelar.");
        } else {
            System.out.print("Introduce el nombre del planeta: ");
            String nombre = scanner.nextLine();
            Planeta planeta = new Planeta(nombre, sistemaEstelar);
            sistemaEstelar.addPlaneta(planeta);
            sistemaEstelarRepository.save(sistemaEstelar);
            planetaRepository.save(planeta);
            System.out.println("Planeta creado.");
        }
    }

    /**
     * Lista todos los planetas almacenados en la base de datos.
     */
    private void listarPlanetas() {
        System.out.println("Lista de planetas:");
        planetaRepository.findAll()
                .forEach(p -> System.out.println(p.getId() + ": " + p.getNombre()));
    }

    /**
     * Elimina un planeta de la base de datos por su ID.
     *
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    private void borrarPlaneta(Scanner scanner) {
        System.out.print("Introduce el ID del planeta a borrar: ");
        Long id = scanner.nextLong();
        planetaRepository.deleteById(id);
        System.out.println("Planeta eliminado.");
    }

    /**
     * Asigna un planeta a una o varias naves.
     *
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    private void asignarPlanetaANaves(Scanner scanner) {
        listarPlanetas();
        System.out.println("Introduce el ID del planeta: ");
        Long idPlaneta = scanner.nextLong();
        scanner.nextLine();
        Planeta planeta = planetaRepository.findById(idPlaneta).orElse(null);

        if (planeta == null) {
            System.out.println("No se encontró el planeta.");
            return;
        }

        System.out.print("¿Cuántas naves deseas asignar?: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("Lista de Naves:");
            naveRepository.findAll().forEach(n -> System.out.println(n.getId() + ": " + n.getNombre()));
            System.out.print("Introduce el ID de la nave a asignar: ");
            Long naveId = scanner.nextLong();
            scanner.nextLine();
            Nave nave = naveRepository.findById(naveId).orElse(null);

            if (nave == null) {
                System.out.println("No existe una nave con ese ID.");
            } else {
                planeta.addNave(nave);
                nave.addPlaneta(planeta);
                naveRepository.save(nave);
            }
        }

        planetaRepository.save(planeta);
        System.out.println("Planeta asignado a las naves.");
    }
    /**Nos muestra todas las naves que tiene el planeta
     * @param scanner Objeto Scanner para la entrada de datos del usuario
     * **/
    private void mostrarNavesDePlaneta(Scanner scanner) {
        System.out.print("Introduce el ID del planeta: ");
        Long planetaId = scanner.nextLong();
        scanner.nextLine(); // Limpiar el buffer

        Planeta planeta = planetaRepository.findById(planetaId).orElse(null);

        if (planeta == null) {
            System.out.println("Planeta no encontrado.");
            return;
        }

        System.out.println("Naves en el planeta " + planeta.getNombre() + ":");
        if (planeta.getNaves().isEmpty()) {
            System.out.println("No hay naves en este planeta.");
        } else {
            planeta.getNaves().forEach(nave ->
                    System.out.println(nave.getId() + ": " + nave.getNombre())
            );
        }
    }
    /**
     * Nos permite cambiar el sistema del planeta por otro
     * @param scanner Objeto Scanner para la entrada de datos del usuario**/
    public void cambiarSistemaDePlaneta(Scanner scanner) {
        System.out.print("Introduce el ID del planeta: ");
        Long planetaId = scanner.nextLong();
        scanner.nextLine(); // Limpiar el buffer

        Planeta planeta = planetaRepository.findById(planetaId).orElse(null);

        if (planeta == null) {
            System.out.println("Planeta no encontrado.");
            return;
        }

        System.out.println("Planeta encontrado: " + planeta.getNombre());
        System.out.println("Lista de sistemas estelares disponibles:");

        // Listar sistemas estelares disponibles
        sistemaEstelarRepository.findAll().forEach(s ->
                System.out.println(s.getId() + ": " + s.getNombre())
        );

        System.out.print("Introduce el ID del nuevo sistema estelar: ");
        Long sistemaId = scanner.nextLong();
        scanner.nextLine(); // Limpiar el buffer

        SistemaEstelar nuevoSistema = sistemaEstelarRepository.findById(sistemaId).orElse(null);

        if (nuevoSistema == null) {
            System.out.println("Sistema estelar no encontrado.");
            return;
        }

        // Asignar el nuevo sistema al planeta
        planeta.setSistemaEstelar(nuevoSistema);
        planetaRepository.save(planeta);

        System.out.println("Sistema estelar cambiado correctamente para el planeta " + planeta.getNombre());
    }


}
