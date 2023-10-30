package it.gdgpt.boundaries;

import it.gdgpt.control.ApplicationControl;
import it.gdgpt.dto.LoanApplication;
import it.gdgpt.dto.LoanResponse;
import it.gdgpt.dto.Person;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/loans")
@RequestScoped
public class LoanResource {

    @Inject
    ApplicationControl applicationControl;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response apply(LoanApplication loanApplication){
        LoanResponse loanResponse = applicationControl.check(loanApplication);
        return Response.ok(loanResponse).build();
    }

    @POST
    @Path("/persons")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response extract(LoanApplication loanApplication){
        Person person = applicationControl.extractPerson(loanApplication);
        return Response.ok(person).build();
    }
}
