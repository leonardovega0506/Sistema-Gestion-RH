package mx.com.snreh.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrabajadorController {

    @Autowired
    private TrabajadorService sTrabajador;

    @GetMapping("/trabajador")
    public List<TrabajadorModel> listarTrabajadores(){
        return sTrabajador.listarTrabajadores();
    }

    @Operation(summary = "Guardar Trabajador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Persona Guardada",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = TrabajadorModel.class))}),
            @ApiResponse(responseCode = "400", description = "Objeto invalido",
            content = @Content),
            @ApiResponse(responseCode = "404", description = "No encontrado",
            content = @Content)})
    @PostMapping("/trabajador")
    public void guardarTrabajador(@RequestBody TrabajadorModel trabajadorModel){
        sTrabajador.guardarTrabajador(trabajadorModel);
    }
    @GetMapping("/trabajador/{numeroTrabajador}")
    public ResponseEntity<TrabajadorModel> obtenerTrabajadorByID(@PathVariable Integer numeroTrabajador){
        try {
            TrabajadorModel trabajadorModel = sTrabajador.obtenerTrabajadorByNumero(numeroTrabajador);
            return new ResponseEntity<TrabajadorModel>(trabajadorModel,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<TrabajadorModel>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/trabajador{numeroTrabajador}")
    public ResponseEntity<TrabajadorModel> actualizarTrabajador(@RequestBody TrabajadorModel trabajadorModel, @PathVariable Integer numeroTrabajador){
        try{
            TrabajadorModel trabajadorModel1 = sTrabajador.obtenerTrabajadorByNumero(numeroTrabajador);
            trabajadorModel1.setNombre_trabajador(trabajadorModel.getNombre_trabajador());
            trabajadorModel1.setApellidoP_trabajador(trabajadorModel.getApellidoP_trabajador());
            trabajadorModel1.setApellidoM_trabajador(trabajadorModel.getApellidoM_trabajador());
            trabajadorModel1.setCelular(trabajadorModel.getCelular());
            trabajadorModel1.setCorreo_electronico(trabajadorModel.getCorreo_electronico());
            trabajadorModel1.setEstatus(trabajadorModel.getEstatus());
            trabajadorModel1.setSueldo(trabajadorModel.getSueldo());
            trabajadorModel1.setPuesto(trabajadorModel.getPuesto());
            sTrabajador.guardarTrabajador(trabajadorModel1);
            return new ResponseEntity<TrabajadorModel>(HttpStatus.ACCEPTED);
        }catch (Exception ex){
            return new ResponseEntity<TrabajadorModel>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/trabajador/{numeroTrabajador}")
    public void eliminarTrabajador(@PathVariable Integer numeroTrabajador){
        sTrabajador.eliminarTrabajador(numeroTrabajador);
    }
}
