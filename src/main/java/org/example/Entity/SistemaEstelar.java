package org.example.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase SistemaEstelar
 * Tiene los datos que tiene que tener un Sistema estelar
 * @author Abel Sabater Muñoz
 * **/
@Entity
@Table(name = "sistema_estelar")
public class SistemaEstelar {
    /**
     * Id del Sistema Estelar**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    /**
     * Representa la galaxia a la que pertenece este sistema estelar.
     * La relación es de muchos a uno, ya que múltiples sistemas estelares pueden pertenecer a una sola galaxia.
     */
    @ManyToOne
    @JoinColumn(name = "galaxia_id")
    private Galaxia galaxia;
    /**
     * Lista de planetas que pertenecen a este sistema estelar.
     * La relación es de uno a muchos, con cascada en todas las operaciones.
     */
    @OneToMany(mappedBy = "sistemaEstelar", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Planeta> planetas;
    /**
     * Constructores
     * Constructor por defecto*
     */
    public SistemaEstelar() {}
    /**
     * Constructor de un sistema con dos parametros y una lista de planetas
     * @param nombre El nombre del sistema estelar.
     * @param galaxia La galaxia a la que pertenece el sistema estelar.
     */
    public SistemaEstelar(String nombre, Galaxia galaxia) {
        this.nombre = nombre;
        this.galaxia = galaxia;
        this.planetas = new ArrayList<>();
    }
    /**
     * Obtiene el identificador único del sistema estelar.
     * @return El ID del sistema estelar.
     */
    public Long getId() {
        return id;
    }
    /**
     * Obtiene el nombre del sistema estelar.
     * @return El nombre del sistema estelar.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Obtiene la galaxia a la que pertenece el sistema estelar.
     * @return La galaxia a la que pertenece este sistema estelar.
     */
    public Galaxia getGalaxia() {
        return galaxia;
    }
    /**
     * Obtiene la lista de planetas que forman parte de este sistema estelar.
     * @return Una lista de {@code Planeta} en este sistema estelar.
     */
    public List<Planeta> getPlanetas() {
        return planetas;
    }
    /**
     * Agrega un planeta al sistema estelar.
     * @param planeta El planeta que se va a agregar a este sistema estelar.
     */
    public void addPlaneta(Planeta planeta) {
        planetas.add(planeta);
    }
    /**
     * Establece un nuevo nombre para el sistema estelar.
     * @param nombre El nuevo nombre del sistema estelar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
