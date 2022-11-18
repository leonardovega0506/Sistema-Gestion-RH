package mx.com.snreh.repository;

import mx.com.snreh.model.NominaTrabajadorModel;
import mx.com.snreh.model.TareaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INomina extends JpaRepository<NominaTrabajadorModel,Long> {
    public List<NominaTrabajadorModel> findByTrabajadorModelId(long id_trabajador);
}
