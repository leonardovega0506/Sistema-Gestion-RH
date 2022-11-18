package mx.com.snreh.repository;

import mx.com.snreh.model.TareaModel;
import mx.com.snreh.model.VacacionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVacaciones extends JpaRepository<VacacionModel,Long> {
    public List<VacacionModel> findByTrabajadorModelId(long id_trabajador);
}
