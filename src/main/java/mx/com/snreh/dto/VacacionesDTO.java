package mx.com.snreh.dto;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class VacacionesDTO {

    private long id_vacacion;

    @NotEmpty
    private String estatus_vacacion;

    @NotEmpty
    private Date fecha_inicio;

    @NotEmpty
    private Date fecha_fin;

    @NotEmpty
    private double prima_vacacional;

    @NotEmpty
    private int cantidad_dias;
}
