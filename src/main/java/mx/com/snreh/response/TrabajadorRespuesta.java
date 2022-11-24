package mx.com.snreh.response;

import lombok.Data;
import mx.com.snreh.dto.TrabajadorDTO;

import java.util.List;

@Data
public class TrabajadorRespuesta {
    private List<TrabajadorDTO> contenido;
    private int numeroPagina;
    private int sizePagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultima;

}