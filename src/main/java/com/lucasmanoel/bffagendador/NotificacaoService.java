package com.lucasmanoel.bffagendador;


import com.lucasmanoel.bffagendador.business.dto.out.TarefasDTOResponse;
import com.lucasmanoel.bffagendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final EmailClient client;

    public void enviarEmail(TarefasDTOResponse dtoResponse){
        client.enviarEmail(dtoResponse);
    }
}
