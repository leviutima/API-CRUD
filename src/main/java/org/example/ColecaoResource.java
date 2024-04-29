package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.models.Modal.Carta;
import org.example.models.Repository.ColecaoRepository;

import java.util.List;

@Path("carta")
public class ColecaoResource{

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

    @PUT
    @Path("{id}")
    public Response udapdateCarta (@PathParam("id")int id, Carta carta){
        carta.setId(id);
            colecaoRepo.updateCarta(carta);
                Response.status(404).build();
            return Response.status(201).entity(carta).build();
        }

    @DELETE
    @Path("{id}")
    public Response deleteCarta(@PathParam("id")int id, Carta carta){
        colecaoRepo.deleteCarta(id);
            Response.status(201).build();
        return Response.status(404).build();
    }
}

