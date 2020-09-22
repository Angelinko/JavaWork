package com.company;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws InterruptedException, DummyException, IOException {
        //"/My_project_HW_Angelina_hw2/messages.dat"
        try (Connection connection = new ConnectionImpl();
             Session session = connection.createSession(true);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(
                             new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            Destination destination = session.createDestination("que01");
            Producer producer = session.createProducer(destination);
            String line;
            while ((line = reader.readLine()) != null) {
                Thread.sleep(2000);
                producer.send(line);
            }
        }
    }
}

