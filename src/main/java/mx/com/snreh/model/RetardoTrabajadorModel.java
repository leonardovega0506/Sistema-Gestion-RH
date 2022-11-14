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

    private Date fecha_retardo;

    private String tiempo_Retardo;

    private double descuento_retardo;

}
