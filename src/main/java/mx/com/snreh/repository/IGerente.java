package mx.com.snreh.repository;

import mx.com.snreh.model.GerenteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGerente extends JpaRepository<GerenteModel, Long> {
}
