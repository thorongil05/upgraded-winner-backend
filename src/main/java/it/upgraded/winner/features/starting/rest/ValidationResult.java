package it.upgraded.winner.features.starting.rest;

import java.util.List;
import java.util.ArrayList;

public class ValidationResult {

    private boolean isValid = false;
    private List<String> validationErrorList = new ArrayList<>();

    private ValidationResult(List<String> validationErrorList) {
        this.validationErrorList = validationErrorList;
        isValid = validationErrorList.isEmpty();
    }

    public static ValidationResult of(List<String> validationErrorList) {
        return new ValidationResult(validationErrorList);
    }

    public List<String> getValidationErrorList() {
        return validationErrorList;
    }

    public void setValidationErrorList(List<String> validationErrorList) {
        this.validationErrorList = validationErrorList;
    }

    public boolean isValid() {
        return isValid;
    }

}
