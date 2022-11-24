package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.TurnosTrabajadoresDTO;

public interface TurnosTrabajadoresService {

    public TurnosTrabajadoresDTO createTurno(long id_trabajador,long id_turno,TurnosTrabajadoresDTO turnosTrabajadoresDTO);

}
