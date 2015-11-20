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
package it.playfellas.hicaptain.sounds;

import android.content.Context;
import android.media.MediaPlayer;

import it.playfellas.hicaptain.R;

/**
 * Created by affo on 21/09/15.
 */
public class Baraldi {
    private static MediaPlayer player = null;
    private static Runnable cb = null;

    private static MediaPlayer getPlayer(Context ctxt, int soundID, final Runnable onComplete) {
        shutUp(false);
        player = MediaPlayer.create(ctxt, soundID);
        if (onComplete != null) {
            cb = onComplete;
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    onComplete.run();
                }
            });
        }
        return player;
    }

    public static void shutUp(boolean runCBs) {
        if (player != null) {
            player.stop();
            player.release();
            if (runCBs && cb != null) {
                cb.run();
            }
        }
        player = null;
        cb = null;
    }

    /**
     * @param ctxt
     * @param onComplete can be `null` if no callback is required
     */
    public static void welcome(Context ctxt, final Runnable onComplete) {
        getPlayer(ctxt, R.raw.welcome, onComplete).start();
    }

    public static void askHelp(Context ctxt, final Runnable onComplete) {
        getPlayer(ctxt, R.raw.help, onComplete).start();
    }

    public static void greet(Context ctxt, final Runnable onComplete) {
        getPlayer(ctxt, R.raw.greetings, onComplete).start();
    }
}
