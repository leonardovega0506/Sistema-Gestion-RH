package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.NominaDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.NominaTrabajadorModel;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.INomina;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.service.implementation.interfaces.NominaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NominaServiceImpl implements NominaService {

    @Autowired(required = false)
    private ModelMapper modelMapper;

    @Autowired
    private INomina iNomina;

    @Autowired
    private ITrabajador iTrabajador;


    @Override
    public NominaDTO createNomina(long id_trabajador, NominaDTO nominaDTO) {
        NominaTrabajadorModel nominaTrabajadorModel = mapearEntidad(nominaDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        nominaTrabajadorModel.setTrabajadorModel(trabajadorModel);
        NominaTrabajadorModel nominaNueva = iNomina.save(nominaTrabajadorModel);

        return mapearDTO(nominaNueva);

    }

    @Override
    public List<NominaDTO> findAllNominas(long id_trabajador) {
        List<NominaTrabajadorModel> nominas = iNomina.findByTrabajadorModelId(id_trabajador);
        return nominas.stream().map(nomina -> mapearDTO(nomina)).collect(Collectors.toList());
    }

    @Override
    public NominaDTO finNominaByID(long id_trabajador, long id_nomina) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        NominaTrabajadorModel nominaTrabajadorModel = iNomina.findById(id_nomina).orElseThrow(() -> new ResourceNotFoundException("Nomina","ID",id_nomina));

        if(!nominaTrabajadorModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta nomina no pertenece al trabjador seleccionado");
        }
        return mapearDTO(nominaTrabajadorModel);
    }

    @Override
    public NominaDTO updateNomina(long id_trabajador, long id_nomina, NominaDTO nominaDTO) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        NominaTrabajadorModel nominaTrabajadorModel = iNomina.findById(id_nomina).orElseThrow(() -> new ResourceNotFoundException("Nomina","ID",id_nomina));

        if(!nominaTrabajadorModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta nomina no pertenece al trabjador seleccionado");
        }
        nominaTrabajadorModel.setNomina_trabajador(nominaDTO.getNomina_trabajador());
        nominaTrabajadorModel.setDescuento_retardo(nominaDTO.getDescuento_retardo());
        nominaTrabajadorModel.setIsr(nominaDTO.getIsr());
        nominaTrabajadorModel.setIva(nominaDTO.getIva());

        NominaTrabajadorModel nominaactualizada = iNomina.save(nominaTrabajadorModel);
        return mapearDTO(nominaactualizada);
    }
    private NominaDTO mapearDTO(NominaTrabajadorModel nominaTrabajadorModel){
        NominaDTO nominaDTO = modelMapper.map(nominaTrabajadorModel, NominaDTO.class);
        return nominaDTO;
    }
    private NominaTrabajadorModel mapearEntidad(NominaDTO nominaDTO){
        NominaTrabajadorModel nominaTrabajadorModel = modelMapper.map(nominaDTO,NominaTrabajadorModel.class);
        return nominaTrabajadorModel;
    }
}
