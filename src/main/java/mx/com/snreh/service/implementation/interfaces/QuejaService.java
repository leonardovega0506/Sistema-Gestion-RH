package mx.com.snreh.service.implementation.interfaces;

import mx.com.snreh.dto.QuejasDTO;

import java.util.List;

public interface QuejaService {

    public QuejasDTO createQueja(long id_trabajador, QuejasDTO quejasDTO);

    public List<QuejasDTO> findAllQuejas(long id_trabajador);

    public QuejasDTO findQueja(long id_trabajador, long id_queja);

    public QuejasDTO updateQueja(long id_trabajador, long id_queja, QuejasDTO quejasDTO);

    public void eliminarQueja(long id_trabajador, long id_queja);
}
