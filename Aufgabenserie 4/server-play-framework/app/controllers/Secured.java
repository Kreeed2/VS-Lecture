package controllers;

import models.UserInfo;
import play.api.Configuration;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security.Authenticator;

import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

public class Secured extends Authenticator {

    @Inject
    private Configuration config;

    private static String getName(Context ctx) {
        String userId = ctx.session().get("userId");
        if (userId != null) {
            UserInfo currUser = UserInfo.findUserById(UUID.fromString(userId));
            return currUser != null ? currUser.username : null;
        } else {
            return null;
        }
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
     *
     * @param ctx The context.
     * @return True if user is logged in.
     */
    public static boolean isLoggedIn(Context ctx) {
        return (getName(ctx) != null);
    }

    /**
     * Return the UserInfo of the logged in user, or null if no user is logged in.
     *
     * @param ctx The context.
     * @return The UserInfo, or null.
     */
    public static UserInfo getUserInfo(Context ctx) {
        return (isLoggedIn(ctx) ? UserInfo.findUserByName(getName(ctx)) : null);
    }

    /**
     * Determines whether or not the user is logged in (used for the Authenticate annotation)
     *
     * @param ctx The context
     * @return The username of the logged in user, null if not logged in
     */
    @Override
    public String getUsername(Context ctx) {
        //See if the user is logged in
        if (ctx.session().get("userId") == null)
            return null;

        //See if session is expired
        String previousTick = ctx.session().get("userTime");
        if (previousTick != null && !previousTick.equals("")) {
            long prevTick = Long.valueOf(previousTick);
            long currTick = new Date().getTime();

            long timeout = (long) config.getLong("play.http.session.maxAge").get();

            if ((currTick - prevTick) > timeout) {
                //session expired
                ctx.session().clear();
                return null;
            }
        }

        //Update time in session
        String tickString = Long.toString(new Date().getTime());
        ctx.session().put("userTime", tickString);

        UserInfo currUser = UserInfo.findUserById(UUID.fromString(ctx.session().get("userId")));
        return currUser != null ? currUser.username : null;
    }

}