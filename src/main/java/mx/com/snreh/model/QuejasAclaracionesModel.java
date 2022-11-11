package mx.com.snreh.model;

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
    private Date fecha_Queja;

    @Column(name = "tipo_queja",nullable = false)
    private String tipo_Queja;

    @Column(name = "cuerpo_queja",nullable = false)
    private String cuerpo_queja;

    @Column(name = "estatus_queja",nullable = false)
    private String estatus_queja;
}
