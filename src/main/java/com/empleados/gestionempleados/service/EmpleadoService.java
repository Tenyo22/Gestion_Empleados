package com.empleados.gestionempleados.service;

import com.empleados.gestionempleados.entities.Cargo;
import com.empleados.gestionempleados.entities.Empleado;
import com.empleados.gestionempleados.exceptions.CustomException;
import com.empleados.gestionempleados.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CargoService cargoService;

    public ResponseEntity<List<Empleado>> list() {
        return new ResponseEntity<>(empleadoRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Empleado> findByNameApe(String name, String apellido){
        Empleado empleado = empleadoRepository.findByNameAndApellido(name, apellido);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    public ResponseEntity<Empleado> getEmpleado(Long id){
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        if(empleado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    public ResponseEntity<Empleado> create(Empleado empleado){
        validateData(empleado);
        Empleado empleadoExists = findByNameApe(empleado.getName(), empleado.getApellido()).getBody();
        if(empleadoExists != null){
            return new ResponseEntity<>(empleadoExists, HttpStatus.OK);
        }
        empleadoRepository.save(empleado);
        return new ResponseEntity<>(empleado, HttpStatus.CREATED);
    }

    public ResponseEntity<Empleado> update(Long id, Empleado empleado){
        Empleado empleadoExists = getEmpleado(id).getBody();
        Cargo cargo = cargoService.findById(empleado.getCargo().getId()).getBody();
        if(empleadoExists == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(cargo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        empleadoExists.setName(empleado.getName());
        empleadoExists.setApellido(empleado.getApellido());
        empleadoExists.setCargo(empleado.getCargo());
        empleadoExists.setSalario(empleado.getSalario());
        empleadoExists.setStatus('1');
        empleadoRepository.save(empleadoExists);
        return new ResponseEntity<>(empleadoExists, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Empleado> delete(Long id){
        empleadoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateData(Empleado empleado){
        if(empleado.getName().isEmpty() || empleado.getName().isBlank()){
            throw new CustomException("Nombre en blanco!");
        }
        if(empleado.getApellido().isEmpty() || empleado.getApellido().isBlank()){
            throw new CustomException("Apellido en blanco!");
        }
        if(Integer.parseInt(empleado.getSalario()) <= 0){
            throw new CustomException("El salario debe ser positivo!");
        }
    }
}
