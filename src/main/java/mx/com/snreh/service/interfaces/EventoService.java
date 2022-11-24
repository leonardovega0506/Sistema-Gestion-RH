package mx.com.snreh.service.interfaces;

import mx.com.snreh.dto.EventoDTO;

import java.util.List;

public interface EventoService {
    public EventoDTO createEvento(long id_gerente, EventoDTO eventoDTO);

    public List<EventoDTO> findAllEventos(long id_gerente);

    public EventoDTO findEvento(long id_gerente, long id_evento);

    public EventoDTO updateEvento(long id_gerente,long id_evento,EventoDTO eventoDTO);

    public void deleteEvento(long id_gerente, long id_evento);
}
