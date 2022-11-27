package mx.com.snreh.controller;

import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.model.TurnosModel;
import mx.com.snreh.model.TurnosTrabajadorModel;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.repository.ITurno;
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

    @Autowired
    private ITurno iTurno;

    private ITrabajador iTrabajador;

    @PostMapping
    public ResponseEntity<TurnosTrabajadorModel> asignarTurnos(@PathVariable(name = "id_trabajador") long id_trabajador, @PathVariable(name = "id_turno") long id_turno) {
        TurnosTrabajadorModel turnosTrabajadorModel = new TurnosTrabajadorModel();
        TurnosModel id_turnoN = iTurno.findById(id_turno).orElseThrow( () -> new ResourceNotFoundException("Turno","ID",id_turno));
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() ->new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        turnosTrabajadorModel.setId_turno(id_turnoN.getId_turno());
        turnosTrabajadorModel.setId_trabajador(trabajadorModel.getId());
        return new ResponseEntity<>(iTurnosTrabajadores.save(turnosTrabajadorModel), HttpStatus.CREATED);
    }
}

