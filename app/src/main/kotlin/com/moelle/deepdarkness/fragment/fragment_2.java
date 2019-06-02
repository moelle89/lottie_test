package com.moelle.deepdarkness.fragment;

import android.content.Context;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.moelle.deepdarkness.R;

import static com.moelle.deepdarkness.MainActivity.DD_Colors;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_2 extends Fragment implements View.OnClickListener {


    public static final String TAG = fragment_2.class.getSimpleName();

    private static final int DIALOG_ID = 0;

    private LinearLayout cat_top1, cat_top2, cat_bottom;
    // image url to download
    private static final String accent6 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/6.png";

    public fragment_2() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(),R.color.transparent));
        View v = inflater.inflate(R.layout.fragment_2, null);
        CardView card1 = v.findViewById(R.id.accent1);
        CardView card4 = v.findViewById(R.id.accent4);

        CardView background1 = v.findViewById(R.id.background1);
        CardView background2 = v.findViewById(R.id.background2);
        CardView background3 = v.findViewById(R.id.background3);
        CardView background4 = v.findViewById(R.id.background4);

        cat_top1 = v.findViewById(R.id.cat_top1);
        cat_top2 = v.findViewById(R.id.cat_top2);
        cat_bottom = v.findViewById(R.id.cat_bottom2);
        cat_top1.setAlpha(0f);
        cat_top2.setAlpha(0f);
        cat_bottom.setAlpha(0f);

        LottieAnimationView keyboard = v.findViewById(R.id.dashboard_head);
        final int fg = ContextCompat.getColor(v.getContext(), R.color.textColor);
        final int bg = ContextCompat.getColor(v.getContext(), R.color.background);
        SimpleColorFilter filterfg = new SimpleColorFilter(fg);
        KeyPath keyfg = new KeyPath("fg", "**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filterfg);
        keyboard.addValueCallback(keyfg, LottieProperty.COLOR_FILTER, callback);
        SimpleColorFilter filterbg = new SimpleColorFilter(bg);
        KeyPath keybg = new KeyPath("bg", "**");
        LottieValueCallback<ColorFilter> callback2 = new LottieValueCallback<ColorFilter>(filterbg);
        keyboard.addValueCallback(keybg, LottieProperty.COLOR_FILTER, callback2);
        keyboard.playAnimation();

        // ini Animations
        Animation vonOben = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_top_to_bottom);
        Animation vonOben2 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_top_to_bottom);
        vonOben2.setStartOffset(70);
        Animation vonOben3 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_top_to_bottom);
        vonOben3.setStartOffset(170);
        Animation vonOben4 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_top_to_bottom);
        vonOben4.setStartOffset(220);
        Animation vonUnten = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten.setStartOffset(170);
        Animation vonUnten2 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten2.setStartOffset(220);
        Animation vonUnten3 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten3.setStartOffset(250);
        Animation vonUnten4 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten4.setStartOffset(280);
        keyboard.setAlpha(0f);
        keyboard.setScaleX(1.3f);
        keyboard.setScaleY(1.3f);
        keyboard.setTranslationY(-100);
        keyboard.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(200).setDuration(600).setInterpolator(new FastOutSlowInInterpolator()).start();
        cat_top1.animate().alpha(1f).setDuration(1000).setStartDelay(200);
        cat_top2.animate().alpha(1f).setDuration(1000).setStartDelay(300);
        cat_bottom.animate().alpha(1f).setDuration(1000).setStartDelay(400);

        card1.setOnClickListener(this);
        card1.setAnimation(vonOben);
        card4.setOnClickListener(this);
        card4.setAnimation(vonOben);

        background1.setAnimation(vonUnten);
        background2.setAnimation(vonUnten2);
        background3.setAnimation(vonUnten3);
        background4.setAnimation(vonUnten4);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accent1: {
                //getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                launchPicker(getView());
                break;
            }
            case R.id.accent4: {
                launchPicker(getView());
                break;
            }
        }
    }

    public void launchPicker(View view) {
        final int Default = ContextCompat.getColor(view.getContext(), R.color.accent1);
        ColorPickerDialog Picker = ColorPickerDialog.newBuilder().create();
        Picker.newBuilder()
                .setDialogTitle(R.string.pickerTitle)
                .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                .setColor(Default)
                .setPresets(DD_Colors)
                .setAllowPresets(true)
                .setDialogId(DIALOG_ID)
                .setShowColorShades(true)
                .setAllowCustom(true)
                .setShowAlphaSlider(false)
                .show(getActivity());
    }
}
