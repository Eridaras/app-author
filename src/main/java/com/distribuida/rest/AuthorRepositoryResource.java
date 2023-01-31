package com.distribuida.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.distribuida.entity.Author;
import com.distribuida.repository.AuthorRepository;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.quarkus.panache.common.Sort;


@Path("/authors")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AuthorRepositoryResource {
    @Inject
    AuthorRepository authorRepository;

    @GET
    public List<Author> list() {
        System.out.println("esta entrando");
        return authorRepository.listAll();
    }


    @GET @Path("{id}")
    public Author getById(@PathParam("id") Integer id){
            Author author = authorRepository.find("id",id)
                    .singleResultOptional()
                    .get();
            if(author ==null){
                throw new WebApplicationException("Author con el id: "+id + "no existe ", 404);
            }
            return author;
    }
    @POST @Transactional
    public Response create(Author author){
        if(author.getId() !=null){
            throw new WebApplicationException("Id fue invalido en la respuesta", 422);
        }
        authorRepository.persist(author);
        return Response.ok(author).status(201).build();
}

    @PUT
    @Path("{id}")
    @Transactional
    public Author update(@PathParam("id") Integer id, Author author){
        if(author.getFirst_name() == null || author.getLast_name()==null){
            throw new WebApplicationException("El nombre o apellido del Author no se tuvo respuesta ", 422);
        }

        Author entity = authorRepository.find("id",id)
                .singleResultOptional()
                .get();
        if(entity==null){
            throw new WebApplicationException("Autor no resgistrado en la base con el id: "+id);
        }
        entity.setLast_name(author.getLast_name());
        entity.setFirst_name(author.getFirst_name());
        return entity;
    }
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id){
        Author author = authorRepository.find("id",id)
                .singleResultOptional()
                .get();
        if(author==null){
            throw new WebApplicationException("Autor con id " +id+" no existe", 404);
        }
        authorRepository.delete(author);
        return Response.status(204).build();
    }

}
