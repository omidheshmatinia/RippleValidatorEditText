package me.omidh.ripplevalidatoredittext;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.omidh.ripplevalidatoredittext.validator.RVEValidator;

import static me.omidh.ripplevalidatoredittext.KeyboardUtility.showKeyboard;

/**
 * Created by Omid Heshmatinia on 5/7/2017.
 */

public class RippleValidatorEditText extends LinearLayout{

  List<RVEValidator> mValidators=new ArrayList<>();
  private int mValidColor = Color.GREEN ;
  private int mNormalColor = Color.BLUE ;
  private int mTypingColor = Color.MAGENTA ;
  private int mErrorColor = Color.RED ;
  private int mWarningColor = Color.YELLOW ;
  private int mEditTextInputType =  EditorInfo.TYPE_NULL;
  private int mImeOptions =  0;
  private int mEditTextColor = Color.BLACK ;
  private int mHintColor = Color.BLACK ;
  private int mHelperTextSize = 10;
  private int mStrokeWidth = 0;
  private int mEditTextSize = 10;
  private int mHelperTextGravity = Gravity.RIGHT;
  private int mEditTextGravity = Gravity.RIGHT|Gravity.CENTER;
  private Boolean mAutoValidate = true;
  private float[] mCornerRadius =new float[]{0,0,0,0,0,0,0,0};
  private String mHintText="";
  private TextView mHelperTextView;
  private EditText mEditText;
  private Typeface mTypeFace;
  private int mCircleRadius=0;
  private Paint mCirclePaint;
  private Paint mTransparentPaint;
  private RippleType rippleType = RippleType.IsPlaying;
  private Drawable mLastBorderDrawable;
  private int[] mNextFocusIds = new int[]{0,0,0,0,0}; //{ Down , Left , Up , Right , Forward}
  private int mHelperAnimation  = R.anim.fade_in_slide_right;

  public void setText(String txt) {
    mEditText.setText(txt);
  }

  /**
   * Set entrance animation of helper text when error happen
   * @param animation
   */
  public void setHelperTextAnimation(@AnimRes int animation){
    mHelperAnimation=animation;
  }

  public boolean validateWith(RVEValidator validator,Boolean showAnimation) {
    if(validator==null)
      throw new NullPointerException("The Validator Should Not Be NULL");

    Boolean isValid=validator.isValid(getText());
    if(!isValid) {
      updateViewColor(UIMode.ERROR,validator.getErrorMessage());
      return false;
    }
    if(showAnimation)
      drawCircle();
    updateViewColor(UIMode.COMPLETE,"");
    return true;
  }

  public void addTextChangedListener(TextWatcher textWatcher) {
    mEditText.addTextChangedListener(textWatcher);
  }

  private enum RippleType{
    IsPlaying,IsClearing,IsEnded,Nothing
  }

  public RippleValidatorEditText(Context context) {
    super(context);
    init(null);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    switch (rippleType){
      case IsEnded:
        rippleType = RippleType.Nothing;
        mCircleRadius = 0;
        canvas.drawPaint(mTransparentPaint);
        //canvas.drawColor(Color.parseColor("#00000000"));
        mLastBorderDrawable.draw(canvas);
        break;
      case IsPlaying:
        canvas.drawCircle(0,getHeight()/2,mCircleRadius,mCirclePaint);
        mLastBorderDrawable.draw(canvas);
        break;
      case IsClearing:
        canvas.drawColor(mValidColor);
        canvas.drawCircle(0, getHeight() / 2, mCircleRadius, mTransparentPaint);
        mLastBorderDrawable.draw(canvas);
        break;
      case Nothing:
        mCircleRadius = 0;
        canvas.drawPaint(mTransparentPaint);
        mLastBorderDrawable.draw(canvas);
        break;
    }
  }

