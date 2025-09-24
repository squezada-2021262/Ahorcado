//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.santiagoquezada.ahorcado.controller;

import com.santiagoquezada.ahorcado.model.Usuarios;
import com.santiagoquezada.ahorcado.service.UsuariosService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/usuarios"})
public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public List<Usuarios> getAllUsuarios() {
        return this.usuariosService.getAllUsuarios();
    }

    @GetMapping({"/{id}"})
    public Usuarios getUsuarioById(@PathVariable Integer id) {
        return this.usuariosService.getUsuarioById(id);
    }

    @PostMapping
    public ResponseEntity<Usuarios> createUsuario(@RequestBody Usuarios usuario) {
        Usuarios savedUsuario = this.usuariosService.saveUsuario(usuario);
        return new ResponseEntity(savedUsuario, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public String updateUsuario(@PathVariable Integer id, @RequestBody Usuarios usuario) {
        return this.usuariosService.updateUsuario(id, usuario);
    }

    @DeleteMapping({"/{id}"})
    public String deleteUsuario(@PathVariable Integer id) {
        return this.usuariosService.deleteUsuario(id);
    }
}
