package views.formData;

import models.UserInfo;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Backing class for the login form.
 */
@Constraints.Validate
public class RegisterFormData implements Constraints.Validatable<List<ValidationError>> {

    /**
     * The submitted username.
     */
    public String username = "";
    /**
     * The submitted password.
     */
    public String password = "";

    /**
     * Required for form instantiation.
     */
    public RegisterFormData() {
    }

    /**
     * Validates Form<RegisterFormData>.
     * Called automatically in the controller by bindFromRequest().
     * Checks to see that username is not used.
     *
     * @return Null if valid, or a List[ValidationError] if problems found.
     */
    @Override
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        if (UserInfo.isUserNameUsed(username)) {
            errors.add(new ValidationError("username", ""));
        }

        return (errors.size() > 0) ? errors : null;
    }
}
