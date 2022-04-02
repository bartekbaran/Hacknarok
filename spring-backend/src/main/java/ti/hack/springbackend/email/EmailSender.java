package ti.hack.springbackend.email;

public interface EmailSender {

    void send(String to, String email);
}
