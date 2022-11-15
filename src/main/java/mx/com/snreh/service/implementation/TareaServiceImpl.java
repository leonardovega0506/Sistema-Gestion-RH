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
    public TareaDTO crearTarea(long id_trabajador, TareaDTO tareaDTO) {
        TareaModel tareaModel = mapearEntidad(tareaDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Numero_trabajador",id_trabajador));

        tareaModel.setTrabajadorModel(trabajadorModel);
        TareaModel nuevaTarea = iTarea.save(tareaModel);
        return mapearDTO(nuevaTarea);
    }

    @Override
    public List<TareaDTO> obtenerTareasTrabajador(long id_trabjador) {
        List<TareaModel> tareas = iTarea.findByTrabajadorId(id_trabjador);
        return tareas.stream().map(tarea -> mapearDTO(tarea)).collect(Collectors.toList());
    }

    @Override
    public TareaDTO obtenerTareaByID(long id_trabajador, long id_tarea) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Numero_trabajador",id_trabajador));
        TareaModel tareaModel = iTarea.findById(id_tarea).orElseThrow(() -> new ResourceNotFoundException("Tarea","Id_tarea",id_tarea));
        if(tareaModel.getTrabajadorModel().getId_trabajador() == trabajadorModel.getId_trabajador()){
            return mapearDTO(tareaModel);
        }
        else {
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta tarea no esta asignada a este trabajador");
        }
    }

    @Override
    public TareaDTO actualizarTarea(long id_trabajador, long id_tarea, TareaDTO tareaDTO) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","Numero_trabajador",id_trabajador));
        TareaModel tareaModel = iTarea.findById(id_tarea).orElseThrow(() -> new ResourceNotFoundException("Tarea","Id_tarea",id_tarea));
        if(tareaModel.getTrabajadorModel().getId_trabajador() == trabajadorModel.getId_trabajador()){
            tareaModel.setEstatus(tareaDTO.getEstatus());
            tareaModel.setFecha(tareaDTO.getFecha());
            return mapearDTO(tareaModel);
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
