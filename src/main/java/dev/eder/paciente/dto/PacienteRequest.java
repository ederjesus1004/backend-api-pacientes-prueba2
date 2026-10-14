package dev.eder.paciente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PacienteRequest(
        @NotBlank(message = "El campo dni es obligatorio")
        @Pattern(regexp = "^\\d{8}$", message = "El campo dni debe tener 8 dígitos")
        String dni,

        @NotBlank(message = "El campo nombres es obligatorio")
        @Size(max = 80, message = "El campo nombres no puede tener más de 80 caracteres")
        String nombres,

        @NotBlank(message = "El campo apellidos es obligatorio")
        @Size(max = 80, message = "El campo apellidos no puede tener más de 80 caracteres")
        String apellidos,

        @NotBlank(message = "El campo email es obligatorio")
        @Email(message = "El campo email debe ser un correo electrónico válido")
        @Size(max = 100, message = "El campo email no puede tener más de 100 caracteres")
        String email,

        @NotBlank(message = "El campo seguro es obligatorio")
        @Size(max = 100, message = "El campo seguro no puede tener más de 100 caracteres")
        String seguro,

        @NotNull(message = "El campo edad es obligatorio")
        @Min(value = 0, message = "El campo edad debe ser un número positivo")
        @Max (value = 120, message = "El campo edad no puede ser mayor a 120")
        Integer edad

) {

}
