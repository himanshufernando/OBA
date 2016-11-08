package tkhub.project.mscoba.MyClass.Font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Himanshu on 11/26/2015.
 */
public class FontTitle extends TextView {
    public FontTitle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FontTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontTitle(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNeueLTStd-Th.otf");
        setTypeface(tf);
    }

}
