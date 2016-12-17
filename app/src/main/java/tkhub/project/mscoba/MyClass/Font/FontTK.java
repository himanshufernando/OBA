package tkhub.project.mscoba.MyClass.Font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Himanshu on 11/26/2015.
 */
public class FontTK extends TextView {
    public FontTK(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FontTK(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontTK(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/neuropol.ttf");
        setTypeface(tf);
    }

}
