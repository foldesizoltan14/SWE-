package user;

import util.jpa.GenericJpaDao;

import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO class for {@link FTodoUser} entity
 */
public class userDao extends GenericJpaDao<FTodoUser> {


 private static userDao instance;

 private userDao() {super(FTodoUser.class);}

 public static userDao getInstance(){
     if(instance==null){
         instance= new userDao();
         instance.setEntityManager(Persistence.createEntityManagerFactory("name").createEntityManager());
     }
     return instance;
 }

    /**
     * Find a user by name
     * @param name the users name
     * @return the user
     */

    public List<FTodoUser> findUser(String name) {
        return entityManager.createQuery("SELECT r FROM FTodoUser r WHERE r.name = name ", FTodoUser.class)
                .getResultList();
    }


    /**
     * Finds the user by name if its already in tha database
     * @param name the users name
     * @return The user we are looking for, if he/she is  already int the database or null if he/she isn't
     */
    public FTodoUser findUserId(String name) {


    TypedQuery<FTodoUser> query =entityManager.createQuery("SELECT r FROM FTodoUser r WHERE r.name = :name ", FTodoUser.class).setParameter("name", name);
        List query2=  query.getResultList();

    if(query2.isEmpty()) {
        return null;
    }else {
        return query.getSingleResult();
    }


    }




}