package mx.com.snreh.dto;

import lombok.Data;

@Data
public class TrabajadorModel {
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
