package mx.com.snreh.repository;

import mx.com.snreh.dto.TurnosTrabajadoresDTO;
import mx.com.snreh.model.Turnos_Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnosTrabajadores extends JpaRepository<Turnos_Trabajador,Long> {
}
