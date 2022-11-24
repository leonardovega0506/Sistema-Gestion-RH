package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.NominaDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.*;
import mx.com.snreh.repository.IHoraExtra;
import mx.com.snreh.repository.INomina;
import mx.com.snreh.repository.IRetardo;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.service.interfaces.HoraExtraService;
import mx.com.snreh.service.interfaces.NominaService;
import mx.com.snreh.service.interfaces.RetardoTrabajadorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NominaServiceImpl implements NominaService {

    @Autowired
    private IRetardo iRetardo;

    @Autowired
    private IHoraExtra iHoraExtra;

    @Autowired
    private INomina iNomina;

    @Autowired
    private ITrabajador iTrabajador;


    @Override
    public NominaDTO createNomina(long id_trabajador, NominaDTO nominaDTO) {
        NominaTrabajadorModel nominaTrabajadorModel = mapearEntidad(nominaDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        nominaTrabajadorModel.setTrabajadorModel(trabajadorModel);
        nominaTrabajadorModel.setNomina_trabajador(calcularNomina(id_trabajador,nominaDTO));
        nominaTrabajadorModel.setDescuento_retardo(calcularDescuenbstos(id_trabajador));
        NominaTrabajadorModel nominaNueva = iNomina.save(nominaTrabajadorModel);
        return mapearDTO(nominaNueva);

    }

    @Override
    public List<NominaDTO> findAllNominasTrabajador(long id_trabajador) {
        List<NominaTrabajadorModel> nominas = iNomina.findByTrabajadorModelId(id_trabajador);
        return nominas.stream().map(nomina -> mapearDTO(nomina)).collect(Collectors.toList());
    }
    @Override
    public List<NominaDTO> findAllNominas() {
        List<NominaTrabajadorModel> nominas = iNomina.findAll();
        return nominas.stream().map(nomina -> mapearDTO(nomina)).collect(Collectors.toList());
    }

    @Override
    public NominaDTO findNominaByID(long id_trabajador, long id_nomina) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        NominaTrabajadorModel nominaTrabajadorModel = iNomina.findById(id_nomina).orElseThrow(() -> new ResourceNotFoundException("Nomina","ID",id_nomina));

        if(!nominaTrabajadorModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta nomina no pertenece al trabjador seleccionado");
        }
        return mapearDTO(nominaTrabajadorModel);
    }

    @Override
    public NominaDTO updateNomina(long id_trabajador, long id_nomina, NominaDTO nominaDTO) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        NominaTrabajadorModel nominaTrabajadorModel = iNomina.findById(id_nomina).orElseThrow(() -> new ResourceNotFoundException("Nomina","ID",id_nomina));

        if(!nominaTrabajadorModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta nomina no pertenece al trabjador seleccionado");
        }
        nominaTrabajadorModel.setNomina_trabajador(nominaDTO.getNomina_trabajador());
        nominaTrabajadorModel.setDescuento_retardo(nominaDTO.getDescuento_retardo());
        nominaTrabajadorModel.setIsr(nominaDTO.getIsr());
        nominaTrabajadorModel.setIva(nominaDTO.getIva());

        NominaTrabajadorModel nominaactualizada = iNomina.save(nominaTrabajadorModel);
        return mapearDTO(nominaactualizada);
    }



    private NominaDTO mapearDTO(NominaTrabajadorModel nominaTrabajadorModel){
        NominaDTO nominaDTO = new NominaDTO();
        nominaDTO.setId_nomina(nominaTrabajadorModel.getId_nomina());
        nominaDTO.setNomina_trabajador(nominaTrabajadorModel.getNomina_trabajador());
        nominaDTO.setIva(nominaTrabajadorModel.getIva());
        nominaDTO.setIsr(nominaTrabajadorModel.getIsr());
        nominaDTO.setFechaNomina(nominaTrabajadorModel.getFechaNomina());
        nominaDTO.setDescuento_retardo(nominaTrabajadorModel.getDescuento_retardo());
        nominaDTO.setRetardo_trabajador(nominaTrabajadorModel.getRetardo_trabajador());
        return nominaDTO;
    }
    private NominaTrabajadorModel mapearEntidad(NominaDTO nominaDTO){
        NominaTrabajadorModel nominaTrabajadorModel = new NominaTrabajadorModel();
        nominaTrabajadorModel.setId_nomina(nominaDTO.getId_nomina());
        nominaTrabajadorModel.setNomina_trabajador(nominaDTO.getNomina_trabajador());
        nominaTrabajadorModel.setIva(nominaDTO.getIva());
        nominaTrabajadorModel.setIsr(nominaDTO.getIsr());
        nominaTrabajadorModel.setFechaNomina(nominaDTO.getFechaNomina());
        nominaTrabajadorModel.setRetardo_trabajador(nominaDTO.getRetardo_trabajador());
        nominaTrabajadorModel.setDescuento_retardo(nominaDTO.getDescuento_retardo());
        return nominaTrabajadorModel;
    }
    private double calcularNomina(long id_trabajador,NominaDTO nominaDTO){
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        List<HoraExtraModel> horasExtra = iHoraExtra.findByTrabajadorModelId(id_trabajador);
        NominaTrabajadorModel nomina = mapearEntidad(nominaDTO);
        double sueldoNormal = trabajadorModel.getSueldo();
        double sueldoConHoraExtras = sueldoNormal + calcularHorasExtras(id_trabajador);
        double sueldoRetardos = sueldoConHoraExtras + calcularDescuenbstos(id_trabajador);

        double sueldoISR= (sueldoRetardos)-(sueldoRetardos)*nomina.getIsr();
        double sueldoNETO= (sueldoISR) - (sueldoNormal)*nomina.getIva();

        return sueldoNETO;

        }
        private double calcularDescuenbstos(long id_trabajador){
            TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
            List<RetardoTrabajadorModel> retardos = iRetardo.findByTrabajadorModelId(id_trabajador);
            double descuentoRetardo=0;
            for(var retardo : retardos){
                descuentoRetardo += retardo.getDescuento_retardo();
            }
            return descuentoRetardo;
    }
    private double calcularHorasExtras(long id_trabajador){
        List<HoraExtraModel> horasExtra = iHoraExtra.findByTrabajadorModelId(id_trabajador);
        double horasExtras =0;
        for(var horas : horasExtra){
        horasExtras += horas.getAumento_total();
        }
        return horasExtras;
    }
}