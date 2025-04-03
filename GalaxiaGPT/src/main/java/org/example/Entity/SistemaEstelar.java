package org.example.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sistema_estelar")
public class SistemaEstelar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "galaxia_id")
    private Galaxia galaxia;

    @OneToMany(mappedBy = "sistemaEstelar", cascade = CascadeType.ALL)
    private List<Planeta> planetas;

    // Getters y Setters
    public SistemaEstelar() {}
    public SistemaEstelar(String nombre, Galaxia galaxia) {
        this.nombre = nombre;
        this.galaxia = galaxia;
        this.planetas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Galaxia getGalaxia() {
        return galaxia;
    }

    public List<Planeta> getPlanetas() {
        return planetas;
    }
    public void addPlaneta(Planeta planeta) {
        planetas.add(planeta);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
