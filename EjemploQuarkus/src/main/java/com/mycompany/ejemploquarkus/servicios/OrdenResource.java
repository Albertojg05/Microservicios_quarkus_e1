package com.mycompany.ejemploquarkus.servicios;

import com.mycompany.ejemploquarkus.entidades.Producto;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author Alberto Jimenez
 */
@Path("/ordenes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdenResource {
    @Inject
    @RestClient
    ProductoServiceClient productClient;    
    
    @POST
    public Response createOrder(Long productId) {
        // Llamada distribuida al otro microservicio
        Producto product = productClient.getById(productId);       
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED)
                       .entity("Pedido creado para: " + product.name)
                       .build();
    }
}
