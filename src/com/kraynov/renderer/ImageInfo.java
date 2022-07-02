package com.kraynov.renderer;

import java.util.Random;

public class ImageInfo {
    final String imageLink;

    public ImageInfo(String imageLink) {
        this.imageLink = imageLink;
    }

    public ImageData downloadImage() {
        int delay = new Random().nextInt(100);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Downloaded image "+ delay +" Bytes by "+Thread.currentThread());
        return new ImageData("Image downloaded from: "+imageLink);
    }
}
