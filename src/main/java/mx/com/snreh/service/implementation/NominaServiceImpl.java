package mx.com.snreh.service.implementation;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        if(trabajadorModel.getEstatus().equals("Baja")||trabajadorModel.getEstatus().equals("Pendiente de baja")){
            return null;
        }
        else{
            nominaTrabajadorModel.setTrabajadorModel(trabajadorModel);
            nominaTrabajadorModel.setNomina_trabajador(calcularNomina(id_trabajador,nominaDTO));
            nominaTrabajadorModel.setDescuento_retardo(calcularDescuenbstos(id_trabajador));
            nominaTrabajadorModel.setIsr(escogerISR(id_trabajador));
            NominaTrabajadorModel nominaNueva = iNomina.save(nominaTrabajadorModel);
            return mapearDTO(nominaNueva);
        }


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

        NominaTrabajadorModel nominaactualizada = iNomina.save(nominaTrabajadorModel);
        return mapearDTO(nominaactualizada);
    }



    private NominaDTO mapearDTO(NominaTrabajadorModel nominaTrabajadorModel){
        NominaDTO nominaDTO = new NominaDTO();
        nominaDTO.setId_nomina(nominaTrabajadorModel.getId_nomina());
        nominaDTO.setNomina_trabajador(nominaTrabajadorModel.getNomina_trabajador());
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
        nominaTrabajadorModel.setIsr(nominaDTO.getIsr());
        nominaTrabajadorModel.setFechaNomina(nominaDTO.getFechaNomina());
        nominaTrabajadorModel.setRetardo_trabajador(nominaDTO.getRetardo_trabajador());
        nominaTrabajadorModel.setDescuento_retardo(nominaDTO.getDescuento_retardo());
        return nominaTrabajadorModel;
    }
    private double calcularNomina(long id_trabajador,NominaDTO nominaDTO){
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        NominaTrabajadorModel nomina = mapearEntidad(nominaDTO);
        double sueldoNormal = trabajadorModel.getSueldo();
        log.info("ESTE SUELDO ES EL DEL TRABAJADOR"+sueldoNormal);
        double sueldoConHoraExtras = sueldoNormal + calcularHorasExtras(id_trabajador);
        log.info("ESTE ES EL SUELDO CON LAS HORAS EXTRAS QUE POSEE: "+sueldoConHoraExtras);
        double sueldoRetardos = sueldoConHoraExtras + calcularDescuenbstos(id_trabajador);
        log.info("ESTE ES EL SUELDO CON RETARDOS SI ES QUE POSEE: "+sueldoRetardos);
        double sueldoISR= (sueldoRetardos)-calcularISR(id_trabajador);
        log.info("ESTE ES EL SUELDO YA CON ISR: "+sueldoISR);
        double sueldoNETO= (sueldoISR);
        log.info("ESTE ES EL SUELDO NETO: "+sueldoNETO);
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
    private double escogerISR(long id_trabajador){
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        double sueldo = trabajadorModel.getSueldo();
        double isrOtorgodado = 0;
        if(sueldo>0 && sueldo< 578.53){
            isrOtorgodado = 0.019;
        }
        else if(sueldo>578.54 && sueldo <4910.18 ){
            isrOtorgodado = 0.064;
        }
        else if(sueldo>4910.19 && sueldo<8629.20){
            isrOtorgodado = 0.1088;
        }
        else if(sueldo>8629.21&sueldo< 10031.07){
            isrOtorgodado = 0.16;
        }
        else if(sueldo>10031.08 && sueldo< 12009.94){
            isrOtorgodado = 0.1792;
        }
        else if(sueldo>12009.95 && sueldo<24222.31){
            isrOtorgodado = 0.2136;
        }
        else if(sueldo>24222.32 && sueldo < 38177.69){
            isrOtorgodado = 0.2352;
        }
        else if(sueldo>38177.70 && sueldo< 72887.50){
            isrOtorgodado = 0.3;
        }
        else if(sueldo>72887.50 && sueldo<97183.33){
            isrOtorgodado = 0.32;
        }
        else if(sueldo>97183.33 && sueldo<291550.00){
            isrOtorgodado = 0.34;
        }
        else if(sueldo>291550.00){
            isrOtorgodado = 0.35;
        }
        else{
            return 0;
        }
        return isrOtorgodado;
    }
    private double calcularISR(long id_trabajador){
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        double sueldo = trabajadorModel.getSueldo();
        double isrSinCuota = 0;
        double isr = 0;

        if(sueldo>0 && sueldo< 578.53){
            isrSinCuota = (sueldo - 0.01 ) *escogerISR(id_trabajador);
            isr = isrSinCuota +0;
        }
        else if(sueldo>578.54 && sueldo <4910.18 ){
           isrSinCuota = (sueldo - 578.53)*escogerISR(id_trabajador);
           isr = isrSinCuota + 11.11;
        }
        else if(sueldo>4910.19 && sueldo<8629.20){
            isrSinCuota = (sueldo - 4910.19)*escogerISR(id_trabajador);
            isr = isrSinCuota + 288.33;
        }
        else if(sueldo>8629.21&sueldo< 10031.07){
            isrSinCuota = (sueldo - 8269.21)*escogerISR(id_trabajador);
            isr = isrSinCuota + 692.96;
        }
        else if(sueldo>10031.08 && sueldo< 12009.94){
            isrSinCuota = (sueldo - 10031.08)*escogerISR(id_trabajador);
            isr = isrSinCuota + 917.26;
        }
        else if(sueldo>12009.95 && sueldo<24222.31){
            isrSinCuota = (sueldo - 12009.95)*escogerISR(id_trabajador);
            isr = isrSinCuota + 1271.87;
        }
        else if(sueldo>24222.32 && sueldo < 38177.69){
            isrSinCuota = (sueldo - 24222.32)*escogerISR(id_trabajador);
            isr = isrSinCuota + 3880.44;
        }
        else if(sueldo>38177.70 && sueldo< 72887.50){
            isrSinCuota = (sueldo - 38177.70)*escogerISR(id_trabajador);
            isr = isrSinCuota +7162.74;
        }
        else if(sueldo>72887.50 && sueldo<97183.33){
           isrSinCuota = (sueldo - 72887.50)*escogerISR(id_trabajador);
           isr = isrSinCuota + 17575.69;
        }
        else if(sueldo>97183.34 && sueldo<291550.00){
            isrSinCuota = (sueldo - 97183.34)*escogerISR(id_trabajador);
            isr = isrSinCuota + 25350.35;
        }
        else if(sueldo>291550.01){
            isrSinCuota = (sueldo - 291550.01)*escogerISR(id_trabajador);
            isr = isrSinCuota + 91435.02;
        }
        else{
            return 0;
        }
        return isr;
    }
}