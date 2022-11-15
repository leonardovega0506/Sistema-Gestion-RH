package mx.com.snreh.repository;

import mx.com.snreh.model.TareaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITarea extends JpaRepository<TareaModel, Long> {
<<<<<<< HEAD
    public List<TareaModel> findByTrabajadorModelId(long id_trabajador);
=======
    public List<TareaModel> findByTrabajadorId(long id_trabajador);
>>>>>>> c1f0822 (Agregar el service de TareaService y TareaServiceImpl)
}
