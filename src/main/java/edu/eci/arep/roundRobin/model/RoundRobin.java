package edu.eci.arep.roundRobin.model;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobin {

    private final AtomicInteger iterator;
    private final ConcurrentHashMap<Integer, Integer> content;

    public RoundRobin(ArrayList<Integer> elements) {
        iterator = new AtomicInteger(-1);
        content = new ConcurrentHashMap<>();
        AtomicInteger elementIndex = new AtomicInteger(-1);
        elements.forEach(element -> content.put(elementIndex.addAndGet(1), element));
    }

    public ConcurrentHashMap<Integer, Integer> getContent() {
        return content;
    }

    public int getNext() {
        if (iterator.get() == content.size() - 1)
            iterator.set(-1);
        return content.get(iterator.addAndGet(1));
    }
}