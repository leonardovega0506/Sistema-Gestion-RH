package mx.com.snreh.dto;

import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class QuejasDTO {

    private long id_queja;

    @NotEmpty(message = "La fecha no puede ser nula")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,fallbackPatterns = {"M/d/dd","dd.MM.yyyy"})
    private Date fecha_Queja;

    @NotEmpty(message = "El tipo no debe ser nulo")
    private String tipo_Queja;

    @NotEmpty(message = "El cuerpo no puede ser nulo")
    @Size(min = 10,message = "Debe de contener al menos 20 caracteres")
    private String cuerpo_queja;

    private String estatus_queja;
}
