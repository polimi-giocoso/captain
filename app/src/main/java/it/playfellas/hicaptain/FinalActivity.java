/*
 * Copyright 2015 Lorenzo Affetti, Giacomo Bresciani, Stefano Cappa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.playfellas.hicaptain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.playfellas.hicaptain.sounds.Baraldi;

/**
 * Created by Stefano Cappa on 21/09/15.
 */
public class FinalActivity extends AppCompatActivity {

    @Bind(R.id.captainImageView)
    ImageButton captainImageView;
    @Bind(R.id.skipSoundButton)
    Button skipSoundButton;

    private boolean greetingsEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.greetingsEnabled = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    @OnClick(R.id.captainImageView)
    public void onClick(View v) {
        if (greetingsEnabled) {
            Baraldi.greet(this, new Runnable() {
                @Override
                public void run() {
                    greetingsEnabled = true;
                }
            });
        }
        greetingsEnabled = false;
    }

    @OnClick(R.id.skipSoundButton)
    public void skipSound(View v) {
        Baraldi.shutUp(true);
    }
}
