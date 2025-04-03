package org.example.Entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nave")
public class Nave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "naves",fetch = FetchType.EAGER)
    private List<Planeta> planetas;
    // Getters y Setters
    public Nave() {}
    public Nave(String nombre) {
        this.nombre = nombre;
        this.planetas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Planeta> getPlanetas() {
        return planetas;
    }
public void addPlaneta(Planeta planeta) {
        this.planetas.add(planeta);

}
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}