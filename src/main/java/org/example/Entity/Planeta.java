package org.example.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Clase Planeta
 * Tiene los datos que tiene que tener un Planeta
 * @author Abel Sabater Muñoz
 * **/
@Entity
@Table(name = "planeta")
public class Planeta {
/**
 * Id del planeta**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nombre del planeta**/
    private String nombre;
    /**
     * Relacion 1:n con la base de datos**/
    @ManyToOne
    @JoinColumn(name = "sistema_estelar_id")
    private SistemaEstelar sistemaEstelar;
    /**
     * Relacion m:n con la base de datos**/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "nave_planeta",
            joinColumns = @JoinColumn(name = "planeta_id"),
            inverseJoinColumns = @JoinColumn(name = "nave_id"))
    private Set<Nave> naves;

    /**
     * Constructor
     * Constructor por defecto**/
    public Planeta() {}

    /**
     * Constructor de un planeta con dos parametros y una lista de naves
     * @param nombre El nombre del planeta.
     * @param sistemaEstelar El sistema estelar al que pertenece el planeta.
     */
    public Planeta(String nombre, SistemaEstelar sistemaEstelar) {
        this.nombre = nombre;
        this.sistemaEstelar = sistemaEstelar;
        this.naves = new HashSet<>();
    }
    /**
     * Obtiene el identificador único del planeta.
     * @return El ID del planeta.
     */
    public Long getId() {
        return id;
    }
    /**
     * Obtiene el nombre del planeta.
     * @return El nombre del planeta.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Obtiene el sistema estelar al que pertenece el planeta.
     * @return El sistema estelar del planeta.
     */
    public SistemaEstelar getSistemaEstelar() {
        return sistemaEstelar;
    }
    /**
     * Obtiene el conjunto de naves que han visitado el planeta.
     * @return Un conjunto de naves que han explorado el planeta.
     */
    public Set<Nave> getNaves() {
        return naves;
    }
    /**
     * Agrega una nave a la lista de naves que han visitado el planeta.
     * @param nave La nave que se va a agregar.
     */
    public void addNave(Nave nave) {
        this.naves.add(nave);
    }
    /**
     * Establece un nuevo nombre para el planeta.
     * @param nombre El nuevo nombre del planeta.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
