package mx.com.snreh.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class RetardoTrabajadorDTO {
    private long id_retardo;

    @NotEmpty(message = "Debe de estar la fecha del retardo")
    private Date fecha_retardo;

    @NotEmpty(message = "Debe de tener el tiempo del retardo")
    private String tiempo_Retardo;

    @NotEmpty(message = "Debe de tener el descuento del retardo")
    private double descuento_retardo;
}
