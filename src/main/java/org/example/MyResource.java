package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
}