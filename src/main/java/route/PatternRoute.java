package route;

import java.util.regex.Pattern;

public class PatternRoute extends Route {
    final Pattern pattern;

    public PatternRoute(String path, String func) {
        super(path, func);
        this.pattern = Pattern.compile(path);
    }

    public Pattern getPattern() {
        return pattern;
    }
}
