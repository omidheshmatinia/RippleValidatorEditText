 [ ![Latest JCenter Version](https://api.bintray.com/packages/omidheshmatinia/maven/RippleValidatorEditText/images/download.svg) ](https://bintray.com/omidheshmatinia/maven/RippleValidatorEditText/_latestVersion)  [![Twitter](https://img.shields.io/badge/Twitter-@Smartiiiiz-blue.svg?style=flat)](http://twitter.com/Smartiiiiz)
![Platform](https://img.shields.io/badge/Platform-Android-green.svg) ![Minimun Android Sdk Version](https://img.shields.io/badge/min--sdk-11-yellowgreen.svg) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Ripple%20Validator%20EditText-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5901)
# RippleValidatorEditText
An Input box with different validators and ripple transition for android. It let you to define custom animation for error text entrance and also comes up with seven ready validators.

## ScreenShots

<img src="/sample/sample1.gif"/>

# Setup
## 1. Provide the gradle dependency
```gradle
compile 'me.omidh:rvelib:0.8.01'
```
## 2. Sample

```xml
 <me.omidh.ripplevalidatoredittext.RippleValidatorEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:rve_hint="My Sample Hint"
        app:rve_font="fonts/iran_sans_mobile.ttf"
        app:rve_helperTextSize="12sp"
        app:rve_editTextSize="14sp"
        app:rve_topLeftCornerRadius="4dp"
        app:rve_bottomLeftCornerRadius="0dp"
        app:rve_bottomRightCornerRadius="0dp"
        app:rve_topRightCornerRadius="4dp"
        app:rve_strokeWidth="1dp"
        android:inputType="number"
        android:nextFocusDown="@+id/editText_next"
        android:nextFocusForward="@+id/editText_next"
        android:imeOptions="actionNext"
        android:textColorHint="#80222222"
        app:rve_helperAnimation="@anim/fab_slide_in_from_right"
        app:rve_normalColor="#222222"
        app:rve_typingColor="#555555"
        app:rve_errorColor="#EF5350"
        app:rve_backgroundColor="#00000000"
        app:rve_validColor="#66BB6A"
        app:rve_editTextColor="#222222"/>
```

## Validator
This Library contains 7 ready validators. It also let you to write you custom validator. 

**EMPTY** , **EMAIL** , **EQUAL** , **BEGIN** , **END** , **MIN_LENGTH** , **PHONE**

For a sample code please visit the [Validator wiki page](https://github.com/omidheshmatinia/RippleValidatorEditText/wiki/Validators)

## Error Text Entrance Animation
You can set your desired animation for entrance of the error text.

it can be set in XML :
```xml
   app:rve_helperAnimation="@anim/fab_slide_in_from_right"
```
      
# Tips

### Focus
If you want to use focus attributes, do not forget to add below line in your xml

```xml
   android:imeOptions="actionNext"
```

## Todo :
 - different ripple effect for each state
 - cleaning the code
 - adding enums to style for android related attributes

# Developed By

> Omid Heshmatinia
> omidheshmatinia@gmail.com
