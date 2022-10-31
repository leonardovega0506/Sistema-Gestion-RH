package mx.com.snreh.service;

import mx.com.snreh.iservice.ITrabajador;
import mx.com.snreh.model.TrabajadorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorService {
    @Autowired
    private ITrabajador iTrabajador;

    public List<TrabajadorModel> listarTrabajadores(){
        return iTrabajador.findAll();
    }
    public void guardarTrabajador(TrabajadorModel trabajadorModel){
        iTrabajador.save(trabajadorModel);
    }
    public TrabajadorModel obtenerTrabajadorByNumero(Integer numeroTrabajador){
       return iTrabajador.findById(numeroTrabajador).get();
    }
    public void eliminarTrabajador(Integer numeroTrabajador){
        iTrabajador.existsById(numeroTrabajador);
    }
}
