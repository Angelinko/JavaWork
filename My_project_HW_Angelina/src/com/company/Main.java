package com.company;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException, DummyException {

        List<String> list = new ArrayList<>();
        list.add("Раз");
        list.add("Два");
        list.add("Три");

        try (Connection connection = new ConnectionImpl();
             Session session = connection.createSession(true)) {
            Destination destination = session.createDestination("que01");
            Producer producer = session.createProducer(destination);
            for (String s : list) {
                Thread.sleep(2000);
                producer.send(s);
            }
        }
    }
}
