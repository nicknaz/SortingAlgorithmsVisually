package Util;

import exceptions.PairsEmptyException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Action {
    private long duration;
    private Queue<Pair> pairs;

    public Action() {
        this.pairs = new LinkedList<>();
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getSize() {
        return pairs.size();
    }

    public boolean isEmpty() {
        return pairs.isEmpty();
    }

    public Pair getPair() {
        if (pairs.isEmpty()) {
            throw new PairsEmptyException("Все пары закончились");
        }
        return pairs.poll();
    }

    public void addPair(Pair pair) {
        this.pairs.add(pair);
    }
}
