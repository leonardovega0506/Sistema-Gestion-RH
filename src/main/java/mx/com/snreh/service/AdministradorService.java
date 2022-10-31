package mx.com.snreh.service;

import mx.com.snreh.iservice.IAdministrador;
import mx.com.snreh.model.AdministradorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {
    @Autowired
    private IAdministrador iAdministrador;

    public List<AdministradorModel> listarAdmins(){
        return iAdministrador.findAll();
    }
    public void guardarAdministrador(AdministradorModel administradorModel){
        iAdministrador.save(administradorModel);
    }
    public AdministradorModel obtenerAdminByID(Integer idAdmin){
        return iAdministrador.findById(idAdmin).get();
    }
    public void eliminarAdministrador(Integer idAdmin){
        iAdministrador.deleteById(idAdmin);
    }
}
