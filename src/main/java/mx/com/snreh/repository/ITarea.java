package mx.com.snreh.repository;

import mx.com.snreh.model.TareaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITarea extends JpaRepository<TareaModel,Long> {
    public List<TareaModel> findBynumero_trabajador(long numero_trabajador);
}
