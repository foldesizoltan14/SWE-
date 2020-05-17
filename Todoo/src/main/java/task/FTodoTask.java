package task;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity

public class FTodoTask {

    @Column

    private String userId;

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    @Temporal(TemporalType.DATE)
    private Date created;

    @Column
    private String description;

    @Column
    private boolean isitdone;


}

