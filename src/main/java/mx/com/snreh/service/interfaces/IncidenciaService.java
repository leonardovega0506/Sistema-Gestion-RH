package mx.com.snreh.service.interfaces;


import mx.com.snreh.dto.IncidenciaDTO;

import java.util.List;

public interface IncidenciaService {

    public IncidenciaDTO createIncidencia(Long id_trabajador, IncidenciaDTO incidenciaDTO);

    public List<IncidenciaDTO> findAllInciencias(Long id_trabajador);

    public IncidenciaDTO findIncidencia(Long id_trabajador, long id_incidencia);
}
