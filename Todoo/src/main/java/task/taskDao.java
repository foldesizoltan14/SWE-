package task;

import util.jpa.GenericJpaDao;

import javax.persistence.Persistence;
import java.util.List;

public class taskDao extends GenericJpaDao<FTodoTask> {
    private static taskDao instance;

    private taskDao() {
        super(FTodoTask.class);
    }

    public static taskDao getInstance() {
        if (instance == null) {
            instance = new taskDao();
            instance.setEntityManager(Persistence.createEntityManagerFactory("name").createEntityManager());
        }
        return instance;
    }

    public List<FTodoTask> findAll() {
        return entityManager.createQuery("SELECT r FROM FTodoTask r",FTodoTask.class)
       .getResultList();
    }

    public List<FTodoTask> findUsersTasks(String id) {


     return entityManager.createQuery("SELECT r FROM FTodoTask r WHERE r.userId = :id AND r.created = CURRENT_DATE ", FTodoTask.class)
        .setParameter("id", id).getResultList();



    }
    public List<FTodoTask> findUsersPreviousTasks(String id) {


        return entityManager.createQuery("SELECT r FROM FTodoTask r WHERE r.userId = :id AND r.created != CURRENT_DATE ", FTodoTask.class)
                .setParameter("id", id).getResultList();



    }










}
