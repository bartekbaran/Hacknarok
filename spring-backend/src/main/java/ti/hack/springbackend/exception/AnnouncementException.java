package ti.hack.springbackend.exception;

public class AnnouncementException extends RuntimeException {

    public AnnouncementException(final String message) {
        super(message);
    }

    public static class AnnoucementNotFoundException extends AnnouncementException {
        public AnnoucementNotFoundException(final Long announcementId) {
            super(String.format("Announcement with id=%s doesn't exist", announcementId));
        }
    }
}
