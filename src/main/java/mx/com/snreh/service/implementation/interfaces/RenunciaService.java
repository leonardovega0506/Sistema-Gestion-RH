package mx.com.snreh.service.implementation.interfaces;

import mx.com.snreh.dto.RenunciaDTO;

public interface RenunciaService {

    public RenunciaDTO createRenuncia(long id_trabajador, RenunciaDTO renunciaDTO);

    public RenunciaDTO findRenuncia(long id_trabajador,long id_renuncia);

    public RenunciaDTO updateRenuncua(long id_trabajador,long id_renuncia, RenunciaDTO renunciaDTO);
}
