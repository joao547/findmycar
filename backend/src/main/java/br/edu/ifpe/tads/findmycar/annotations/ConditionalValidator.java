package br.edu.ifpe.tads.findmycar.annotations;


import br.edu.ifpe.tads.findmycar.enums.TipoUsuario;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;

import static org.springframework.util.ObjectUtils.isEmpty;

public class ConditionalValidator implements ConstraintValidator<Conditional, Object> {

    private String selected;
    private String[] required;
    private String message;
    private String[] values;

    @Override
    public void initialize(Conditional requiredIfChecked) {
        selected = requiredIfChecked.selected();
        required = requiredIfChecked.required();
        message = requiredIfChecked.message();
        values = requiredIfChecked.values();
    }

    @Override
    public boolean isValid(Object objectToValidate, ConstraintValidatorContext context) {
        boolean validValue;
        boolean isValid = true;
        BeanWrapperImpl wrapper = new BeanWrapperImpl(objectToValidate);
        TipoUsuario actualValue = (TipoUsuario) wrapper.getPropertyValue(selected);
        if (actualValue != null && Arrays.asList(values).contains(actualValue.getName())) {
            for (String propName : required) {
                Object requiredValue = wrapper.getPropertyValue(propName);
                validValue = requiredValue != null && !isEmpty(requiredValue);
                System.out.println("value: " + "" + requiredValue);
                if (!validValue) {
                    isValid = false;
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(message).addPropertyNode(propName).addConstraintViolation();
                }
            }
        }
        return isValid;
    }
}
