package org.example.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "planeta")
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "sistema_estelar_id")
    private SistemaEstelar sistemaEstelar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "nave_planeta",
            joinColumns = @JoinColumn(name = "planeta_id"),
            inverseJoinColumns = @JoinColumn(name = "nave_id"))
    private Set<Nave> naves;

    // Getters y Setters
    public Planeta() {}
    public Planeta(String nombre, SistemaEstelar sistemaEstelar) {
        this.nombre = nombre;
        this.sistemaEstelar = sistemaEstelar;
        this.naves = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public SistemaEstelar getSistemaEstelar() {
        return sistemaEstelar;
    }

    public Set<Nave> getNaves() {
        return naves;
    }
    public void addNave(Nave nave) {
        this.naves.add(nave);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
