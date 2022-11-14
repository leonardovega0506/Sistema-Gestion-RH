package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "tarea")
@Entity
public class TareaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Tarea;

    @Column(name = "nombre_tarea")
    private String nombre;

    @Column(name = "fecha_tarea")
    private Date fecha;

    @Column(name = "estatus_tarea")
    private String estatus;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_trabajador",nullable = false)
    private TrabajadorModel trabajadorModel;*/
}
