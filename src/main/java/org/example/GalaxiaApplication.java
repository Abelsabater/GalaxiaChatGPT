package org.example;
import org.example.Controller.GalaxiaController;
import org.example.Controller.NaveController;
import org.example.Controller.PlanetaController;
import org.example.Controller.SistemaEstelarController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


/**
 * Clase principal de la aplicación que gestiona una galaxia.
 * Implementa  CommandLineRunner para ejecutar la lógica en la línea de comandos.
 */
@SpringBootApplication
public class GalaxiaApplication implements CommandLineRunner {

    @Autowired
    private GalaxiaController galaxiaController;

    @Autowired
    private SistemaEstelarController sistemaEstelarController;

    @Autowired
    private PlanetaController planetaController;

    @Autowired
    private NaveController naveController;

    /**
     * Metodo principal que inicia el Spring Boot
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(GalaxiaApplication.class, args);
    }

    /**
     * Metodo que se ejecuta al iniciar la aplicación, mostrando un menú interactivo para la gestión de galaxias
     *
     * @param args Argumentos de la línea de comandos.
     */
    @Override
    public void run(String ...args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nGestión de la Galaxia");
            System.out.println("1. Gestionar Galaxias");
            System.out.println("2. Gestionar Sistemas Estelares");
            System.out.println("3. Gestionar Planetas");
            System.out.println("4. Gestionar Naves");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    galaxiaController.gestionarGalaxias(scanner);
                    break;
                case 2:
                    sistemaEstelarController.gestionarSistemas(scanner);
                    break;
                case 3:
                    planetaController.gestionarPlanetas(scanner);
                    break;
                case 4:
                    naveController.gestionarNaves(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
    }
}

