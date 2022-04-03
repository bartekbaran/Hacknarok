package ti.hack.springbackend.announcement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    Optional<Announcement> findByAnnouncementId(final Long announcementId);

    @Transactional
    @Modifying
    @Query("UPDATE Announcement a SET a.title = ?1, a.category = ?2, a.side = ?3, " +
            "a.url = ?4, a.isValid = ?5, a.location = ?6, a.latitude = ?7, " +
            "a.longitude = ?8 WHERE a.announcementId = ?9")
    void editAnnouncement(final Long title, final String content, final String category, final String side,
                          final String url, final boolean isValid, final String location,
                          final Float latitude, final Float longitude, final Long announcementId);
}
