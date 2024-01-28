package com.empleados.gestionempleados.service;

import com.empleados.gestionempleados.entities.Cargo;
import com.empleados.gestionempleados.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public ResponseEntity<List<Cargo>> getAll(){
        return new ResponseEntity<>(cargoRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Cargo> findByCargo(String cargo){
        Cargo cargoExists = cargoRepository.findByCargo(cargo);
        return new ResponseEntity<>(cargoExists, HttpStatus.OK);
    }

    public ResponseEntity<Cargo> findById(Long id){
        Cargo cargo = cargoRepository.findById(id).orElse(null);
        return new ResponseEntity<>(cargo, HttpStatus.OK);
    }

    public ResponseEntity<Cargo> create(Cargo cargo) {
        Cargo cargoExists = findByCargo(cargo.getCargo()).getBody();

        if(cargoExists != null){
            return new ResponseEntity<>(cargoExists, HttpStatus.OK);
        }
        System.out.println(cargo);
        cargoRepository.save(cargo);
        return new ResponseEntity<>(cargo, HttpStatus.CREATED);
    }
}
