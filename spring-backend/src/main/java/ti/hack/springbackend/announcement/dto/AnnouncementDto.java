package ti.hack.springbackend.announcement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnnouncementDto {

    private final Long announcementId;
    private final String title;
    private final String content;
    private final String category;
    private final String side;
    private final String url;
    private final LocalDateTime created;
    private final boolean isValid;
    private final String location;
    private final Float latitude;
    private final Float longitude;
}
