package org.example.Controller;

import org.example.Entity.Galaxia;
import org.example.Entity.SistemaEstelar;
import org.example.Repository.GalaxiaRepository;
import org.example.Repository.SistemaEstelarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class SistemaEstelarController {

    @Autowired
    private SistemaEstelarRepository sistemaEstelarRepository;
    @Autowired
    private GalaxiaRepository galaxiaRepository;

    public void gestionarSistemas(Scanner scanner) {
        while (true) {
            System.out.println("\nGestionar Sistemas Estelares:");
            System.out.println("1. Crear Sistema Estelar");
            System.out.println("2. Listar Sistemas Estelares");
            System.out.println("3. Borrar Sistema Estelar");
            System.out.println("4. Volver");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Introduce el id de la galaxia a la que pertenece");
                    Long idGalaxia = scanner.nextLong();
                    scanner.nextLine();
                    Galaxia galaxia=galaxiaRepository.findById(idGalaxia).orElse(null);
                    if (galaxia == null) {
                        System.out.println("El galaxia no existe");
                    }else{
                        System.out.print("Introduce el nombre del sistema estelar: ");
                        String nombre = scanner.nextLine();
                        SistemaEstelar sistema = new SistemaEstelar();
                        sistema.setNombre(nombre);
                        sistema = new SistemaEstelar(nombre, galaxia);
                        sistemaEstelarRepository.save(sistema);
                        System.out.println("Sistema Estelar creado.");
                    }
                    break;
                case 2:
                    System.out.println("Lista de sistemas estelares:");
                    sistemaEstelarRepository.findAll().forEach(s -> System.out.println(s.getId() + ": " + s.getNombre()));
                    break;
                case 3:
                    System.out.print("Introduce el ID del sistema estelar a borrar: ");
                    Long id = scanner.nextLong();
                    sistemaEstelarRepository.deleteById(id);
                    System.out.println("Sistema Estelar eliminado.");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
