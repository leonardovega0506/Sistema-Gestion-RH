package mx.com.snreh.repository;

import mx.com.snreh.model.AdministradorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdministrador extends JpaRepository<AdministradorModel,Long> {
}
