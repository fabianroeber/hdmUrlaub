package validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Der Validator prüft ob die Erfasssung von Urlaubsbeginn und Urlaubsende
 * korrekt erfolgt
 * 
 * @author Markus Schmieder
 *
 */
@FacesValidator("dateRangeValidator")
public class DateRangeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null) {
			return;
		}

		UIInput startDateComponent = (UIInput) component.getAttributes().get(
				"startDateComponent");

		if (!startDateComponent.isValid()) {
			return;
		}

		Date startDate = (Date) startDateComponent.getValue();

		if (startDate == null) {
			return;
		}

		Date endDate = (Date) value;

		if (startDate.after(endDate)) {
			startDateComponent.setValid(false);
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Eine Zeitreise in die Vergangenheit wäre auch mal ein schöner Urlaub! ;)",
							null));
		}
	}

}
