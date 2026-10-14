package dev.eder.paciente.mapper;

import dev.eder.paciente.dto.PacienteRequest;
import dev.eder.paciente.dto.PacienteResponse;
import dev.eder.paciente.entity.Paciente;

public class PacienteMapper {
    private PacienteMapper() {
        // Constructor privado para evitar instanciación
    }

    public static Paciente toEntity(PacienteRequest request){
        Paciente paciente = new Paciente();
        actualizarEntidad(request,paciente);
        return paciente;
    }


    public static void actualizarEntidad (PacienteRequest request, Paciente paciente){
        paciente.setDni(request.dni().trim());
        paciente.setNombres(request.nombres().trim());
        paciente.setApellidos(request.apellidos().trim());
        paciente.setEmail(normalizarEmail(request.email()));
        paciente.setSeguro(request.seguro());
        paciente.setEdad(request.edad());
    }


    public static PacienteResponse toResponse(Paciente paciente){ 
        return PacienteResponse.builder()
                .id(paciente.getId())
                .dni(paciente.getDni())
                .nombres(paciente.getNombres())
                .apellidos(paciente.getApellidos())
                .descripcion(paciente.getNombres() + " " + paciente.getApellidos() + " - " + paciente.getSeguro())
                .email(paciente.getEmail())
                .seguro(paciente.getSeguro())
                .fechaRegistro(paciente.getFechaRegistro())
                .build();
    }



    public static String normalizarEmail(String email){
        return email.trim().toLowerCase();
    }
}
