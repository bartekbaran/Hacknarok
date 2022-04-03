package ti.hack.springbackend.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConfigEnum {

    HOSTNAME("http://localhost:8080");

    private final String value;
}
