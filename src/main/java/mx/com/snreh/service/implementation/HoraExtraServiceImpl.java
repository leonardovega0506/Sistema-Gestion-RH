package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.HoraExtraDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.HoraExtraModel;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.IHoraExtra;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.service.interfaces.HoraExtraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HoraExtraServiceImpl implements HoraExtraService {

    @Autowired(required = false)
    private ModelMapper modelMapper;

    @Autowired
    private ITrabajador iTrabajador;

    @Autowired
    private IHoraExtra iHoraExtra;

    @Override
    public HoraExtraDTO createHoraExtra(Long id_trabajador, HoraExtraDTO horaExtraDTO) {

        HoraExtraModel horaExtraModel = mapearEntidad(horaExtraDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        if(trabajadorModel.getEstatus().equals("Baja")||trabajadorModel.getEstatus().equals("En Vacaciones")||trabajadorModel.getEstatus().equals("Pendiente de baja")||trabajadorModel.getEstatus().equals("Permiso")||trabajadorModel.getEstatus().equals("Incapacitado")){
            return null;
        }
        else{
            horaExtraModel.setAumento_total(horaExtraModel.getCosto_hora()*horaExtraDTO.getCantidad_horas());
            horaExtraModel.setTrabajadorModel(trabajadorModel);
            HoraExtraModel horaExtraNueva = iHoraExtra.save(horaExtraModel);
            return mapearDTO(horaExtraNueva);
        }

    }

    @Override
    public List<HoraExtraDTO> findAllHoraExtraTrabajadorID(long id_trabajador) {
        List<HoraExtraModel> horasExtras = iHoraExtra.findByTrabajadorModelId(id_trabajador);
        return horasExtras.stream().map(horaExtra -> mapearDTO(horaExtra)).collect(Collectors.toList());
    }

    @Override
    public List<HoraExtraDTO> findAllHorasExtra() {
        List<HoraExtraModel> horasExtras = iHoraExtra.findAll();
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
        HoraExtraDTO horaExtraDTO = new HoraExtraDTO();
        horaExtraDTO.setId_horaExtra(horaExtraModel.getId_horaExtra());
        horaExtraDTO.setCantidad_horas(horaExtraModel.getCantidad_horas());
        horaExtraDTO.setFecha_HoraExtra(horaExtraModel.getFecha_HoraExtra());
        horaExtraDTO.setCosto_hora(horaExtraModel.getCosto_hora());
        horaExtraDTO.setAumento_total(horaExtraModel.getAumento_total());
        return horaExtraDTO;
    }
    private HoraExtraModel mapearEntidad(HoraExtraDTO horaExtraDTO){
        HoraExtraModel horaExtraModel = new HoraExtraModel();
        horaExtraModel.setId_horaExtra(horaExtraDTO.getId_horaExtra());
        horaExtraModel.setCantidad_horas(horaExtraDTO.getCantidad_horas());
        horaExtraModel.setFecha_HoraExtra(horaExtraDTO.getFecha_HoraExtra());
        horaExtraModel.setCosto_hora(horaExtraDTO.getCosto_hora());
        horaExtraModel.setAumento_total(horaExtraDTO.getAumento_total());
        return horaExtraModel;
    }
}
