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
import java.util.UUID;

public class MessageController extends Controller {

    @Inject
    private FormFactory formFactory;

    //get all messages
    public Result index() {
        List<Message> messages = Message.finder.all();

        return ok(index.render("Main", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), messages, session("userTime")));
    }

    //create a message
    @Security.Authenticated(Secured.class)
    public Result create() {
        Form<Message> messageForm = formFactory.form(Message.class);
        return ok(create.render("Create", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), messageForm, session("userTime")));
    }

    //save a book
    @Security.Authenticated(Secured.class)
    public Result save() {
        Form<Message> messageForm = formFactory.form(Message.class).bindFromRequest();
        if(messageForm.hasErrors())
            return badRequest(create.render("Save", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), messageForm, session("userTime")));

        Message message = messageForm.get();
        message.creator = UUID.fromString(session("userId"));
        message.save();

        return redirect(routes.MessageController.index());
    }

    //edit a message based on id
    @Security.Authenticated(Secured.class)
    public Result edit(Integer id) {
        Message message = Message.finder.byId(id);

        if (message ==  null)
            return notFound("Message not found");
        if (!message.creator.equals(UUID.fromString(session("userId"))))
            return notAcceptable("Not own bulletin");

        Form<Message> messageForm = formFactory.form(Message.class).fill(message);

        session("messageId", id.toString());
        return ok(edit.render("Edit", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), messageForm, session("userTime")));
    }

    //update edited message into database
    @Security.Authenticated(Secured.class)
    public Result update() {
        Message message = formFactory.form(Message.class).bindFromRequest().get();
        //Check to make sure we can edit an existing message
        if (!session().containsKey("messageId")) {
            return redirect(routes.MessageController.index());
        }

        //Get the message reference out of the DB
        Message oldMessage = Message.finder.byId(Integer.valueOf(session("messageId")));
        if (oldMessage == null)
            return notFound("Message not found");
        if (!oldMessage.creator.equals(UUID.fromString(session("userId"))))
            return notAcceptable("Not own bulletin");

        oldMessage.author = message.author;
        oldMessage.message = message.message;
        oldMessage.version++;
        oldMessage.update();

        session().remove("messageId");
        return redirect(routes.MessageController.index());
    }

    //delete message based on id
    @Security.Authenticated(Secured.class)
    public Result delete(Integer id) {
        Message message = Message.finder.byId(id);

        if (message == null)
            return notFound("Message not found");
        if (!message.creator.equals(UUID.fromString(session("userId"))))
            return notAcceptable("Not own bulletin");

        message.delete();

        return redirect(routes.MessageController.index());
    }

    //show message details
    public Result show(Integer id) {
        Message message = Message.finder.byId(id);

        if (message == null)
            return notFound("Message not found");

        return ok(show.render("Show", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), message, session("userTime")));
    }
}
