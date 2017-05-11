package me.omidh.ripplevalidatoredittext.validator;

import android.support.annotation.NonNull;

/**
 * Base Validator class to either implement or inherit from for custom validation
 */
public abstract class RVEValidator {
  /**
   * Error message that the view will display if validation fails.
   * <p/>
   * This is protected, so you can change this dynamically in your {@link #isValid(CharSequence)}
   * implementation. If necessary, you can also interact with this via its getter and setter.
   */
  protected String mErrorMessage;

  public RVEValidator(@NonNull String errorMessage) {
    this.mErrorMessage = errorMessage;
  }

  public void setErrorMessage(@NonNull String errorMessage) {
    this.mErrorMessage = errorMessage;
  }

  @NonNull
  public String getErrorMessage() {
    return this.mErrorMessage;
  }

  /**
   * Abstract method to implement your own validation checking.
   *
   * @param text    The CharSequence representation of the text in the EditText field. Cannot be null, but may be empty.
   * @return True if valid, false if not
   */
  public abstract boolean isValid(@NonNull CharSequence text);
}
