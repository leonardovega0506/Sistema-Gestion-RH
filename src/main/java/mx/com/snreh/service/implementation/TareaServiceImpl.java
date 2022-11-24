package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.TareaDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.TareaModel;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.ITarea;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.service.interfaces.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private ITarea iTarea;

    @Autowired
    private ITrabajador iTrabajador;

    @Override
    public TareaDTO createTarea(long id_trabajador, TareaDTO tareaDTO) {
        TareaModel tareaModel = mapearEntidad(tareaDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Numero_trabajador",id_trabajador));
        trabajadorModel.setEstatus("Ocupado");
        tareaModel.setTrabajadorModel(trabajadorModel);
        TareaModel nuevaTarea = iTarea.save(tareaModel);
        return mapearDTO(nuevaTarea);
    }

    @Override
    public List<TareaDTO> findTareasTrabajador(long id_trabjador) {
        List<TareaModel> tareas = iTarea.findByTrabajadorModelId(id_trabjador);
        return tareas.stream().map(tarea -> mapearDTO(tarea)).collect(Collectors.toList());
    }
    @Override
    public List<TareaDTO> findAllTareas() {
        List<TareaModel> tareas = iTarea.findAll();
        return tareas.stream().map(tarea -> mapearDTO(tarea)).collect(Collectors.toList());
    }

    @Override
    public TareaDTO findTareaByID(long id_trabajador, long id_tarea) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Numero_trabajador",id_trabajador));
        TareaModel tareaModel = iTarea.findById(id_tarea).orElseThrow(() -> new ResourceNotFoundException("Tarea","Id_tarea",id_tarea));
        if(tareaModel.getTrabajadorModel().getId() == trabajadorModel.getId()){
            return mapearDTO(tareaModel);
        }
        else {
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta tarea no esta asignada a este trabajador");
        }
    }

    @Override
    public TareaDTO updateTarea(long id_trabajador, long id_tarea, TareaDTO tareaDTO) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Numero_trabajador",id_trabajador));
        TareaModel tareaModel = iTarea.findById(id_tarea).orElseThrow(() -> new ResourceNotFoundException("Tarea","Id_tarea",id_tarea));
        if(tareaModel.getTrabajadorModel().getId() == trabajadorModel.getId()){
            trabajadorModel.setEstatus("Libre");
            tareaModel.setEstatus(tareaDTO.getEstatus());
            tareaModel.setFecha(tareaDTO.getFecha());
            tareaModel.setTrabajadorModel(trabajadorModel);
            TareaModel tareaActualizada = iTarea.save(tareaModel);
            return mapearDTO(tareaActualizada);
        }
        else {
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta tarea no esta asignada a este trabajador");
        }
    }


    private TareaDTO mapearDTO(TareaModel tareaModel){
        TareaDTO tareaDTO = new TareaDTO();
        tareaDTO.setId_Tarea(tareaModel.getId_Tarea());
        tareaDTO.setEstatus(tareaModel.getEstatus());
        tareaDTO.setFecha(tareaModel.getFecha());
        tareaDTO.setNombre(tareaModel.getNombre());

        return tareaDTO;
    }

    private TareaModel mapearEntidad(TareaDTO tareaDTO){
        TareaModel tareaModel = new TareaModel();
        tareaModel.setId_Tarea(tareaDTO.getId_Tarea());
        tareaModel.setFecha(tareaDTO.getFecha());
        tareaModel.setEstatus(tareaDTO.getEstatus());
        tareaModel.setNombre(tareaModel.getNombre());

        return tareaModel;
    }
}
