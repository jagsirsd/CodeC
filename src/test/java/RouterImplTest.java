
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RouterImplTest {
    //Usage:
//// setup routes
//        router.addRoute("/bar", "barFunc")
//router.addRoute("/foo", "fooFunc")
//
//// query for route
//router.route("/bar") -> "barFunc"
    @Test
    public void checkRouteIsAdded() {
        Router router = new RouterImpl();
        router.addRoute("/bar", "barFunc");
        router.addRoute("/foo", "fooFunc");

        String actualReturnedRoute = router.route("/bar");
        Assertions.assertEquals("barFunc", actualReturnedRoute);
    }

    @Test
    public void checkRouteIsAddedAsWildCard() {
        Router router = new RouterImpl();

        router.addRoute("/foo", "fooFunc");
        router.addRoute("/far*", "fooFuncReg");

//        router.route("/far") -> "farFunc"
//        router.route("/farXyz") -> "farFunc"
//        router.route("/farAbc") -> "farFunc"

        String actualReturnedRoute = router.route("/farXyz");
        Assertions.assertEquals("fooFuncReg", actualReturnedRoute);

        actualReturnedRoute = null;
        actualReturnedRoute = router.route("/farAbc");
        Assertions.assertEquals("fooFuncReg", actualReturnedRoute);
    }

    @Test
    public void checkRouteNullForNull() {
        Router router = new RouterImpl();

        String actualReturnedRoute = router.route("/bar");
        Assertions.assertNull(actualReturnedRoute);
    }


    @Test
    public void checkRouteNullForEmptyOrNull() {
        Router router = new RouterImpl();

        router.addRoute("", "");
        router.addRoute(null, "");

        String actualReturnedRoute =router.route("");
        Assertions.assertNull(actualReturnedRoute);

        actualReturnedRoute =router.route(null);
        Assertions.assertNull(actualReturnedRoute);

    }
}