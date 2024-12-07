package org.example;
import java.util.UUID;
public class Commit {

    private String hash;
    private String message;
    private Commit parent;

    public Commit(String message, Commit parent) {
        this.message = message;
        this.parent = parent;
        this.hash = generateHash();
    }

    public String getHash() {
        return hash;
    }

    private String generateHash() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Commit{" +
                "hash='" + hash + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

