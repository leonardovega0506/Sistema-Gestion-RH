package mx.com.snreh.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Table(name = "turno_trabajador")
@Entity
public class TurnosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private int id_turno;

    @Column(name = "turno",nullable = false)
    private String turno;

    @Column(name = "cantidad_horas",nullable = false)
    private int cantidad_horas;



}
