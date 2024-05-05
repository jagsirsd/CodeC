package route;

import org.junit.platform.commons.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RouterServiceImpl implements RouterService {
    Map<String, Route> routes = new HashMap<>();
    Map<String, PatternRoute> patternRoutes = new HashMap<>();

    @Override
    public void addRoute(String path, String result) {
        if (StringUtils.isBlank(path)) {
            //LOG: Log the path is null and return
            return;
        }
        if (routes.containsKey(path)) {
            //LOG: Log that we are replacing route
        }
        if(path.contains("*")) {
            patternRoutes.put(path, new PatternRoute(path, result));
        } else {
            routes.put(path, new Route(path, result));
        }
    }

    @Override
    public String route(String path) {
        if(StringUtils.isBlank(path)) {
            return null;
        }
        Route route = routes.get(path);
        if(route == null) {
            route = checkIfSomeWildCardRouteAvailable(path);
        }
        String result = null;
        if(route != null) {
            result = route.getFunc();
        }
        return result;
    }

    private PatternRoute checkIfSomeWildCardRouteAvailable(String path) {
        PatternRoute result = patternRoutes.values().stream().map(patternRoute -> {
            //TODO: use some form of cache for patterns
            Pattern pattern = patternRoute.getPattern();

            return pattern.matcher(path).find() ? patternRoute : null;
        }).findFirst().orElse(null); //Externalize the special char

        //Return the first

        return result;
    }
}
