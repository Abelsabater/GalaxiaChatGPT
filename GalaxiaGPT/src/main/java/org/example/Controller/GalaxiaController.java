package org.example.Controller;

import org.example.Entity.Galaxia;
import org.example.Repository.GalaxiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GalaxiaController {

    @Autowired
    private GalaxiaRepository galaxiaRepository;

    public void gestionarGalaxias(Scanner scanner) {
        while (true) {
            System.out.println("\nGestionar Galaxias:");
            System.out.println("1. Crear Galaxia");
            System.out.println("2. Listar Galaxias");
            System.out.println("3. Borrar Galaxia");
            System.out.println("4. Volver");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el nombre de la galaxia: ");
                    String nombre = scanner.nextLine();
                    Galaxia galaxia = new Galaxia();
                    galaxia.setNombre(nombre);
                    galaxiaRepository.save(galaxia);
                    System.out.println("Galaxia creada.");
                    break;
                case 2:
                    System.out.println("Lista de galaxias:");
                    galaxiaRepository.findAll().forEach(g -> System.out.println(g.getId() + ": " + g.getNombre()));
                    break;
                case 3:
                    System.out.print("Introduce el ID de la galaxia a borrar: ");
                    Long id = scanner.nextLong();
                    galaxiaRepository.deleteById(id);
                    System.out.println("Galaxia eliminada.");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}