package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.GroupMember;
import utils.EMF_Creator;
import facades.MemberFacade;
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
@Path("member")
public class MemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final MemberFacade FACADE = MemberFacade.getMemberFacade(EMF);
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
        long count = FACADE.getMemberCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllGroupMembers() {
        List<GroupMember> member = FACADE.getAllGroupMembers();
        return GSON.toJson(member);

    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMemberById(@PathParam ("id") long id) {
        GroupMember member = FACADE.getMemberByID(id);
        return GSON.toJson(member);
    }
    
    @Path("name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMemberByName(@PathParam ("name") String name) {
        List <GroupMember> member = FACADE.getMemberByName(name);
        return GSON.toJson(member);
    }
}
