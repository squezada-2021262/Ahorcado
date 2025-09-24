//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.santiagoquezada.ahorcado.service;

import com.santiagoquezada.ahorcado.model.Usuarios;
import java.util.List;

public interface UsuariosService {
    List<Usuarios> getAllUsuarios();

    Usuarios getUsuarioById(Integer id);

    Usuarios saveUsuario(Usuarios usuario);

    String updateUsuario(Integer id, Usuarios usuario);

    String deleteUsuario(Integer id);
}
