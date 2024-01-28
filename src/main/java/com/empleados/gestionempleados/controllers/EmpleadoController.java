package com.empleados.gestionempleados.controllers;

import com.empleados.gestionempleados.entities.Empleado;
import com.empleados.gestionempleados.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    private ResponseEntity<List<Empleado>> list(){
        return empleadoService.list();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Empleado> getEmpleado(@PathVariable Long id){
        return empleadoService.getEmpleado(id);
    }

    @PostMapping
    private ResponseEntity<Empleado> create(@RequestBody Empleado empleado){
        return empleadoService.create(empleado);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Empleado> update(@PathVariable Long id, @RequestBody Empleado empleado){
        return empleadoService.update(id, empleado);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Empleado> delete(@PathVariable Long id){
        return empleadoService.delete(id);
    }

}
