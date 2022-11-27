package mx.com.snreh.controller;

import io.swagger.models.Response;
import mx.com.snreh.dto.EventoDTO;
import mx.com.snreh.service.interfaces.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class EventoController {

    @Autowired
    private EventoService sEvento;

    @GetMapping("/snrhe/gerentes/{id_gerente}/eventos/")
    public List<EventoDTO> listarEventosGerenteId(@PathVariable(value = "id_gerente") long id_gerente){
        return sEvento.findAllEventosGerenteID(id_gerente);
    }
    @GetMapping("/snrhe/gerentes/{id_gerente}/eventos/{id_evento}")
    public ResponseEntity<EventoDTO> obtenerEventoById(@PathVariable(value = "id_gerente") long id_gerente, @PathVariable(value = "id_evento") long id_evento){
        EventoDTO eventoDTO = sEvento.findEvento(id_gerente,id_evento);
        return new ResponseEntity<>(eventoDTO, HttpStatus.OK);
    }

    @GetMapping("/snrhe/eventos/")
    public List<EventoDTO> listarEventos(){
        return sEvento.findAllEventos();
    }

    @PreAuthorize("hasRole('GERENTE')")
    @PostMapping("/snrhe/gerentes/{id_gerente}/eventos/")
    public ResponseEntity<EventoDTO> crearEvento(@PathVariable(value = "id_gerente")long id_gerente,@Valid @RequestBody EventoDTO eventoDTO){
        return new ResponseEntity<>(sEvento.createEvento(id_gerente,eventoDTO),HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('GERENTE')")
    @PutMapping("/snrhe/gerentes/{id_gerente}/eventos/{id_evento}")
    public ResponseEntity<EventoDTO> actualizarEvento(@PathVariable(value = "id_gerente") long id_gerente, @PathVariable("id_evento") long id_evento, @Valid @RequestBody EventoDTO eventoDTO){
        EventoDTO eventoActualizado = sEvento.updateEvento(id_gerente, id_evento, eventoDTO);
        return new ResponseEntity<>(eventoActualizado,HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('GERENTE')")
    @DeleteMapping("/snrhe/gerentes/{id_gerente}/eventos/{id_evento}")
    public ResponseEntity<String> borrarEvento(@PathVariable(value = "id_gerente") long id_gerente,@PathVariable("id_evento") long id_evento){
        sEvento.deleteEvento(id_gerente, id_evento);
        return new ResponseEntity<>("Comentario eliminado con exito",HttpStatus.NO_CONTENT);
    }

}