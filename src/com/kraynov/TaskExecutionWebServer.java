package com.kraynov;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class TaskExecutionWebServer {
    private static final int NTHREADS = 2;
    private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);
    private static final String QUIT_COMMAND = "q";

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()){
            try {
                final Socket connection = socket.accept();
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        handleRequest(reader);
                    }
                };
                exec.execute(task);
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown()) {
                    System.out.println("Задача отклонена" + e);
                }
            }
        }
    }

    private static void handleRequest(BufferedReader reader) {
        System.out.println("Handling request started "+Thread.currentThread());
        try {
            if(reader.ready()) {
                String request = reader.readLine();
                System.out.println("request: "+request);
                if (QUIT_COMMAND.equalsIgnoreCase(request)) {
                    System.out.println("Quiting");
                    exec.shutdown();
                } else {
                    if (new Random().nextInt(10) < 5) throw new RuntimeException("MyException");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Client {

        public static void main(String[] args) throws IOException, InterruptedException {
            for (int i = 0; i < 10; i++) {
                Socket socket = new Socket("localhost", 80);
                OutputStream outputStream = socket.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write("321");
                writer.flush();
                socket.close();
                Thread.sleep(1000);
            }
            Socket socket = new Socket("localhost", 80);
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write("q");
            writer.flush();
            socket.close();
        }
    }
}
