package com.lagorta.fireility.util;

import android.content.Context;
import android.widget.Toast;

public final class Util {

    public static void showToast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }
}
