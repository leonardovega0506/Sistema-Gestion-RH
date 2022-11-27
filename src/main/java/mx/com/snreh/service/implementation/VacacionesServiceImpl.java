package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.VacacionesDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.model.VacacionModel;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.repository.IVacaciones;
import mx.com.snreh.service.interfaces.VacacionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacacionesServiceImpl implements VacacionesService {

    @Autowired
    private ITrabajador iTrabajador;

    @Autowired
    private IVacaciones iVacaciones;

    @Override
    public VacacionesDTO createVacacion(long id_trabajador, VacacionesDTO vacacionesDTO) {
        VacacionModel vacacionModel =mapearEntidad(vacacionesDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Id",id_trabajador));
        trabajadorModel.setEstatus("Pendiente de Aprobacion Vacaciones");
        vacacionModel.setTrabajadorModel(trabajadorModel);
        VacacionModel nuevaVacacion = iVacaciones.save(vacacionModel);
        return mapearDTO(nuevaVacacion);
    }

    @Override
    public List<VacacionesDTO> findAllVacacionesTrabajador(long id_trabajador) {
       List<VacacionModel> vacaciones = iVacaciones.findByTrabajadorModelId(id_trabajador);
        return vacaciones.stream().map(vacacion -> mapearDTO(vacacion)).collect(Collectors.toList());
    }
    @Override
    public List<VacacionesDTO> findAllVacaciones() {
        List<VacacionModel> vacaciones = iVacaciones.findAll();
        return  vacaciones.stream().map(vacacion -> mapearDTO(vacacion)).collect(Collectors.toList());
    }

    @Override
    public VacacionesDTO findVacaciones(long id_trabajador, long id_vacaciones) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Id",id_trabajador));
        VacacionModel vacacionModel = iVacaciones.findById(id_vacaciones).orElseThrow(() -> new ResourceNotFoundException("Vacaciones","ID",id_vacaciones));

        if(!vacacionModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta vacacion no esta asociada al trabajador");
        }
        return mapearDTO(vacacionModel);
    }

    @Override
    public VacacionesDTO updateVacaciones(long id_trabajador, long id_vacaciones, VacacionesDTO vacacionesDTO) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Id",id_trabajador));
        VacacionModel vacacionModel = iVacaciones.findById(id_vacaciones).orElseThrow(() -> new ResourceNotFoundException("Vacaciones","ID",id_vacaciones));

        if(!vacacionModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta vacacion no esta asociada al trabajador");
        }
        if(vacacionModel.getEstatus_vacacion().equals("Aprobada")){
            trabajadorModel.setEstatus("En Vacaciones");
            vacacionModel.setEstatus_vacacion(vacacionesDTO.getEstatus_vacacion());
            vacacionModel.setPrima_vacacional(vacacionesDTO.getPrima_vacacional());
            vacacionModel.setFecha_inicio(vacacionesDTO.getFecha_inicio());
            vacacionModel.setFecha_fin(vacacionModel.getFecha_inicio());
            vacacionModel.setCantidad_dias(vacacionesDTO.getCantidad_dias());
            VacacionModel vacacionActualizada = iVacaciones.save(vacacionModel);
            return mapearDTO(vacacionActualizada);
        }
        else if(vacacionModel.getEstatus_vacacion().equals("Pendiente")){
            vacacionModel.setEstatus_vacacion(vacacionesDTO.getEstatus_vacacion());
            vacacionModel.setPrima_vacacional(vacacionesDTO.getPrima_vacacional());
            vacacionModel.setFecha_inicio(vacacionesDTO.getFecha_inicio());
            vacacionModel.setFecha_fin(vacacionModel.getFecha_inicio());
            vacacionModel.setCantidad_dias(vacacionesDTO.getCantidad_dias());
            VacacionModel vacacionActualizada = iVacaciones.save(vacacionModel);
            return mapearDTO(vacacionActualizada);
        }
        else if(vacacionModel.getEstatus_vacacion().equals("Rechazada")){
            trabajadorModel.setEstatus("Libre");
            vacacionModel.setPrima_vacacional(vacacionesDTO.getPrima_vacacional());
            vacacionModel.setFecha_inicio(vacacionesDTO.getFecha_inicio());
            vacacionModel.setFecha_fin(vacacionModel.getFecha_inicio());
            vacacionModel.setCantidad_dias(vacacionesDTO.getCantidad_dias());
            VacacionModel vacacionActualizada = iVacaciones.save(vacacionModel);
            return mapearDTO(vacacionActualizada);
        }
    else{
        return null;
        }


    }

    @Override
    public void deleteVacaciones(long id_trabajador, long id_vacaciones) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Id",id_trabajador));
        VacacionModel vacacionModel = iVacaciones.findById(id_vacaciones).orElseThrow(() -> new ResourceNotFoundException("Vacaciones","ID",id_vacaciones));

        if(!vacacionModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta vacacion no esta asociada al trabajador");
        }
        iVacaciones.delete(vacacionModel);
    }


    private VacacionesDTO mapearDTO(VacacionModel vacacionModel){
        VacacionesDTO vacacionesDTO = new VacacionesDTO();
        vacacionesDTO.setId_vacacion(vacacionModel.getId_vacacion());
        vacacionesDTO.setEstatus_vacacion(vacacionModel.getEstatus_vacacion());
        vacacionesDTO.setPrima_vacacional(vacacionModel.getPrima_vacacional());
        vacacionesDTO.setFecha_inicio(vacacionModel.getFecha_inicio());
        vacacionesDTO.setFecha_fin(vacacionModel.getFecha_fin());
        vacacionesDTO.setCantidad_dias(vacacionModel.getCantidad_dias());
        return vacacionesDTO;
    }

    private VacacionModel mapearEntidad(VacacionesDTO vacacionesDTO){
        VacacionModel vacacionModel = new VacacionModel();
        vacacionModel.setId_vacacion(vacacionesDTO.getId_vacacion());
        vacacionModel.setEstatus_vacacion(vacacionesDTO.getEstatus_vacacion());
        vacacionModel.setPrima_vacacional(vacacionesDTO.getPrima_vacacional());
        vacacionModel.setFecha_inicio(vacacionesDTO.getFecha_inicio());
        vacacionModel.setFecha_fin(vacacionesDTO.getFecha_fin());
        vacacionModel.setCantidad_dias(vacacionesDTO.getCantidad_dias());
        return vacacionModel;
    }
}
