package com.mystery.core.validation;

import lombok.Getter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/** Used to collect validations and hold a summary. */
public class ValidationResult {
  @Getter
  private final List<String> errors = new LinkedList<>();
  
  private ValidationResult() {}
  
  /** Adds an error to the collection. */
  public ValidationResult addError(String message) {
    errors.add(message);
    return this;
  }
  
  /** Adds all errors to current validation. */
  public ValidationResult checkAndCollect(ValidationResult validation) {
    errors.addAll(validation.getErrors());
    return this;
  }
  
  /** Returns true if validation has no errors. */
  public boolean isValid() {
    return errors.isEmpty();
  }
  
  /** Returns true if validation has errors. */
  public boolean isInvalid() {
    return !errors.isEmpty();
  }
  
  /** Returns a new instance without errors. */
  public static ValidationResult ok() {
    return new ValidationResult();
  }
  
  /** Returns a new instance with an error. */
  public static ValidationResult error(String message) {
    ValidationResult result = new ValidationResult();
    result.addError(message);
    return result;
  }
  
  /** Returns a new instance with a collection of errors. */
  public static ValidationResult error(Collection<String> messages) {
    ValidationResult result = new ValidationResult();
    result.errors.addAll(messages);
    return result;
  }
  
  /** Returns a summary of given validations. */
  public static ValidationResult collect(ValidationResult... validations) {
    ValidationResult result = new ValidationResult();
    for (ValidationResult validation : validations) {
      result.checkAndCollect(validation);
    }
    return result;
  }
}
