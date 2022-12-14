package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.NominaDTO;

import java.util.List;

public interface NominaService {
    public NominaDTO createNomina(long id_trabajador, NominaDTO nominaDTO);

    public List<NominaDTO> findAllNominasTrabajador(long id_trabajador);

    public NominaDTO findNominaByID(long id_trabajador, long id_nomina);

    public NominaDTO updateNomina(long id_trabajador, long id_nomina, NominaDTO nominaDTO);

    public List<NominaDTO> findAllNominas();
}
