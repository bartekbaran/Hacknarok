package ti.hack.springbackend.user.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="token")
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name="token_id_seq", sequenceName = "token_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_id_seq")
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "expires", nullable = false)
    private LocalDateTime expires;

    @Column(name = "confirmed")
    private LocalDateTime confirmed;

    @Column(name = "uid", nullable = false)
    private Long uid;

    ConfirmationToken(String token, LocalDateTime created, LocalDateTime expires, LocalDateTime confirmed, Long uid){
        this.token = token;
        this.created = created;
        this.expires = expires;
        this.confirmed = confirmed;
        this.uid = uid;
    }
}
