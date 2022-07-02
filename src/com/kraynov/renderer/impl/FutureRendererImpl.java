package com.kraynov.renderer.impl;

import com.kraynov.renderer.ImageData;
import com.kraynov.renderer.ImageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureRendererImpl extends AbstractRenderer {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void render(final CharSequence source) {
        Future<List<ImageData>> downloadImagesFuture = executor.submit(new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> images = new ArrayList<ImageData>();
                for (ImageInfo info : scanForImageInfo(source)) {
                    images.add(info.downloadImage());
                }
                return images;
            }
        });

        renderText(source);
        List<ImageData> images = new ArrayList<ImageData>();
        try {
            images = downloadImagesFuture.get();
            for(ImageData image: images) {
                renderImage(image);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            downloadImagesFuture.cancel(true);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        executor.shutdown();
    }
}
