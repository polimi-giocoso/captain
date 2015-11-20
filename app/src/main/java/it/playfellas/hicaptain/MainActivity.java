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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
public class MainActivity extends ImmersiveAppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.centralImageView)
    ImageButton centralImageView;
    @Bind(R.id.captainImageView)
    ImageButton captainImageView;
    @Bind(R.id.pruaImageView)
    ImageButton pruaImageView;
    @Bind(R.id.skipSoundButton)
    Button skipSoundButton;

    private boolean captainEnabled;
    private boolean pruaEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        this.captainEnabled = true;
        this.pruaEnabled = false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.captainEnabled = true;
        this.pruaEnabled = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.captainImageView)
    public void captainClicked(View v) {
        if (captainEnabled && !pruaEnabled) {
            Log.d(TAG, "Captain clicked");
            this.captainEnabled = false;
            this.startCaptainSound();
        }
    }

    @OnClick(R.id.pruaImageView)
    public void pruaClicked(View v) {
        if (!captainEnabled && pruaEnabled) {
            Log.d(TAG, "Prua clicked");
            this.startPruaSound();
            this.pruaEnabled = false;
        }
    }

    private void startCaptainSound() {
        Baraldi.welcome(this, new Runnable() {
            @Override
            public void run() {
                captainSoundFinished();
            }
        });
    }

    private void captainSoundFinished() {
        pruaEnabled = true;
    }

    private void startPruaSound() {
        Baraldi.askHelp(this, new Runnable() {
            @Override
            public void run() {
                pruaSoundFinished();
            }
        });
    }

    private void pruaSoundFinished() {
        startActivity(new Intent(this, ObloActivity.class));
    }

    @OnClick(R.id.skipSoundButton)
    public void skipSound(View v) {
        Baraldi.shutUp(true);
    }
}
