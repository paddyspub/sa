package hashnocrash;

import java.util.List;

public class Bucket {
    private int hash;
    private List<HashEntry> hashEntries;

    public Bucket(int hash, List<HashEntry> hashEntries) {
        this.hash = hash;
        this.hashEntries = hashEntries;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public List<HashEntry> getHashEntries() {
        return hashEntries;
    }

    public void setHashEntries(List<HashEntry> hashEntries) {
        this.hashEntries = hashEntries;
    }
}
