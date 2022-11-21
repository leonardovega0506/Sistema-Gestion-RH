package mx.com.snreh.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TurnosDTO {

    private int id_turno;

    @NotEmpty
    private String turno;

    @NotEmpty
    private int cantidad_horas;
}
