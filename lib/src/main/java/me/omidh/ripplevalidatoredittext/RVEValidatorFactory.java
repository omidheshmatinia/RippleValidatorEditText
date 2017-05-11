package me.omidh.ripplevalidatoredittext;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;
import me.omidh.ripplevalidatoredittext.validator.RVERegexpValidator;
import me.omidh.ripplevalidatoredittext.validator.RVEValidator;

/**
 * Created by Omid Heshmatinia on 5/8/2017.
 */

public class RVEValidatorFactory {

  public static RVEValidator getValidator(@RVEValidatorType.VType int type ,String error,@Nullable Object item){
    switch (type){
      default:
      case RVEValidatorType.EMPTY:
        return EmptyChecker(error);
      case RVEValidatorType.EMAIL:
        return EmailChecker(error);
      case RVEValidatorType.EQUAL:
        return EqualChecker((String) item,error);
      case RVEValidatorType.BEGIN:
        return BeginChecker(error,(String) item);
      case RVEValidatorType.END:
        return EndChecker(error,(String) item);
      case RVEValidatorType.PHONE:
        return PhoneNumberChecker(error);
      case RVEValidatorType.MIN_LENGTH:
        return MinLengthChecker(error,(int)item);
    }
  }

  private static RVEValidator EmptyChecker(final String errTxt){
    return new RVEValidator(errTxt) {
      @Override public boolean isValid(@NonNull CharSequence text) {
        if(text.toString().trim().equals(""))
          return false;
        return true;
      }
    };
  }

  private static RVEValidator EqualChecker(final String equalString,final String errTxt){
    return new RVEValidator(errTxt) {
      @Override public boolean isValid(@NonNull CharSequence text) {
        return equalString.equals(text.toString());
      }
    };
  }

  private static RVEValidator MinLengthChecker(final String errTxt,final int minLength){
    return new RVEValidator(errTxt) {
      @Override public boolean isValid(@NonNull CharSequence text) {
        return ! (text.length() < minLength);
      }
    };
  }

  private static RVEValidator PhoneNumberChecker(String error){
    return new RVEValidator(error) {
      @Override public boolean isValid(@NonNull CharSequence text) {
        Boolean isNumber= Patterns.PHONE.matcher(text).matches();
        if(!isNumber) {
          return false;
        }
        return true;
      }
    };
  }

  private static RVEValidator BeginChecker(String error,final String fullText){
    return new RVEValidator(error) {
      @Override public boolean isValid(@NonNull CharSequence text) {
        Boolean beginWith09 = text.toString().startsWith(fullText);
        if(!beginWith09) {
          return false;
        }
        return true;
      }
    };
  }

  private static RVEValidator EndChecker(String error,final String fullText){
    return new RVEValidator(error) {
      @Override public boolean isValid(@NonNull CharSequence text) {
        Boolean beginWith09 = text.toString().endsWith(fullText);
        if(!beginWith09) {
          return false;
        }
        return true;
      }
    };
  }

  private static RVEValidator EmailChecker(final String error) {
    return new RVERegexpValidator(error,"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
  }
}
