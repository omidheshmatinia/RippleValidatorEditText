package me.omidh.ripplevalidatoredittext;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Omid Heshmatinia on 5/8/2017.
 */

class KeyboardUtility {

  public static void showKeyboard(Context ct,View v){
    ((InputMethodManager) ct.getSystemService(ct.INPUT_METHOD_SERVICE))
        .showSoftInput(v, InputMethodManager.SHOW_FORCED);
  }
}
