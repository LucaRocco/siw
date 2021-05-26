package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.CollezioneService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CollezioneValidator implements Validator {

    private CollezioneService collezioneService;

    public CollezioneValidator(CollezioneService collezioneService) {
        this.collezioneService = collezioneService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Collezione.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "curatore", "required");
    }
}
