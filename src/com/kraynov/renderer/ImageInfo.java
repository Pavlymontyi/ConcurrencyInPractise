package com.kraynov.renderer;

import java.util.Random;

public class ImageInfo {
    final String imageLink;

    public ImageInfo(String imageLink) {
        this.imageLink = imageLink;
    }

    public ImageData downloadImage() {
        System.out.println("Downloading image by "+Thread.currentThread());
        return new ImageData("Image downloaded from: "+imageLink);
    }
}
