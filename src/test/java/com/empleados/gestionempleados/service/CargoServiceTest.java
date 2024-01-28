package com.empleados.gestionempleados.service;

import com.empleados.gestionempleados.entities.Cargo;
import com.empleados.gestionempleados.repository.CargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CargoServiceTest {

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private CargoService cargoService;

    private Cargo cargo;
    private Cargo cargo1;
    private Cargo cargo2;

    @BeforeEach
    void setUp() {
        data();
    }

    @Test
    void getAll() {
        when(cargoRepository.findAll()).thenReturn(Arrays.asList(cargo));
        List<Cargo> result = cargoService.getAll().getBody();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        Cargo cargo1 = result.get(0);
        assertEquals(0L, cargo1.getId());
        assertEquals("Gerente", cargo1.getCargo());
        assertEquals('1', cargo1.getStatus());
    }

    @Test
    void findByCargo() {
        when(cargoRepository.findByCargo(anyString())).thenReturn(cargo2);
        Cargo result = cargoService.findByCargo("PM").getBody();

        assertNotNull(result);

        assertEquals(2L, result.getId());
        assertEquals("PM", result.getCargo());
        assertEquals('1', result.getStatus());
    }

    @Test
    void findById() {
        // Case 1: El cargo existe en la base de datos
        when(cargoRepository.findById(anyLong())).thenReturn(Optional.ofNullable(cargo));
        Cargo result = cargoService.findById(0L).getBody();

        assertNotNull(result);

        assertEquals(0L, result.getId());
        assertEquals("Gerente", result.getCargo());
        assertEquals('1', result.getStatus());
    }

    @Test
    void create() {
        when(cargoRepository.save(any(Cargo.class))).thenReturn(cargo);
        Cargo result = cargoService.create(cargo).getBody();

        assertNotNull(result);

        assertEquals(0L, result.getId());
        assertEquals("Gerente", result.getCargo());
        assertEquals('1', result.getStatus());
    }

    private void data(){
        cargo = new Cargo();
        cargo.setId(0L);
        cargo.setCargo("Gerente");
        cargo.setStatus('1');

        cargo1 = new Cargo();
        cargo1.setId(1L);
        cargo1.setCargo("Desarrollador");
        cargo1.setStatus('1');

        cargo2 = new Cargo();
        cargo2.setId(2L);
        cargo2.setCargo("PM");
        cargo2.setStatus('1');

        cargoRepository.saveAll(Arrays.asList(cargo, cargo1, cargo2));
    }
}