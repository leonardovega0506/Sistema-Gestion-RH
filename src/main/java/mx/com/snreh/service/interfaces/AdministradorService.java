package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.AdministradorDTO;
import mx.com.snreh.response.AdministradorRespuesta;

public interface AdministradorService {
    public AdministradorDTO crearAdministrador(AdministradorDTO administradorDTO);

    public AdministradorRespuesta obtenerAdministradores(int numeroPagina, int sizePagina);

    public AdministradorDTO obtenerAdministradorById(long id);

    public AdministradorDTO actualizarAdministrador(AdministradorDTO administradorDTO, long id);

    public void eliminiarAdministrador(long id);
}
