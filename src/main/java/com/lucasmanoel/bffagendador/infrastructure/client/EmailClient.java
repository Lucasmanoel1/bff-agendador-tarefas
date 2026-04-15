package com.lucasmanoel.bffagendador.infrastructure.client;

import com.lucasmanoel.bffagendador.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {
    @PostMapping
    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}
