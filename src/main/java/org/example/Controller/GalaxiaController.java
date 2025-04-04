package org.example.Controller;
import org.example.Entity.Galaxia;
import org.example.Repository.GalaxiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;
/**
 * Clase para gestionar las galaxias mediante un menú interactivo.
 * Permite crear, listar y eliminar galaxias almacenadas en la base de datos.
 */
@Component
public class GalaxiaController {

    @Autowired
    private GalaxiaRepository galaxiaRepository;
    /**
     * Metodo principal para gestionar galaxias mediante un menú interactivo.
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    public void gestionarGalaxias(Scanner scanner) {
        int opcion=0;
        while (opcion!=5) {
            System.out.println("\nGestionar Galaxias:");
            System.out.println("1. Crear Galaxia");
            System.out.println("2. Listar Galaxias");
            System.out.println("3. Borrar Galaxia");
            System.out.println("4. Mostrar Sistemas");
            System.out.println("5. Volver");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer
            switch (opcion) {
                case 1:
                    crearGalaxia(scanner);
                    break;
                case 2:
                    listarGalaxias();
                    break;
                case 3:
                    borrarGalaxia(scanner);
                    break;
                case 4:
                    mostrarSistemasDeGalaxia(scanner);
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    /**
     * Crea una nueva galaxia y la almacena en la base de datos.
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    private void crearGalaxia(Scanner scanner) {
        System.out.print("Introduce el nombre de la galaxia: ");
        String nombre = scanner.nextLine();
        Galaxia galaxia = new Galaxia();
        galaxia.setNombre(nombre);
        galaxiaRepository.save(galaxia);
        System.out.println("Galaxia creada.");
    }
    /**
     * Lista todas las galaxias almacenadas en la base de datos.
     */
    private void listarGalaxias() {
        System.out.println("Lista de galaxias:");
        galaxiaRepository.findAll().forEach(g -> System.out.println(g.getId() + ": " + g.getNombre()));
    }
    /**
     * Elimina una galaxia de la base de datos por su ID.
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    private void borrarGalaxia(Scanner scanner) {
        System.out.print("Introduce el ID de la galaxia a borrar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Limpiar el buffer
        galaxiaRepository.deleteById(id);
        System.out.println("Galaxia eliminada.");
    }
    /**Nos muestra todos los sistemas que tiene la galaxia
     * @param scanner Objeto Scanner para la entrada de datos del usuario
     * **/
    private void mostrarSistemasDeGalaxia(Scanner scanner) {
        System.out.print("Introduce el ID de la galaxia: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar el buffer
        Galaxia galaxia = galaxiaRepository.findById(id).orElse(null);
        if (galaxia == null) {
            System.out.println("Galaxia no encontrada.");
            return;
        }
        System.out.println("Sistemas estelares en la galaxia " + galaxia.getNombre() + ":");
        if (galaxia.getSistemasEstelares().isEmpty()) {
            System.out.println("No hay sistemas estelares en esta galaxia.");
        } else {
            galaxia.getSistemasEstelares().forEach(sistema ->
                    System.out.println(sistema.getId() + ": " + sistema.getNombre())
            );
        }
    }
}
