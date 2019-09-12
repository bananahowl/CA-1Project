package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Joke;
import facades.JokeFacade;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("Joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final JokeFacade FACADE = JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMemberCount() {
        long count = FACADE.getJokeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllGroupMembers() {
        List<Joke> meme = FACADE.getAllJokes();
        return GSON.toJson(meme);

    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMemberById(@PathParam("id") long id) {
        Joke meme = FACADE.getJokeByID(id);
        return GSON.toJson(meme);
    }

    @Path("name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeByName(@PathParam("name") String name) {
        List<Joke> meme = FACADE.getJokeByName(name);
        return GSON.toJson(meme);
    }

    @Path("/populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {

        FACADE.populate();
        return "{\"msg\":\"Done\"}";
    }

    @Path("random")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandomJoke() {
        List<Joke> all = FACADE.getAllJokes();  
        return GSON.toJson(all.get((int) Math.ceil(Math.random() * all.size()) - 1));
    }
}
