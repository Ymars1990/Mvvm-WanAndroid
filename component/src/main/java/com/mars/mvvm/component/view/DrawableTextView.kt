package com.mars.mvvm.component.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import com.mars.mvvm.component.R

/**
 * @author Mars
 */
/**
 * RTextView
 *
 *
 * 1. DrawableTextView 让你从此不再编写和管理大量 selector 文件（这个太可恨了）
 * 2. DrawableTextView 改造了 drawableLeft/drawableXXX 图片的大小，从此你不在需要使用 LinearLayout + ImageView + TextView 就能够直接实现文字带图片的功能，关键还能设置icon大小
 * 3. DrawableTextView 能够直接设置各种圆角效果： 四周圆角，某一方向圆角，甚至椭圆，圆形都简单实现。 边框效果，虚线边框都是一个属性搞定
 * 4. DrawableTextView 不仅能够定义默认状态的背景，边框，连按下/点击状态通通一起搞定
 * 5. DrawableTextView 按下变色支持：背景色，边框，文字，drawableLeft/xxx （这个赞啊）
 *
 *
 */
class DrawableTextView @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(mContext, attrs) {
    //icon
    var iconHeight = 0
        private set
    var iconWidth = 0
        private set
    var iconDirection = 0
        private set
    //corner
    private var mCornerRadius = 0f
    var cornerRadiusTopLeft = 0f
        private set
    var cornerRadiusTopRight = 0f
        private set
    var cornerRadiusBottomLeft = 0f
        private set
    var cornerRadiusBottomRight = 0f
        private set
    //BorderWidth
    private var mBorderDashWidth = 0f
    private var mBorderDashGap = 0f
    var borderWidthNormal = 0
        private set
    var borderWidthPressed = 0
        private set
    var borderWidthUnable = 0
        private set
    //BorderColor
    var borderColorNormal = 0
        private set
    var borderColorPressed = 0
        private set
    var borderColorUnable = 0
        private set
    //Background
    var backgroundColorNormal = 0
        private set
    var backgroundColorPressed = 0
        private set
    var backgroundColorUnable = 0
        private set
    private var mBackgroundNormal: GradientDrawable? = null
    private var mBackgroundPressed: GradientDrawable? = null
    private var mBackgroundUnable: GradientDrawable? = null
    // Text
    var textColorNormal = 0
        private set
    var pressedTextColor = 0
        private set
    var textColorUnable = 0
        private set
    private var mTextColorStateList: ColorStateList? = null
    //Icon
    private var mIcon: Drawable? = null
    var iconNormal: Drawable? = null
        private set
    var iconPressed: Drawable? = null
        private set
    var iconUnable: Drawable? = null
        private set
    //typeface
    var typefacePath: String? = null
        private set
    private val states = arrayOfNulls<IntArray>(4)
    private var mStateBackground: StateListDrawable? = null
    private val mBorderRadii = FloatArray(8)
    /**
     * Cache the touch slop from the context that created the view.
     */
    private val mTouchSlop: Int
    private val mGestureDetector: GestureDetector
    /**
     * 是否设置对应的属性
     */
    private var mHasPressedBgColor = false
    private var mHasUnableBgColor = false
    private var mHasPressedBorderColor = false
    private var mHasUnableBorderColor = false
    private var mHasPressedBorderWidth = false
    private var mHasUnableBorderWidth = false
    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (enabled) {
            if (iconNormal != null) {
                mIcon = iconNormal
                setIcon()
            }
        } else {
            if (iconUnable != null) {
                mIcon = iconUnable
                setIcon()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) {
            return true
        }
        mGestureDetector.onTouchEvent(event)
        val action = event.action
        when (action) {
            MotionEvent.ACTION_UP -> if (iconNormal != null) {
                mIcon = iconNormal
                setIcon()
            }
            MotionEvent.ACTION_MOVE -> {
                val x = event.x.toInt()
                val y = event.y.toInt()
                if (isOutsideView(x, y)) {
                    if (iconNormal != null) {
                        mIcon = iconNormal
                        setIcon()
                    }
                }
            }
            MotionEvent.ACTION_CANCEL -> if (iconNormal != null) {
                mIcon = iconNormal
                setIcon()
            }
            else -> {
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 初始化控件属性
     *
     * @param context
     * @param attrs
     */
    private fun initAttributeSet(
        context: Context?,
        attrs: AttributeSet?
    ) {
        if (context == null || attrs == null) {
            setup()
            return
        }
        val a = context.obtainStyledAttributes(attrs, R.styleable.DrawableTextView)
        //corner
        mCornerRadius =
            a.getDimensionPixelSize(R.styleable.DrawableTextView_corner_radius, -1).toFloat()
        cornerRadiusTopLeft =
            a.getDimensionPixelSize(R.styleable.DrawableTextView_corner_radius_top_left, 0)
                .toFloat()
        cornerRadiusTopRight =
            a.getDimensionPixelSize(R.styleable.DrawableTextView_corner_radius_top_right, 0)
                .toFloat()
        cornerRadiusBottomLeft =
            a.getDimensionPixelSize(R.styleable.DrawableTextView_corner_radius_bottom_left, 0)
                .toFloat()
        cornerRadiusBottomRight =
            a.getDimensionPixelSize(R.styleable.DrawableTextView_corner_radius_bottom_right, 0)
                .toFloat()
        //border
        mBorderDashWidth =
            a.getDimensionPixelSize(R.styleable.DrawableTextView_border_dash_width, 0).toFloat()
        mBorderDashGap =
            a.getDimensionPixelSize(R.styleable.DrawableTextView_border_dash_gap, 0).toFloat()
        borderWidthNormal =
            a.getDimensionPixelSize(R.styleable.DrawableTextView_border_width_normal, 0)
        borderWidthPressed =
            a.getDimensionPixelSize(R.styleable.DrawableTextView_border_width_pressed, 0)
        borderWidthUnable =
            a.getDimensionPixelSize(R.styleable.DrawableTextView_border_width_unable, 0)
        borderColorNormal = a.getColor(
            R.styleable.DrawableTextView_border_color_normal,
            Color.TRANSPARENT
        )
        borderColorPressed = a.getColor(
            R.styleable.DrawableTextView_border_color_pressed,
            Color.TRANSPARENT
        )
        borderColorUnable = a.getColor(
            R.styleable.DrawableTextView_border_color_unable,
            Color.TRANSPARENT
        )
        //icon
//Vector兼容处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iconNormal = a.getDrawable(R.styleable.DrawableTextView_icon_src_normal)
            iconPressed = a.getDrawable(R.styleable.DrawableTextView_icon_src_pressed)
            iconUnable = a.getDrawable(R.styleable.DrawableTextView_icon_src_unable)
        } else {
            val normalId = a.getResourceId(R.styleable.DrawableTextView_icon_src_normal, -1)
            val pressedId = a.getResourceId(R.styleable.DrawableTextView_icon_src_pressed, -1)
            val unableId = a.getResourceId(R.styleable.DrawableTextView_icon_src_unable, -1)
            if (normalId != -1) {
                iconNormal = AppCompatResources.getDrawable(context, normalId)
            }
            if (pressedId != -1) {
                iconPressed = AppCompatResources.getDrawable(context, pressedId)
            }
            if (unableId != -1) {
                iconUnable = AppCompatResources.getDrawable(context, unableId)
            }
        }
        iconWidth = a.getDimensionPixelSize(R.styleable.DrawableTextView_icon_width, 0)
        iconHeight = a.getDimensionPixelSize(R.styleable.DrawableTextView_icon_height, 0)
        iconDirection = a.getInt(
            R.styleable.DrawableTextView_icon_direction,
            ICON_DIR_LEFT
        )
        //text
        textColorNormal =
            a.getColor(R.styleable.DrawableTextView_text_color_normal, currentTextColor)
        pressedTextColor =
            a.getColor(R.styleable.DrawableTextView_text_color_pressed, currentTextColor)
        textColorUnable =
            a.getColor(R.styleable.DrawableTextView_text_color_unable, currentTextColor)
        //background
        backgroundColorNormal = a.getColor(R.styleable.DrawableTextView_background_normal, 0)
        backgroundColorPressed = a.getColor(R.styleable.DrawableTextView_background_pressed, 0)
        backgroundColorUnable = a.getColor(R.styleable.DrawableTextView_background_unable, 0)
        //typeface
        typefacePath = a.getString(R.styleable.DrawableTextView_text_typeface)
        a.recycle()
        mHasPressedBgColor = backgroundColorPressed != 0
        mHasUnableBgColor = backgroundColorUnable != 0
        mHasPressedBorderColor = borderColorPressed != 0
        mHasUnableBorderColor = borderColorUnable != 0
        mHasPressedBorderWidth = borderWidthPressed != 0
        mHasUnableBorderWidth = borderWidthUnable != 0
        //setup
        setup()
    }

    /**
     * 设置
     */
    private fun setup() {
        mBackgroundNormal = GradientDrawable()
        mBackgroundPressed = GradientDrawable()
        mBackgroundUnable = GradientDrawable()
        val drawable = background
        mStateBackground = if (drawable != null && drawable is StateListDrawable) {
            drawable
        } else {
            StateListDrawable()
        }
        /**
         * 设置背景默认值
         */
        if (!mHasPressedBgColor) {
            backgroundColorPressed = backgroundColorNormal
        }
        if (!mHasUnableBgColor) {
            backgroundColorUnable = backgroundColorNormal
        }
        mBackgroundNormal!!.setColor(backgroundColorNormal)
        mBackgroundPressed!!.setColor(backgroundColorPressed)
        mBackgroundUnable!!.setColor(backgroundColorUnable)
        //pressed, focused, normal, unable
        states[0] = intArrayOf(android.R.attr.state_enabled, android.R.attr.state_pressed)
        states[1] = intArrayOf(android.R.attr.state_enabled, android.R.attr.state_focused)
        states[3] = intArrayOf(-android.R.attr.state_enabled)
        states[2] = intArrayOf(android.R.attr.state_enabled)
        mStateBackground!!.addState(states[0], mBackgroundPressed)
        mStateBackground!!.addState(states[1], mBackgroundPressed)
        mStateBackground!!.addState(states[3], mBackgroundUnable)
        mStateBackground!!.addState(states[2], mBackgroundNormal)
        /**
         * icon
         */
        mIcon = if (isEnabled == false) {
            iconUnable
        } else {
            iconNormal
        }
        /**
         * 设置边框默认值
         */
        if (!mHasPressedBorderWidth) {
            borderWidthPressed = borderWidthNormal
        }
        if (!mHasUnableBorderWidth) {
            borderWidthUnable = borderWidthNormal
        }
        if (!mHasPressedBorderColor) {
            borderColorPressed = borderColorNormal
        }
        if (!mHasUnableBorderColor) {
            borderColorUnable = borderColorNormal
        }
        if (backgroundColorNormal == 0 && backgroundColorUnable == 0 && backgroundColorPressed == 0) { //未设置自定义背景色
/* if (mBorderColorPressed == 0 && mBorderColorUnable == 0 && mBorderColorNormal == 0) {//未设置自定义边框
                //获取原生背景并设置
                setBackgroundState(true);
            } else {
                setBackgroundState(false);
            }*/
//获取原生背景并设置
            setBackgroundState(true)
        } else { //设置背景资源
            setBackgroundState(false)
        }
        //设置文本颜色
        setTextColor()
        //设置边框
        setBorder()
        //设置ICON
        setIcon()
        //设置圆角
        setRadius()
        //设置文本字体样式
        setTypeface()
    }

    /**
     * 是否移出view
     *
     * @param x
     * @param y
     * @return
     */
    private fun isOutsideView(x: Int, y: Int): Boolean {
        var flag = false
        // Be lenient about moving outside of buttons
        if (x < 0 - mTouchSlop || x >= width + mTouchSlop ||
            y < 0 - mTouchSlop || y >= height + mTouchSlop
        ) { // Outside button
            flag = true
        }
        return flag
    }

    /*********************
     * BackgroundColor
     */
    fun setStateBackgroundColor(normal: Int, pressed: Int, unable: Int): DrawableTextView {
        backgroundColorNormal = normal
        backgroundColorPressed = pressed
        backgroundColorUnable = unable
        mHasPressedBgColor = true
        mHasUnableBgColor = true
        mBackgroundNormal!!.setColor(backgroundColorNormal)
        mBackgroundPressed!!.setColor(backgroundColorPressed)
        mBackgroundUnable!!.setColor(backgroundColorUnable)
        setBackgroundState(false)
        return this
    }

    fun setBackgroundColorNormal(colorNormal: Int): DrawableTextView {
        backgroundColorNormal = colorNormal
        /**
         * 设置背景默认值
         */
        if (!mHasPressedBgColor) {
            backgroundColorPressed = backgroundColorNormal
            mBackgroundPressed!!.setColor(backgroundColorPressed)
        }
        if (!mHasUnableBgColor) {
            backgroundColorUnable = backgroundColorNormal
            mBackgroundUnable!!.setColor(backgroundColorUnable)
        }
        mBackgroundNormal!!.setColor(backgroundColorNormal)
        setBackgroundState(false)
        return this
    }

    fun setBackgroundColorPressed(colorPressed: Int): DrawableTextView {
        backgroundColorPressed = colorPressed
        mHasPressedBgColor = true
        mBackgroundPressed!!.setColor(backgroundColorPressed)
        setBackgroundState(false)
        return this
    }

    fun setBackgroundColorUnable(colorUnable: Int): DrawableTextView {
        backgroundColorUnable = colorUnable
        mHasUnableBgColor = true
        mBackgroundUnable!!.setColor(backgroundColorUnable)
        setBackgroundState(false)
        return this
    }

    private fun setBackgroundState(unset: Boolean) { //未设置自定义属性,并且设置背景颜色时
        val drawable = background
        if (unset && drawable is ColorDrawable) {
            val color = drawable.color
            setStateBackgroundColor(color, color, color) //获取背景颜色值设置 StateListDrawable
        }
        //设置背景资源
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(if (unset) drawable else mStateBackground)
        } else {
            background = if (unset) drawable else mStateBackground
        }
    }

    /************************
     * Typeface
     */
    fun setTypeface(typefacePath: String?): DrawableTextView {
        this.typefacePath = typefacePath
        setTypeface()
        return this
    }

    private fun setTypeface() {
        if (!TextUtils.isEmpty(typefacePath)) {
            val assetManager = mContext.assets
            val typeface = Typeface.createFromAsset(assetManager, typefacePath)
            setTypeface(typeface)
        }
    }

    /************************
     * Icon
     */
    fun setIconNormal(icon: Drawable?): DrawableTextView {
        iconNormal = icon
        mIcon = icon
        setIcon()
        return this
    }

    fun setIconPressed(icon: Drawable?): DrawableTextView {
        iconPressed = icon
        mIcon = icon
        setIcon()
        return this
    }

    fun setIconUnable(icon: Drawable?): DrawableTextView {
        iconUnable = icon
        mIcon = icon
        setIcon()
        return this
    }

    fun setIconSize(iconWidth: Int, iconHeight: Int): DrawableTextView {
        this.iconWidth = iconWidth
        this.iconHeight = iconHeight
        setIcon()
        return this
    }

    fun setIconWidth(iconWidth: Int): DrawableTextView {
        this.iconWidth = iconWidth
        setIcon()
        return this
    }

    fun setIconHeight(iconHeight: Int): DrawableTextView {
        this.iconHeight = iconHeight
        setIcon()
        return this
    }

    fun setIconDirection(iconDirection: Int): DrawableTextView {
        this.iconDirection = iconDirection
        setIcon()
        return this
    }

    private fun setIcon() { //未设置图片大小
        if (iconHeight == 0 && iconWidth == 0) {
            if (mIcon != null) {
                iconWidth = mIcon!!.intrinsicWidth
                iconHeight = mIcon!!.intrinsicHeight
            }
        }
        setIcon(mIcon, iconWidth, iconHeight, iconDirection)
    }

    private fun setIcon(drawable: Drawable?, width: Int, height: Int, direction: Int) {
        if (drawable != null) {
            if (width != 0 && height != 0) {
                drawable.setBounds(0, 0, width, height)
            }
            when (direction) {
                ICON_DIR_LEFT -> setCompoundDrawables(
                    drawable,
                    null,
                    null,
                    null
                )
                ICON_DIR_TOP -> setCompoundDrawables(
                    null,
                    drawable,
                    null,
                    null
                )
                ICON_DIR_RIGHT -> setCompoundDrawables(
                    null,
                    null,
                    drawable,
                    null
                )
                ICON_DIR_BOTTOM -> setCompoundDrawables(
                    null,
                    null,
                    null,
                    drawable
                )
            }
        }
    }

    /************************
     * text color
     */
    fun setTextColorNormal(textColor: Int): DrawableTextView {
        textColorNormal = textColor
        if (pressedTextColor == 0) {
            pressedTextColor = textColorNormal
        }
        if (textColorUnable == 0) {
            textColorUnable = textColorNormal
        }
        setTextColor()
        return this
    }

    fun setPressedTextColor(textColor: Int): DrawableTextView {
        pressedTextColor = textColor
        setTextColor()
        return this
    }

    fun setTextColorUnable(textColor: Int): DrawableTextView {
        textColorUnable = textColor
        setTextColor()
        return this
    }

    fun setTextColor(normal: Int, pressed: Int, unable: Int) {
        textColorNormal = normal
        pressedTextColor = pressed
        textColorUnable = unable
        setTextColor()
    }

    private fun setTextColor() {
        val colors =
            intArrayOf(pressedTextColor, pressedTextColor, textColorNormal, textColorUnable)
        mTextColorStateList = ColorStateList(states, colors)
        setTextColor(mTextColorStateList)
    }

    /*********************
     * border
     */
    fun setBorderWidthNormal(width: Int): DrawableTextView {
        borderWidthNormal = width
        if (!mHasPressedBorderWidth) {
            borderWidthPressed = borderWidthNormal
            setBorderPressed()
        }
        if (!mHasUnableBorderWidth) {
            borderWidthUnable = borderWidthNormal
            setBorderUnable()
        }
        setBorderNormal()
        return this
    }

    fun setBorderColorNormal(color: Int): DrawableTextView {
        borderColorNormal = color
        if (!mHasPressedBorderColor) {
            borderColorPressed = borderColorNormal
            setBorderPressed()
        }
        if (!mHasUnableBorderColor) {
            borderColorUnable = borderColorNormal
            setBorderUnable()
        }
        setBorderNormal()
        return this
    }

    fun setBorderWidthPressed(width: Int): DrawableTextView {
        borderWidthPressed = width
        mHasPressedBorderWidth = true
        setBorderPressed()
        return this
    }

    fun setBorderColorPressed(color: Int): DrawableTextView {
        borderColorPressed = color
        mHasPressedBorderColor = true
        setBorderPressed()
        return this
    }

    fun setBorderWidthUnable(width: Int): DrawableTextView {
        borderWidthUnable = width
        mHasUnableBorderWidth = true
        setBorderUnable()
        return this
    }

    fun setBorderColorUnable(color: Int): DrawableTextView {
        borderColorUnable = color
        mHasUnableBorderColor = true
        setBorderUnable()
        return this
    }

    fun setBorderWidth(normal: Int, pressed: Int, unable: Int) {
        borderWidthNormal = normal
        borderWidthPressed = pressed
        borderWidthUnable = unable
        mHasPressedBorderWidth = true
        mHasUnableBorderWidth = true
        setBorder()
    }

    fun setBorderColor(normal: Int, pressed: Int, unable: Int) {
        borderColorNormal = normal
        borderColorPressed = pressed
        borderColorUnable = unable
        mHasPressedBorderColor = true
        mHasUnableBorderColor = true
        setBorder()
    }

    var borderDashWidth: Float
        get() = mBorderDashWidth
        set(dashWidth) {
            mBorderDashWidth = dashWidth
            setBorder()
        }

    var borderDashGap: Float
        get() = mBorderDashGap
        set(dashGap) {
            mBorderDashGap = dashGap
            setBorder()
        }

    fun setBorderDash(dashWidth: Float, dashGap: Float) {
        mBorderDashWidth = dashWidth
        mBorderDashGap = dashGap
        setBorder()
    }

    private fun setBorder() {
        mBackgroundNormal!!.setStroke(
            borderWidthNormal,
            borderColorNormal,
            mBorderDashWidth,
            mBorderDashGap
        )
        mBackgroundPressed!!.setStroke(
            borderWidthPressed,
            borderColorPressed,
            mBorderDashWidth,
            mBorderDashGap
        )
        mBackgroundUnable!!.setStroke(
            borderWidthUnable,
            borderColorUnable,
            mBorderDashWidth,
            mBorderDashGap
        )
        setBackgroundState(false)
    }

    private fun setBorderNormal() {
        mBackgroundNormal!!.setStroke(
            borderWidthNormal,
            borderColorNormal,
            mBorderDashWidth,
            mBorderDashGap
        )
        setBackgroundState(false)
    }

    private fun setBorderPressed() {
        mBackgroundPressed!!.setStroke(
            borderWidthPressed,
            borderColorPressed,
            mBorderDashWidth,
            mBorderDashGap
        )
        setBackgroundState(false)
    }

    private fun setBorderUnable() {
        mBackgroundUnable!!.setStroke(
            borderWidthUnable,
            borderColorUnable,
            mBorderDashWidth,
            mBorderDashGap
        )
        setBackgroundState(false)
    }

    /*********************
     * radius
     */
    var cornerRadius: Float
        get() = mCornerRadius
        set(radius) {
            mCornerRadius = radius
            setRadius()
        }

    fun setCornerRadiusTopLeft(topLeft: Float): DrawableTextView {
        mCornerRadius = -1f
        cornerRadiusTopLeft = topLeft
        setRadius()
        return this
    }

    fun setCornerRadiusTopRight(topRight: Float): DrawableTextView {
        mCornerRadius = -1f
        cornerRadiusTopRight = topRight
        setRadius()
        return this
    }

    fun setCornerRadiusBottomRight(bottomRight: Float): DrawableTextView {
        mCornerRadius = -1f
        cornerRadiusBottomRight = bottomRight
        setRadius()
        return this
    }

    fun setCornerRadiusBottomLeft(bottomLeft: Float): DrawableTextView {
        mCornerRadius = -1f
        cornerRadiusBottomLeft = bottomLeft
        setRadius()
        return this
    }

    fun setCornerRadius(
        topLeft: Float,
        topRight: Float,
        bottomRight: Float,
        bottomLeft: Float
    ) {
        mCornerRadius = -1f
        cornerRadiusTopLeft = topLeft
        cornerRadiusTopRight = topRight
        cornerRadiusBottomRight = bottomRight
        cornerRadiusBottomLeft = bottomLeft
        setRadius()
    }

    private fun setRadiusRadii() {
        mBackgroundNormal!!.cornerRadii = mBorderRadii
        mBackgroundPressed!!.cornerRadii = mBorderRadii
        mBackgroundUnable!!.cornerRadii = mBorderRadii
        setBackgroundState(false)
    }

    private fun setRadius() {
        if (mCornerRadius >= 0) {
            mBorderRadii[0] = mCornerRadius
            mBorderRadii[1] = mCornerRadius
            mBorderRadii[2] = mCornerRadius
            mBorderRadii[3] = mCornerRadius
            mBorderRadii[4] = mCornerRadius
            mBorderRadii[5] = mCornerRadius
            mBorderRadii[6] = mCornerRadius
            mBorderRadii[7] = mCornerRadius
            setRadiusRadii()
            return
        }
        if (mCornerRadius < 0) {
            mBorderRadii[0] = cornerRadiusTopLeft
            mBorderRadii[1] = cornerRadiusTopLeft
            mBorderRadii[2] = cornerRadiusTopRight
            mBorderRadii[3] = cornerRadiusTopRight
            mBorderRadii[4] = cornerRadiusBottomRight
            mBorderRadii[5] = cornerRadiusBottomRight
            mBorderRadii[6] = cornerRadiusBottomLeft
            mBorderRadii[7] = cornerRadiusBottomLeft
            setRadiusRadii()
            return
        }
    }

    private fun dp2px(dp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        )
    }

    /**
     * 手势处理
     */
    internal inner class SimpleOnGesture : SimpleOnGestureListener() {
        override fun onShowPress(e: MotionEvent) {
            if (iconPressed != null) {
                mIcon = iconPressed
                setIcon()
            }
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            if (iconNormal != null) {
                mIcon = iconNormal
                setIcon()
            }
            return false
        }
    }

    companion object {
        //default value
        const val ICON_DIR_LEFT = 1
        const val ICON_DIR_TOP = 2
        const val ICON_DIR_RIGHT = 3
        const val ICON_DIR_BOTTOM = 4

    }

    init {
        mTouchSlop = ViewConfiguration.get(mContext).scaledTouchSlop
        mGestureDetector = GestureDetector(mContext, SimpleOnGesture())
        initAttributeSet(mContext, attrs)
    }



}