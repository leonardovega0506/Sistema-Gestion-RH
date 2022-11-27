package mx.com.snreh.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class RenunciaDTO {

    private long id_renuncia;

    @NotEmpty
    private Date fecha_renuncia;

    @NotEmpty
    private String motivo_renuncia;

    @NotEmpty
    private double finiquito;

    @NotEmpty
    private long tiempoTrabajado;

    private String estatusRenuncia;
}
