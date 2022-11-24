package mx.com.snreh.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "turnos_trabajadores")
public class Turnos_Trabajador {

    @Id
    private Long id_trabajador;

    private Long id_turno;
}
