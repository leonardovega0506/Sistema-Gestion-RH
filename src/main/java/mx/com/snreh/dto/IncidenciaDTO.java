package mx.com.snreh.dto;


import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class IncidenciaDTO {

    private long id_incidencia;

    @NotEmpty
    private String tipo_incidencia;

    @NotEmpty
    private String nombre_incidencia;

    @NotEmpty
    private String evidencia_incidencia;

    @NotEmpty
    private Date fecha_incidencia;
}
