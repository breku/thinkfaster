package com.kcal.model;

/**
 * User: Breku
 * Date: 02.07.14
 */
public class Counter {

    private String id;

    private long seq;

    public Counter(String id, long seq) {
        this.id = id;
        this.seq = seq;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}
