package com.demo.spring.programacion.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.programacion.dto.usuario.UsuarioCreateDto;
import com.demo.spring.programacion.dto.usuario.UsuarioDto;
import com.demo.spring.programacion.dto.usuario.UsuarioResumenDto;
import com.demo.spring.programacion.service.usuario.UsuarioService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

     @GetMapping()
    public List<UsuarioDto> getUsuarios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String colorFavorito
    ) {

        List<UsuarioDto> usuarios = usuarioService.obtenerTodos(nombre, email, colorFavorito);
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
            @Valid @RequestBody UsuarioCreateDto usuarioCreateDto
            ){
        UsuarioDto usuarioCreado = usuarioService.crearUsuario(usuarioCreateDto);
        return ResponseEntity
                .created( URI.create( "/api/v1/usuarios/" + usuarioCreado.getId() ) )
                .body( usuarioCreado ) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario( @PathVariable(name = "id") UUID id, @Valid @RequestBody UsuarioCreateDto usuarioCreateDto){
        log.info("Solicitud para actualizar usuario con id {}", id);
        UsuarioDto usuarioDto = usuarioService.updateUsuario( id, usuarioCreateDto );
        if( usuarioDto == null ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok( usuarioDto );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario( @PathVariable(name = "id") UUID id ){
        boolean wasDeleted = usuarioService.eliminarUsuario( id );
        if( !wasDeleted ){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/resumen")
    public ResponseEntity<UsuarioResumenDto> obtenerResumen(@PathVariable UUID id) {
        UsuarioResumenDto resumen = usuarioService.obtenerResumenUsuario(id);
        return ResponseEntity.ok(resumen);
    }

}