package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.EventoDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.EventosModel;
import mx.com.snreh.model.GerenteModel;
import mx.com.snreh.repository.IEvento;
import mx.com.snreh.repository.IGerente;
import mx.com.snreh.service.implementation.interfaces.EventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired(required = false)
    private ModelMapper modelMapper;

    @Autowired
    private IEvento iEvento;

    @Autowired
    private IGerente iGerente;


    @Override
    public EventoDTO createEvento(long id_gerente, EventoDTO eventoDTO) {
        EventosModel eventosModel = mapearEntidad(eventoDTO);
        GerenteModel gerenteModel = iGerente.findById(id_gerente).orElseThrow(() -> new ResourceNotFoundException("Gerente","ID",id_gerente));

        eventosModel.setGerenteModel(gerenteModel);
        EventosModel eventoNuevo = iEvento.save(eventosModel);

        return mapearDTO(eventoNuevo);
    }

    @Override
    public List<EventoDTO> findAllEventos(long id_gerente) {
        List<EventosModel> eventos = iEvento.findByGerenteModelId(id_gerente);
        return  eventos.stream().map(evento -> mapearDTO(evento)).collect(Collectors.toList());
    }

    @Override
    public EventoDTO findEvento(long id_gerente, long id_evento) {
        GerenteModel gerenteModel = iGerente.findById(id_gerente).orElseThrow(() -> new ResourceNotFoundException("Gerente","ID",id_gerente));

        EventosModel eventosModel = iEvento.findById(id_evento).orElseThrow(() -> new ResourceNotFoundException("Evento","Id",id_evento));

        if(eventosModel.getGerenteModel().getId() != gerenteModel.getId()){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"El evento no pertenece a este gerente");
        }
        return mapearDTO(eventosModel);
    }

    @Override
    public EventoDTO updateEvento(long id_gerente, long id_evento,EventoDTO eventoDTO) {
        GerenteModel gerenteModel = iGerente.findById(id_gerente).orElseThrow(() -> new ResourceNotFoundException("Gerente","ID",id_gerente));

        EventosModel eventosModel = iEvento.findById(id_evento).orElseThrow(() -> new ResourceNotFoundException("Evento","Id",id_evento));

        if(eventosModel.getGerenteModel().getId() != gerenteModel.getId()){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"El evento no pertenece a este gerente");
        }
        eventosModel.setFecha_evento(eventoDTO.getFecha_evento());
        eventosModel.setCuerpo_evento(eventoDTO.getCuerpo_evento());

        EventosModel eventoActualizado = iEvento.save(eventosModel);
        return mapearDTO(eventoActualizado);
    }

    @Override
    public void deleteEvento(long id_gerente, long id_evento) {
        GerenteModel gerenteModel = iGerente.findById(id_gerente).orElseThrow(() -> new ResourceNotFoundException("Gerente","ID",id_gerente));

        EventosModel eventosModel = iEvento.findById(id_evento).orElseThrow(() -> new ResourceNotFoundException("Evento","Id",id_evento));

        if(eventosModel.getGerenteModel().getId() != gerenteModel.getId()){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"El evento no pertenece a este gerente");
        }
        iEvento.delete(eventosModel);
    }
    private EventoDTO mapearDTO(EventosModel eventosModel){
        EventoDTO eventoDTO = modelMapper.map(eventosModel, EventoDTO.class);
        return eventoDTO;
    }
    private EventosModel mapearEntidad(EventoDTO eventoDTO){
        EventosModel eventosModel = modelMapper.map(eventoDTO,EventosModel.class);
        return eventosModel;
    }
}
