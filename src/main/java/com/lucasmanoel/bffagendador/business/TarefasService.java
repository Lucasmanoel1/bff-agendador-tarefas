package com.lucasmanoel.bffagendador.business;


import com.lucasmanoel.bffagendador.business.dto.in.TarefasDTORequest;
import com.lucasmanoel.bffagendador.business.dto.out.TarefasDTOResponse;
import com.lucasmanoel.bffagendador.business.enums.StatusNotificacaoEnum;
import com.lucasmanoel.bffagendador.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient client;

    public TarefasDTOResponse gravaTarefa(String token, TarefasDTORequest dto){
        return client.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaListaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
        return client.buscaListaTarefasEventos(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> busaListaTarefasEmail(String token){
        return client.buscaListaTarefasEmail(token);
    }

    public void deletaTarefaId(String id,String token){
        client.deletaTarefaId(id, token);
    }
    public TarefasDTOResponse alteraStatusTarefa(StatusNotificacaoEnum status, String id, String token){
       return client.alteraStatusTarefa(status, id, token);
    }
    public TarefasDTOResponse updateTarefa(TarefasDTORequest dto, String id, String token){
       return client.updateTarefas(dto, id, token);
    }
}
