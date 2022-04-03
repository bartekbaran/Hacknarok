package ti.hack.springbackend.announcement;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ti.hack.springbackend.announcement.dto.AnnouncementDto;
import ti.hack.springbackend.announcement.dto.CreateAnnouncementDto;
import ti.hack.springbackend.announcement.dto.EditAnnouncementDto;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/ancmnt")
@AllArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    public List<AnnouncementDto> getAllAnnouncement() {
        return this.announcementService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> createAnnouncement(final Authentication authentication,
                                                final CreateAnnouncementDto createAnnouncementDto) {
        this.announcementService.createAnnouncement(authentication, createAnnouncementDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Announcement created successfully");
    }

    @PostMapping("edit")
    public ResponseEntity<?> editAnnouncement(final Authentication authentication,
                                              final EditAnnouncementDto editAnnouncementDto) {
        this.announcementService.editAnnouncement(authentication, editAnnouncementDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Announcement edited successfully");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAnnouncement(final Authentication authentication,
                                                final Long announcementId) {
        this.announcementService.deleteAnnouncement(authentication, announcementId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Announcement deleted successfully");
    }
}
