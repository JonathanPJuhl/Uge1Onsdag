/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Jonathan
 */
@Path("animals_db")
public class AnimalFromDB {
private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
/**
 *Creates the @Random variable here, because instantiating it within the method 
 * will result in a number based on whatever seed is chosen at the time, 
 * which, depending on system, can give decidedly non-random values 
 */
Random rand = new Random();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalFromDB
     * @return an instance of java.lang.String
     */
    
@Path("animals")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimals() {
  EntityManager em = emf.createEntityManager();
  try{
      TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
      List<Animal> animals = query.getResultList();
      return new Gson().toJson(animals);
   } finally {
          em.close();
   }
}
@Path("animalbyid/{id}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimals2(@PathParam("id") int id) {
  EntityManager em = emf.createEntityManager();
  try{
      Animal animal = em.find(Animal.class, id);
      if(animal!=null){
      return new Gson().toJson(animal);}
      else{
          return null;
      }
   } finally {
          em.close();
   }
}
@Path("animalbytype/{type}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimals2(@PathParam("type") String type) {
  EntityManager em = emf.createEntityManager();
  try{
      TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type=:type", Animal.class);
      query.setParameter("type", type);
      List<Animal> animals = query.getResultList();
      if(animals!=null){
      return new Gson().toJson(animals);}
      else{
          return null;
      }
   } finally {
          em.close();
   }
}
@Path("randomanimal")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimals3() {
  EntityManager em = emf.createEntityManager();
  try{
      TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
      List<Animal> animals = query.getResultList();
      int size = animals.size();
      int max = size;
      
      int id = rand.nextInt(max);
      Animal animal = em.find(Animal.class, id);
      if(animals.size()!=0){
      return new Gson().toJson(animal);}
      else{
          return null;
      }
   } finally {
          em.close();
   }
}

    
}
