package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.TurnosDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.model.TurnosModel;
import mx.com.snreh.repository.ITurno;
import mx.com.snreh.respuesta.TurnosRespuesta;
import mx.com.snreh.service.interfaces.TurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired(required = false)
    private ModelMapper modelMapper;

    @Autowired
    private ITurno iTurno;

    @Override
    public TurnosDTO createTurno(TurnosDTO turnosDTO) {
        TurnosModel turnosModel = mapearEntidad(turnosDTO);
        TurnosModel nuevoTurno = iTurno.save(turnosModel);
        return mapearDTO(nuevoTurno);
    }

    @Override
    public TurnosRespuesta findAllTurnos(int numeroPagina, int sizePagina) {
        Pageable pageable = PageRequest.of(numeroPagina,sizePagina);
        Page<TurnosModel> turnos = iTurno.findAll(pageable);
        List<TurnosModel> listaTurnos = turnos.getContent();
        List<TurnosDTO> contenido = listaTurnos.stream().map(turno -> mapearDTO(turno)).collect(Collectors.toList());
        TurnosRespuesta respuesta = new TurnosRespuesta();
        respuesta.setContenido(contenido);
        respuesta.setNumeroPagina(turnos.getNumber());
        respuesta.setSizePagina(turnos.getSize());
        respuesta.setTotalElementos(turnos.getTotalElements());
        respuesta.setTotalPaginas(turnos.getTotalPages());
        respuesta.setUltima(turnos.isLast());
        return respuesta;
    }

    @Override
    public TurnosDTO findTurnoBy(long id_turno) {
        TurnosModel turnosModel = iTurno.findById(id_turno).orElseThrow(() -> new ResourceNotFoundException("Turno","id",id_turno));
        return mapearDTO(turnosModel);
    }

    @Override
    public TurnosDTO updateTurno(long id_turno, TurnosDTO turnosDTO) {
        TurnosModel turnosModel = iTurno.findById(id_turno).orElseThrow(() -> new ResourceNotFoundException("Turno","ID",id_turno));

        turnosModel.setTurno(turnosDTO.getTurno());
        turnosModel.setCantidad_horas(turnosDTO.getCantidad_horas());

        TurnosModel turnoActualizado = iTurno.save(turnosModel);
        return mapearDTO(turnoActualizado);
    }

    @Override
    public void deleteTurno(long id_turno) {
        TurnosModel turnosModel = iTurno.findById(id_turno).orElseThrow(() -> new ResourceNotFoundException("Turno","ID",id_turno));
        iTurno.delete(turnosModel);
    }

    private TurnosDTO mapearDTO(TurnosModel turnosModel){
        TurnosDTO turnosDTO = modelMapper.map(turnosModel,TurnosDTO.class);
        return turnosDTO;
    }
    private TurnosModel mapearEntidad(TurnosDTO turnosDTO){
        TurnosModel turnosModel = modelMapper.map(turnosDTO,TurnosModel.class);
        return turnosModel;
    }
}
