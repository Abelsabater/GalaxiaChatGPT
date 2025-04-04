package org.example.Repository;

import org.example.Entity.Galaxia;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio de Galaxia
 * @see org.springframework.data.jpa.repository.JpaRepository **/
public interface GalaxiaRepository extends JpaRepository<Galaxia, Long> {
}