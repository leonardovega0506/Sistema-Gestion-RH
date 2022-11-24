package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.TareaDTO;
import mx.com.snreh.dto.TurnosDTO;
import mx.com.snreh.respuesta.TurnosRespuesta;

import java.util.List;

public interface TurnoService {

    public TurnosDTO createTurno(TurnosDTO turnosDTO);

    public TurnosRespuesta findAllTurnos(int numeroPagina, int sizePagina);

    public TurnosDTO findTurnoBy(long id_turno);

    public TurnosDTO updateTurno(long id_turno, TurnosDTO turnosDTO);

    public void deleteTurno(long id_turno);
}
