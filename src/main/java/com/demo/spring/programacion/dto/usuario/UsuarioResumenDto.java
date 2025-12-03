package com.demo.spring.programacion.dto.usuario;

import java.time.LocalDate;

public class UsuarioResumenDto {

    private String nombre;
    private String email;
    private String colorFavorito;
    private Long cantidadEntradas;
    private LocalDate fechaUltimaEntrada;

    public UsuarioResumenDto(String nombre, String email, String colorFavorito,
                             Long cantidadEntradas, LocalDate fechaUltimaEntrada) {
        this.nombre = nombre;
        this.email = email;
        this.colorFavorito = colorFavorito;
        this.cantidadEntradas = cantidadEntradas;
        this.fechaUltimaEntrada = fechaUltimaEntrada;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getColorFavorito() { return colorFavorito; }
    public Long getCantidadEntradas() { return cantidadEntradas; }
    public LocalDate getFechaUltimaEntrada() { return fechaUltimaEntrada; }
}