package pl.laskowski.marcin.contactapp.ui.screen.colorpicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.parceler.Parcels;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.laskowski.marcin.contactapp.R;
import pl.laskowski.marcin.contactapp.model.Contact;
import pl.laskowski.marcin.contactapp.ui.router.Keys;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ColorPickerActivity
        extends AppCompatActivity {

    private Contact contact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        ButterKnife.bind(this);
        contact = Parcels.unwrap(getIntent().getParcelableExtra(Keys.CONTACT));
    }

    @OnClick({R.id.activityColorPicker_ivBlue,
            R.id.activityColorPicker_ivRed,
            R.id.activityColorPicker_ivGreen})
    public void onColorClicked(View iv) {
        switch (iv.getId()) {
            case R.id.activityColorPicker_ivBlue:
                finishWithColor(R.color.blue);
                break;
            case R.id.activityColorPicker_ivGreen:
                finishWithColor(R.color.green);
                break;
            case R.id.activityColorPicker_ivRed:
                finishWithColor(R.color.red);
                break;
        }
    }

    private void finishWithColor(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(this, colorRes);
        Intent intent = new Intent();
        intent.putExtra(Keys.CONTACT, Parcels.wrap(contact));
        intent.putExtra(Keys.COLOR, color);
        setResult(RESULT_OK, intent);
        finish();
    }

}
