package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "retardo")
public class RetardoTrabajadorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_retardo;

    @Column(name = "fecha_retardo",nullable = false)
    private Date fecha_retardo;

    @Column(name = "tiempo_retardo",nullable = false)
    private String tiempo_Retardo;

    @Column(name = "descuento_retardo",nullable = false)
    private double descuento_retardo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trabajador_id")
    private TrabajadorModel trabajadorModel;

}
