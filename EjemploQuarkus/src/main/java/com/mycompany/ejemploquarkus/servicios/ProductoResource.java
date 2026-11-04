package com.mycompany.ejemploquarkus.servicios;

import com.mycompany.ejemploquarkus.entidades.Producto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author Alberto Jimenez
 */

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
public class ProductoResource {
    
    @GET
    @Path("/{id}")
    public Producto getProduct(@PathParam("id") Long id) {
        return new Producto(id, "Laptop Gaming", 1500.00);
    }
}
