package com.mars.mvvm.component.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.mars.mvvm.common_utils.LogManger;
import com.mars.mvvm.component.enumutils.DrawablePos;

/**
 * @author Mars
 * EditText 编辑框
 * 支持设置图标点击事件处理
 */
public class MyClickerEditText extends AppCompatEditText implements TextWatcher {

    private EtDrawableOnclicker onclicker;
    private DrawablePos[] drawablePos = new DrawablePos[]{
            DrawablePos.LEFT, DrawablePos.RIGHT, DrawablePos.TOP, DrawablePos.BOTTOM
    };

    public Drawable[] getDrawables() {
        return drawables;
    }

    public void setDrawables(Drawable[] drawables) {
        this.drawables = drawables;
        for (Drawable drawable : this.drawables) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
        }
    }

    private Drawable[] drawables;

    public MyClickerEditText(Context context) {
        super(context);
    }

    public MyClickerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyClickerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
        Drawable[] drawables = getCompoundDrawables();
        for (int i = 0; i < drawables.length; i++) {
            Drawable drawable = drawables[i];
            //如果右边没有图片，不再处理
            if (drawable == null) {
                continue;
            }
            //如果不是按下事件，不再处理
            if (event.getAction() != MotionEvent.ACTION_UP) {
                continue;
            }
            if (event.getX() > getWidth()
                    - getPaddingRight()
                    - drawable.getIntrinsicWidth()) {
                if (onclicker != null) {
                    onclicker.clickerPos(drawablePos[i], this);
                    return true;
                }
            }
            continue;
        }
        return super.onTouchEvent(event);
    }

    public void setOnclicker(EtDrawableOnclicker onclicker) {
        this.onclicker = onclicker;
        this.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        LogManger.Companion.logE("s.length", s.length());
        if (s.length() > 0) {
            setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
        } else {
            setCompoundDrawables(null, null, null, null);
        }
    }


    public interface EtDrawableOnclicker {
        void clickerPos(DrawablePos drawablePos, View view);
    }
}
