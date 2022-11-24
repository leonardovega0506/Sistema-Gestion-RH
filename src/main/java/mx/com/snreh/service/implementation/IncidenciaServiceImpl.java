package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.IncidenciaDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.IncidenciaModel;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.repository.IncidenciaI;
import mx.com.snreh.service.interfaces.IncidenciaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    private ITrabajador iTrabajador;

    @Autowired
    private IncidenciaI incidenciaI;

    @Autowired(required = false)
    private ModelMapper modelMapper;

    @Override
    public IncidenciaDTO createIncidencia(Long id_trabajador, IncidenciaDTO incidenciaDTO) {
        IncidenciaModel incidenciaModel = mapearEntidad(incidenciaDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        incidenciaModel.setTrabajadorModel(trabajadorModel);
        IncidenciaModel nuevaIncidencia = incidenciaI.save(incidenciaModel);
        return mapearDTO(nuevaIncidencia);
    }

    @Override
    public List<IncidenciaDTO> findAllInciencias(Long id_trabajador) {
        List<IncidenciaModel> incidencias = incidenciaI.findByTrabajadorModelId(id_trabajador);
        return incidencias.stream().map(incidencia -> mapearDTO(incidencia)).collect(Collectors.toList());
    }

    @Override
    public IncidenciaDTO findIncidencia(Long id_trabajador, long id_incidencia) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        IncidenciaModel incidenciaModel = incidenciaI.findById(id_incidencia).orElseThrow(() -> new ResourceNotFoundException("Incidencia","ID",id_incidencia));

        if(!incidenciaModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta incidencia no pertenece al trabajador seleccionado");
        }
        return mapearDTO(incidenciaModel);

    }




    private IncidenciaDTO mapearDTO(IncidenciaModel incidenciaModel){
        IncidenciaDTO incidenciaDTO = modelMapper.map(incidenciaModel,IncidenciaDTO.class);
        return incidenciaDTO;
    }
    private IncidenciaModel mapearEntidad(IncidenciaDTO incidenciaDTO){
        IncidenciaModel incidenciaModel = modelMapper.map(incidenciaDTO, IncidenciaModel.class);
        return  incidenciaModel;
    }
}