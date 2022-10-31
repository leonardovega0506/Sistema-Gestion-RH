package mx.com.snreh.controller;

import mx.com.snreh.model.AdministradorModel;
import mx.com.snreh.model.GerenteModel;
import mx.com.snreh.service.AdministradorService;
import mx.com.snreh.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdministradorController {
    @Autowired
    private AdministradorService sAdmin;

    @GetMapping("/administrador")
    public List<AdministradorModel> listarAdministrador(){
        return sAdmin.listarAdmins();
    }

    @PostMapping("/administrador")
    public void guardarAdmin(@RequestBody AdministradorModel administradorModel){
        sAdmin.guardarAdministrador(administradorModel);
    }
    @GetMapping("/administrador/{id}")
    public ResponseEntity<AdministradorModel> obtenerAdminByID(@PathVariable Integer id){
        try {
            AdministradorModel administradorModel = sAdmin.obtenerAdminByID(id);
            return new ResponseEntity<AdministradorModel>(administradorModel, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<AdministradorModel>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/administrador/{id}")
    public ResponseEntity<AdministradorModel> actualizarAdmin(@RequestBody AdministradorModel administradorModel, @PathVariable Integer id){
        try{
            AdministradorModel administradorModel1 = sAdmin.obtenerAdminByID(id);
            administradorModel1.setNombre(administradorModel.getNombre());
            administradorModel1.setUsuario(administradorModel.getUsuario());
            sAdmin.guardarAdministrador(administradorModel1);
            return new ResponseEntity<AdministradorModel>(HttpStatus.ACCEPTED);
        }catch (Exception ex){
            return new ResponseEntity<AdministradorModel>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/administrador/{id}")
    public void eliminarAdmin(@PathVariable Integer id){
        sAdmin.eliminarAdministrador(id);
    }
}
