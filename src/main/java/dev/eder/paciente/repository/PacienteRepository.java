package dev.eder.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.eder.paciente.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    boolean existsByDni(String dni);
    boolean existsByDniAndIdNot(String dni, Long id);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
