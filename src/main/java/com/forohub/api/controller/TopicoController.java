package com.forohub.api.controller;

import com.forohub.api.domain.topico.*;
import com.forohub.api.domain.usuarios.Usuario;
import com.forohub.api.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private TopicoRepository topicoRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public TopicoController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder) {


        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            return ResponseEntity.badRequest().body(null); // Manejar error adecuadamente en un caso real
        }

        Usuario usuario = usuarioRepository.findById(datosRegistroTopico.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, usuario));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().toString(),
                topico.getCurso(),
                topico.getFecha());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> obtenerTopicos(Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosRespuestaTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopico(@PathVariable Long id) {
        Topico t = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(t);

        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos
    ) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = optionalTopico.get();

        // Validar duplicados
        boolean duplicado = topicoRepository.existsByTituloAndMensaje(
                datos.titulo(),
                datos.mensaje()
        );
        if (duplicado) {
            return ResponseEntity.badRequest().build();
        }

        topico.actualizarDatos(datos);

        topicoRepository.save(topico);

        // Preparar respuesta
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().toString(),
                topico.getCurso(),
                topico.getFecha()
        );

        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        // Verificar si el tópico existe
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Topico t = topicoRepository.getReferenceById(id);

        // desactivar el tópico
        t.desactivarTopico();

        // Retornar respuesta exitosa sin contenido
        return ResponseEntity.noContent().build();
    }
}
