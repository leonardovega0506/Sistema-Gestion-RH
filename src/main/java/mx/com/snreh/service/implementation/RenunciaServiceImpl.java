package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.RenunciaDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.RenunciaTrabajadorModel;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.IRenuncia;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.service.interfaces.RenunciaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RenunciaServiceImpl implements RenunciaService {

    @Autowired(required = false)
    private ModelMapper modelMapper;

    @Autowired
    private ITrabajador iTrabajador;

    @Autowired
    private IRenuncia iRenuncia;

    @Override
    public RenunciaDTO createRenuncia(long id_trabajador, RenunciaDTO renunciaDTO) {
        RenunciaTrabajadorModel renunciaTrabajadorModel = mapearEntidad(renunciaDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        renunciaTrabajadorModel.setTrabajadorModel(trabajadorModel);
        RenunciaTrabajadorModel renuncia = iRenuncia.save(renunciaTrabajadorModel);
        return mapearDTO(renuncia);
    }

    @Override
    public RenunciaDTO findRenuncia(long id_trabajador, long id_renuncia) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        RenunciaTrabajadorModel renunciaTrabajadorModel = iRenuncia.findById(id_renuncia).orElseThrow(() -> new ResourceNotFoundException("Renuncia","ID",id_renuncia));

        if(!renunciaTrabajadorModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"No es posible recuperar esa hoja de renuncia");
        }
        return mapearDTO(renunciaTrabajadorModel);
    }

    @Override
    public RenunciaDTO updateRenuncua(long id_trabajador, long id_renuncia, RenunciaDTO renunciaDTO) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        RenunciaTrabajadorModel renunciaTrabajadorModel = iRenuncia.findById(id_renuncia).orElseThrow(() -> new ResourceNotFoundException("Renuncia","ID",id_renuncia));

        if(!renunciaTrabajadorModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"No es posible recuperar esa hoja de renuncia");
        }
        trabajadorModel.setEstatus("Dado de baja");
        renunciaTrabajadorModel.setFiniquito(renunciaDTO.getFiniquito());

        TrabajadorModel trabajadorActualizado = iTrabajador.save(trabajadorModel);
        RenunciaTrabajadorModel renunciaActualizada = iRenuncia.save(renunciaTrabajadorModel);

        return mapearDTO(renunciaActualizada);
    }

    private RenunciaDTO mapearDTO(RenunciaTrabajadorModel renunciaTrabajadorModel){
        RenunciaDTO renunciaDTO = modelMapper.map(renunciaTrabajadorModel,RenunciaDTO.class);
        return renunciaDTO;
    }
    private RenunciaTrabajadorModel mapearEntidad(RenunciaDTO renunciaDTO){
        RenunciaTrabajadorModel renunciaTrabajadorModel = modelMapper.map(renunciaDTO, RenunciaTrabajadorModel.class);
        return renunciaTrabajadorModel;
    }
}