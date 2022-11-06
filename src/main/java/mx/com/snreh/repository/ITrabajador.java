package mx.com.snreh.iservice;

import mx.com.snreh.model.TrabajadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrabajador extends JpaRepository<TrabajadorModel, Integer> {
}
