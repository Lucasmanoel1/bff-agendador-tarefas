package com.lucasmanoel.bffagendador.infrastructure.client;


import com.lucasmanoel.bffagendador.business.dto.in.TarefasDTORequest;
import com.lucasmanoel.bffagendador.business.dto.out.TarefasDTOResponse;
import com.lucasmanoel.bffagendador.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestHeader("Authorization") String token);
    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListaTarefasEventos(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                      @RequestHeader("Authorization") String token);
    @GetMapping
    List<TarefasDTOResponse> buscaListaTarefasEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaId(@RequestParam String id,
                       @RequestHeader("Authorization") String token);
    @PatchMapping
    TarefasDTOResponse alteraStatusTarefa(@RequestParam StatusNotificacaoEnum status,
                                          @RequestParam String id,
                                          @RequestHeader("Authorization") String token);
    @PutMapping
    TarefasDTOResponse updateTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestParam String id,
                                     @RequestHeader("Authorization") String token);
}
