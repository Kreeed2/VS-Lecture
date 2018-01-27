package controllers;

import models.UserInfo;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formData.RegisterFormData;
import views.html.authentification.login;
import views.html.authentification.profile;
import views.html.authentification.register;

import javax.inject.Inject;

public class LoginController extends Controller {

    @Inject
    private FormFactory formFactory;

    /**
     * Provides the Login page (only to unauthenticated users).
     *
     * @return The Login page.
     */
    public Result login() {
        Form<UserInfo> dataForm = formFactory.form(UserInfo.class);
        return ok(login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), dataForm, session("userTime")));
    }

    /**
     * Provides the Register page (only to unauthenticated users).
     *
     * @return The register page.
     */
    public Result register() {
        Form<RegisterFormData> dataForm = formFactory.form(RegisterFormData.class);
        return ok(register.render("Register", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), dataForm, session("userTime")));
    }


    /**
     * Processes a registration form submission from an unauthenticated user.
     * First we bind the HTTP POST data to an instance of RegisterFormData.
     * The binding process will invoke the RegisterFormData.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     *
     * @return The index page with the results of validation.
     */
    public Result postRegister() {
        // Get the submitted form data from the request object, and run validation.
        Form<RegisterFormData> formData = formFactory.form(RegisterFormData.class).bindFromRequest();

        if (formData.hasErrors()) {
            flash("error", "Login credentials not valid.");
            return badRequest(register.render("Register", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, session("userTime")));
        } else {
            // username/password OK, so now we set the session variable and only go to authenticated pages.
            UserInfo newUser = new UserInfo();
            newUser.password = formData.get().password;
            newUser.username = formData.get().username;
            newUser.save();

            session().clear();
            session("userId", newUser.uuid.toString());
            return redirect(routes.LoginController.profile());
        }
    }

    /**
     * Processes a login form submission from an unauthenticated user.
     * First we bind the HTTP POST data to an instance of LoginFormData.
     * The binding process will invoke the LoginFormData.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     *
     * @return The index page with the results of validation.
     */
    public Result postLogin() {

        // Get the submitted form data from the request object, and run validation.
        Form<UserInfo> formData = formFactory.form(UserInfo.class).bindFromRequest();

        if (formData.hasErrors()) {
            flash("error", "Login credentials not valid.");
            return badRequest(login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, session("userTime")));
        } else {
            // username/password OK, so now we set the session variable and only go to authenticated pages.
            session().remove("userId");
            UserInfo currUser = UserInfo.findUserByName(formData.get().username);
            if (currUser != null) {
                session("userId", currUser.uuid.toString());
            } else
                return badRequest(login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, session("userTime")));


            return redirect(routes.LoginController.profile());
        }
    }

    /**
     * Logs out (only for authenticated users) and returns them to the Index page.
     *
     * @return A redirect to the Index page.
     */
    @Security.Authenticated(Secured.class)
    public Result logout() {
        session().remove("userId");
        return redirect(routes.MessageController.index());
    }

    /**
     * Provides the Profile page (only to authenticated users).
     *
     * @return The Profile page.
     */
    @Security.Authenticated(Secured.class)
    public Result profile() {
        return ok(profile.render("Profile", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), session("userTime")));
    }
}
