package be.pxl.auctions.rest;

import be.pxl.auctions.model.User;
import be.pxl.auctions.rest.resource.UserCreateResource;
import be.pxl.auctions.rest.resource.UserResource;
import be.pxl.auctions.service.UserService;
import be.pxl.auctions.util.exception.DuplicateEmailException;
import be.pxl.auctions.util.exception.InvalidDateException;
import be.pxl.auctions.util.exception.InvalidEmailException;
import be.pxl.auctions.util.exception.RequiredFieldException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
public class UserRest {
    private static final Logger LOGGER = LogManager.getLogger(UserRest.class);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<UserResource> result = userService.getAllUsers().stream().map(u -> mapToUserResource(u)).collect(Collectors.toList());
        return Response.ok(result).build();
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResource getUserById(@PathParam("userId") long userId) {
        User user = userService.getUserById(userId);
        return mapToUserResource(user);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserCreateResource userCreateResource) {
        try {
            User user = userService.createUser(mapToUser(userCreateResource));
            return Response.created(UriBuilder.fromPath("/users/" + user.getId()).build()).build();
        } catch (RequiredFieldException | InvalidEmailException | DuplicateEmailException | InvalidDateException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage()).build();
        }
    }

    private User mapToUser(UserCreateResource userCreateResource) throws InvalidDateException {
        User user = new User();
        user.setFirstName(userCreateResource.getFirstName());
        user.setLastName(userCreateResource.getLastName());
        try {
            user.setDateOfBirth(LocalDate.parse(userCreateResource.getDateOfBirth(), DATE_FORMAT));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("[" + user.getDateOfBirth() + "] is not a valid date. Excepted format: dd/mm/yyyy");
        }
        user.setEmail(userCreateResource.getEmail());
        return user;
    }

    private UserResource mapToUserResource(User user) {
        UserResource userResource = new UserResource();
        userResource.setId(user.getId());
        userResource.setFirstName(user.getFirstName());
        userResource.setLastName(user.getLastName());
        userResource.setDateOfBirth(user.getDateOfBirth());
        userResource.setAge(user.getAge());
        userResource.setEmail(user.getEmail());
        return userResource;
    }
}
