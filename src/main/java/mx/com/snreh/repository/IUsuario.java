package mx.com.snreh.repository;

import mx.com.snreh.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuario extends JpaRepository<UsuarioModel, Long> {
    public Optional<UsuarioModel> findByEmail(String email);

    public Optional<UsuarioModel> findByUsernameOrEmail(String username,String email);

    public Optional<UsuarioModel> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
