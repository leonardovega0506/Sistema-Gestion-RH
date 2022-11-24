package mx.com.snreh.service.implementation;

import mx.com.snreh.dto.QuejasDTO;
import mx.com.snreh.exception.ResourceNotFoundException;
import mx.com.snreh.exception.SNRHEException;
import mx.com.snreh.model.QuejasAclaracionesModel;
import mx.com.snreh.model.TrabajadorModel;
import mx.com.snreh.repository.IQueja;
import mx.com.snreh.repository.ITrabajador;
import mx.com.snreh.service.implementation.interfaces.QuejaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuejaServiceImpl implements QuejaService {

    @Autowired(required = false)
    private ModelMapper modelMapper;

    @Autowired
    private IQueja iQueja;

    @Autowired
    private ITrabajador iTrabajador;

    @Override
    public QuejasDTO createQueja(long id_trabajador, QuejasDTO quejasDTO) {
        QuejasAclaracionesModel quejasAclaracionesModel= mapearEntidad(quejasDTO);
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));

        quejasAclaracionesModel.setTrabajadorModel(trabajadorModel);
        QuejasAclaracionesModel nuevaQueja = iQueja.save(quejasAclaracionesModel);

        return mapearDTO(nuevaQueja);
    }

    @Override
    public List<QuejasDTO> findAllQuejas(long id_trabajador) {
        List<QuejasAclaracionesModel> quejas = iQueja.findByTrabajadorModelId(id_trabajador);
        return quejas.stream().map(queja -> mapearDTO(queja)).collect(Collectors.toList());
    }

    @Override
    public QuejasDTO findQueja(long id_trabajador, long id_queja) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        QuejasAclaracionesModel quejasAclaracionesModel = iQueja.findById(id_queja).orElseThrow(() -> new ResourceNotFoundException("Queja o Aclaracion","ID",id_queja));

        if(!quejasAclaracionesModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta queja no esta hecha por este trabajador");
        }
        return mapearDTO(quejasAclaracionesModel);
    }

    @Override
    public QuejasDTO updateQueja(long id_trabajador, long id_queja, QuejasDTO quejasDTO) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        QuejasAclaracionesModel quejasAclaracionesModel = iQueja.findById(id_queja).orElseThrow(() -> new ResourceNotFoundException("Queja o Aclaracion","ID",id_queja));

        if(!quejasAclaracionesModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta queja no esta hecha por este trabajador");
        }
        quejasAclaracionesModel.setCuerpo_queja(quejasDTO.getCuerpo_queja());
        quejasAclaracionesModel.setEstatus_queja(quejasDTO.getEstatus_queja());

        return mapearDTO(quejasAclaracionesModel);
    }

    @Override
    public void eliminarQueja(long id_trabajador, long id_queja) {
        TrabajadorModel trabajadorModel = iTrabajador.findById(id_trabajador).orElseThrow(() -> new ResourceNotFoundException("Trabajador","ID",id_trabajador));
        QuejasAclaracionesModel quejasAclaracionesModel = iQueja.findById(id_queja).orElseThrow(() -> new ResourceNotFoundException("Queja o Aclaracion","ID",id_queja));

        if(!quejasAclaracionesModel.getTrabajadorModel().getId().equals(trabajadorModel.getId())){
            throw new SNRHEException(HttpStatus.BAD_REQUEST,"Esta queja no esta hecha por este trabajador");
        }
        iQueja.delete(quejasAclaracionesModel);
    }
    private QuejasDTO mapearDTO(QuejasAclaracionesModel quejasAclaracionesModel){
        QuejasDTO quejasDTO = modelMapper.map(quejasAclaracionesModel,QuejasDTO.class);
        return quejasDTO;
    }
    private QuejasAclaracionesModel mapearEntidad(QuejasDTO quejasDTO){
        QuejasAclaracionesModel quejasAclaracionesModel = modelMapper.map(quejasDTO,QuejasAclaracionesModel.class);
        return quejasAclaracionesModel;
    }

}
