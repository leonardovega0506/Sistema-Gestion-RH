package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Table(name = "turno_trabajador")
@Entity
public class TurnosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_turno;

    @Column(name = "turno",nullable = false)
    private String turno;

    @Column(name = "cantidad_horas",nullable = false)
    private int cantidad_horas;


}
