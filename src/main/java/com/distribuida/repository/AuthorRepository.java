package com.distribuida.repository;

import com.distribuida.entity.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AuthorRepository implements PanacheRepository<Author> {



}
