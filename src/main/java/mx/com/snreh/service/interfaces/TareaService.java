package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.TareaDTO;

import java.util.List;

public interface TareaService {
    public TareaDTO createTarea (long id_trabajador, TareaDTO tareaDTO);

    public List<TareaDTO> findTareasTrabajador(long id_trabjador);

    public TareaDTO findTareaByID(long id_trabajador, long id_tarea);

    public TareaDTO updateTarea(long id_trabajador, long id_tarea, TareaDTO tareaDTO);

    public List<TareaDTO> findAllTareas();
}
