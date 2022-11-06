package mx.com.snreh.dto;

import lombok.Data;

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
