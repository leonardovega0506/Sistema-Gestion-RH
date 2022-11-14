package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Rol")
public class RolModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_rol;

    @Column(name = "nombre_rol",length = 60,nullable = false)
    private String nombre_rol;

}
