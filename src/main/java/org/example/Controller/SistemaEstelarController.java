package org.example.Controller;

import org.example.Entity.Galaxia;
import org.example.Entity.SistemaEstelar;
import org.example.Repository.GalaxiaRepository;
import org.example.Repository.SistemaEstelarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;


/**
 * Controlador para gestionar los sistemas estelares dentro de una galaxia.
 * Permite la creación, listado y eliminación de sistemas estelares.
 */
@Component
public class SistemaEstelarController {

    @Autowired
    private SistemaEstelarRepository sistemaEstelarRepository;

    @Autowired
    private GalaxiaRepository galaxiaRepository;

    /**
     * Metodo para gestionar los sistemas estelares mediante un menú interactivo
     *
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    public void gestionarSistemas(Scanner scanner) {
        int opcion=0;
        while (opcion!=5) {
            System.out.println("\nGestionar Sistemas Estelares:");
            System.out.println("1. Crear Sistema Estelar");
            System.out.println("2. Listar Sistemas Estelares");
            System.out.println("3. Borrar Sistema Estelar");
            System.out.println("4.Mostrar Planetas");
            System.out.println("5. Volver");

            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    crearSistemaEstelar(scanner);
                    break;
                case 2:
                    listarSistemasEstelares();
                    break;
                case 3:
                    borrarSistemaEstelar(scanner);
                    break;
                case 4:
                    mostrarPlanetasDeSistema(scanner);
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    /**
     * Crea un nuevo sistema estelar en una galaxia existente.
     *
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    private void crearSistemaEstelar(Scanner scanner) {
        System.out.println("Introduce el id de la galaxia a la que pertenece");
        Long idGalaxia = scanner.nextLong();
        scanner.nextLine();
        Galaxia galaxia = galaxiaRepository.findById(idGalaxia).orElse(null);

        if (galaxia == null) {
            System.out.println("La galaxia no existe");
        } else {
            System.out.print("Introduce el nombre del sistema estelar: ");
            String nombre = scanner.nextLine();
            SistemaEstelar sistema = new SistemaEstelar(nombre, galaxia);
            galaxia.addSistema(sistema);
            galaxiaRepository.save(galaxia);
            sistemaEstelarRepository.save(sistema);
            System.out.println("Sistema Estelar creado.");
        }
    }

    /**
     * Lista todos los sistemas estelares almacenados en la base de datos.
     */
    private void listarSistemasEstelares() {
        System.out.println("Lista de sistemas estelares:");
        sistemaEstelarRepository.findAll()
                .forEach(s -> System.out.println(s.getId() + ": " + s.getNombre()));
    }

    /**
     * Elimina un sistema estelar por su ID.
     *
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     */
    private void borrarSistemaEstelar(Scanner scanner) {
        System.out.print("Introduce el ID del sistema estelar a borrar: ");
        Long id = scanner.nextLong();
        sistemaEstelarRepository.deleteById(id);
        System.out.println("Sistema Estelar eliminado.");
    }
    /**Nos muestra todos los planetas que tiene un sistema
     * @param scanner Objeto Scanner para la entrada de datos del usuario
     * **/
    private void mostrarPlanetasDeSistema(Scanner scanner) {
        System.out.print("Introduce el ID del sistema estelar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar el buffer

        SistemaEstelar sistemaEstelar = sistemaEstelarRepository.findById(id).orElse(null);

        if (sistemaEstelar == null) {
            System.out.println("Sistema estelar no encontrado.");
            return;
        }

        System.out.println("Planetas en el sistema estelar " + sistemaEstelar.getNombre() + ":");
        if (sistemaEstelar.getPlanetas().isEmpty()) {
            System.out.println("No hay planetas en este sistema estelar.");
        } else {
            sistemaEstelar.getPlanetas().forEach(planeta ->
                    System.out.println(planeta.getId() + ": " + planeta.getNombre())
            );
        }
    }
}