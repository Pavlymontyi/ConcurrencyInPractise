package com.kraynov.renderer.impl;

import com.kraynov.renderer.ImageData;
import com.kraynov.renderer.ImageInfo;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadRenderer extends AbstractRenderer {

    @Override
    public void render(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList();
        for (ImageInfo imageInfo : scanForImageInfo(source)) {
            imageData.add(imageInfo.downloadImage());
        }
        for (ImageData data: imageData) {
            renderImage(data);
        }
    }

    @Override
    public void stop() {
        //nothing to do
    }
}
