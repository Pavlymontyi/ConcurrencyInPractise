package com.kraynov.renderer;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadRenderer implements PageRenderer {

    @Override
    public void render(CharSequence source) {
        renderText(source);
        List<ImageInfo> imageData = new ArrayList();
        for (ImageInfo imageInfo : scanForImageInfo(source)) {
            imageData.add(imageInfo);
        }
        for (ImageInfo data: imageData) {
            renderImage(data);
        }
    }

    private ImageInfo[] scanForImageInfo(CharSequence source) {
        return new ImageInfo[5];
    }

    private void renderText(CharSequence source) {
        System.out.println("rendering text");
    }

    private void renderImage(ImageInfo data) {
        System.out.println("rendering image: "+data);
    }
}
