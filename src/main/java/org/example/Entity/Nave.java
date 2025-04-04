package org.example.Entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase Nave
 * Tiene los datos que tiene que tener una Nave
 * @author Abel Sabater Muñoz
 * **/
@Entity
@Table(name = "nave")
public class Nave {
    /**
     * Id de la nave
     * **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nombre de la nave
     * **/
    private String nombre;
    /**
     * Relación m:n con la base de datos
     * **/
    @ManyToMany(mappedBy = "naves", fetch = FetchType.EAGER)
    private List<Planeta> planetas;
    /**Constructores
     * Constructor por defecto**/
    public Nave() {}
    /**Constructor con un parametro y una lista de planetas
     * @param nombre nombre del planeta**/
    public Nave(String nombre) {
        this.nombre = nombre;
        this.planetas = new ArrayList<>();
    }
    /**
     * Obtiene el identificador único de la nave.
     * @return El ID de la nave.
     */
    public Long getId() {
        return id;
    }
    /**
     * Obtiene el nombre de la nave.
     * @return El nombre dels nave.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Obtiene la lista de planetas asociados a este objeto.
     * @return Una lista de {@code Planeta}.
     */
    public List<Planeta> getPlanetas() {
        return planetas;
    }
    /**
     * Agrega un planeta a la lista de planetas asociados a este objeto.
     * @param planeta El planeta que se va a agregar.
     */
    public void addPlaneta(Planeta planeta) {
        this.planetas.add(planeta);
    }
    /**
     * Establece un nuevo nombre para la nave.
     * @param nombre El nuevo nombre de la nave.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}