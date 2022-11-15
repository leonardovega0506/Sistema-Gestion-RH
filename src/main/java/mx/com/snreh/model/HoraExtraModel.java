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
}
