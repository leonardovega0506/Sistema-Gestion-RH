package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "gerente",uniqueConstraints = {@UniqueConstraint(columnNames = {"numero_gerente"})})
public class GerenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gerente")
    private Long id;

    @Column(name = "numero_gerente",nullable = false)
    private long numero_gerente;

    @Column(name = "nombre_gerente",nullable = false)
    private String nombre_gerente;

    @Column(name = "apellidoP_gerente",nullable = false)
    private String apellidoP_gerente;

    @Column(name = "apellidoM_gerente",nullable = false)
    private String apllidoM_gerente;

    @Column(name = "sueldo_gerente",nullable = false)
    private double sueldo;

    @Column(name ="estatus_gerente",nullable = false)
    private String estaus;

    @Column(name = "celular_gerente",nullable = false)
    private String celular;

    @Column(name = "correo_electronico_gerente",nullable = false)
    private String correo_electronico;

    @Column(name = "puesto_gerente")
    private String puesto;
}
