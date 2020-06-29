package com.company;

public class Pair {
    int firstId;

    public int getFirstId() {
        return firstId;
    }

    public void setFirstId(int firstId) {
        this.firstId = firstId;
    }

    public int getSecondId() {
        return secondId;
    }

    public void setSecondId(int secondId) {
        this.secondId = secondId;
    }

    int secondId;

    @Override
    public String toString() {
        return String.format("%d, %d", firstId, secondId);
    }
}
