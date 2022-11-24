package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.TareaDTO;

import java.util.List;

public interface TareaService {
    public TareaDTO crearTarea (long id_trabajador, TareaDTO tareaDTO);

    public List<TareaDTO> obtenerTareasTrabajador(long id_trabjador);

    public TareaDTO obtenerTareaByID(long id_trabajador, long id_tarea);

    public TareaDTO actualizarTarea(long id_trabajador, long id_tarea, TareaDTO tareaDTO);
}
