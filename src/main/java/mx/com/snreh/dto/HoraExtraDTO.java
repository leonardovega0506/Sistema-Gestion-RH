package mx.com.snreh.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class HoraExtraDTO {

    private long id_horaExtra;

    @NotEmpty
    private Date fecha_HoraExtra;

    @NotEmpty
    private int cantidad_horas;

    @NotEmpty
    private double costo_hora;


    private double aumento_total;

}
