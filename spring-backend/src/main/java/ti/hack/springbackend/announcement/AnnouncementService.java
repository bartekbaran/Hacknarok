package ti.hack.springbackend.announcement;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ti.hack.springbackend.announcement.dto.AnnouncementDto;
import ti.hack.springbackend.announcement.dto.CreateAnnouncementDto;
import ti.hack.springbackend.announcement.dto.EditAnnouncementDto;
import ti.hack.springbackend.exception.AnnouncementException;
import ti.hack.springbackend.exception.UserException;
import ti.hack.springbackend.utils.mapper.AnnouncementMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnnouncementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementService.class);

    private final AnnouncementRepository announcementRepository;

    public List<AnnouncementDto> getAll() {
        return this.announcementRepository.findAll().stream()
                .map(AnnouncementMapper::announcementToAnnouncementDto)
                .collect(Collectors.toList());
    }

    public void createAnnouncement(final Authentication authentication,
                                   final CreateAnnouncementDto createAnnouncementDto) {
        final Announcement announcement = new Announcement(authentication.getName(),
                        createAnnouncementDto.getTitle(), createAnnouncementDto.getContent(),
                        createAnnouncementDto.getCategory(), createAnnouncementDto.getSide(),
                        LocalDateTime.now(), createAnnouncementDto.getLocation());

        LOGGER.info("createAnnouncement() created new announcement={}", announcement);
        this.announcementRepository.save(announcement);

        LOGGER.debug("createAnnouncement() announcement saved to the database");
    }

    public void editAnnouncement(final Authentication authentication,
                                 final EditAnnouncementDto editAnnouncementDto) {

        final Optional<Announcement> optionalAnnouncement = this.announcementRepository.findByAnnouncementId(editAnnouncementDto.getAnnouncementId());

        if (optionalAnnouncement.isEmpty()) {
            LOGGER.info("editAnnouncement() announcement with id={}, doesn't exist", editAnnouncementDto.getAnnouncementId());
            throw new AnnouncementException.AnnoucementNotFoundException(editAnnouncementDto.getAnnouncementId());
        }

        final Announcement oldAnnouncement = optionalAnnouncement.get();

        if (!oldAnnouncement.getEmail().equals(authentication.getName())) {
            LOGGER.warn("editAnnouncement() logged user cannot edit announcement with id={}", oldAnnouncement.getAnnouncementId());
            throw new UserException.InvalidPermissionsException();
        }

        this.announcementRepository.editAnnouncement(editAnnouncementDto.getAnnouncementId(), editAnnouncementDto.getContent(),
                editAnnouncementDto.getCategory().toString(), editAnnouncementDto.getSide().toString(), editAnnouncementDto.getUrl(),
                editAnnouncementDto.isValid(), editAnnouncementDto.getLocation(), editAnnouncementDto.getLatitude(),
                editAnnouncementDto.getLongitude(), editAnnouncementDto.getAnnouncementId());
        LOGGER.info("editAnnouncement() edit announcement by {}", editAnnouncementDto);
    }

    public void deleteAnnouncement(final Authentication authentication,
                                   final Long announcementId) {
        LOGGER.info("deleteAnnouncement() delete announcement with id={}", announcementId);

        final Optional<Announcement> optionalAnnouncement = this.announcementRepository.findByAnnouncementId(announcementId);
        if (optionalAnnouncement.isEmpty()) {
            LOGGER.info("deleteAnnouncement() there's no announcement with id={}", announcementId);
            throw new AnnouncementException.AnnoucementNotFoundException(announcementId);
        }

        if (!optionalAnnouncement.get().getEmail().equals(authentication.getName())) {
            LOGGER.info("deleteAnnouncement() logged user cannot change this post");
            throw new UserException.InvalidPermissionsException();
        }

        this.announcementRepository.deleteAnnouncementByAnnouncementId(announcementId);
    }
}
