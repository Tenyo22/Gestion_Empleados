package com.empleados.gestionempleados.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String apellido;

    @ManyToOne()
    @JoinColumn(name = "cargo")
    private Cargo cargo;
    private String salario;
    private char status;


}
