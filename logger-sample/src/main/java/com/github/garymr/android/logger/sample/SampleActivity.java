package com.github.garymr.android.logger.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.garymr.android.logger.Level;
import com.github.garymr.android.logger.Logger;

import java.util.HashMap;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        Logger.v("onCreate");

        findViewById(R.id.logv_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.v("This is a %s level of the log", "VERBOSE");
            }
        });

        findViewById(R.id.logd_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d("This is a %s level of the log", "DEBUG");
            }
        });

        findViewById(R.id.logi_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.i("This is a %s level of the log", "INFO");
            }
        });

        findViewById(R.id.logw_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    throw new IllegalArgumentException();
                } catch (Exception e) {
                    Logger.w(e, "This is a %s level of the log", "WARN");
                }
            }
        });

        findViewById(R.id.loge_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    throw new IllegalArgumentException();
                } catch (Exception e) {
                    Logger.e(e, "This is a %s level of the log", "ERROR");
                }
            }
        });

        findViewById(R.id.loga_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.wtf("This is a %s level of the log", "ASSERT");
            }
        });

        findViewById(R.id.logd_custom_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.tag("LoggerSample-MYTAG").d("This is a %s level of the log", "DEBUG");
            }
        });

        findViewById(R.id.log_json_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.json(Level.DEBUG, "{\"total\":1,\"reult\":[{\"code\":\"JD\",\"name\":\"北京首都航空有限公司\"}],\"error_code\":0,\"reason\":\"Succes\"}");
            }
        });

        findViewById(R.id.log_map_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("error_message", "success");
                map.put("error_code", 100);
                Logger.v(map);
            }
        });
    }
}
