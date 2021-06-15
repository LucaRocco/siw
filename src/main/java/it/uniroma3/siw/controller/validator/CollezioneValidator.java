package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Collezione;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

@Component
public class CollezioneValidator implements Validator {

    @Override
    public boolean supports(final Class<?> aClass) {
        return Collezione.class.equals(aClass);
    }

    @Override
    public void validate(final Object o, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
    }

    public void validate(final Object o, final String curatoreSelezionato, final Errors errors) {
        this.validate(o, errors);

        if("null".equals(curatoreSelezionato)) {
            errors.rejectValue("curatore", "required");
        }
    }
}
