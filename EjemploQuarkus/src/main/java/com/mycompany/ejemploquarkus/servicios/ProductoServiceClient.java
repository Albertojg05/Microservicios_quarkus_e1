/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ejemploquarkus.servicios;

import com.mycompany.ejemploquarkus.entidades.Producto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
/**
 *
 * @author Alberto Jimenez
 */
@RegisterRestClient(baseUri = "http://localhost:8081") // URL del Product Service
@Path("/productos")
public interface ProductoServiceClient {
    @GET
    @Path("/{id}")
    Producto getById(@PathParam("id") Long id);
}
