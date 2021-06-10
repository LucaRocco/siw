package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OperaValidator implements Validator {

    private OperaService operaService;

    public OperaValidator(final OperaService operaService) {
        this.operaService = operaService;
    }


    @Override
    public boolean supports(final Class<?> aClass) {
        return Opera.class.equals(aClass);
    }

    @Override
    public void validate(final Object o, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
    }
}
