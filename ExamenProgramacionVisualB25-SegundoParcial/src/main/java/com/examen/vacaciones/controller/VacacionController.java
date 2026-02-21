package com.examen.vacaciones.controller;

import com.examen.vacaciones.dto.VacacionDTO;
import com.examen.vacaciones.service.VacacionService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vacaciones")
public class VacacionController {

    private final VacacionService vacacionService;

    public VacacionController(VacacionService vacacionService) {
        this.vacacionService = vacacionService;
    }

    @PostMapping
    public ResponseEntity<VacacionDTO> crear(
            @Valid @RequestBody VacacionDTO vacacionDTO,
            @RequestHeader(value = "usuario", defaultValue = "SYSTEM") String usuario) {
        VacacionDTO creada = vacacionService.crear(vacacionDTO, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<VacacionDTO>> obtenerTodas() {
        List<VacacionDTO> lista = vacacionService.obtenerTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacacionDTO> obtenerPorId(
            @PathVariable("id") Integer idVacacion) {
        Optional<VacacionDTO> vacacion = vacacionService.obtenerPorId(idVacacion);
        if (vacacion.isPresent()) {
            return ResponseEntity.ok(vacacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacacionDTO> actualizar(
            @PathVariable("id") Integer idVacacion,
            @Valid @RequestBody VacacionDTO vacacionDTO,
            @RequestHeader(value = "usrModifica", defaultValue = "SYSTEM") String usuarioModifica) {
        Optional<VacacionDTO> resultado = vacacionService.actualizar(idVacacion, vacacionDTO, usuarioModifica);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/inactivar")
    public ResponseEntity<VacacionDTO> inactivar(
            @PathVariable("id") Integer idVacacion,
            @RequestHeader(value = "usrModifica", defaultValue = "SYSTEM") String usuarioModifica) {
        Optional<VacacionDTO> resultado = vacacionService.inactivar(idVacacion, usuarioModifica);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}