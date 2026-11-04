/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ordenquarkus.servicios;

import com.mycompany.ordenquarkus.entidades.Orden;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.WebApplicationException;
import java.util.List;

/**
 *
 * @author Alberto Jimenez
 */
@Path("/ordenes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdenResource {

    @RestClient
    ProductoServiceClient productoService;

    @GET
    public List<Orden> obtenerTodas() {
        return Orden.listAll();
    }

    @POST
    @Transactional
    public Response crearOrden(Orden orden) {
        try {
            ProductoDTO producto = productoService.obtenerProducto(orden.productoId);

            if (producto == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("El producto no existe").build();
            }

            orden.total = producto.precio * orden.cantidad;
            orden.persist();

            return Response.status(Response.Status.CREATED).entity(orden).build();

        } catch (WebApplicationException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error al consultar el producto. Verifica que el ID sea correcto y el servicio de productos esté en ejecución.").build();
        }
    }
}
