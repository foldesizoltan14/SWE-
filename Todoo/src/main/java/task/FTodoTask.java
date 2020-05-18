package task;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity

/**
 * This is the class which represents a task of the user
 */
public class FTodoTask {

    /**
     * The users id
     */
    @Column
    private String userId;

    /**
     * The task id
     */

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the task
     */
    @Column
    private String name;

    /**
     * The date of the creation
     */
    @Column
    @Temporal(TemporalType.DATE)
    private Date created;

    /**
     * The description of the task
     */
    @Column
    private String description;




}

