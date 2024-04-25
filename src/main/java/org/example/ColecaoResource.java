package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.example.models.Modal.Carta;
import org.example.models.Repository.ColecaoRepository;

import java.util.List;

@Path("carta")
public class ColecaoResource {

    ColecaoRepository colecaoRepo = new ColecaoRepository();

    @GET
    public List<Carta>getCartas(){
        return colecaoRepo.getCartas();
    }

    @GET
    @Path("{id}")
    public Response getCarta(@PathParam("id") int id){
        var carta = colecaoRepo.getCarta(id);
        if (carta == null)
            Response.status(404).build();
        return Response.status(201).entity(carta).build();
    }

    @POST
    public Response createCarta(Carta carta){
        if (carta == null)
            Response.status(404).entity("A CARTA NÃO PODE SER NULA").build();
        colecaoRepo.createCarta(carta);
        return Response.status(201).entity(carta).build();
    }
}
