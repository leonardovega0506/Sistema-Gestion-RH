package mx.com.snreh.repository;

import mx.com.snreh.model.QuejasAclaracionesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IQueja extends JpaRepository<QuejasAclaracionesModel, Long> {
    public List<QuejasAclaracionesModel> findByTrabajadorModelId(long id_trabajador);
}
