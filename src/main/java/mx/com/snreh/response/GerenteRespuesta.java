package mx.com.snreh.response;

import lombok.Data;
import mx.com.snreh.dto.GerenteDTO;

import java.util.List;

@Data
public class GerenteRespuesta {
    private List<GerenteDTO> contenido;
    private int numeroPagina;
    private int sizePagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultima;

}