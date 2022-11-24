package mx.com.snreh.controller;

import mx.com.snreh.dto.RetardoTrabajadorDTO;
import mx.com.snreh.service.interfaces.RetardoTrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/snrhe/trabajadores/{id_trabajador}/retardos/")
public class RetardoController {

    @Autowired
    private RetardoTrabajadorService sRetardo;

    @GetMapping
    public List<RetardoTrabajadorDTO> listarRetardos(@PathVariable(value = "id_trabajador") long id_trabajador){
        return sRetardo.obtenerRetardos(id_trabajador);
    }
    @GetMapping("{id_retardo}")
    public ResponseEntity<RetardoTrabajadorDTO> obtenerRetardoByID(@PathVariable(value = "id_trabajador")long id_trabajador, @PathVariable(value = "id") long id_retardo){
        RetardoTrabajadorDTO retardoTrabajadorDTO = sRetardo.obtenberRetardoById(id_trabajador,id_retardo);
        return new ResponseEntity<>(retardoTrabajadorDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<RetardoTrabajadorDTO> generarRetardo(@PathVariable(value = "id_trabajador") long id_trabajador, @Valid @RequestBody RetardoTrabajadorDTO retardoTrabajadorDTO){
        return new ResponseEntity<>(sRetardo.crearRetardo(id_trabajador,retardoTrabajadorDTO),HttpStatus.CREATED);
    }
}
