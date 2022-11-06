package mx.com.snreh.iservice;

import mx.com.snreh.model.AdministradorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdministrador extends JpaRepository<AdministradorModel, Integer> {
}
