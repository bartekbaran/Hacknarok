package ti.hack.springbackend.config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ConfigEnum {

    HOSTNAME("https://localhost:8080");

    private final String value;
}
