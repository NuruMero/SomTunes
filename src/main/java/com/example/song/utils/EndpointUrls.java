package com.example.song.utils;

/**
 * Contiene las strings de los enlaces para las peticiones.
 * Editar una de las strings aquí cambiará también los enlaces de la API.
 */
public class EndpointUrls {
    // Generic
    public static final String Base = "/api";
    public static final String requiresID = "/{id}";
    public static final String requiresText = "/name/{name}";
    public static final String search = "/search";

    // Bands
    public static final String Bands = "/bands";

    // Songs
    public static final String Songs = "/songs";
    public static final String MadeBy = "/by";
}
