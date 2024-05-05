import org.junit.platform.commons.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class RouterImpl implements Router {
    Map<String, String> routes = new ConcurrentHashMap<>();

    @Override
    public void addRoute(String path, String result) {
        if (StringUtils.isBlank(path)) {
            //LOG: Log the path is null and return
            return;
        }
        if (routes.containsKey(path)) {
            //LOG: Log that we are replacing route
        }
        routes.put(path, result);
    }

    @Override
    public String route(String path) {
        if (StringUtils.isBlank(path)) {
            //LOG: Log the path is null and return
            return null;
        }
        String result = routes.get(path);
        if (result == null) {
            result = checkIfSomeWildCardRouteAvailable(path);
        }
        return result;
    }

    private String checkIfSomeWildCardRouteAvailable(String path) {
        String result = routes.entrySet().stream().filter(e -> e.getKey().contains("*")).map(regEx -> {
            //TODO: use some form of cache for patterns
            Pattern pattern = Pattern.compile(regEx.getKey());

            return pattern.matcher(path).find() ? regEx.getValue() : null;
        }).findFirst().orElse(null); //Externalize the special char

        //Return the first

        return result;
    }
}
