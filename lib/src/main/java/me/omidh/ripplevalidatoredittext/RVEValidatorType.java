package me.omidh.ripplevalidatoredittext;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Omid Heshmatinia on 5/8/2017.
 */

public class RVEValidatorType {
  public final static int EMAIL = 1;
  public final static int EMPTY = 2;
  public final static int EQUAL = 3;
  public final static int MIN_LENGTH = 4;
  public final static int BEGIN = 5;
  public final static int END = 6;
  public final static int PHONE = 7;

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({EMAIL,EMPTY,EQUAL,MIN_LENGTH,BEGIN,END,PHONE})
  @interface VType{}
}
