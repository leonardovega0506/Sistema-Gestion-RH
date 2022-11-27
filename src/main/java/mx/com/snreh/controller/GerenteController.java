package mx.com.snreh.controller;


import mx.com.snreh.dto.GerenteDTO;
import mx.com.snreh.response.GerenteRespuesta;
import mx.com.snreh.service.interfaces.GerenteService;
import mx.com.snreh.util.ConstantesGlobales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/snrhe/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService sGerente;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public GerenteRespuesta listarGerentes(
            @RequestParam(value = "pageNo",defaultValue = ConstantesGlobales.NUMERO_PAGINA_DEFECTO,required = false)int numeroPagina,
            @RequestParam(value = "pageSize",defaultValue = ConstantesGlobales.MEDIDA_PAGINA_DEFECTO, required = false) int sizePagina){
        return sGerente.obtenerGerentes(numeroPagina,sizePagina);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<GerenteDTO> obtenerGerenteByNumero_Gerente(@PathVariable(name = "id") long numero_gerente){
        return ResponseEntity.ok(sGerente.obtenerGerenteByID(numero_gerente));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GerenteDTO> crearGerentes(@Valid @RequestBody GerenteDTO gerenteDTO){
        return new ResponseEntity<>(sGerente.crearGerente(gerenteDTO), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<GerenteDTO> actualizarGerente(@Valid @RequestBody GerenteDTO gerenteDTO,@PathVariable(name = "id") long numero_gerente){
        GerenteDTO gerenteRespuesta = sGerente.actualizarGerente(gerenteDTO,numero_gerente);
        return new ResponseEntity<>(gerenteRespuesta,HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarGerente(@PathVariable(name = "id") long numero_gerente){
        sGerente.eliminarGerente(numero_gerente);
        return new ResponseEntity<>("Trabajador eliminado con exito",HttpStatus.NO_CONTENT);
    }
}