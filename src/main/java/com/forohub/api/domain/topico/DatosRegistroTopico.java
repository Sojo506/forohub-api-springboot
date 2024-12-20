package com.forohub.api.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotNull
        Long idUsuario,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String curso

) {
}

/*
*  mensaje TEXT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TINYINT(1) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    curso */
