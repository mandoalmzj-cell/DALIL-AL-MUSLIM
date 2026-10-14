
package com.mando.deenpro;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final int bg = Color.rgb(8, 31, 26);
    private final int card = Color.rgb(18, 57, 46);
    private final int gold = Color.rgb(232, 204, 120);
    private final int white = Color.WHITE;
    private final int muted = Color.rgb(190, 207, 198);

    private LinearLayout root;
    private LinearLayout content;
    private LinearLayout nav;
    private SharedPreferences prefs;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("MandoMuslim", MODE_PRIVATE);
        count = prefs.getInt("tasbih_count", 0);

        getWindow().setStatusBarColor(bg);
        getWindow().setNavigationBarColor(bg);

        showHome();
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private LinearLayout.LayoutParams lp(int w, int h) {
        return new LinearLayout.LayoutParams(w, h);
    }

    private TextView label(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER_VERTICAL);
        t.setTypeface(Typeface.DEFAULT);
        t.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        return t;
    }

    private void setup(String page) {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(bg);
        root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(18, 18, 18, 22);
        content.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(
                -1, 0, 1));

        nav = new LinearLayout(this);
        nav.setOrientation(LinearLayout.HORIZONTAL);
        nav.setGravity(Gravity.CENTER);
        nav.setPadding(4, 7, 4, 7);
        nav.setBackgroundColor(card);

        addNav("⌂", "الرئيسية", this::showHome);
        addNav("▤", "القرآن", this::showQuran);
        addNav("◉", "القبلة", this::showQibla);
        addNav("☰", "المزيد", this::showMore);

        root.addView(nav, lp(-1, 66));
        setContentView(root);
    }

    private void addNav(String icon, String title, Runnable action) {
        LinearLayout item = new LinearLayout(this);
        item.setOrientation(LinearLayout.VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.setClickable(true);
        item.setFocusable(true);

        TextView symbol = label(icon, 23, gold);
        symbol.setGravity(Gravity.CENTER);
        TextView name = label(title, 11, white);
        name.setGravity(Gravity.CENTER);

        item.addView(symbol);
        item.addView(name);
        item.setOnClickListener(v -> action.run());

        nav.addView(item, new LinearLayout.LayoutParams(
                0, -1, 1));
    }

    private void heading(String text) {
        TextView t = label(text, 21, gold);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setPadding(2, 22, 2, 12);
        content.addView(t, lp(-1, -2));
    }

    private void paragraph(String text, int size) {
        TextView t = label(text, size, white);
        t.setGravity(Gravity.CENTER_VERTICAL);
        t.setPadding(0, 5, 0, 5);
        content.addView(t, lp(-1, -2));
    }

    private void tile(String icon, String title, String subtitle,
                      Runnable action) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.HORIZONTAL);
        box.setGravity(Gravity.CENTER_VERTICAL);
        box.setPadding(16, 15, 16, 15);
        box.setBackground(shape(card, 22));
        box.setClickable(true);
        box.setFocusable(true);

        TextView symbol = label(icon, 29, gold);
        symbol.setGravity(Gravity.CENTER);
        box.addView(symbol, lp(48, -2));

        LinearLayout words = new LinearLayout(this);
        words.setOrientation(LinearLayout.VERTICAL);
        words.setPadding(12, 0, 8, 0);

        TextView name = label(title, 17, white);
        name.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        words.addView(name);

        TextView desc = label(subtitle, 12, muted);
        words.addView(desc);

        box.addView(words, new LinearLayout.LayoutParams(
                0, -2, 1));

        TextView arrow = label("‹", 28, gold);
        box.addView(arrow);

        LinearLayout.LayoutParams p = lp(-1, -2);
        p.setMargins(0, 5, 0, 7);
        content.addView(box, p);
        box.setOnClickListener(v -> action.run());
    }

    private void showHome() {
        setup("الرئيسية");

        LinearLayout hero = new LinearLayout(this);
        hero.setOrientation(LinearLayout.VERTICAL);
        hero.setPadding(20, 22, 20, 22);
        hero.setBackground(shape(card, 26));

        TextView brand = label("دليل المسلم 💫👳‍♂️", 25, gold);
        brand.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        brand.setGravity(Gravity.CENTER);
        hero.addView(brand, lp(-1, -2));

        TextView welcome = label(
                "رفيقك في القرآن والذكر والعبادة", 15, white);
        welcome.setGravity(Gravity.CENTER);
        welcome.setPadding(0, 10, 0, 5);
        hero.addView(welcome, lp(-1, -2));

        content.addView(hero, lp(-1, -2));

        heading("🌙 يومك مع الله");
        tile("📖", "القرآن الكريم",
                "قراءة السور ومتابعة وردك", this::showQuran);
        tile("🎧", "استماع القرآن",
                "صفحة الاستماع والتلاوات", this::showAudio);
        tile("🤲", "الأذكار والأدعية",
                "أذكار وأدعية يومية", this::showAzkar);

        heading("🕌 عبادتك");
        tile("◉", "اتجاه القبلة",
                "صفحة اتجاه القبلة", this::showQibla);
        tile("🕰", "مواقيت الصلاة",
                "صفحة مواقيت الصلاة", this::showPrayer);
        tile("📿", "السبحة الإلكترونية",
                "عداد محفوظ على جهازك", this::showTasbih);
        tile("✨", "الورد اليومي",
                "تابع إنجاز عباداتك", this::showWird);
    }

    private void page(String title, String description) {
        setup(title);
        heading(title);
        paragraph(description, 16);
        tile("⌂", "العودة للرئيسية",
                "الرجوع إلى الصفحة الرئيسية", this::showHome);
    }

    private void showQuran() {
        page("📖 القرآن الكريم",
                "قسم المصحف يحتاج ربط نص قرآني كامل وموثوق، مع فهرس السور والآيات وحفظ موضع القراءة. لم تتم إضافة نص غير موثوق.");
        tile("🎧", "الاستماع للقرآن",
                "فتح صفحة التلاوات", this::showAudio);
    }

    private void showAudio() {
        page("🎧 استماع القرآن",
                "مشغل التلاوات يحتاج ربط ملفات صوتية موثوقة، مع اختيار القارئ والسورة وأزرار التشغيل والإيقاف.");
    }

    private void showQibla() {
        page("🧭 اتجاه القبلة",
                "الاتجاه الدقيق يتطلب موقع الهاتف وحساسات البوصلة ومعايرة الجهاز. هذه الصفحة لا تعرض اتجاهًا وهميًا.");
    }

    private void showPrayer() {
        page("🕌 مواقيت الصلاة",
                "حساب المواقيت يتطلب موقعك وطريقة حساب معتمدة، ثم إضافة جدول الصلاة والتنبيهات.");
    }

    private void showAzkar() {
        setup("🤲 الأذكار والأدعية");
        heading("أذكار قصيرة");

        String[] azkar = {
                "سبحان الله وبحمده",
                "أستغفر الله وأتوب إليه",
                "لا إله إلا الله",
                "اللهم صل وسلم على نبينا محمد ﷺ"
        };

        for (String z : azkar) {
            tile("🤲", z, "ذكر", () ->
                    Toast.makeText(this, z,
                            Toast.LENGTH_SHORT).show());
        }
        tile("⌂", "الرئيسية", "العودة", this::showHome);
    }

    private void showTasbih() {
        setup("📿 السبحة الإلكترونية");
        heading("سبحان الله وبحمده");

        TextView number = label(String.valueOf(count), 56, gold);
        number.setGravity(Gravity.CENTER);
        content.addView(number, lp(-1, 130));

        paragraph("عدد التسبيحات المحفوظ", 15);

        tile("＋", "تسبيحة جديدة",
                "اضغط لزيادة العداد", () -> {
                    count++;
                    prefs.edit().putInt("tasbih_count", count).apply();
                    showTasbih();
                });

        tile("↻", "تصفير العداد",
                "إعادة العدد إلى صفر", () -> {
                    count = 0;
                    prefs.edit().putInt("tasbih_count", 0).apply();
                    showTasbih();
                });

        tile("⌂", "الرئيسية", "العودة", this::showHome);
    }

    private void showWird() {
        setup("✨ الورد اليومي");
        heading("إنجاز اليوم");

        String[] tasks = {
                "قراءة القرآن",
                "أذكار الصباح",
                "أذكار المساء",
                "الصلاة على النبي ﷺ",
                "الاستغفار"
        };

        for (String task : tasks) {
            String key = "wird_" + task;
            boolean done = prefs.getBoolean(key, false);

            tile(done ? "✅" : "◯", task,
                    done ? "تم الإنجاز اليوم" : "اضغط لتسجيل الإنجاز",
                    () -> {
                        prefs.edit().putBoolean(key, !prefs.getBoolean(key, false)).apply();
                        showWird();
                    });
        }

        tile("⌂", "الرئيسية", "العودة", this::showHome);
    }

    private void showMore() {
        setup("المزيد");
        heading("المزيد من الخدمات");

        tile("📿", "السبحة", "العداد المحفوظ", this::showTasbih);
        tile("🤲", "الأذكار", "الأذكار اليومية", this::showAzkar);
        tile("✨", "الورد اليومي", "متابعة الإنجاز", this::showWird);
        tile("⚙", "الإدارة", "إعدادات الإدارة تحتاج تأمينًا وربطًا بالخادم",
                () -> new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("لوحة الإدارة")
                        .setMessage("لم يتم تفعيل لوحة الإدارة المركزية بعد. لن نعرضها كأنها تعمل قبل إعداد المصادقة والخادم.")
                        .setPositiveButton("حسنًا", null)
                        .show());
        tile("⌂", "الرئيسية", "العودة", this::showHome);
    }
}
