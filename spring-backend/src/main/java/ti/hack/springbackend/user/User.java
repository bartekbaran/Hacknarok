package ti.hack.springbackend.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    public User(String email, String nickname, String password, String role, Date created, Date lastLogin, boolean confirmed) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.created = created;
        this.lastLogin = lastLogin;
        this.confirmed = confirmed;
    }

    @Id
    @Column(name = "uid", nullable = false)
    @SequenceGenerator(name="users_uid_seq", sequenceName = "users_uid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_uid_seq")
    private Long userId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "confirmed", columnDefinition = "boolean default false")
    private boolean confirmed;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return confirmed;
    }
}
