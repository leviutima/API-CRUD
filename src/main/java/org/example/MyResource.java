package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.Carta;
import org.example.models.Colecao;

import java.util.ArrayList;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("colecao-de-cartas")
    @Produces(MediaType.APPLICATION_JSON)
    public Colecao getColecao() {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(1,"Geralt de rivia", "bruxo", 15000));
        cartas.add(new Carta(2, "Ornn", "Deus forja", 1000000));
        cartas.add(new Carta(3, "Levi", "Aprendendo Java",15000));
        return new Colecao(1,"cartas-1", cartas);
    }

    @POST
    @Path("colecao-de-cartas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void postColecao(Carta carta){
        System.out.println(carta.toString());
    }

    @PUT
    @Path("colecao-de-cartas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Carta updateColecao(@PathParam("id")int id, Carta carta){
        return carta;
    }

    @DELETE
    @Path("colecao-de-cartas/{id}")
    public Response deleteCarta (@PathParam("id")int id){
        return Response.status(201).build();
    }
}
