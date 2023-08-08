package com.will.locadora.Repository;

import com.will.locadora.Entity.Jogos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogosRepository extends JpaRepository<Jogos,Long> {
}
