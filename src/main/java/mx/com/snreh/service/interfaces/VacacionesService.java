package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.VacacionesDTO;

import java.util.List;

public interface VacacionesService {
    public VacacionesDTO createVacacion(long id_trabajador, VacacionesDTO vacacionesDTO);

    public List<VacacionesDTO> findAllVacacionesTrabajador(long id_trabajador);

    public VacacionesDTO findVacaciones(long id_trabajador, long id_vacaciones);

    public VacacionesDTO updateVacaciones(long id_trabajador, long id_vacaciones, VacacionesDTO vacacionesDTO);

    public void deleteVacaciones(long id_trabajador, long id_vacaciones);

    public List<VacacionesDTO> findAllVacaciones();
}
