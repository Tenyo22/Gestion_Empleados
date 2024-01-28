package com.empleados.gestionempleados.repository;

import com.empleados.gestionempleados.entities.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Cargo findByCargo(String cargo);
}
