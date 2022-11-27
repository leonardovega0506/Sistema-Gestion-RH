package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.NominaDTO;
import mx.com.snreh.dto.RenunciaDTO;
import mx.com.snreh.dto.TrabajadorDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.NominaTrabajadorModel;
import mx.com.snreh.model.RenunciaTrabajadorModel;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.INomina;
import mx.com.snreh.repository.IRenuncia;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.service.interfaces.RenunciaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class RenunciaServiceImpl implements RenunciaService {

    @Autowired
    private ITrabajador iTrabajador;

    @Autowired
    private IRenuncia iRenuncia;

    @Autowired
    private INomina iNomina;

    @Override
    public RenunciaDTO createRenuncia(long id_trabajador, RenunciaDTO renunciaDTO) {
        RenunciaTrabajadorModel renunciaTrabajadorModel = mapearEntidad(renunciaDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        trabajadorModel.setEstatus("Pendiente de baja");
        renunciaTrabajadorModel.setEstatusRenuncia("Pendiente");
        renunciaTrabajadorModel.setTrabajadorModel(trabajadorModel);
        renunciaTrabajadorModel.setFiniquito(obtenerFiniquito(id_trabajador,renunciaDTO));
        RenunciaTrabajadorModel renuncia = iRenuncia.save(renunciaTrabajadorModel);
        return mapearDTO(renuncia);
    }

    @Override
    public RenunciaDTO findRenuncia(long id_trabajador, long id_renuncia) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        RenunciaTrabajadorModel renunciaTrabajadorModel = iRenuncia.findById(id_renuncia).orElseThrow(() -> new ResourceNotFoundException("Renuncia","ID",id_renuncia));

        if(!renunciaTrabajadorModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"No es posible recuperar esa hoja de renuncia");
        }
        return mapearDTO(renunciaTrabajadorModel);
    }

    @Override
    public RenunciaDTO updateRenuncua(long id_trabajador, long id_renuncia, RenunciaDTO renunciaDTO) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        RenunciaTrabajadorModel renunciaTrabajadorModel = iRenuncia.findById(id_renuncia).orElseThrow(() -> new ResourceNotFoundException("Renuncia","ID",id_renuncia));

        if(!renunciaTrabajadorModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"No es posible recuperar esa hoja de renuncia");
        }
        if(renunciaTrabajadorModel.getEstatusRenuncia().equals("Finalizado")){
            trabajadorModel.setEstatus("Baja");
            renunciaTrabajadorModel.setFiniquito(renunciaDTO.getFiniquito());

            TrabajadorModel trabajadorActualizado = iTrabajador.save(trabajadorModel);
            RenunciaTrabajadorModel renunciaActualizada = iRenuncia.save(renunciaTrabajadorModel);

            return mapearDTO(renunciaActualizada);
        }
        else{
            return null;
        }

    }

    private RenunciaDTO mapearDTO(RenunciaTrabajadorModel renunciaTrabajadorModel){
        RenunciaDTO renunciaDTO = new RenunciaDTO();
        renunciaDTO.setId_renuncia(renunciaTrabajadorModel.getId_renuncia());
        renunciaDTO.setFecha_renuncia(renunciaTrabajadorModel.getFecha_renuncia());
        renunciaDTO.setMotivo_renuncia(renunciaTrabajadorModel.getMotivo_renuncia());
        renunciaDTO.setFiniquito(renunciaTrabajadorModel.getFiniquito());
        renunciaDTO.setTiempoTrabajado((int) renunciaTrabajadorModel.getTiempoTrabajado());
        return renunciaDTO;
    }
    private RenunciaTrabajadorModel mapearEntidad(RenunciaDTO renunciaDTO){
        RenunciaTrabajadorModel renunciaTrabajadorModel = new RenunciaTrabajadorModel();
        renunciaTrabajadorModel.setId_renuncia(renunciaDTO.getId_renuncia());
        renunciaTrabajadorModel.setFecha_renuncia(renunciaDTO.getFecha_renuncia());
        renunciaTrabajadorModel.setMotivo_renuncia(renunciaDTO.getMotivo_renuncia());
        renunciaTrabajadorModel.setFiniquito(renunciaDTO.getFiniquito());
        renunciaTrabajadorModel.setTiempoTrabajado((int) renunciaDTO.getTiempoTrabajado());
        return renunciaTrabajadorModel;
    }
    private double obtenerFiniquito(long id_trabajador, RenunciaDTO renunciaDTO){
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        RenunciaTrabajadorModel renunciaTrabajadorModel = mapearEntidad(renunciaDTO);

        renunciaTrabajadorModel.setTrabajadorModel(trabajadorModel);
        List<NominaTrabajadorModel> nominas = iNomina.findByTrabajadorModelId(id_trabajador);

        double sueldoDiario = trabajadorModel.getSueldo()/28;
        double sueldoFinal = (sueldoDiario * renunciaTrabajadorModel.getTiempoTrabajado())+calcularNominas(id_trabajador);
        double aguinaldoDiario = sueldoDiario*15;
        double aguinaldoFiniquito = (aguinaldoDiario/365)* renunciaTrabajadorModel.getTiempoTrabajado();
        double vacacionesAdeudio = ((renunciaTrabajadorModel.getTiempoTrabajado()*sueldoDiario)/365) *sueldoDiario;
        double vacacionesfinal = vacacionesAdeudio*0.025;
        double impuestos = (vacacionesfinal+aguinaldoFiniquito+sueldoFinal) - (vacacionesfinal+aguinaldoFiniquito+sueldoFinal)*0.16;
        double finiquitoNeto = (vacacionesfinal+aguinaldoFiniquito+sueldoFinal)- impuestos;
        return  finiquitoNeto;
    }
    private double calcularNominas(long id_trabajador){
        List<NominaTrabajadorModel> nominas = iNomina.findByTrabajadorModelId(id_trabajador);
        double totalNomina = 0;
        for(var nomina : nominas){
            totalNomina += nomina.getNomina_trabajador();
        }
        return totalNomina;
    }
}