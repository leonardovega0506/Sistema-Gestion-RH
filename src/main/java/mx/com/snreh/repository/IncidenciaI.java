package mx.com.snreh.repository;

import mx.com.snreh.model.IncidenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenciaI extends JpaRepository<IncidenciaModel,Long> {
    public List<IncidenciaModel> findByTrabajadorModelId(long id_trabajador);
}
