package mx.com.snreh.repository;

import mx.com.snreh.model.RetardoTrabajadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRetardo extends JpaRepository<RetardoTrabajadorModel,Long> {
    public List<RetardoTrabajadorModel> findByTrabajadorModelId(long id_trabajador);
}
