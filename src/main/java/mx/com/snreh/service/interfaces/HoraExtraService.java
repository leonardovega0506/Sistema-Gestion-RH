package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.HoraExtraDTO;

import java.util.List;

public interface HoraExtraService {
    public HoraExtraDTO createHoraExtra(Long id_trabajador, HoraExtraDTO horaExtraDTO);

    public List<HoraExtraDTO> findAllHoraExtraTrabajadorID(long id_trabajador);

    public HoraExtraDTO findHoraExtra(long id_trabajador, long id_horaExtra);

    public List<HoraExtraDTO> findAllHorasExtra();
}
