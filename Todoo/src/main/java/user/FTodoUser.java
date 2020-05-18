package user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class FTodoUser {

        /**
         * The users id
         */
        @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
        private int userId;
        /**
         * The name of the user
         */
        @Column(unique = true)
        private String name;


        public int getUserId() {
                return userId;
        }
}
