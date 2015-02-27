/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Michael
 */
@Path("{part}")
public class RestAPI {

    List<Player> playerNames = new ArrayList<>();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestAPI
     */
    public RestAPI() {
        playerNames.add(new Player(1, "James Rodriguez", "pas"));
        playerNames.add(new Player(2, "Thomas Mueller", "pas"));
        playerNames.add(new Player(3, "Messi", "pas"));
        playerNames.add(new Player(4, "Neymar", "pas"));
        playerNames.add(new Player(5, "van Persie", "pas"));
    }

    /**
     * Retrieves representation of an instance of rest.RestAPI
     *
     * @param part
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getJson(@PathParam("part") String part) {
        if (part.equalsIgnoreCase("AllPlayerNames")) {
            return getAllPlayers();
        } else {
            return "Page not found";
        }
    }

    /**
     * Retrieves representation of an instance of rest.RestAPI
     *
     * @param id
     * @param part
     * @return an instance of java.lang.String
     */
    @Path("{id}")
    @GET
    @Produces("text/plain")
    public String getJson(@PathParam("part") String part, @PathParam("id") String id) {
        if (part.equalsIgnoreCase("player")) {
            return getPlayer(Integer.parseInt(id));
        } else {
            return "Page not found";
        }
    }

    private String getAllPlayers() {
        String jsonText = "";
        StringBuilder sb = new StringBuilder(jsonText);

        //{ "id": 1, "name": "James Rodríguez", “Country”: “Columbia”},
        sb.append("[");
        for (Player p : playerNames) {
            sb.append("{");
            sb.append("\"id\": " + p.getId());
            sb.append(", \"name\": " + p.getName());
            sb.append(", \"country\": " + p.getCountry());
            sb.append("}");
            sb.append(",");
        }

        jsonText = sb.toString();
        jsonText = jsonText.substring(0, jsonText.lastIndexOf(","));
        jsonText += "]";

        return jsonText;
    }

    private String getPlayer(int id) {
        String jsonText = "";
        StringBuilder sb = new StringBuilder(jsonText);
        Player p = null;
        for (Player existingP : playerNames) {
            if (existingP.getId() == id) {
                p = existingP;
            }
        }
        if (p == null) {
            jsonText = "{\"errCode\": 404, \"errMsg\" : \"No player found with the given ID\" }";
        } else {
            sb.append("{");
            sb.append("\"id\": " + p.getId());
            sb.append(", \"name\": " + p.getName());
            sb.append(", \"country\": " + p.getCountry());
            sb.append("}");
            jsonText = sb.toString();
        }

        return jsonText;
    }

    /**
     * PUT method for updating or creating an instance of RestAPI
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putXml(String content) {
    }
}
