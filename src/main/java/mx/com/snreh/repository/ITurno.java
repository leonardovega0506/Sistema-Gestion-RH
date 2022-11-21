package mx.com.snreh.repository;

import mx.com.snreh.model.TurnosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITurno extends JpaRepository<TurnosModel,Long> {
    public Optional<TurnosModel> findByTurno(String turno);
}
