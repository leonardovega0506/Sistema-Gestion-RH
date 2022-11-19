package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.VacacionesDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.model.VacacionModel;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.repository.IVacaciones;
import mx.com.snreh.service.implementation.interfaces.VacacionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacacionesServiceImpl implements VacacionesService {

    @Autowired(required = false)
    private ModelMapper modelMapper;

    @Autowired
    private ITrabajador iTrabajador;

    @Autowired
    private IVacaciones iVacaciones;

    @Override
    public VacacionesDTO createVacacion(long id_trabajador, VacacionesDTO vacacionesDTO) {
        VacacionModel vacacionModel =mapearEntidad(vacacionesDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Id",id_trabajador));

        vacacionModel.setTrabajadorModel(trabajadorModel);
        VacacionModel nuevaVacacion = iVacaciones.save(vacacionModel);
        return mapearDTO(nuevaVacacion);
    }

    @Override
    public List<VacacionesDTO> findAllVacaciones(long id_trabajador) {
        return null;
    }

    @Override
    public VacacionesDTO findVacaciones(long id_trabajador, long id_vacaciones) {
        return null;
    }

    @Override
    public VacacionesDTO updateVacaciones(long id_trabajador, long id_vacaciones, VacacionesDTO vacacionesDTO) {
        return null;
    }

    @Override
    public void deleteVacaciones(long id_trabajador, long id_vacaciones) {

    }

    private VacacionesDTO mapearDTO(VacacionModel vacacionModel){
        VacacionesDTO vacacionesDTO = modelMapper.map(vacacionModel,VacacionesDTO.class);
        return vacacionesDTO;
    }

    private VacacionModel mapearEntidad(VacacionesDTO vacacionesDTO){
        VacacionModel vacacionModel= modelMapper.map(vacacionesDTO,VacacionModel.class);
        return vacacionModel;
    }
}
