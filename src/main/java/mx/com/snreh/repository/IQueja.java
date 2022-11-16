package mx.com.snreh.repository;

import mx.com.snreh.dto.QuejasDTO;
import mx.com.snreh.model.QuejasAclaracionesModel;
import mx.com.snreh.model.TareaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IQueja extends JpaRepository<QuejasAclaracionesModel, Long> {
    public List<TareaModel> findByTrabajadorModelId(long id_trabajador);
}
