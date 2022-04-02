package ti.hack.springbackend.utils;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String>{
    private static final String REGEX_FOR_EMAIL_VALIDATION = "^(.+)@(.+)$";

    @Override
    public boolean test(String email) {

        Pattern pattern = Pattern.compile(REGEX_FOR_EMAIL_VALIDATION);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
