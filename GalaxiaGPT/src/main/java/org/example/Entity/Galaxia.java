package org.example.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "galaxia")
public class Galaxia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "galaxia", cascade = CascadeType.ALL)
    private List<SistemaEstelar> sistemasEstelares;


    // Getters y Setters
    public Galaxia() {}
    public Galaxia(String nombre) {
        this.nombre = nombre;
        this.sistemasEstelares = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public void addSistema(SistemaEstelar sistemaEstelar) {
        this.sistemasEstelares.add(sistemaEstelar);
    }

    public List<SistemaEstelar> getSistemasEstelares() {
        return sistemasEstelares;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
