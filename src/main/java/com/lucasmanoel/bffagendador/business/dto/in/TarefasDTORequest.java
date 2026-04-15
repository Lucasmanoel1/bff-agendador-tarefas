package com.lucasmanoel.bffagendador.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasmanoel.bffagendador.business.enums.StatusNotificacaoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TarefasDTORequest {
    private String nome;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(type = "string", pattern = "dd-MM-yyyy HH:mm:ss", example = "30-03-2026 15:00:00")
    private LocalDateTime dataEvento;
}
