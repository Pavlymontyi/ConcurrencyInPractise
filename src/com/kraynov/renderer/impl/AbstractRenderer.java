package com.kraynov.renderer.impl;

import com.kraynov.renderer.ImageData;
import com.kraynov.renderer.ImageInfo;
import com.kraynov.renderer.PageRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class AbstractRenderer implements PageRenderer {

    protected List<ImageInfo> scanForImageInfo(CharSequence source) {
        Random random = new Random();
        return Arrays.asList(new ImageInfo("ImageLink: " + random.nextInt(200)),
                new ImageInfo("ImageLink: " + random.nextInt(200)),
                new ImageInfo("ImageLink: " + random.nextInt(200)),
                new ImageInfo("ImageLink: " + random.nextInt(200)),
                new ImageInfo("ImageLink: " + random.nextInt(200)));
    }

    protected void renderText(CharSequence source) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("rendering text by "+Thread.currentThread());
    }

    protected void renderImage(ImageData data) {
        System.out.println("rendering image: "+data + " by "+Thread.currentThread());
    }
}
