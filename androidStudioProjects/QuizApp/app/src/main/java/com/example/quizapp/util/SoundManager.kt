package com.example.quizapp.util

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

class SoundManager(context: Context) {

    private val soundPool: SoundPool
    private var correctSoundId: Int = 0
    private var wrongSoundId: Int = 0
    private var tickSoundId: Int = 0
    private var loaded = false

    init {
        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(3)
            .setAudioAttributes(attrs)
            .build()

        soundPool.setOnLoadCompleteListener { _, _, _ -> loaded = true }

        // These would reference actual sound files in res/raw/
        // For now we use Android system defaults via ToneGenerator as fallback
    }

    fun playCorrect() {
        if (loaded && correctSoundId != 0) {
            soundPool.play(correctSoundId, 1f, 1f, 1, 0, 1f)
        }
    }

    fun playWrong() {
        if (loaded && wrongSoundId != 0) {
            soundPool.play(wrongSoundId, 1f, 1f, 1, 0, 1f)
        }
    }

    fun playTick() {
        if (loaded && tickSoundId != 0) {
            soundPool.play(tickSoundId, 0.5f, 0.5f, 0, 0, 1f)
        }
    }

    fun release() {
        soundPool.release()
    }
}
