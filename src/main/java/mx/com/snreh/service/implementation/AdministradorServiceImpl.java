package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.AdministradorDTO;
import mx.com.snreh.dto.GerenteDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.model.AdministradorModel;
import mx.com.snreh.model.GerenteModel;
import mx.com.snreh.repository.IAdministrador;
import mx.com.snreh.response.AdministradorRespuesta;
import mx.com.snreh.service.implementation.interfaces.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministradorServiceImpl implements AdministradorService{

    @Autowired
    private IAdministrador iAdministrador;

    @Override
    public AdministradorDTO crearAdministrador(AdministradorDTO administradorDTO) {
        AdministradorModel administradorModel = mapearEntidad(administradorDTO);
        AdministradorModel nuevoAdmin = iAdministrador.save(administradorModel);
        AdministradorDTO respuesta = mapearDTO(nuevoAdmin);

        return respuesta;
    }

    @Override
    public AdministradorRespuesta obtenerAdministradores(int numeroPagina, int sizePagina) {
        Pageable pageable = PageRequest.of(numeroPagina,sizePagina);
        Page<AdministradorModel> admins = iAdministrador.findAll(pageable);
        List<AdministradorModel> listaAdmins = admins.getContent();
        List<AdministradorDTO> contenido = listaAdmins.stream().map(admin -> mapearDTO(admin)).collect(Collectors.toList());
        AdministradorRespuesta respuesta = new AdministradorRespuesta();
        respuesta.setContenido(contenido);
        respuesta.setNumeroPagina(admins.getNumber());
        respuesta.setSizePagina(admins.getSize());
        respuesta.setTotalElementos(admins.getTotalElements());
        respuesta.setTotalPaginas(admins.getTotalPages());
        respuesta.setUltima(admins.isLast());
        return respuesta;
    }

    @Override
    public AdministradorDTO obtenerAdministradorById(long id) {
        AdministradorModel administradorModel = iAdministrador.findById(id).orElseThrow(() -> new ResourceNotFoundException("Administrador","ID",id));
        return mapearDTO(administradorModel);
    }

    @Override
    public AdministradorDTO actualizarAdministrador(AdministradorDTO administradorDTO, long id) {
        AdministradorModel administradorModel = iAdministrador.findById(id).orElseThrow(() -> new ResourceNotFoundException("Administrador","ID",id));

        administradorModel.setUsuario(administradorDTO.getUsuario());
        administradorModel.setNombre(administradorDTO.getNombre());
        AdministradorModel adminactualizado = iAdministrador.save(administradorModel);
        return mapearDTO(adminactualizado);
    }

    @Override
    public void eliminiarAdministrador(long id) {
        AdministradorModel administradorModel = iAdministrador.findById(id).orElseThrow(() -> new ResourceNotFoundException("Administrador","ID",id));
        iAdministrador.delete(administradorModel);
    }

    private AdministradorDTO mapearDTO(AdministradorModel administradorModel){
        AdministradorDTO administradorDTO = new AdministradorDTO();
        administradorDTO.setId_administrador(administradorModel.getId_administrador());
        administradorDTO.setNombre(administradorModel.getNombre());
        administradorDTO.setUsuario(administradorModel.getUsuario());
        return administradorDTO;
    }
    private AdministradorModel mapearEntidad(AdministradorDTO administradorDTO){
        AdministradorModel administradorModel = new AdministradorModel();
        administradorModel.setId_administrador(administradorDTO.getId_administrador());
        administradorModel.setNombre(administradorDTO.getNombre());
        administradorModel.setUsuario(administradorDTO.getUsuario());
        return administradorModel;
    }
}