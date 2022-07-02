package com.kraynov.renderer;

public class ImageData {
    private String imageData;
    public ImageData(String imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "Some image data{" +
                "imageData='" + imageData + '\'' +
                '}';
    }
}
