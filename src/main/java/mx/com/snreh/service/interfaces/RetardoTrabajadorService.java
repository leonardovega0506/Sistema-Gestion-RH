package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.RetardoTrabajadorDTO;

import java.util.List;

public interface RetardoTrabajadorService {

    public RetardoTrabajadorDTO createRetardo(long id_trabajador, RetardoTrabajadorDTO retardoTrabajadorDTO);

    public List<RetardoTrabajadorDTO> findRetardosTrabajadorID(long id_trabajador);

    public RetardoTrabajadorDTO findRetardoById(long id_trabajador,long id_retardo);

    public List<RetardoTrabajadorDTO> findAllRetardos();
}
