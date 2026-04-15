package com.lucasmanoel.bffagendador.business;



import com.lucasmanoel.bffagendador.business.dto.in.EnderecoDTORequest;
import com.lucasmanoel.bffagendador.business.dto.in.LoginRequestDTO;
import com.lucasmanoel.bffagendador.business.dto.in.TelefoneDTORequest;
import com.lucasmanoel.bffagendador.business.dto.in.UsuarioDTORequest;
import com.lucasmanoel.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.lucasmanoel.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.lucasmanoel.bffagendador.business.dto.out.UsuarioDTOResponse;
import com.lucasmanoel.bffagendador.business.dto.out.ViaCepDTOResponse;
import com.lucasmanoel.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTORequest){
        return client.salvaUsuario(usuarioDTORequest);
    }
    public String loginUsuario(LoginRequestDTO dto){
        return  client.login(dto);
    }

    public UsuarioDTOResponse buscaUsuarioFindByEmail(String email, String token){
        return client.buscaUsuarioPorEmail(email, token);
    }
    public void deletaUsuarioPorEmail(String email, String token){
         client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto){
        return client.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco (Long idEndereco, EnderecoDTORequest enderecoDTORequest, String token){
        return client.atualizaEndereco(enderecoDTORequest, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone (Long idTelefone, TelefoneDTORequest telefoneDTORequest, String token){
        return client.atualizaTelefone(telefoneDTORequest, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto){
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto){
        return client.cadastraTelefone(dto, token);
    }

    public ViaCepDTOResponse buscarEnderecoPorCep(String cep){
        return client.buscarDadosCep(cep);
    }
}