  private void drawEmptyCircle(){
    rippleType = RippleType.IsClearing;
    ValueAnimator va = ValueAnimator.ofInt(0,getWidth());
    va.setDuration(700);
    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      public void onAnimationUpdate(ValueAnimator animation) {
        mCircleRadius=(int)animation.getAnimatedValue();
        if(mCircleRadius==getWidth())
          rippleType = RippleType.IsEnded;
        invalidate();
      }
    });
    va.start();
  }

  private void drawCircle(){
    rippleType = RippleType.IsPlaying;
    mCirclePaint.setColor(mValidColor);
    ValueAnimator va = ValueAnimator.ofInt(0,getWidth());
    va.setDuration(700);
    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      public void onAnimationUpdate(ValueAnimator animation) {
        mCircleRadius=(int)animation.getAnimatedValue();
        if(mCircleRadius==getWidth())
          drawEmptyCircle();
        invalidate();
      }
    });
    va.setStartDelay(500);
    va.start();
  }

  public RippleValidatorEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  private void init(AttributeSet attrs){
    setLayerType(LAYER_TYPE_HARDWARE, null);
    if(attrs!=null){
      //if(!isInEditMode()) {
      TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RippleValidatorEditText);
      mEditTextInputType = a.getInt(R.styleable.RippleValidatorEditText_android_inputType, EditorInfo.TYPE_NULL);
      mNextFocusIds[0] = a.getResourceId(R.styleable.RippleValidatorEditText_android_nextFocusDown, 0);
      mNextFocusIds[1] = a.getResourceId(R.styleable.RippleValidatorEditText_android_nextFocusLeft, 0);
      mNextFocusIds[2] = a.getResourceId(R.styleable.RippleValidatorEditText_android_nextFocusUp, 0);
      mNextFocusIds[3] = a.getResourceId(R.styleable.RippleValidatorEditText_android_nextFocusRight, 0);
      mNextFocusIds[4] = a.getResourceId(R.styleable.RippleValidatorEditText_android_nextFocusForward, 0);
      mImeOptions = a.getInt(R.styleable.RippleValidatorEditText_android_imeOptions, 0);
      mHelperAnimation = a.getResourceId(R.styleable.RippleValidatorEditText_rve_helperAnimation,R.anim.fade_in_slide_right);
      mHintColor = a.getColor(R.styleable.RippleValidatorEditText_android_textColorHint, mHintColor);
      mAutoValidate = a.getBoolean(R.styleable.RippleValidatorEditText_rve_validateOnFocusLost,mAutoValidate);
      mHintText = a.getString(R.styleable.RippleValidatorEditText_rve_hint);
      mEditTextGravity = a.getInt(R.styleable.RippleValidatorEditText_rve_editTextGravity,mEditTextGravity);
      String typeface=a.getString(R.styleable.RippleValidatorEditText_rve_font);
      if(typeface!=null){
        mTypeFace = Typeface.createFromAsset(getContext().getAssets(),typeface);
      }
      mStrokeWidth = a.getDimensionPixelOffset(R.styleable.RippleValidatorEditText_rve_strokeWidth,mStrokeWidth);
      mEditTextSize=a.getDimensionPixelSize(R.styleable.RippleValidatorEditText_rve_editTextSize,(int) convertDipToPixels(mEditTextSize));
      mHelperTextSize=a.getDimensionPixelSize(R.styleable.RippleValidatorEditText_rve_helperTextSize,(int)convertDipToPixels(mHelperTextSize));
      //corner radius
      float topRight = a.getDimension(R.styleable.RippleValidatorEditText_rve_topRightCornerRadius,0);
      float topLeft = a.getDimension(R.styleable.RippleValidatorEditText_rve_topLeftCornerRadius,0);
      float BottomRight = a.getDimension(R.styleable.RippleValidatorEditText_rve_bottomRightCornerRadius,0);
      float BottomLeft = a.getDimension(R.styleable.RippleValidatorEditText_rve_bottomLeftCornerRadius,0);
      mCornerRadius = new float[]{topLeft,topLeft,topRight,topRight,BottomRight,BottomRight,BottomLeft,BottomLeft};
      // colors
      mNormalColor = a.getColor(R.styleable.RippleValidatorEditText_rve_normalColor,mNormalColor);
      mErrorColor = a.getColor(R.styleable.RippleValidatorEditText_rve_errorColor,mErrorColor);
      mTypingColor = a.getColor(R.styleable.RippleValidatorEditText_rve_typingColor,mTypingColor);
      mEditTextColor = a.getColor(R.styleable.RippleValidatorEditText_rve_editTextColor,mEditTextColor);
      mValidColor = a.getColor(R.styleable.RippleValidatorEditText_rve_validColor,mValidColor);

      a.recycle();
      //}
    }

    mCirclePaint = new Paint();
    mCirclePaint.setColor(mErrorColor);
    mTransparentPaint=new Paint();
    mTransparentPaint.setColor(Color.parseColor("#00000000"));
    mTransparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

    // To gain focus on edit text if user used next focus
    this.setOnFocusChangeListener(new OnFocusChangeListener() {
      @Override public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus) {
          mHelperTextView.setVisibility(GONE);
          mEditText.setVisibility(VISIBLE);
          mEditText.requestFocus();
          showKeyboard(getContext(),mEditText);
        }
      }
    });

    this.setOrientation(VERTICAL);
    mEditText=new EditText(getContext());
    LayoutParams lp =new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
    mEditText.setPadding(getPaddingLeft(),getPaddingTop(),getPaddingRight(),getPaddingBottom());
    lp.weight = 1;
    mEditText.setLayoutParams(lp);
    //mEditText.addTextChangedListener(new TextWatcher() {
    //  @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //  }
    //
    //  @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
    //
    //  }
    //
    //  @Override public void afterTextChanged(Editable s) {
    //    //if(!s.toString().trim().equals("")){
    //    //  mHelperTextView.setVisibility(VISIBLE);
    //    //  mHelperTextView.setText(s.toString().trim());
    //    //  setBackGroundOfLayout(getShapeBackground(mWarningColor));
    //    //  mHelperTextView.setTextColor(mWarningColor);
    //    //} else {
    //    //  mHelperTextView.setVisibility(GONE);
    //    //  setBackGroundOfLayout(getShapeBackground(mNormalColor));
    //    //}
    //  }
    //});
    mEditText.setHint(mHintText);
    mEditText.setGravity(mEditTextGravity);
    mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX,mEditTextSize);
    mEditText.setBackgroundColor(Color.parseColor("#00000000"));
    mEditText.setTextColor(mEditTextColor);
    mEditText.setHintTextColor(mHintColor);
    mEditText.setInputType(mEditTextInputType);
    mEditText.setImeOptions(mImeOptions);
    mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
      @Override public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus)
          isValid(mAutoValidate);
        else
          updateViewColor(UIMode.TYPING,"");
      }
    });
    mHelperTextView = new TextView(getContext());
    mHelperTextView.setMaxLines(1);
    mHelperTextView.setVisibility(GONE);
    mHelperTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,mHelperTextSize);
    mHelperTextView.setGravity(mHelperTextGravity);
    mHelperTextView.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        mHelperTextView.setVisibility(GONE);
        mEditText.setVisibility(VISIBLE);
        mEditText.requestFocus();
        //showKeyboard();
        showKeyboard(getContext(),mEditText);
      }
    });
    if(mTypeFace != null){
      mHelperTextView.setTypeface(mTypeFace);
      mEditText.setTypeface(mTypeFace);
    }
    setupNextFocusViews();
    updateViewColor(UIMode.NORMAL,"");
    setBackGroundOfLayout(getShapeBackground(mNormalColor));
    this.addView(mHelperTextView);
    this.addView(mEditText);
    this.setGravity(Gravity.CENTER);
  }

  /**
   * Setup Next focus ids which are set in xml
   */
  private void setupNextFocusViews() {
    for(int i=0;i<mNextFocusIds.length;i++){
      if(mNextFocusIds[i]==0)
        continue;
      switch (i){
        case 0:
          mEditText.setNextFocusDownId(mNextFocusIds[i]);
          break;
        case 1:
          mEditText.setNextFocusLeftId(mNextFocusIds[i]);
          break;
        case 2:
          mEditText.setNextFocusUpId(mNextFocusIds[i]);
          break;
        case 3:
          mEditText.setNextFocusRightId(mNextFocusIds[i]);
          break;
        case 4:
          mEditText.setNextFocusForwardId(mNextFocusIds[i]);
          break;
      }
    }
  }

  private void updateViewColor(@UIMode.UiType int type,String txt) {
    int color = 0;
    int visibility = VISIBLE;
    switch (type){
      case UIMode.NORMAL:
        mEditText.setVisibility(VISIBLE);
        color = mNormalColor;
        visibility=GONE;
        break;
      case UIMode.COMPLETE:
        mEditText.setVisibility(VISIBLE);
        color = mValidColor;
        visibility=GONE;
        break;
      case UIMode.ERROR:
        mEditText.setVisibility(GONE);
        color = mErrorColor;
        visibility=VISIBLE;
        break;
      case UIMode.TYPING:
        mEditText.setVisibility(VISIBLE);
        color = mTypingColor;
        visibility=GONE;
        break;
    }
    setBackGroundOfLayout(getShapeBackground(color));
    mHelperTextView.setTextColor(color);
    mHelperTextView.setVisibility(visibility);
    mHelperTextView.setText(txt);
  }


  private void setBackGroundOfLayout(Drawable shape){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      setBackground(shape);
    } else {
      setBackgroundDrawable(shape);
    }
  }

  private Drawable getShapeBackground(@ColorInt int color){
    GradientDrawable shape =  new GradientDrawable();
    ((GradientDrawable)shape.mutate()).setCornerRadii( mCornerRadius );
    shape.setStroke(mStrokeWidth,color);
    mLastBorderDrawable = shape;
    return shape;
  }

  private float convertDipToPixels(float dips) {
    return (dips * getContext().getResources().getDisplayMetrics().density + 0.5f);
  }

  public Boolean isValid(Boolean showAnimation) {
    for(int i=0;i<mValidators.size();i++){
      Boolean isValid=mValidators.get(i).isValid(getText());
      if(!isValid) {
        updateViewColor(UIMode.ERROR,mValidators.get(i).getErrorMessage());
        return false;
      }
    }
    if(showAnimation)
      drawCircle();
    updateViewColor(UIMode.COMPLETE,"");
    return true;
  }


  /**
   * @return entered input of edittext
   */
  public CharSequence getText() {
    return mEditText.getText();
  }

  public void addValidator(RVEValidator... validator) {
    mValidators = Arrays.asList(validator);
  }


}
