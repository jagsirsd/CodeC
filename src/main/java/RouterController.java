import route.RouterService;

public class RouterController implements Router {
    final RouterService routerService;

    public RouterController(RouterService routerService) {
        this.routerService = routerService;
    }

    @Override
    public void addRoute(String path, String result) {
        routerService.addRoute(path, result);
    }

    @Override
    public String route(String path) {
        return routerService.route(path);
    }

}
