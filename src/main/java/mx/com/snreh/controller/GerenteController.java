package mx.com.snreh.controller;

import mx.com.snreh.model.GerenteModel;
import mx.com.snreh.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GerenteController {
    @Autowired
    private GerenteService sGerente;

    @GetMapping("/gerente")
    public List<GerenteModel> listarGerentes(){
        return sGerente.listarGerente();
    }

    @PostMapping("/gerente")
    public void guardarGerente(@RequestBody GerenteModel gerenteModel){
        sGerente.guardarGerente(gerenteModel);
    }
    @GetMapping("/gerente/{numeroGerente}")
    public ResponseEntity<GerenteModel> obtenerGerenteByID(@PathVariable Integer numeroGerente){
        try {
            GerenteModel gerenteModel = sGerente.obtenerGerenteByID(numeroGerente);
            return new ResponseEntity<GerenteModel>(gerenteModel, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<GerenteModel>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/gerente/{numeroGerente}")
    public ResponseEntity<GerenteModel> actualizarGerente(@RequestBody GerenteModel gerenteModel, @PathVariable Integer numeroGerente){
        try{
            GerenteModel gerenteModel1 = sGerente.obtenerGerenteByID(numeroGerente);
            gerenteModel1.setNombre_gerente(gerenteModel.getNombre_gerente());
            gerenteModel1.setApellidoP_gerente(gerenteModel.getApellidoP_gerente());
            gerenteModel1.setApllidoM_gerente(gerenteModel.getApllidoM_gerente());
            gerenteModel1.setCelular(gerenteModel.getCelular());
            gerenteModel1.setCorreo_electronico(gerenteModel.getCorreo_electronico());
            gerenteModel1.setEstaus(gerenteModel.getEstaus());
            gerenteModel1.setSueldo(gerenteModel.getSueldo());
            gerenteModel1.setPuesto(gerenteModel.getPuesto());
            sGerente.guardarGerente(gerenteModel1);
            return new ResponseEntity<GerenteModel>(HttpStatus.ACCEPTED);
        }catch (Exception ex){
            return new ResponseEntity<GerenteModel>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/gerente/{numeroGerente}")
    public void eliminarGerente(@PathVariable Integer numeroGerenter){
        sGerente.eliminarGerente(numeroGerenter);

    }
}
