package mx.com.snreh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "queja")
public class QuejasAclaracionesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_queja;

    @Column(name = "fecha_queja",nullable = false)
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date fecha_Queja;

    @Column(name = "tipo_queja",nullable = false)
    private String tipo_Queja;

    @Column(name = "cuerpo_queja",nullable = false)
    private String cuerpo_queja;

    @Column(name = "estatus_queja",nullable = false)
    private String estatus_queja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador",nullable = false)
    private TrabajadorModel trabajadorModel;
}

