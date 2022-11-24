package mx.com.snreh.repository;

import mx.com.snreh.model.TurnosTrabajadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnosTrabajadores extends JpaRepository<TurnosTrabajadorModel,Long> {
}
