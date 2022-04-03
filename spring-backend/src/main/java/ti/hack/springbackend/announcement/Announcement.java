package ti.hack.springbackend.announcement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "announcements")
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {

    @Id
    @Column(name = "aid", nullable = false)
    @SequenceGenerator(name="announcements_aid_seq", sequenceName = "announcements_aid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "announcements_aid_seq")
    private Long announcementId;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "category")
    private AnnouncementTypeEnum category;

    @Column(name = "side", nullable = false)
    private AnnouncementSideEnum side;

    @Column(name = "external_url")
    private String url;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @Column(name = "is_valid", nullable = false)
    private boolean isValid;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    public Announcement(final String email, final String title, final String content,
                        final AnnouncementTypeEnum category, final AnnouncementSideEnum side,
                        final LocalDateTime created, final String location) {
        this.email = email;
        this.title = title;
        this.content = content;
        this.category = category;
        this.side = side;
        this.url = null;
        this.created = created;
        this.lastUpdate = null;
        this.isValid = true;
        this.location = location;
        this.latitude = null;
        this.longitude = null;
    }
}
