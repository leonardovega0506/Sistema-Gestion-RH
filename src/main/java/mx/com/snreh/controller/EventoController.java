package mx.com.snreh.controller;

import mx.com.snreh.dto.EventoDTO;
import mx.com.snreh.service.interfaces.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/snrhe/gerentes/{id_gerente}/eventos/")
public class EventoController {

    @Autowired
    private EventoService sEvento;

    @GetMapping
    public List<EventoDTO> listarEventos(@PathVariable(value = "id_gerente") long id_gerente){
        return sEvento.findAllEventos(id_gerente);
    }
    @GetMapping("{id_evento}")
    public ResponseEntity<EventoDTO> obtenerEventoById(@PathVariable(value = "id_gerente") long id_gerente, @PathVariable(value = "id_evento") long id_evento){
        EventoDTO eventoDTO = sEvento.findEvento(id_gerente,id_evento);
        return new ResponseEntity<>(eventoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventoDTO> crearEvento(@PathVariable(value = "id_gerente")long id_gerente,@Valid @RequestBody EventoDTO eventoDTO){
        return new ResponseEntity<>(sEvento.createEvento(id_gerente,eventoDTO),HttpStatus.CREATED);
    }
    @PutMapping("{id_evento}")
    public ResponseEntity<EventoDTO> actualizarEvento(@PathVariable(value = "id_gerente") long id_gerente, @PathVariable("id_evento") long id_evento, @Valid @RequestBody EventoDTO eventoDTO){
        EventoDTO eventoActualizado = sEvento.updateEvento(id_gerente, id_evento, eventoDTO);
        return new ResponseEntity<>(eventoActualizado,HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("{id_evento}")
    public ResponseEntity<String> borrarEvento(@PathVariable(value = "id_gerente") long id_gerente,@PathVariable("id_evento") long id_evento){
        sEvento.deleteEvento(id_gerente, id_evento);
        return new ResponseEntity<>("Comentario eliminado con exito",HttpStatus.NO_CONTENT);
    }
}