package mx.com.snreh.repository;

import mx.com.snreh.model.RolModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRol extends JpaRepository<RolModel,Long> {
    public Optional<RolModel> findByNombre(String nombre);
}
