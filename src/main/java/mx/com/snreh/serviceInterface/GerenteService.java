package mx.com.snreh.service;

import mx.com.snreh.dto.AdministradorDTO;
import mx.com.snreh.dto.GerenteDTO;
import mx.com.snreh.dto.GerenteRespuesta;

public interface GerenteService {
    public GerenteDTO crearGerente(GerenteDTO gerenteDTO);

    public GerenteRespuesta obtenerGerentes(int numeroPagina, int sizePagina, String orderBy, String sortDir);

    public GerenteDTO obtenerGerenteByID(long numero_gerente);

    public GerenteDTO actualizarPublicacion(GerenteDTO gerenteDTO, long numero_gerente);

    public void eliminarGerente(long numero_gerente);
}
