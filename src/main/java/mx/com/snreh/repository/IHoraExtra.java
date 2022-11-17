package mx.com.snreh.repository;

import mx.com.snreh.model.HoraExtraModel;
import mx.com.snreh.model.TareaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHoraExtra extends JpaRepository<HoraExtraModel,Long> {
    public List<HoraExtraModel> findByTrabajadorModelId(long id_trabajador);
}
