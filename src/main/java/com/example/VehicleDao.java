package com.example;
import org.springframework.stereotype.Repository;
import org.springframework.http.ResponseEntity;
import javax.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
/**
 * Data Access Object - provide some specific data operations without exposing details of the database
 * Access data for the Greeting entity.
 * Repository annotation allows Spring to find and configure the DAO.
 * Transactional annonation will cause Spring to call begin() and commit()
 * at the start/end of the method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class VehicleDao {
    //PersistenceContext annotation used to specify there is a database source.
    //EntityManager is used to create and remove persistent entity instances,
    //to find entities by their primary key, and to query over entities.
    @PersistenceContext
    private EntityManager entityManager;

    //Insert greeting into the database.
    public void add(Vehicle vehicle) {
        entityManager.persist(vehicle);
        return;
    }

    //Return the greeting with the passed-in id.
    public Vehicle getById(int id) {
        return entityManager.find(Vehicle.class, id);
    }

    //Find vehicle with given id and remove from database
    public ResponseEntity<String> deleteById(int id){
        try {
            entityManager.remove(getById(id));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    //Update vehicle in inventory
    public Vehicle updateVehicle(Vehicle vehicle){
        return entityManager.merge(vehicle);
    }
}