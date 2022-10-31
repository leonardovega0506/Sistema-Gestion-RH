package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class TrabajadorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_trabajador;

    private int numero_trabajador;
    private String nombre_trabajador;
    private String apellidoP_trabajador;
    private String apellidoM_trabajador;
    private double sueldo;
    private String estatus;
    private String celular;
    private String correo_electronico;
    private String puesto;
}
