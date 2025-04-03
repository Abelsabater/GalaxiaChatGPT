package org.example.Controller;
import org.example.Entity.Nave;
import org.example.Entity.Planeta;
import org.example.Entity.SistemaEstelar;
import org.example.Repository.NaveRepository;
import org.example.Repository.PlanetaRepository;
import org.example.Repository.SistemaEstelarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class PlanetaController {

    @Autowired
    private SistemaEstelarRepository sistemaEstelarRepository;
    @Autowired
    private PlanetaRepository planetaRepository;
    @Autowired
    private NaveRepository naveRepository;

    public void gestionarPlanetas(Scanner scanner) {
        while (true) {
            System.out.println("\nGestionar Planetas:");
            System.out.println("1. Crear Planeta");
            System.out.println("2. Listar Planetas");
            System.out.println("3. Borrar Planeta");
            System.out.println("4. Assignar Planeta a nave");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Introduce el id del sistema al que pertenece");
                    Long idPlaneta = scanner.nextLong();
                    scanner.nextLine();
                    SistemaEstelar sistemaEstelar= sistemaEstelarRepository.findById(idPlaneta).orElse(null);
                    if (sistemaEstelar == null) {
                        System.out.println("No existe el sistema al que pertenece");
                    }else{
                        System.out.print("Introduce el nombre del planeta: ");
                        String nombre = scanner.nextLine();
                        Planeta planeta = new Planeta();
                        planeta.setNombre(nombre);
                        planeta=new Planeta(nombre,sistemaEstelar);
                        planetaRepository.save(planeta);
                        System.out.println("Planeta creado.");
                    }
                    break;
                case 2:
                    System.out.println("Lista de planetas:");
                    planetaRepository.findAll().forEach(p -> System.out.println(p.getId() + ": " + p.getNombre()));
                    break;
                case 3:
                    System.out.print("Introduce el ID del planeta a borrar: ");
                    Long id = scanner.nextLong();
                    planetaRepository.deleteById(id);
                    System.out.println("Planeta eliminado.");
                    break;
                case 4:
                    System.out.println("Lista de Planetas:");
                    planetaRepository.findAll().forEach(p -> System.out.println(p.getId() + ": " + p.getNombre()));
                    System.out.println("Dime el id del planeta: ");
                    Long idplaneta = scanner.nextLong();
                    scanner.nextLine();
                    Planeta planeta=planetaRepository.findById(idplaneta).orElse(null);
                    if (planeta == null) {
                        System.out.println("Planeta no encontrada con ese ID.");
                    }else{
                        System.out.print("Quantas naves quieres");
                        int cant=scanner.nextInt();
                        scanner.nextLine();
                        for (int i = 0; i < cant; i++) {
                            System.out.println("Lista de Naves:");
                            naveRepository.findAll().forEach(n -> System.out.println(n.getId() + ": " + n.getNombre()));
                            System.out.print("Introduce el ID de la nave a asignar: ");
                            Long naveId = scanner.nextLong();
                            scanner.nextLine();
                            Nave nave=naveRepository.findById(naveId).orElse(null);
                            if (nave == null) {
                                System.out.println("No existe la nave con ese ID.");
                            }else{
                                planeta.addNave(nave);
                                nave.addPlaneta(planeta);
                                naveRepository.save(nave);
                            }
                        }
                    }
                    planetaRepository.save(planeta);
                    System.out.println("Planeta asignada a las naves.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}