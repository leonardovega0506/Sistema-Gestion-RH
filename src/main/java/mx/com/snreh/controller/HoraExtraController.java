package mx.com.snreh.controller;

import mx.com.snreh.dto.HoraExtraDTO;

import mx.com.snreh.service.interfaces.HoraExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/snrhe/trabajadores/{id_trabajador}/horaExtra/")
public class HoraExtraController {

    @Autowired
    private HoraExtraService sHora;

    @GetMapping
    public List<HoraExtraDTO> listarHorasExtra(@PathVariable(value = "id_trabajador") Long id_trabajador){
        return sHora.finAllHoraExtra(id_trabajador);
    }

    @GetMapping("{id_horaExtra}")
    public ResponseEntity<HoraExtraDTO> obtenerHoraExtra(@PathVariable(value = "id_trabajador") Long id_trabajador,@PathVariable(value = "id_horaExtra") long id_horaExtra){
        HoraExtraDTO horaExtraDTO = sHora.findHoraExtra(id_trabajador,id_horaExtra);

        return new ResponseEntity<>(horaExtraDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<HoraExtraDTO> registrarHoraExtra(@PathVariable(value = "id_trabajdor") Long id_trabajador, @Valid @RequestBody HoraExtraDTO horaExtraDTO){
        return new ResponseEntity<>(sHora.createHoraExtra(id_trabajador,horaExtraDTO),HttpStatus.CREATED);
    }

}