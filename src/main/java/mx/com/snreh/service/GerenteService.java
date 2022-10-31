package mx.com.snreh.service;

import mx.com.snreh.iservice.IGerente;
import mx.com.snreh.model.GerenteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenteService {
    @Autowired
    private IGerente iGerente;

    public List<GerenteModel> listarGerente(){
        return iGerente.findAll();
    }
    public void guardarGerente(GerenteModel gerenteModel){
        iGerente.save(gerenteModel);
    }
    public GerenteModel obtenerGerenteByID(Integer numeroGerente){
        return iGerente.findById(numeroGerente).get();
    }
    public void eliminarGerente(Integer numeroGerente){
        iGerente.deleteById(numeroGerente);
    }
}
