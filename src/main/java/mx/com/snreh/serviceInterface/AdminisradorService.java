package mx.com.snreh.service;

import mx.com.snreh.dto.AdministradorDTO;
import mx.com.snreh.dto.AdministradorRespuesta;

public interface AdminisradorService {
    public AdministradorDTO crearAdministrador(AdministradorDTO administradorDTO);

    public AdministradorRespuesta obtenerAdmins(int numeroPagina, int sizePagina, String orderBy, String sortDir);

    public AdministradorDTO obtenerAdminByID(long id_admin);

    public AdministradorDTO actualizarAdmin(AdministradorDTO administradorDTO, long id_admin);

    public void eliminarAdmin(long id_admin);
}
