package com.kraynov.renderer;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadRenderer implements PageRenderer {

    @Override
    public void render(CharSequence source) {
        renderText(source);
        List<ImageInfo> images = new ArrayList();
        for (ImageInfo imageInfo :
                scanForImageInfo(source)) {
            images.add(imageInfo);
        }
        renderImages(images);
    }

    private ImageInfo[] scanForImageInfo(CharSequence source) {
        return new ImageInfo[5];
    }

    private void renderText(CharSequence source) {
        System.out.println("rendering text");
    }

    private


}
