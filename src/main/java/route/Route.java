package route;

public class Route {
    private final String path;
    private final String func;

    public Route(String path, String func) {
        this.path = path;
        this.func = func;
    }

    public String getPath() {
        return path;
    }

    public String getFunc() {
        return func;
    }
}
