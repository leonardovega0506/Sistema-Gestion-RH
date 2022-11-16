package mx.com.snreh.service.implementation.interfaces;

import mx.com.snreh.dto.RetardoTrabajadorDTO;

import java.util.List;

public interface RetardoTrabajadorService {

    public RetardoTrabajadorDTO crearRetardo(long id_trabajador, RetardoTrabajadorDTO retardoTrabajadorDTO);

    public List<RetardoTrabajadorDTO> obtenerRetardos(long id_trabajador);

    public RetardoTrabajadorDTO obtenberRetardoById(long id_trabajador,long id_retardo);
}
