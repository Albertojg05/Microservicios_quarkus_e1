package com.mycompany.ejemploquarkus.servicios;

import com.mycompany.ejemploquarkus.entidades.Producto;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Alberto Jimenez
 */
@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoResource {

    @GET
    public List<Producto> obtenerTodos() {
        return Producto.listAll();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Producto producto = Producto.findById(id);
        if (producto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(producto).build();
    }

    @POST
    @Transactional
    public Response crear(Producto producto) {
        producto.persist();
        return Response.status(Response.Status.CREATED).entity(producto).build();
    }
}
