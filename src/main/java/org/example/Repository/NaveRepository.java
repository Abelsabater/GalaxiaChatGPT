package org.example.Repository;

import org.example.Entity.Nave;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio de Nave
 * @see org.springframework.data.jpa.repository.JpaRepository **/
public interface NaveRepository extends JpaRepository<Nave, Long> {
}
