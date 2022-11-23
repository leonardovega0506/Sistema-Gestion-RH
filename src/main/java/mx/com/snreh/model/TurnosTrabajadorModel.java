package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "turnos_trabajadores")
public class TurnosTrabajadorModel {

    @Column(name = "id_trabajador")
    private long id_trabajador;

    @Column(name = "id_turno")
    private long id_turno;
}
