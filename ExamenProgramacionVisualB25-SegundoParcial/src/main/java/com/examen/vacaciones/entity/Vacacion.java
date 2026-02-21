package com.examen.vacaciones.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vacaciones")
public class Vacacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacacion")
    private Integer idVacacion;

    @Column(name = "nombre_empleado", nullable = false, length = 120)
    private String nombreEmpleado;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "motivo", length = 255)
    private String motivo;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = "A";

    @Column(name = "usr_creacion", nullable = false, length = 63)
    private String usrCreacion;

    @Column(name = "fe_creacion", nullable = false, updatable = false)
    private LocalDateTime feCreacion;

    @Column(name = "usr_ult_modificacion", length = 63)
    private String usrUltModificacion;

    @Column(name = "fe_ult_modificacion")
    private LocalDateTime feUltModificacion;

    @PrePersist
    protected void onCreate() {
        this.feCreacion = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = "A";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.feUltModificacion = LocalDateTime.now();
    }

    public Integer getIdVacacion() {
        return idVacacion;
    }

    public void setIdVacacion(Integer idVacacion) {
        this.idVacacion = idVacacion;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsrCreacion() {
        return usrCreacion;
    }

    public void setUsrCreacion(String usrCreacion) {
        this.usrCreacion = usrCreacion;
    }

    public LocalDateTime getFeCreacion() {
        return feCreacion;
    }

    public void setFeCreacion(LocalDateTime feCreacion) {
        this.feCreacion = feCreacion;
    }

    public String getUsrUltModificacion() {
        return usrUltModificacion;
    }

    public void setUsrUltModificacion(String usrUltModificacion) {
        this.usrUltModificacion = usrUltModificacion;
    }

    public LocalDateTime getFeUltModificacion() {
        return feUltModificacion;
    }

    public void setFeUltModificacion(LocalDateTime feUltModificacion) {
        this.feUltModificacion = feUltModificacion;
    }
}
