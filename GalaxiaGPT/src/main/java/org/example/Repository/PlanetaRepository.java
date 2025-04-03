package org.example.Repository;

import org.example.Entity.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
}