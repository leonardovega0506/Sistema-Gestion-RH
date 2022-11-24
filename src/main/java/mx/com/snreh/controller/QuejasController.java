package mx.com.snreh.controller;

import mx.com.snreh.dto.QuejasDTO;
import mx.com.snreh.service.interfaces.QuejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/snrhe/trabajadores/{id_trabajador}/quejas/")
public class QuejasController {
    @Autowired
    private QuejaService sQueja;

    @GetMapping
    public List<QuejasDTO> listarQuejas(@PathVariable(value = "id_trabajador") long id_trabajador){
        return sQueja.findAllQuejas(id_trabajador);
    }
    @GetMapping("{id_queja}")
    public ResponseEntity<QuejasDTO> obtenerQuejaByID(@PathVariable(value = "id_trabajador") long id_trabajador, @PathVariable(value = "id_queja") long id_queja){
        QuejasDTO quejasDTO = sQueja.findQueja(id_trabajador,id_queja);
        return new ResponseEntity<>(quejasDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<QuejasDTO> crearQueja(@PathVariable(value = "id_trabajador") long id_trabajador, @Valid @RequestBody QuejasDTO quejasDTO){
        return new ResponseEntity<>(sQueja.createQueja(id_trabajador,quejasDTO),HttpStatus.CREATED);
    }
    @PutMapping("{id_queja}")
    public ResponseEntity<QuejasDTO> actualizarQueja(@PathVariable(value = "id_trabajador") long id_trabajador,@PathVariable(value = "id_queja") long id_queja,@Valid @RequestBody QuejasDTO quejasDTO){
        QuejasDTO quejaActualizada = sQueja.updateQueja(id_trabajador,id_queja,quejasDTO);
        return new ResponseEntity<>(quejaActualizada,HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("{id_queja}")
    public ResponseEntity<String> eliminarQueja(@PathVariable(value = "id_trabajador") long id_trabajador,@PathVariable(value = "id_queja") long id_queja){
        sQueja.eliminarQueja(id_trabajador,id_queja);
        return new ResponseEntity<>("Queja eliminada Correctamente",HttpStatus.NO_CONTENT);
    }
}