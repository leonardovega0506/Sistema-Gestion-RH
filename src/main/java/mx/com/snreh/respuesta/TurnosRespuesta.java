package mx.com.snreh.respuesta;

import lombok.Data;
import mx.com.snreh.dto.TurnosDTO;

import java.util.List;

@Data
public class TurnosRespuesta {

    private List<TurnosDTO> contenido;
    private int numeroPagina;
    private int sizePagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultima;
}
