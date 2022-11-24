package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.TurnosTrabajadoresDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.model.TurnosModel;
import mx.com.snreh.model.TurnosTrabajadorModel;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.repository.ITurno;
import mx.com.snreh.repository.ITurnosTrabajadores;
import mx.com.snreh.service.interfaces.TurnoService;
import mx.com.snreh.service.interfaces.TurnosTrabajadoresService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnosTrabajadoresServiceImpl implements TurnosTrabajadoresService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ITurnosTrabajadores iTurnosTrabajadores;

    @Autowired
    private ITrabajador iTrabajador;

    @Autowired
    private ITurno iTurno;

    @Override
    public TurnosTrabajadoresDTO createTurno(long id_trabajador, long id_turno, TurnosTrabajadoresDTO turnosTrabajadoresDTO) {
        TurnosTrabajadorModel turnosTrabajadorModel = mapearEntidad(turnosTrabajadoresDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        TurnosModel turnosModel = iTurno.findById(id_turno).orElseThrow(() -> new ResourceNotFoundException("Turno","ID",id_turno));
        turnosTrabajadorModel.setId_trabajador(trabajadorModel.getId());
        turnosTrabajadorModel.setId_turno(turnosModel.getId_turno());

        TurnosTrabajadorModel nuevaRelacion = iTurnosTrabajadores.save(turnosTrabajadorModel);
        return mapearDTO(nuevaRelacion);
    }

    private TurnosTrabajadoresDTO mapearDTO(TurnosTrabajadorModel turnosTrabajadorModel){
        TurnosTrabajadoresDTO turnosTrabajadoresDTO = modelMapper.map(turnosTrabajadorModel,TurnosTrabajadoresDTO.class);
        return turnosTrabajadoresDTO;
    }
    private TurnosTrabajadorModel mapearEntidad(TurnosTrabajadoresDTO turnosTrabajadoresDTO) {
        TurnosTrabajadorModel turnosTrabajadorModel = modelMapper.map(turnosTrabajadoresDTO, TurnosTrabajadorModel.class);
        return turnosTrabajadorModel;
    }
}
