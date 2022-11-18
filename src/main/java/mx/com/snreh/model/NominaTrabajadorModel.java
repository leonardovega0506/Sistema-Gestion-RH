package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nomina")
@Data
public class NominaTrabajadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_nomina;

    @Column(name = "fecha_nomina_trabajador", nullable = false)
    private Date fechaNomina;

    @Column(name = "nomina_trabajador",nullable = false)
    private double nomina_trabajador;

    @Column(name = "isr_trabajador",nullable = false)
    private double isr;

    @Column(name = "descuento_retardo",nullable = false)
    private double descuento_retardo;

    @Column(name = "iva_trabajador",nullable = false)
    private double iva;

    @Column(name = "cantidad_retardos",nullable = false)
    private int retardo_trabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador",nullable = false)
    private TrabajadorModel trabajadorModel;
}
