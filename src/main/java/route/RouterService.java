package route;

public interface RouterService {

    void addRoute(String path, String result) ;
    String route(String path);
}
