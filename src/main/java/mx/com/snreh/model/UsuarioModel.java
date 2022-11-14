package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "usuario",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),@UniqueConstraint(columnNames = {"email"})})
@Entity
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "username",nullable = false,length = 10)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;
}
