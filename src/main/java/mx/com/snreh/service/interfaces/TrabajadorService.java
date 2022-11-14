package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.TrabajadorDTO;
import mx.com.snreh.respuesta.TrabajadorRespuesta;

public interface TrabajadorService {

    public TrabajadorDTO crearTrabajador(TrabajadorDTO trabajadorDTO);

    public TrabajadorRespuesta obtenerTrabajadores(int numeroPagina, int sizePagina, String orderBy, String sortDir);

    public TrabajadorDTO obtenerTrabajadorByNumeroTrabajador(long numero_trabajador);

    public TrabajadorDTO actualizarTrabajador(TrabajadorDTO trabajadorDTO, long numero_trabajador);

    public void eliminarTrabajador(long numero_trabajador);
}
