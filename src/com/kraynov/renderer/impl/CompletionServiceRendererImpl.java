package com.kraynov.renderer.impl;

import com.kraynov.renderer.ImageData;
import com.kraynov.renderer.ImageInfo;

import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceRendererImpl extends AbstractRenderer {
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Override
    public void render(CharSequence source) {
        List<ImageInfo> imageInfos = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executor);

        for (final ImageInfo info : imageInfos) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return info.downloadImage();
                }
            });
        }

        renderText(source);

        for (int i = 0; i < imageInfos.size(); i++) {
            try {
                renderImage(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void stop() {
        executor.shutdown();
    }
}
