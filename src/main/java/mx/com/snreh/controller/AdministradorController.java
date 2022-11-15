package mx.com.snreh.controller;

import io.swagger.models.Response;
import mx.com.snreh.dto.AdministradorDTO;
import mx.com.snreh.respuesta.AdministradorRespuesta;
import mx.com.snreh.service.interfaces.AdministradorService;
import mx.com.snreh.util.ConstantesGlobales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("snrhe/admin")
public class AdministradorController {

    @Autowired
    private AdministradorService sAdministrador;

    @GetMapping
    public AdministradorRespuesta listarAdministradores(
            @RequestParam(value = "pageNo",defaultValue = ConstantesGlobales.NUMERO_PAGINA_DEFECTO, required = false) int numeroPagina,
            @RequestParam(value = "pageSize",defaultValue = ConstantesGlobales.MEDIDA_PAGINA_DEFECTO,required = false) int sizePagina){
        return sAdministrador.obtenerAdministradores(numeroPagina,sizePagina);
    }
    @GetMapping("{id}")
    public ResponseEntity<AdministradorDTO> obtenerAdminById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok((sAdministrador.obtenerAdministradorById(id)));
    }
    @PostMapping
    public ResponseEntity<AdministradorDTO> guardarAdmin(@Valid @RequestBody AdministradorDTO administradorDTO){
        return new ResponseEntity<>(sAdministrador.crearAdministrador(administradorDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AdministradorDTO> actualizarAdmin(@Valid @RequestBody AdministradorDTO administradorDTO,@PathVariable(name = "id") long id){
        AdministradorDTO adminRespuesta = sAdministrador.actualizarAdministrador(administradorDTO,id);
        return new ResponseEntity<>(adminRespuesta,HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAdministrador(@PathVariable(name = "id") long id){
        sAdministrador.eliminiarAdministrador(id);
        return new ResponseEntity<>("Administrador eliminado con exito",HttpStatus.NO_CONTENT);
    }

}
