package user;

import util.jpa.GenericJpaDao;

import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public List<FTodoUser> findUser(String name) {
        return entityManager.createQuery("SELECT r FROM FTodoUser r WHERE r.name = name ", FTodoUser.class)
                .getResultList();
    }

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