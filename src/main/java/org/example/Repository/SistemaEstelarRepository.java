package org.example.Repository;

import org.example.Entity.SistemaEstelar;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio de SistemaEstelar
 * @see org.springframework.data.jpa.repository.JpaRepository **/
public interface SistemaEstelarRepository extends JpaRepository<SistemaEstelar, Long>{
}