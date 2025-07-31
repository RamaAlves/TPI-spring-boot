package com.informatorio.info_market.service.usuario.impl;

import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.exception.notFound.NotFoundException;
import com.informatorio.info_market.repository.usuario.UsuarioRepository;
import com.informatorio.info_market.service.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario getUsuarioEntityById(UUID idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()){
            return usuario.get();
        }else{
            throw new NotFoundException("No se encontró el usuario con id: "+idUsuario);
        }
    }
    /*@Override
    public Usuario getUsuarioById(UUID idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()){
            //agregar mapper
            //return usuario.get();
        }else{
            throw new NotFoundException("No se encontró el usuario con id: "+idUsuario);
        }
    }*/


}
