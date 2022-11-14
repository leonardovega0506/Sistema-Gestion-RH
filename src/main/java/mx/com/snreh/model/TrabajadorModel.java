package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "trabajador",uniqueConstraints = {@UniqueConstraint(columnNames = "numero_trabajador")})
public class TrabajadorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_trabajador;

    @Column(name = "numero_trabajador",nullable = false)
    private long numero_trabajador;

    @Column(name="nombre_trabajador",nullable = false)
    private String nombre_trabajador;

    @Column(name = "apellidoP_trabajor",nullable = false)
    private String apellidoP_trabajador;

    @Column(name = "apellidoM_trabajador",nullable = false)
    private String apellidoM_trabajador;

    @Column(name = "sueldo_trabajador",nullable = false)
    private double sueldo;

    @Column(name = "estatus_trabajador",nullable = false)
    private String estatus;

    @Column(name = "celular_trabajador",nullable = false)
    private String celular;

    @Column(name = "correo_electronico_trabajador",nullable = false)
    private String correo_electronico;

    @Column(name = "puesto_trabajador",nullable = false)
    private String puesto;

    @OneToMany(mappedBy = "trabajadorModel",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<TareaModel> tareas = new HashSet<>();

}
