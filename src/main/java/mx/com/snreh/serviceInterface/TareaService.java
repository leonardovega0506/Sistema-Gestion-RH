package mx.com.snreh.service;

import mx.com.snreh.dto.TareaDTO;

import java.util.List;

public interface TareaService {
    public TareaDTO crearTarea(Integer numero_trabajador, TareaDTO tareaDTO);

    public List<TareaDTO> obtenerTareas(Integer numero_trabajador);

    public TareaDTO obtenerTareaByID(Integer  numero_trabajador, Integer id_tarea);

    public TareaDTO actualizarTarea(Integer numero_trabajador, TareaDTO tareaDTO, Integer id_tarea);

    public void eliminarTarea(Integer numero_trabajador, Integer id_tarea);
}
