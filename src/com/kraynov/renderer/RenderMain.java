package com.kraynov.renderer;

import com.kraynov.renderer.impl.FutureRendererImpl;
import com.kraynov.renderer.impl.SingleThreadRenderer;

public class RenderMain {

    public static void main(String[] args) {
        PageRenderer renderer = new FutureRendererImpl();
        renderer.render("sdsadsadsa");
        renderer.stop();
    }
}
