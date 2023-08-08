package com.will.locadora.Repository;

import com.will.locadora.Entity.Filmes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmesRepository extends JpaRepository<Filmes,Long> {
}
