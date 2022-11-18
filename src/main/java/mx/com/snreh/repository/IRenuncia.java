package mx.com.snreh.repository;

import mx.com.snreh.model.RenunciaTrabajadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRenuncia extends JpaRepository<RenunciaTrabajadorModel,Long> {
}
