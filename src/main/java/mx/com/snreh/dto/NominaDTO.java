package mx.com.snreh.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
public class NominaDTO {

    private long id_nomina;

    @NotEmpty
    private Date fechaNomina;

    @NotEmpty
    private double nomina_trabajador;

    @NotEmpty
    private double isr;

    @NotEmpty
    private double descuento_retardo;

    @NotEmpty
    private double iva;

    @NotEmpty
    private int retardo_trabajador;
}
