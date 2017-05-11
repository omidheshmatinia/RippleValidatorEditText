package me.omidh.ripplevalidatoredittext.validator;

import android.support.annotation.NonNull;
import java.util.regex.Pattern;

/**
 * Custom validator for Regexes
 */
public class RVERegexpValidator extends RVEValidator {

  private Pattern pattern;

  public RVERegexpValidator(@NonNull String errorMessage, @NonNull String regex) {
    super(errorMessage);
    pattern = Pattern.compile(regex);
  }

  @Override
  public boolean isValid(@NonNull CharSequence text) {
    return pattern.matcher(text).matches();
  }
}