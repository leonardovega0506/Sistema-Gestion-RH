package mx.com.snreh.respuesta;

import lombok.Data;
import mx.com.snreh.dto.AdministradorDTO;

import java.util.List;

@Data
public class AdministradorRespuesta {
    private List<AdministradorDTO> contenido;
    private int numeroPagina;
    private int sizePagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultima;

}
