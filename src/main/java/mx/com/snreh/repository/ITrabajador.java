package mx.com.snreh.repository;

import mx.com.snreh.model.TareaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrabajador extends JpaRepository<TareaModel, Long> {
}
