package dev.eder.paciente.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record PacienteResponse(
    Long id,
    String dni,
    String nombres,
    String apellidos,
    String descripcion,
    String email,
    String seguro,
    Integer edad,
    LocalDateTime fechaRegistro
) {

}
