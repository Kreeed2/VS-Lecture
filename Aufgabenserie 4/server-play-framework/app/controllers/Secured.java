package controllers;

import models.UserInfo;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security.Authenticator;

public class Secured extends Authenticator {

    /**
     * Determines whether or not the user is logged in
     * @param ctx The context
     * @return The username of the logged in user, null if not logged in
     */
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("username");
    }

    public static String getName(Context ctx) {
        return ctx.session().get("username");
    }

    /**
     * Automatic redirect to login page if unauthorized
     * @param ctx The context
     * @return The login page
     */
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.LoginController.login());
    }

    /**
     * True if there is a logged in user, false otherwise.
     * @param ctx The context.
     * @return True if user is logged in.
     */
    public static boolean isLoggedIn(Context ctx) {
        return (getName(ctx) != null);
    }

    /**
     * Return the UserInfo of the logged in user, or null if no user is logged in.
     * @param ctx The context.
     * @return The UserInfo, or null.
     */
    public static UserInfo getUserInfo(Context ctx) {
        return (isLoggedIn(ctx) ? UserInfo.findUserByName(getName(ctx)) : null);
    }

}