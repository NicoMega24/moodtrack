package com.demo.spring.programacion.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.programacion.dto.usuario.UsuarioCreateDto;
import com.demo.spring.programacion.dto.usuario.UsuarioDto;
import com.demo.spring.programacion.dto.usuario.UsuarioResumenDto;
import com.demo.spring.programacion.service.usuario.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDto> getUsuarios() {

        List<UsuarioDto> usuarios = usuarioService.obtenerTodos();
        return usuarios;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(
            @PathVariable(name = "id") UUID id
            ) {
        Optional<UsuarioDto> usuario = usuarioService.obtenerPorId(id);

        if( usuario.isPresent() ){
            return ResponseEntity.ok( usuario.get() ) ;
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<UsuarioDto> createUsuario(
            @RequestBody UsuarioCreateDto usuarioCreateDto
            ){
        UsuarioDto usuarioCreado = usuarioService.crearUsuario(usuarioCreateDto);
        return ResponseEntity.ok( usuarioCreado );
    }

    @GetMapping("/{id}/resumen")
    public ResponseEntity<UsuarioResumenDto> obtenerResumen(@PathVariable UUID id) {
        UsuarioResumenDto resumen = usuarioService.obtenerResumenUsuario(id);
        return ResponseEntity.ok(resumen);
    }

}