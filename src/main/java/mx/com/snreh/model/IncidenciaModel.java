package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "incidencia")
public class IncidenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_incidencia;

    @Column(name = "tipo_incidencia")
    private String tipo_incidencia;

    @Column(name = "nombre_incidencia")
    private String nombre_incidencia;

    @Column(name = "evidencia_incidencia")
    private String evidencia_incidencia;

    @Column(name = "fecha_incidencia")
    private Date fecha_incidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador",nullable = false)
    private TrabajadorModel trabajadorModel;
}
