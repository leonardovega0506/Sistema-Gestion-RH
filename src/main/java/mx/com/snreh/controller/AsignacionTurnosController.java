package mx.com.snreh.controller;

import mx.com.snreh.dto.TurnosTrabajadoresDTO;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.model.Turnos_Trabajador;
import mx.com.snreh.repository.ITurnosTrabajadores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/snrhe/asignacion-turnos")
public class AsignacionTurnosController {

    @Autowired
    private ITurnosTrabajadores iTurnosTrabajadores;

    @PostMapping
    public ResponseEntity<Turnos_Trabajador> asignarTurnos(@RequestBody Turnos_Trabajador turnosTrabajadores) {
        return new ResponseEntity<>(iTurnosTrabajadores.save(turnosTrabajadores), HttpStatus.CREATED);
    }
}

