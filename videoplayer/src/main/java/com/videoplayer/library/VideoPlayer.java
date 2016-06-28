package com.videoplayer.library;

import android.content.Context;
import android.util.AttributeSet;

import com.videoplayer.library.ui.video.VideoMediaPlayerView;

/**
 * @author lyric
 * @description
 * @time 2016/6/28 16:32
 */
public class VideoPlayer extends VideoMediaPlayerView {

    public VideoPlayer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VideoPlayer(Context context) {
        super(context);
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
