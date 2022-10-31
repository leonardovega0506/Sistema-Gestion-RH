package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class GerenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_gerente;

    private int numero_gerente;
    private String nombre_gerente;
    private String apellidoP_gerente;
    private String apllidoM_gerente;
    private double sueldo;
    private String estaus;
    private String celular;
    private String correo_electronico;
    private String puesto;
}
