package dev.eder.paciente.excepcion;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
    LocalDateTime feccha,
    int estado,
    String mensaje,
    Map<String , String> errores
) {

}
