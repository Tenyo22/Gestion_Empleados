package com.empleados.gestionempleados.controllers;

import com.empleados.gestionempleados.entities.Cargo;
import com.empleados.gestionempleados.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoController implements Serializable {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<List<Cargo>> list(){
        return cargoService.getAll();
    }

    @PostMapping
    public ResponseEntity<Cargo> create(@RequestBody Cargo cargo){
        return cargoService.create(cargo);
    }
}
