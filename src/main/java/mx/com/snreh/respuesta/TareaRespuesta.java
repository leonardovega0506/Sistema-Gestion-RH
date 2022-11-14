package mx.com.snreh.respuesta;

import lombok.Data;
import mx.com.snreh.dto.TareaDTO;

import java.util.List;

@Data
public class TareaRespuesta {
    private List<TareaDTO> contenido;
    private int numeroPagina;
    private int sizePagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultima;

}
