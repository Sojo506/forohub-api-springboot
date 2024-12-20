package com.forohub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        LocalDateTime fecha
) {
    public DatosRespuestaTopico(Topico t) {
        this(t.getId(), t.getTitulo(), t.getMensaje(), t.getAutor(), t.getCurso(), t.getFecha());
    }
}
