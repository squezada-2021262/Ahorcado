//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.santiagoquezada.ahorcado.service;

import com.santiagoquezada.ahorcado.model.Usuarios;
import com.santiagoquezada.ahorcado.repository.UsuariosRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UsuariosServiceImpl implements UsuariosService {
    private final UsuariosRepository usuariosRepository;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public List<Usuarios> getAllUsuarios() {
        return this.usuariosRepository.findAll();
    }

    public Usuarios getUsuarioById(Integer id) {
        return (Usuarios)this.usuariosRepository.findById(id).orElse((Usuarios) null);
    }

    public Usuarios saveUsuario(Usuarios usuario) {
        return (Usuarios)this.usuariosRepository.save(usuario);
    }

    public String updateUsuario(Integer id, Usuarios usuario) {
        Usuarios existingUsuario = (Usuarios)this.usuariosRepository.findById(id).orElse((Usuarios) null);
        if (existingUsuario != null) {
            existingUsuario.setCorreoUsuario(usuario.getCorreoUsuario());
            existingUsuario.setContrasena(usuario.getContrasena());
            this.usuariosRepository.save(existingUsuario);
            return "Se han actualizado los datos del usuario.";
        } else {
            return "No se pudo actualizar el usuario. El ID no existe.";
        }
    }

    public String deleteUsuario(Integer id) {
        if (this.usuariosRepository.existsById(id)) {
            this.usuariosRepository.deleteById(id);
            return "Se ha eliminado el usuario de la base de datos.";
        } else {
            return "No se pudo eliminar el usuario. El ID no existe.";
        }
    }
}
