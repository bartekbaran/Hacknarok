package ti.hack.springbackend.utils.mapper;

import ti.hack.springbackend.announcement.Announcement;
import ti.hack.springbackend.announcement.dto.AnnouncementDto;

public class AnnouncementMapper {
    public static AnnouncementDto announcementToAnnouncementDto(final Announcement announcement) {
        return new AnnouncementDto(announcement.getAnnouncementId(), announcement.getTitle(),
                announcement.getContent(), announcement.getContent(), announcement.getSide().toString(),
                announcement.getUrl(), announcement.getCreated(), announcement.isValid(),
                announcement.getLocation(), announcement.getLatitude(), announcement.getLongitude());
    }
}
