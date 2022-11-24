package mx.com.snreh.service.implementation.interfaces;

import mx.com.snreh.dto.GerenteDTO;
import mx.com.snreh.response.GerenteRespuesta;

public interface GerenteService {

    public GerenteDTO crearGerente(GerenteDTO gerenteDTO);

    public GerenteRespuesta obtenerGerentes(int numeroPagina, int sizePagina);

    public GerenteDTO obtenerGerenteByID(long numero_gerente);

    public GerenteDTO actualizarGerente(GerenteDTO gerenteDTO,long numero_gerente);

    public void eliminarGerente(long numero_gerente);
}