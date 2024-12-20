package com.forohub.api.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean status;
    private String autor;
    private String curso;

    public Topico() {

    }

    public Topico(Long id, String titulo, String mensaje, LocalDateTime fecha, Boolean status, String autor, String curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.status = status;
        this.autor = autor;
        this.curso = curso;
    }

    public Topico(@Valid DatosRegistroTopico datosRegistroTopico) {
        this.status = true;
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha = LocalDateTime.now();
        this.curso = datosRegistroTopico.curso();
        this.autor = "Sojo";
    }

    public void actualizarDatos(DatosActualizarTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.curso() != null) {
            this.curso = datos.curso();
        }
    }

    public void desactivarTopico() {
        this.status = false;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
