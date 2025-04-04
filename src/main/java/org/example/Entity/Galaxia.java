package org.example.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Galaxia
 * Tiene los datos que tiene que tener una Galaxia
 * @author Abel Sabater Muñoz
 * **/

@Entity
@Table(name = "galaxia")
public class Galaxia {
    /**
     * Id de la galaxia
     * **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nombre de la galaxia
     * **/
    private String nombre;
    /**
     * Lista de sistemas estelares que pertenecen a esta galaxia
     * Relacion de 1:n con la base de datos
     * **/
    @OneToMany(mappedBy = "galaxia", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SistemaEstelar> sistemasEstelares;

/**
 *Constructores
 * Constructor por defecto**/
    public Galaxia() {}
    /**Constructor con un parametro y la lista de sistemas estelares
     * @param nombre nombre de la galaxia
     * **/
    public Galaxia(String nombre) {
        this.nombre = nombre;
        this.sistemasEstelares = new ArrayList<>();
    }

    /**
     * Obtiene el identificador único de la galaxia.
     *
     * @return El ID de la galaxia.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la galaxia.
     * @return El nombre de la galaxia.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Agrega un sistema estelar a la lista de sistemas de la galaxia.
     * @param sistemaEstelar El sistema estelar que se va a agregar.
     */
    public void addSistema(SistemaEstelar sistemaEstelar) {
        this.sistemasEstelares.add(sistemaEstelar);
    }

    /**
     * Obtiene la lista de sistemas estelares asociados a esta galaxia.
     * @return Una lista de sistemas estelares.
     */
    public List<SistemaEstelar> getSistemasEstelares() {
        return sistemasEstelares;
    }

    /**
     * Establece un nuevo nombre para la galaxia.
     * @param nombre El nuevo nombre de la galaxia.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
