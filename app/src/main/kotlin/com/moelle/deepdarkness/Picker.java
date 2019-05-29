package com.moelle.deepdarkness;

import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.jaredrummler.android.colorpicker.ColorPanelView;
import com.jaredrummler.android.colorpicker.ColorPickerView;
import com.jaredrummler.android.colorpicker.ColorPickerView.OnColorChangedListener;

public class Picker extends AppCompatActivity implements OnColorChangedListener, View.OnClickListener {

    private ColorPickerView colorPickerView;
    private ColorPanelView newColorPanelView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);

        setContentView(R.layout.picker);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int initialColor = prefs.getInt("color_3", 0xFF000000);

        colorPickerView = findViewById(R.id.cpv_color_picker_view);
        ColorPanelView colorPanelView = findViewById(R.id.cpv_color_panel_old);
        newColorPanelView = findViewById(R.id.cpv_color_panel_new);

        Button btnOK = findViewById(R.id.okButton);
        Button btnCancel = findViewById(R.id.cancelButton);

        ((LinearLayout) colorPanelView.getParent()).setPadding(colorPickerView.getPaddingLeft(), 0,
                colorPickerView.getPaddingRight(), 0);

        colorPickerView.setOnColorChangedListener(this);
        colorPickerView.setColor(initialColor, true);
        colorPanelView.setColor(initialColor);

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override public void onColorChanged(int newColor) {
        newColorPanelView.setColor(colorPickerView.getColor());
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okButton:
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
                edit.putInt("color_3", colorPickerView.getColor());
                edit.apply();
                finish();
                break;
            case R.id.cancelButton:
                finish();
                break;
        }
    }
}