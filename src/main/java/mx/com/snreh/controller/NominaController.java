package mx.com.snreh.controller;

import mx.com.snreh.dto.NominaDTO;
import mx.com.snreh.service.implementation.interfaces.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/snrhe/trabajadores/{id_trabajador}/nominas/")
public class NominaController {

    @Autowired
    private NominaService sNomina;

    @GetMapping
    private List<NominaDTO> listarNominas(@PathVariable(value = "id_trabajador") long id_trabajador){
        return sNomina.findAllNominas(id_trabajador);
    }

    @GetMapping({"id_nomina"})
    public ResponseEntity<NominaDTO> obtenerNomina(@PathVariable(value = "id_trabajador") long id_trabajador, @PathVariable(value = "id_nomina") long id_nomina){
        NominaDTO nominaDTO = sNomina.finNominaByID(id_trabajador,id_nomina);
        return new ResponseEntity<>(nominaDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<NominaDTO> crearNomina(@PathVariable(value = "id_trabajador") long id_trabajador, @Valid @RequestBody NominaDTO nominaDTO){
        return new ResponseEntity<>(sNomina.createNomina(id_trabajador,nominaDTO),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<NominaDTO> actualizarNomina(@PathVariable(value = "id_trabajador") long id_trabajador,@PathVariable(value = "id_nomina") long id_nomina,@Valid @RequestBody NominaDTO nominaDTO){
        NominaDTO noominaActualiza = sNomina.updateNomina(id_trabajador,id_nomina,nominaDTO);
        return new ResponseEntity<>(noominaActualiza, HttpStatus.NO_CONTENT);
    }
}
