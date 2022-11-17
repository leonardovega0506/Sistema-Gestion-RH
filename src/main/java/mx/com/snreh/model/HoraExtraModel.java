package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "hora_extra")
public class HoraExtraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_horaExtra;

    @Column(name = "fecha_horaExtra",nullable = false)
    private Date fecha_HoraExtra;

    @Column(name = "cantidad_horasExtra",nullable = false)
    private int cantidad_horas;

    @Column(name = "costo_hora",nullable = false)
    private double costo_hora;

    @Column(name = "aumento_total")
    private double aumento_total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador", nullable = false)
    private TrabajadorModel trabajadorModel;


}
