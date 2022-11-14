package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.GerenteDTO;
import mx.com.snreh.dto.TrabajadorDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.model.GerenteModel;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.IGerente;
import mx.com.snreh.respuesta.GerenteRespuesta;
import mx.com.snreh.respuesta.TrabajadorRespuesta;
import mx.com.snreh.service.interfaces.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GerenteServiceImpl implements GerenteService {

    @Autowired
    private IGerente iGerente;

    @Override
    public GerenteDTO crearGerente(GerenteDTO gerenteDTO) {
        GerenteModel gerenteModel = mapearEntidad(gerenteDTO);
        GerenteModel nuevoGerente = iGerente.save(gerenteModel);
        GerenteDTO respuesta = mapearDTO(nuevoGerente);

        return respuesta;
    }

    @Override
    public GerenteRespuesta obtenerGerentes(int numeroPagina, int sizePagina) {
        Pageable pageable = PageRequest.of(numeroPagina,sizePagina);
        Page<GerenteModel> gerentes = iGerente.findAll(pageable);
        List<GerenteModel> listaGerentes = gerentes.getContent();
        List<GerenteDTO> contenido = listaGerentes.stream().map(gerente -> mapearDTO(gerente)).collect(Collectors.toList());
        GerenteRespuesta respuesta = new GerenteRespuesta();
        respuesta.setContenido(contenido);
        respuesta.setNumeroPagina(gerentes.getNumber());
        respuesta.setSizePagina(gerentes.getSize());
        respuesta.setTotalElementos(gerentes.getTotalElements());
        respuesta.setTotalPaginas(gerentes.getTotalPages());
        respuesta.setUltima(gerentes.isLast());
        return respuesta;
    }

    @Override
    public GerenteDTO obtenerGerenteByID(long numero_gerente) {
        GerenteModel gerenteModel = iGerente.findById(numero_gerente).orElseThrow(() -> new ResourceNotFoundException("Gerente","Numero gerente",numero_gerente));
        return mapearDTO(gerenteModel);
    }

    @Override
    public GerenteDTO actualizarGerente(GerenteDTO gerenteDTO, long numero_gerente) {
        GerenteModel gerenteModel = iGerente.findById(numero_gerente).orElseThrow(() -> new ResourceNotFoundException("Gerente","Numero gerente",numero_gerente));
        gerenteModel.setNumero_gerente(gerenteDTO.getNumero_gerente());
        gerenteModel.setNombre_gerente(gerenteDTO.getNombre_gerente());
        gerenteModel.setApellidoP_gerente(gerenteDTO.getApellidoP_gerente());
        gerenteModel.setApllidoM_gerente(gerenteDTO.getApllidoM_gerente());
        gerenteModel.setSueldo(gerenteDTO.getSueldo());
        gerenteModel.setEstaus(gerenteDTO.getEstaus());
        gerenteModel.setCelular(gerenteDTO.getCelular());
        gerenteModel.setCorreo_electronico(gerenteDTO.getCorreo_electronico());

        GerenteModel gerenteActualizado= iGerente.save(gerenteModel);
        return mapearDTO(gerenteActualizado);
    }

    @Override
    public void eliminarGerente(long numero_gerente) {
        GerenteModel gerenteModel = iGerente.findById(numero_gerente).orElseThrow(() -> new ResourceNotFoundException("Gerente","Numero gerente",numero_gerente));
        iGerente.delete(gerenteModel);
    }
    public GerenteDTO mapearDTO(GerenteModel gerenteModel){
        GerenteDTO gerenteDTO = new GerenteDTO();
        gerenteDTO.setId_gerente(gerenteModel.getId_gerente());
        gerenteDTO.setNumero_gerente(gerenteModel.getNumero_gerente());
        gerenteDTO.setNombre_gerente(gerenteModel.getNombre_gerente());
        gerenteDTO.setApellidoP_gerente(gerenteModel.getApellidoP_gerente());
        gerenteDTO.setApllidoM_gerente(gerenteModel.getApllidoM_gerente());
        gerenteDTO.setSueldo(gerenteModel.getSueldo());
        gerenteDTO.setEstaus(gerenteModel.getEstaus());
        gerenteDTO.setCelular(gerenteModel.getCelular());
        gerenteDTO.setCorreo_electronico(gerenteModel.getCorreo_electronico());

        return gerenteDTO;
    }
    public GerenteModel mapearEntidad(GerenteDTO gerenteDTO){
        GerenteModel gerenteModel = new GerenteModel();
        gerenteModel.setId_gerente(gerenteDTO.getId_gerente());
        gerenteModel.setNumero_gerente(gerenteDTO.getNumero_gerente());
        gerenteModel.setNombre_gerente(gerenteDTO.getNombre_gerente());
        gerenteModel.setApellidoP_gerente(gerenteDTO.getApellidoP_gerente());
        gerenteModel.setApllidoM_gerente(gerenteDTO.getApllidoM_gerente());
        gerenteModel.setSueldo(gerenteDTO.getSueldo());
        gerenteModel.setEstaus(gerenteDTO.getEstaus());
        gerenteModel.setCelular(gerenteDTO.getCelular());
        gerenteModel.setCorreo_electronico(gerenteDTO.getCorreo_electronico());

        return gerenteModel;
    }
}
