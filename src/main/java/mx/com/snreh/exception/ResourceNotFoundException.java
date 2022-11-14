package mx.com.snreh.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID=1L;
    private String nombreRecurso;
    private String nombreCampo;
    private long valorCampo;

    public ResourceNotFoundException( String nombreRecurso, String nombreCampo, long valorCampo) {
        super(String.format("%s No encontrada con: %s: '%s'",nombreRecurso,nombreCampo,valorCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }
}
