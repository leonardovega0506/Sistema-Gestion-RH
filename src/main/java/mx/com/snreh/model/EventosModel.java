package mx.com.snreh.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "eventos")
@Data
public class EventosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_evento",nullable = false)
    private Date fecha_evento;

    @Column(name = "tipo_evento",nullable = false)
    private String tipo_evento;

    @Column(name = "titulo_evento",nullable = false)
    private String titulo_evento;

    @Column(name = "cuerpo_evento",nullable = false)
    private String cuerpo_evento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gerente", nullable = false)
    private GerenteModel gerenteModel;
}
