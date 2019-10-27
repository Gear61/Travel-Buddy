package com.randomappsinc.travelbuddy.common;

import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.randomappsinc.travelbuddy.R;
import com.randomappsinc.travelbuddy.util.UIUtil;

public class StandardActivity extends AppCompatActivity {

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        UIUtil.closeKeyboard(this);
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_left_out, R.anim.slide_left_in);
    }

    @Override
    public void finish() {
        UIUtil.closeKeyboard(this);
        super.finish();
        overridePendingTransition(R.anim.slide_right_out, R.anim.slide_right_in);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
