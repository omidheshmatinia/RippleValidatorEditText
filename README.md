 [ ![Latest JCenter Version](https://api.bintray.com/packages/omidheshmatinia/maven/RippleValidatorEditText/images/download.svg) ](https://bintray.com/omidheshmatinia/maven/RippleValidatorEditText/_latestVersion)  [![Twitter](https://img.shields.io/badge/Twitter-@Smartiiiiz-blue.svg?style=flat)](http://twitter.com/Smartiiiiz)
![Platform](https://img.shields.io/badge/Platform-Android-green.svg) ![Minimun Android Sdk Version](https://img.shields.io/badge/min--sdk-11-yellowgreen.svg)
# RippleValidatorEditText
An Input box with different validators and ripple transition for android (under development)

# NOTE :  Under development

# Setup
## 1. Provide the gradle dependency
```gradle
compile 'me.omidh:rvelib:0.5.18'
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
        app:rve_normalColor="#222222"
        app:rve_typingColor="#555555"
        app:rve_errorColor="#EF5350"
        app:rve_backgroundColor="#00000000"
        app:rve_validColor="#66BB6A"
        app:rve_editTextColor="#222222"/>
```

# Tips
### Focus
If you want to use focus attributes, do not forget to add below line in your xml

```xml
android:imeOptions="actionNext"
```

## Todo :
 - different ripple effect for each state
 - accept more parameter from attr
 - cleaning the code
 - adding enums to style for android related attributes
 - add validators wiki to readme file
 - make a few samples

# Developed By

> Omid Heshmatinia
> omidheshmatinia@gmail.com
