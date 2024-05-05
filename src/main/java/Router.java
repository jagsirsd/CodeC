public interface Router {
    /**
     * Add route to already available routes
     *
     * @param path
     * @param result
     * @return
     */
    void addRoute(String path, String result);

    /**
     * Search for path and return a route if available/set
     *
     * @param path
     * @return
     */
    String route( String path) ;

}

//Usage:
//// setup routes
//        router.addRoute("/bar", "barFunc")
//router.addRoute("/foo", "fooFunc")
//
//// query for route
//router.route("/bar") -> "barFunc"