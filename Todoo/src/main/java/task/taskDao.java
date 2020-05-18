package task;

import util.jpa.GenericJpaDao;

import javax.persistence.Persistence;
import java.util.List;

/**
 * DAO class for the {@link FTodoTask} entity
 */

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


    /**
     * Returns the list of every tasks of a person with the id of {@code n} on that day
     *
     * @param id the users id
     * @return every task on that day with the id
     */


    public List<FTodoTask> findUsersTasks(String id) {


        return entityManager.createQuery("SELECT r FROM FTodoTask r WHERE r.userId = :id AND r.created = CURRENT_DATE ", FTodoTask.class)
                .setParameter("id", id).getResultList();


    }

    /**
     * Returns the list of every tasks of a person with the id of {@code n} on every previous  days
     *
     * @param id the users id
     * @return every task with the id on every previous day
     */
    public List<FTodoTask> findUsersPreviousTasks(String id) {


        return entityManager.createQuery("SELECT r FROM FTodoTask r WHERE r.userId = :id AND r.created != CURRENT_DATE ", FTodoTask.class)
                .setParameter("id", id).getResultList();


    }

    public int days(String id) {


        return entityManager.createQuery("SELECT COUNT(*) FROM FTodoTask r WHERE r.userId = :id GROUP BY r.created,r.userId,r.id ", Long.class)
                .setParameter("id", id).getResultList().size();





    }
}
