package br.edu.ifpe.tads.findmycar.annotations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.InvocationTargetException;
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
        Boolean valid = true;
        BeanWrapperImpl wrapper = new BeanWrapperImpl(objectToValidate);
        Object actualValue = wrapper.getPropertyValue(selected);
        if (Arrays.asList(values).contains(actualValue)) {
            for (String propName : required) {
                Object requiredValue = wrapper.getPropertyValue(propName);
                valid = requiredValue != null && !isEmpty(requiredValue);
                System.out.println("value: " + "" + requiredValue);
                if (!valid) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(message).addPropertyNode(propName).addConstraintViolation();
                }
            }
        }
        return valid;
    }
}
