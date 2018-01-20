package controllers;

import models.Message;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.message.create;
import views.html.message.edit;
import views.html.message.index;
import views.html.message.show;

import javax.inject.Inject;
import java.util.List;

public class MessageController extends Controller {

    @Inject
    private FormFactory formFactory;

    //get all messages
    public Result index() {
        List<Message> messages = Message.finder.all();
        return ok(index.render("Main", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), messages));
    }

    //create a message
    @Security.Authenticated(Secured.class)
    public Result create() {
        Form<Message> messageForm = formFactory.form(Message.class);
        return ok(create.render("Create", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), messageForm));
    }

    //save a book
    @Security.Authenticated(Secured.class)
    public Result save() {
        Form<Message> messageForm = formFactory.form(Message.class).bindFromRequest();
        if(messageForm.hasErrors())
            return badRequest(create.render("Save", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), messageForm));

        Message message = messageForm.get();
        message.creator = session("username");
        message.save();

        return redirect(routes.MessageController.index());
    }

    //edit a message based on id
    @Security.Authenticated(Secured.class)
    public Result edit(Integer id) {
        Message message = Message.finder.byId(id);
        if (message ==  null)
            return notFound("Message not found");
        if (!message.creator.equals(session("username")))
            return notAcceptable("Not own bulletin");

        Form<Message> messageForm = formFactory.form(Message.class).fill(message);

        session("id", id.toString());
        return ok(edit.render("Edit", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), messageForm));
    }

    //update edited message into database
    @Security.Authenticated(Secured.class)
    public Result update() {
        Message message = formFactory.form(Message.class).bindFromRequest().get();
        if (!session().containsKey("id")) {
            return redirect(routes.MessageController.index());
        }
        if (!message.creator.equals(session("username")))
            return notAcceptable("Not own bulletin");

        Message oldMessage = Message.finder.byId(Integer.valueOf(session("id")));
        if (oldMessage == null)
            return notFound("Message not found");
        oldMessage.author = message.author;
        oldMessage.message = message.message;
        oldMessage.version++;
        oldMessage.update();

        return redirect(routes.MessageController.index());
    }

    //delete message based on id
    @Security.Authenticated(Secured.class)
    public Result delete(Integer id) {
        Message message = Message.finder.byId(id);
        if (message == null)
            return notFound("Message not found");
        if (!message.creator.equals(session("username")))
            return notAcceptable("Not own bulletin");
        message.delete();

        return redirect(routes.MessageController.index());
    }

    //show message details
    public Result show(Integer id) {
        Message message = Message.finder.byId(id);
        if (message == null)
            return notFound("Message not found");

        return ok(show.render("Show", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), message));
    }
}
