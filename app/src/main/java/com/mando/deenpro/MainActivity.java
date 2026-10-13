package com.mando.deenpro;

import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final int green = Color.rgb(12, 48, 38);
    private final int gold = Color.rgb(232, 204, 120);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(green);
        getWindow().setNavigationBarColor(green);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(24, 32, 24, 24);
        root.setBackgroundColor(green);
        root.setLayoutDirection(android.view.View.LAYOUT_DIRECTION_RTL);

        TextView title = new TextView(this);
        title.setText("دليل المسلم 💫👳‍♂️");
        title.setTextSize(27);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setTextColor(gold);
        title.setGravity(Gravity.CENTER);
        root.addView(title, new LinearLayout.LayoutParams(-1, -2));

        TextView subtitle = new TextView(this);
        subtitle.setText("رفيقك للقرآن والذكر والعبادة");
        subtitle.setTextSize(16);
        subtitle.setTextColor(Color.WHITE);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 12, 0, 28);
        root.addView(subtitle, new LinearLayout.LayoutParams(-1, -2));

        addSection(root, "📖 القرآن الكريم");
        addSection(root, "🤲 الأذكار والأدعية");
        addSection(root, "🕌 مواقيت الصلاة");
        addSection(root, "🧭 اتجاه القبلة");
        addSection(root, "📿 السبحة الإلكترونية");
        addSection(root, "🌙 الورد اليومي");

        TextView footer = new TextView(this);
        footer.setText("نسأل الله أن ينفع به الجميع 💚");
        footer.setTextColor(gold);
        footer.setTextSize(14);
        footer.setGravity(Gravity.CENTER);
        footer.setPadding(0, 24, 0, 0);
        root.addView(footer);

        setContentView(root);
    }

    private void addSection(LinearLayout root, String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextSize(17);
        button.setTextColor(green);
        button.setAllCaps(false);
        button.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(gold));

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(-1, -2);
        params.setMargins(0, 5, 0, 5);
        root.addView(button, params);
    }
}
