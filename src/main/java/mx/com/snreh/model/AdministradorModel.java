package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "administrador",uniqueConstraints = {@UniqueConstraint(columnNames = "usuario_Administrador")})
public class AdministradorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_administrador;

    @Column(name = "usuario_Administrador")
    private String usuario;

    @Column(name = "nombre_Administrador")
    private String nombre;

}
