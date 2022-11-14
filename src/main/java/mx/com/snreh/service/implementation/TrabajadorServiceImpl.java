package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.TrabajadorDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.respuesta.TrabajadorRespuesta;
import mx.com.snreh.service.interfaces.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    @Autowired
    private ITrabajador iTrabajador;


    @Override
    public TrabajadorDTO crearTrabajador(TrabajadorDTO trabajadorDTO) {
        TrabajadorModel trabajadorModel = mapearEntidad(trabajadorDTO);
        TrabajadorModel trabajadorNuevo = iTrabajador.save(trabajadorModel);
        TrabajadorDTO trabajadorRespuesta = mapearDTO(trabajadorNuevo);
        return trabajadorRespuesta;
    }

    @Override
    public TrabajadorRespuesta obtenerTrabajadores(int numeroPagina, int sizePagina) {
        Pageable pageable = PageRequest.of(numeroPagina,sizePagina);
        Page<TrabajadorModel> trabajadores = iTrabajador.findAll(pageable);
        List<TrabajadorModel> listaTrabajadores = trabajadores.getContent();
        List<TrabajadorDTO> contenido = listaTrabajadores.stream().map(trabajador -> mapearDTO(trabajador)).collect(Collectors.toList());
        TrabajadorRespuesta respuesta = new TrabajadorRespuesta();
        respuesta.setContenido(contenido);
        respuesta.setNumeroPagina(trabajadores.getNumber());
        respuesta.setSizePagina(trabajadores.getSize());
        respuesta.setTotalElementos(trabajadores.getTotalElements());
        respuesta.setTotalPaginas(trabajadores.getTotalPages());
        respuesta.setUltima(trabajadores.isLast());
        return respuesta;
    }

    @Override
    public TrabajadorDTO obtenerTrabajadorById(long id) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Numero_trabajador",id));
        return mapearDTO(trabajadorModel);
    }

    @Override
    public TrabajadorDTO actualizarTrabajador(TrabajadorDTO trabajadorDTO, long id) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Numero_trabajador",id));

        trabajadorModel.setNombre_trabajador(trabajadorDTO.getNombre_trabajador());
        trabajadorModel.setApellidoP_trabajador(trabajadorDTO.getApellidoP_trabajador());
        trabajadorModel.setApellidoM_trabajador(trabajadorDTO.getApellido_Mtrabajador());
        trabajadorModel.setPuesto(trabajadorDTO.getPuesto());
        trabajadorModel.setSueldo(trabajadorDTO.getSueldo());
        trabajadorModel.setCelular(trabajadorDTO.getCelular());
        trabajadorModel.setCorreo_electronico(trabajadorDTO.getCorreo_electronico());
        trabajadorModel.setNumeroTrabajador(trabajadorDTO.getNumeroTrabajador());
        TrabajadorModel trabajadorActualizado = iTrabajador.save(trabajadorModel);

        return mapearDTO(trabajadorActualizado);
    }

    @Override
    public void eliminarTrabajador(long id) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Numero_trabajador",id));
        iTrabajador.delete(trabajadorModel);
    }

    private TrabajadorDTO mapearDTO(TrabajadorModel trabajadorModel){
        TrabajadorDTO trabajadorDTO = new TrabajadorDTO();
        trabajadorDTO.setId_trabajador(trabajadorModel.getId_trabajador());
        trabajadorDTO.setNumeroTrabajador(trabajadorModel.getNumeroTrabajador());
        trabajadorDTO.setNombre_trabajador(trabajadorModel.getNombre_trabajador());
        trabajadorDTO.setApellidoP_trabajador(trabajadorModel.getApellidoP_trabajador());
        trabajadorDTO.setApellido_Mtrabajador(trabajadorModel.getApellidoM_trabajador());
        trabajadorDTO.setSueldo(trabajadorModel.getSueldo());
        trabajadorDTO.setEstatus(trabajadorModel.getEstatus());
        trabajadorDTO.setCelular(trabajadorModel.getCelular());
        trabajadorDTO.setCorreo_electronico(trabajadorModel.getCorreo_electronico());
        trabajadorDTO.setPuesto(trabajadorModel.getPuesto());
        return trabajadorDTO;
    }
    private TrabajadorModel mapearEntidad(TrabajadorDTO trabajadorDTO){
        TrabajadorModel trabajadorModel = new TrabajadorModel();
        trabajadorModel.setId_trabajador(trabajadorDTO.getId_trabajador());
        trabajadorModel.setNumeroTrabajador(trabajadorDTO.getNumeroTrabajador());
        trabajadorModel.setNombre_trabajador(trabajadorDTO.getNombre_trabajador());
        trabajadorModel.setApellidoP_trabajador(trabajadorDTO.getApellidoP_trabajador());
        trabajadorModel.setApellidoM_trabajador(trabajadorDTO.getApellidoP_trabajador());
        trabajadorModel.setSueldo(trabajadorDTO.getSueldo());
        trabajadorModel.setEstatus(trabajadorDTO.getEstatus());
        trabajadorModel.setCelular(trabajadorDTO.getCelular());
        trabajadorModel.setCorreo_electronico(trabajadorDTO.getCorreo_electronico());
        trabajadorModel.setPuesto(trabajadorDTO.getPuesto());

        return trabajadorModel;
    }
}
