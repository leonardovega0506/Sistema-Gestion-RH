package mx.com.snreh.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class EventoDTO {
    private long id;

    @NotEmpty(message = "la fecha no puede estar vacia")
    private Date fecha_evento;

    @NotEmpty(message = "El tipo de evento no puede ser vacio")
    private String tipo_evento;

    @NotEmpty(message = "El titulo no puede ser vacio")
    private String titulo_evento;

    @NotEmpty(message = "El cuerpo del evento no puede ser vacio")
    @Size(min = 10, message = "El cuerpo del evento debe tener al menos 10 cararcteres")
    private String cuerpo_evento;
}
