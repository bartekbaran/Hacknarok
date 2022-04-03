package ti.hack.springbackend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(final String nickname);
    Optional<User> findByEmail(final String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a SET a.confirmed = TRUE WHERE a.userId = ?1")
    void enableAppUser(final Long userId);

    @Transactional
    @Modifying
    @Query("Update User a SET a.lastLogin = ?1 WHERE a.userId = ?2")
    void updateLastLogin(final LocalDateTime localDate, final Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE User a SET a.password = ?2 WHERE a.email = ?1")
    void updatePassword(final String email, final String newPassword);
}
