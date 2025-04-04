package org.example.Controller;
import org.example.Entity.Nave;
import org.example.Entity.Planeta;
import org.example.Repository.NaveRepository;
import org.example.Repository.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;
/**
 * Controlador para gestionar las naves espaciales.
 * Permite la creación, listado, eliminación y asignación de naves a planetas.
 */
@Component
public class NaveController {
    @Autowired
    private PlanetaRepository planetaRepository;
    @Autowired
    private NaveRepository naveRepository;
    /**
     *Metodo para gestionar las naves mediante un menú interactivo.
     *
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    public void gestionarNaves(Scanner scanner) {
        int opcion=0;
        while (opcion!=6) {
            System.out.println("\nGestionar Naves:");
            System.out.println("1. Crear Nave");
            System.out.println("2. Listar Naves");
            System.out.println("3. Borrar Nave");
            System.out.println("4. Asignar Nave a Planeta/s");
            System.out.println("5. Ver en que planetas se situa la nave");
            System.out.println("6. Cambiar los planetas de la nave (teneis que poner tambien los planetas que quereis mantener)");
            System.out.println("7. Volver");

            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    crearNave(scanner);
                    break;
                case 2:
                    listarNaves();
                    break;
                case 3:
                    borrarNave(scanner);
                    break;
                case 4:
                    asignarNaveAPlanetas(scanner);
                    break;
                case 5:
                    mostrarPlanetasDeNave(scanner);
                    break;
                case 6:
                    cambiarPlanetasDeNave(scanner);
                case 7:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    /**
     * Crea una nueva nave espacial.
     *
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    private void crearNave(Scanner scanner) {
        System.out.print("Introduce el nombre de la nave: ");
        String nombre = scanner.nextLine();
        Nave nave = new Nave(nombre);
        naveRepository.save(nave);
        System.out.println("Nave creada.");
    }
    /**
     * Lista todas las naves almacenadas en la base de datos.
     */
    private void listarNaves() {
        System.out.println("Lista de naves:");
        naveRepository.findAll()
                .forEach(n -> System.out.println(n.getId() + ": " + n.getNombre()));
    }
    /**
     * Elimina una nave de la base de datos por su ID.
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    private void borrarNave(Scanner scanner) {
        System.out.print("Introduce el ID de la nave a borrar: ");
        Long id = scanner.nextLong();
        naveRepository.deleteById(id);
        System.out.println("Nave eliminada.");
    }
    /**
     * Asigna una nave a uno o varios planetas.
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    private void asignarNaveAPlanetas(Scanner scanner) {
        listarNaves();
        System.out.print("Introduce el ID de la nave a asignar: ");
        Long naveId = scanner.nextLong();
        scanner.nextLine();
        Nave nave = naveRepository.findById(naveId).orElse(null);
        if (nave == null) {
            System.out.println("Nave no encontrada con ese ID.");
            return;
        }
        System.out.print("¿Cuántos planetas quieres asignar?: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Lista de Planetas:");
            planetaRepository.findAll().forEach(p -> System.out.println(p.getId() + ": " + p.getNombre()));
            System.out.print("Introduce el ID del planeta: ");
            Long planetaId = scanner.nextLong();
            scanner.nextLine();
            Planeta planeta = planetaRepository.findById(planetaId).orElse(null);

            if (planeta == null) {
                System.out.println("Planeta no encontrado con ese ID.");
            } else {
                nave.addPlaneta(planeta);
                planeta.addNave(nave);
                planetaRepository.save(planeta);
            }
        }
        naveRepository.save(nave);
        System.out.println("Nave asignada a los planetas.");
    }
    /**Nos muestra todos los planetas en la que se situa la nave
     * @param scanner Objeto Scanner para la entrada de datos del usuario
     * **/
    private void mostrarPlanetasDeNave(Scanner scanner) {
        System.out.print("Introduce el ID de la nave: ");
        Long naveId = scanner.nextLong();
        scanner.nextLine(); // Limpiar el buffer
        Nave nave = naveRepository.findById(naveId).orElse(null);
        if (nave == null) {
            System.out.println("Nave no encontrada.");
            return;
        }
        System.out.println("La nave " + nave.getNombre() + " se situa en estos planetas:");
        if (nave.getPlanetas().isEmpty()) {
            System.out.println("Esta nave no está asignada a ningún planeta.");
        } else {
            nave.getPlanetas().forEach(planeta ->
                    System.out.println(planeta.getId() + ": " + planeta.getNombre())
            );
        }
    }
    /**
     * Nos permite cambiar todos los planetas de la nave por otros
     * @param scanner Objeto Scanner para la entrada de datos del usuario**/
    public void cambiarPlanetasDeNave(Scanner scanner) {
        // Solicitar el ID de la nave a modificar
        System.out.print("Introduce el ID de la nave: ");
        Long naveId = scanner.nextLong();
        scanner.nextLine(); // Limpiar el buffer
        Nave nave = naveRepository.findById(naveId).orElse(null);
        if (nave == null) {
            System.out.println("Nave no encontrada.");
            return;
        }
        System.out.println("Nave encontrada: " + nave.getNombre());
        // Eliminar la asociación actual de planetas con la nave
        nave.getPlanetas().clear();
        System.out.println("Lista de planetas disponibles:");
        // Listar todos los planetas
        planetaRepository.findAll().forEach(p ->
                System.out.println(p.getId() + ": " + p.getNombre())
        );
        System.out.print("¿Cuántos planetas quieres asignar a la nave? ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Introduce el ID del planeta a asignar: ");
            Long planetaId = scanner.nextLong();
            scanner.nextLine(); // Limpiar el buffer
            Planeta planeta = planetaRepository.findById(planetaId).orElse(null);
            if (planeta == null) {
                System.out.println("Planeta no encontrado con ese ID.");
            } else {
                // Asignar el planeta a la nave
                nave.addPlaneta(planeta);
                planeta.addNave(nave);
                planetaRepository.save(planeta); // Guardar el planeta con la nueva nave asignada
            }
        }
        // Guardar la nave con los nuevos planetas asignados
        naveRepository.save(nave);
        System.out.println("Planetas actualizados correctamente para la nave " + nave.getNombre());
    }
}