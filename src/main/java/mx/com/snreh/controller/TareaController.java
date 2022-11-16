package mx.com.snreh.controller;

import mx.com.snreh.dto.TareaDTO;
import mx.com.snreh.service.implementation.interfaces.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/snrhe/trabajadores/{id_trabajador}/tareas/")
public class TareaController {

    @Autowired
    private TareaService sTarea;

    @GetMapping
    public List<TareaDTO> listarTareas(@PathVariable(value = "id_trabajador") long id_trabajador){
        return sTarea.obtenerTareasTrabajador(id_trabajador);
    }

    @GetMapping("{id_tarea}")
    public ResponseEntity<TareaDTO> obtenerTareaByID(@PathVariable(value = "id_trabajador") long id_trabajador,@PathVariable(value = "id_tarea") long id_tarea){
        TareaDTO tareaDTO = sTarea.obtenerTareaByID(id_trabajador,id_tarea);
        return new ResponseEntity<>(tareaDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<TareaDTO> asignarTarea(@PathVariable(value = "id_trabajador") long id_trabajador, @Valid @RequestBody TareaDTO tareaDTO){
        return new ResponseEntity<>(sTarea.crearTarea(id_trabajador,tareaDTO),HttpStatus.CREATED);
    }

    @PutMapping("{id_tarea}")
    public ResponseEntity<TareaDTO> actualizarTarea(@PathVariable(value = "id_trabajador") long id_trabajador,@PathVariable(value = "id_tarea") long id_tarea,@Valid @RequestBody TareaDTO tareaDTO){
        TareaDTO tareaActualizada = sTarea.actualizarTarea(id_trabajador,id_tarea,tareaDTO);
        return new ResponseEntity<>(tareaActualizada,HttpStatus.NO_CONTENT);
    }
}