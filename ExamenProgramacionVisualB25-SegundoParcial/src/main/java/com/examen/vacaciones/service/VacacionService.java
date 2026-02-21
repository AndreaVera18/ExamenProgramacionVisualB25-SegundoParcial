package com.examen.vacaciones.service;

import com.examen.vacaciones.dto.VacacionDTO;
import com.examen.vacaciones.entity.Vacacion;
import com.examen.vacaciones.repository.VacacionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VacacionService {

    private final VacacionRepository vacacionRepository;

    public VacacionService(VacacionRepository vacacionRepository) {
        this.vacacionRepository = vacacionRepository;
    }

    public VacacionDTO crear(VacacionDTO vacacionDTO, String usuarioCreacion) {
        // PASO 1: validar que nombreEmpleado no sea null ni vacío
        if (vacacionDTO.getNombreEmpleado() == null || vacacionDTO.getNombreEmpleado().isBlank()) {
            throw new IllegalArgumentException("El nombre del empleado es obligatorio");
        }
        // PASO 2: validar que fechaInicio y fechaFin no sean null
        if (vacacionDTO.getFechaInicio() == null || vacacionDTO.getFechaFin() == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias");
        }
        // PASO 3: validar regla de negocio: fechaFin NO puede ser menor que fechaInicio
        if (vacacionDTO.getFechaFin().isBefore(vacacionDTO.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser menor que la fecha de inicio");
        }
        // PASO 4: convertir DTO a entidad
        Vacacion entidad = convertirAEntidad(vacacionDTO);
        // PASO 5: asignar estado "A" y usrCreacion con el header recibido
        entidad.setEstado("A");
        entidad.setUsrCreacion(usuarioCreacion);
        // PASO 6: guardar en base
        Vacacion guardada = vacacionRepository.save(entidad);
        // PASO 7: convertir la entidad guardada a DTO y retornar
        return convertirADTO(guardada);
    }

    public List<VacacionDTO> obtenerTodas() {
        // PASO 1: obtener todas las vacaciones
        // PASO 2: mapear cada entidad a DTO
        // PASO 3: retornar la lista mapeada
        return vacacionRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<VacacionDTO> obtenerPorId(Integer idVacacion) {
        // PASO 1: buscar por id
        // PASO 2: si existe, mapear a DTO
        // PASO 3: si no existe, retornar Optional.empty()
        return vacacionRepository.findById(idVacacion)
                .map(this::convertirADTO);
    }

    public Optional<VacacionDTO> actualizar(Integer idVacacion, VacacionDTO vacacionDTO, String usuarioModifica) {
        // PASO 1: buscar el registro por id
        Optional<Vacacion> optional = vacacionRepository.findById(idVacacion);
        // PASO 2: si no existe, retornar Optional.empty()
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Vacacion entidad = optional.get();
        // PASO 3: validar fechas (fechaFin >= fechaInicio) antes de guardar
        if (vacacionDTO.getFechaInicio() != null && vacacionDTO.getFechaFin() != null
                && vacacionDTO.getFechaFin().isBefore(vacacionDTO.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser menor que la fecha de inicio");
        }
        // PASO 4: actualizar campos editables: nombreEmpleado, fechaInicio, fechaFin, motivo
        entidad.setNombreEmpleado(vacacionDTO.getNombreEmpleado());
        entidad.setFechaInicio(vacacionDTO.getFechaInicio());
        entidad.setFechaFin(vacacionDTO.getFechaFin());
        entidad.setMotivo(vacacionDTO.getMotivo());
        // PASO 5: actualizar usrUltModificacion con el header recibido
        entidad.setUsrUltModificacion(usuarioModifica);
        // PASO 6: guardar entidad actualizada y mapear a DTO
        return Optional.of(convertirADTO(vacacionRepository.save(entidad)));
    }

    public Optional<VacacionDTO> inactivar(Integer idVacacion, String usuarioModifica) {
        // PASO 1: buscar el registro por id
        Optional<Vacacion> optional = vacacionRepository.findById(idVacacion);
        // PASO 2: si no existe, retornar Optional.empty()
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Vacacion entidad = optional.get();
        // PASO 3: baja lógica: estado = "I" (NO eliminar físicamente)
        entidad.setEstado("I");
        // PASO 4: asignar usrUltModificacion
        entidad.setUsrUltModificacion(usuarioModifica);
        // PASO 5: guardar y retornar DTO actualizado
        return Optional.of(convertirADTO(vacacionRepository.save(entidad)));
    }

    private VacacionDTO convertirADTO(Vacacion vacacion) {
        VacacionDTO dto = new VacacionDTO();
        dto.setIdVacacion(vacacion.getIdVacacion());
        dto.setNombreEmpleado(vacacion.getNombreEmpleado());
        dto.setFechaInicio(vacacion.getFechaInicio());
        dto.setFechaFin(vacacion.getFechaFin());
        dto.setMotivo(vacacion.getMotivo());
        dto.setEstado(vacacion.getEstado());
        return dto;
    }

    private Vacacion convertirAEntidad(VacacionDTO dto) {
        Vacacion entity = new Vacacion();
        entity.setIdVacacion(dto.getIdVacacion());
        entity.setNombreEmpleado(dto.getNombreEmpleado());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setMotivo(dto.getMotivo());
        entity.setEstado(dto.getEstado());
        return entity;
    }
}