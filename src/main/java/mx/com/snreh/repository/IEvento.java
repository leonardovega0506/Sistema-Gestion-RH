package mx.com.snreh.repository;

import mx.com.snreh.model.EventosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEvento extends JpaRepository<EventosModel, Long> {

    public List<EventosModel> findByGerenteModelId(long id_gerente);
}
