package ti.hack.springbackend.announcement.dto;

import lombok.Data;
import ti.hack.springbackend.announcement.AnnouncementSideEnum;
import ti.hack.springbackend.announcement.AnnouncementTypeEnum;

@Data
public class CreateAnnouncementDto {

    private final Long userId;
    private final String title;
    private final String content;
    private final AnnouncementTypeEnum category;
    private final AnnouncementSideEnum side;
    private final String location;

}
