package com.lucasmanoel.bffagendador.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasmanoel.bffagendador.business.enums.StatusNotificacaoEnum;
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
    private LocalDateTime dataEvento;
}
