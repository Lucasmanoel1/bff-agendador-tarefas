package com.lucasmanoel.bffagendador.controller;


import com.lucasmanoel.bffagendador.business.TarefasService;
import com.lucasmanoel.bffagendador.business.dto.in.TarefasDTORequest;
import com.lucasmanoel.bffagendador.business.dto.out.TarefasDTOResponse;
import com.lucasmanoel.bffagendador.business.enums.StatusNotificacaoEnum;
import com.lucasmanoel.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro Tarefas dos usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Criar tarefa",description = "Cria uma nova tarefa para o usuário com status inicial pendente")
    @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos para criação da tarefa")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.gravaTarefa(token, dto));
    }
    @GetMapping("/eventos")
    @Operation(summary = "Listar tarefas por período",description = "Retorna as tarefas do usuário dentro de um intervalo de datas informado")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    @ApiResponse(responseCode = "400", description = "Parâmetros de data inválidos")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaTarefasEventos(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                                             @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.buscaListaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }
    @GetMapping
    @Operation(summary = "Listar tarefas do usuário",description = "Retorna todas as tarefas associadas ao usuário")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    @ApiResponse(responseCode = "400", description = "Token inválido ou não informado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaTarefasEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.busaListaTarefasEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deletar tarefa",description = "Remove uma tarefa com base no ID informado")
    @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<Void> deletaTarefaId(@RequestParam String id,
                                               @RequestHeader(name = "Authorization", required = false) String token){
        tarefasService.deletaTarefaId(id, token);
        return ResponseEntity.ok().build();
    }
    @PatchMapping
    @Operation(summary = "Atualizar status da tarefa",description = "Altera o status de uma tarefa existente")
    @ApiResponse(responseCode = "200", description = "Status da tarefa atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos para atualização de status")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public  ResponseEntity<TarefasDTOResponse> alteraStatusTarefa(@RequestParam StatusNotificacaoEnum status,
                                                                  @RequestParam String id,
                                                                  @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.alteraStatusTarefa(status, id, token));
    }
    @PutMapping
    @Operation(summary = "Atualizar tarefa",description = "Atualiza os dados de uma tarefa existente com base no ID informado")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização da tarefa")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefa(dto, id, token));
    }
}
