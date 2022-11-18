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
    @Column(name = "id_trabajador")
    private Long id;

    @Column(name = "numero_trabajador",nullable = false)
    private Long numero_trabajador;

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

    @OneToMany(mappedBy = "trabajadorModel",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<RetardoTrabajadorModel> retardos = new HashSet<>();

    @OneToMany(mappedBy = "trabajadorModel",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<QuejasAclaracionesModel> listaQuejas = new HashSet<>();

    @OneToMany(mappedBy = "trabajadorModel",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<HoraExtraModel> listaHorasExtra = new HashSet<>();

    @OneToMany(mappedBy = "trabajadorModel",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<IncidenciaModel> listaIncidencias = new HashSet<>();

    @OneToMany(mappedBy = "trabajadorModel",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<NominaTrabajadorModel> listaNominas = new HashSet<>();

    @OneToOne(mappedBy = "trabajadorModel",cascade = CascadeType.ALL,orphanRemoval = true)
    private RenunciaTrabajadorModel renuncia;

    @OneToMany(mappedBy = "trabajadorModel",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<VacacionModel> listaVacaciones = new HashSet<>();
}
