package controllers;

import models.Message;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.create;
import views.html.edit;
import views.html.index;
import views.html.show;

import javax.inject.Inject;
import java.util.List;

public class MessageController extends Controller {

    @Inject
    FormFactory formFactory;

    //get all messages
    public Result index() {
        List<Message> messages = Message.finder.all();

        return ok(index.render(messages));
    }

    //create a message
    public Result create() {
        Form<Message> messageForm = formFactory.form(Message.class);
        return ok(create.render(messageForm));
    }

    //save a book
    public Result save() {
        Form<Message> messageForm = formFactory.form(Message.class).bindFromRequest();
        if(messageForm.hasErrors())
            return badRequest(create.render(messageForm));

        Message message = messageForm.get();
        message.save();

        return redirect(routes.MessageController.index());
    }

    //edit a message based on id
    public Result edit(Integer id) {
        Message message = Message.finder.byId(id);
        if (message ==  null)
            return notFound("Message not found");

        Form<Message> messageForm = formFactory.form(Message.class).fill(message);

        session("id", id.toString());
        return ok(edit.render(messageForm));
    }

    //update edited message into database
    public Result update() {
        Message message = formFactory.form(Message.class).bindFromRequest().get();
        if (!session().containsKey("id")) {
            flash("invalidSession", "Invalid Session");
            return redirect(routes.MessageController.index());
        }
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
    public Result delete(Integer id) {
        Message message = Message.finder.byId(id);
        if (message == null)
            return notFound("Message not found");
        message.delete();

        return redirect(routes.MessageController.index());
    }

    //show message details
    public Result show(Integer id) {
        Message message = Message.finder.byId(id);
        if (message == null)
            return notFound("Message not found");

        return ok(show.render(message));
    }
}
