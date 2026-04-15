package com.lucasmanoel.bffagendador.controller;


import com.lucasmanoel.bffagendador.business.UsuarioService;
import com.lucasmanoel.bffagendador.business.dto.in.EnderecoDTORequest;
import com.lucasmanoel.bffagendador.business.dto.in.LoginRequestDTO;
import com.lucasmanoel.bffagendador.business.dto.in.TelefoneDTORequest;
import com.lucasmanoel.bffagendador.business.dto.in.UsuarioDTORequest;
import com.lucasmanoel.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.lucasmanoel.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.lucasmanoel.bffagendador.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuários",description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Dados inválidos ou usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTOResponse){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTOResponse));
    }

    @PostMapping("/login")
    @Operation(summary = "Realizar login", description = "Autentica o usuário com email e senha e retorna o token de acesso.")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Email ou senha inválidos")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO){
        return usuarioService.loginUsuario(loginRequestDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar usuário por email",description = "Busca os dados do usuário no sistema utilizando o email informado.")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.buscaUsuarioFindByEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Excluir usuário", description = "Remove um usuário do sistema utilizando o email informado.")
    @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader("Authorization") String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados do usuário", description = "Atualiza os dados cadastrais do usuário autenticado.")
    @ApiResponse(responseCode = "200", description = "Dados atualizados com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<UsuarioDTOResponse> atualizarDadosUsuario(@RequestBody UsuarioDTORequest dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar endereço do usuário", description = "Atualiza o endereço do usuário utilizando o identificador informado.")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário ou endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTORequest, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar endereço do usuário", description = "Atualiza o endereço do usuário utilizando o identificador informado.")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário ou endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTORequest, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastrar telefone", description = "Cadastra um novo telefone para o usuário autenticado.")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                                                @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDTORequest));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastrar endereço", description = "Cadastra um novo endereço para o usuário autenticado.")
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }
}
