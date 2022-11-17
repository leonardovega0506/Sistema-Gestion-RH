package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.HoraExtraDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.HoraExtraModel;
import mx.com.snreh.model.TareaModel;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.IHoraExtra;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.service.implementation.interfaces.HoraExtraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HoraExtraServiceImpl implements HoraExtraService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ITrabajador iTrabajador;

    @Autowired
    private IHoraExtra iHoraExtra;

    @Override
    public HoraExtraDTO createHoraExtra(Long id_trabajador, HoraExtraDTO horaExtraDTO) {
        HoraExtraModel horaExtraModel = mapearEntidad(horaExtraDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        horaExtraModel.setTrabajadorModel(trabajadorModel);
        HoraExtraModel horaExtraNueva = iHoraExtra.save(horaExtraModel);


        return mapearDTO(horaExtraNueva);
    }

    @Override
    public List<HoraExtraDTO> finAllHoraExtra(long id_trabajador) {
        List<HoraExtraModel> horasExtras = iHoraExtra.findByTrabajadorModelId(id_trabajador);
        return horasExtras.stream().map(horaExtra -> mapearDTO(horaExtra)).collect(Collectors.toList());
    }

    @Override
    public HoraExtraDTO findHoraExtra(long id_trabajador, long id_horaExtra) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        HoraExtraModel horaExtraModel = iHoraExtra.findById(id_horaExtra).orElseThrow(() -> new ResourceNotFoundException("Hora extra","ID",id_horaExtra));

        if(!horaExtraModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"La hora extra no pertene al trabajador");
        }
        return mapearDTO(horaExtraModel);
    }

    private HoraExtraDTO mapearDTO(HoraExtraModel horaExtraModel){
        HoraExtraDTO horaExtraDTO = modelMapper.map(horaExtraModel,HoraExtraDTO.class);
        return horaExtraDTO;
    }
    private HoraExtraModel mapearEntidad(HoraExtraDTO horaExtraDTO){
        HoraExtraModel horaExtraModel = modelMapper.map(horaExtraDTO, HoraExtraModel.class);
        return horaExtraModel;
    }
}
