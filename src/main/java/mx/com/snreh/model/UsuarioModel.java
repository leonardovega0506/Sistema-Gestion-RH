package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "usuario",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),@UniqueConstraint(columnNames = {"email"})})
@Entity
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id_usuario;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "username",nullable = false,length = 10)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_roles",joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName ="id_usuario"),inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "rol_id"))
    private Set<RolModel> roles = new HashSet<>();
}
