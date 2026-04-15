package com.lucasmanoel.bffagendador.infrastructure.client;


import com.lucasmanoel.bffagendador.business.dto.in.EnderecoDTORequest;
import com.lucasmanoel.bffagendador.business.dto.in.LoginRequestDTO;
import com.lucasmanoel.bffagendador.business.dto.in.TelefoneDTORequest;
import com.lucasmanoel.bffagendador.business.dto.in.UsuarioDTORequest;
import com.lucasmanoel.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.lucasmanoel.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.lucasmanoel.bffagendador.business.dto.out.UsuarioDTOResponse;
import com.lucasmanoel.bffagendador.business.dto.out.ViaCepDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO loginRequestDTO);

    @DeleteMapping("/{email}")
    void  deletaUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);
    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);
    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTOResponse,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);
    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTOResponse,
                                         @RequestHeader("Authorization") String token);
    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep(@PathVariable("cep") String cep);

}
