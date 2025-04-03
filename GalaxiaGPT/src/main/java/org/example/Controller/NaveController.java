package org.example.Controller;

import org.example.Entity.Nave;
import org.example.Entity.Planeta;
import org.example.Repository.NaveRepository;
import org.example.Repository.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class NaveController {

    @Autowired
    private PlanetaRepository planetaRepository;

    @Autowired
    private NaveRepository naveRepository;

    public void gestionarNaves(Scanner scanner) {
        while (true) {
            System.out.println("\nGestionar Naves:");
            System.out.println("1. Crear Nave");
            System.out.println("2. Listar Naves");
            System.out.println("3. Borrar Nave");
            System.out.println("4. Asignar Nave a Planeta/s");
            System.out.println("5. Volver");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el nombre de la nave: ");
                    String nombre = scanner.nextLine();
                    Nave nave = new Nave();
                    nave.setNombre(nombre);
                    naveRepository.save(nave);
                    System.out.println("Nave creada.");
                    break;
                case 2:
                    System.out.println("Lista de naves:");
                    naveRepository.findAll().forEach(n -> System.out.println(n.getId() + ": " + n.getNombre()));
                    break;
                case 3:
                    System.out.print("Introduce el ID de la nave a borrar: ");
                    Long id = scanner.nextLong();
                    naveRepository.deleteById(id);
                    System.out.println("Nave eliminada.");
                    break;
                case 4:
                    System.out.println("Lista de Naves:");
                    naveRepository.findAll().forEach(n -> System.out.println(n.getId() + ": " + n.getNombre()));
                    System.out.print("Introduce el ID de la nave a asignar: ");
                    Long naveId = scanner.nextLong();
                    scanner.nextLine();  // Limpiar el buffer
                    nave = naveRepository.findById(naveId).orElse(null);
                    if (nave == null) {
                        System.out.println("Nave no encontrada con ese ID.");
                        return;
                    }else {
                        // Mostrar todos los planetas
                        System.out.print("Quantos planetas quieres");
                        int cant=scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Lista de Planetas:");
                        planetaRepository.findAll().forEach(p -> System.out.println(p.getId() + ": " + p.getNombre()));
                        for (int i = 0; i < cant; i++) {
                            System.out.println("Dime el id del planeta ");
                            Long planetaId = scanner.nextLong();
                            scanner.nextLine();
                            Planeta planeta = planetaRepository.findById(planetaId).orElse(null);
                            if (planeta == null) {
                                System.out.println("Planeta no encontrada con ese ID.");
                            }else{
                                nave.addPlaneta(planeta);
                                planeta.addNave(nave);
                                planetaRepository.save(planeta);
                            }
                        }
                    }
                    // Asignar los planetas a la nave
                    naveRepository.save(nave);
                    System.out.println("Nave asignada a los planetas.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
