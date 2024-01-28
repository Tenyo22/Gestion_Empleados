package com.empleados.gestionempleados.repository;

import com.empleados.gestionempleados.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Empleado findByNameAndApellido(String name, String apellido);
}
