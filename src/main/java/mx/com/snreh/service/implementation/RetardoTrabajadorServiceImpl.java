package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.RetardoTrabajadorDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.RetardoTrabajadorModel;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.IRetardo;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.service.implementation.interfaces.RetardoTrabajadorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetardoTrabajadorServiceImpl implements RetardoTrabajadorService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IRetardo iRetardo;

    @Autowired
    private ITrabajador iTrabajador;

    @Override
    public RetardoTrabajadorDTO crearRetardo(long id_trabajador, RetardoTrabajadorDTO retardoTrabajadorDTO) {
        RetardoTrabajadorModel retardoTrabajadorModel = mapearEntidad(retardoTrabajadorDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        retardoTrabajadorModel.setTrabajadorModel(trabajadorModel);
        RetardoTrabajadorModel retardoNuevo = iRetardo.save(retardoTrabajadorModel);

        return mapearDTO(retardoNuevo);
    }

    @Override
    public List<RetardoTrabajadorDTO> obtenerRetardos(long id_trabajador) {
       List<RetardoTrabajadorModel> retardos = iRetardo.findByTrabajadorModelId(id_trabajador);
       return retardos.stream().map(retardo -> mapearDTO(retardo)).collect(Collectors.toList());
    }

    @Override
    public RetardoTrabajadorDTO obtenberRetardoById(long id_trabajador, long id_retardo) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        RetardoTrabajadorModel retardoTrabajadorModel = iRetardo.findById(id_retardo).orElseThrow(() -> new ResourceNotFoundException("Retardo","ID",id_retardo));

        if(!retardoTrabajadorModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"El retardo no pertenece al trabajador");
        }
        return mapearDTO(retardoTrabajadorModel);
    }

    private RetardoTrabajadorDTO mapearDTO(RetardoTrabajadorModel retardoTrabajadorModel){
        RetardoTrabajadorDTO retardoTrabajadorDTO = modelMapper.map(retardoTrabajadorModel,RetardoTrabajadorDTO.class);
        return  retardoTrabajadorDTO;
    }
    private RetardoTrabajadorModel mapearEntidad(RetardoTrabajadorDTO retardoTrabajadorDTO){
        RetardoTrabajadorModel retardoTrabajadorModel = modelMapper.map(retardoTrabajadorDTO,RetardoTrabajadorModel.class);
        return retardoTrabajadorModel;
    }
}
