package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "renuncia")
public class RenunciaTrabajadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_renuncia;

    @Column(name = "fecha_renuncia",nullable = false)
    private Date fecha_renuncia;

    @Column(name = "motivo_renuncia",nullable = false)
    private String motivo_renuncia;

    @Column(name = "finiquito",nullable = false)
    private double finiquito;

    private int tiempoTrabajado;

    private String estatusRenuncia;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador")
    private TrabajadorModel trabajadorModel;
}
