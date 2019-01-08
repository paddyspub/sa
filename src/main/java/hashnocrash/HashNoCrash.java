package hashnocrash;

import java.util.List;

public class HashNoCrash {
    private static final long ONE_BILLION = 10000000000L;
    private static List<Long> buckets;
    // This hash will take 1-8 chars and then sum the ascii values of them to create buckets.
    // There are up to 8 characters of 0-9a-zA-AZ for the key which is (10 + 26 + 26) ^ 8 which is
    // 218,340,105,584,896 possible keys.  The requirements state that there shall be no collisions for up to 1 billion
    // keys.
    //
    // 1 billion buckets will be created by bitwise XORing the ascii values of each character
    public long hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); ++i) {
           hash ^= (int) key.charAt(i);
        }
        return hash % ONE_BILLION;
    }

    // given a key, hash it, search for the hash in the list of buckets, if found add it to that bucket's
    // array list, else if that bucket doesnt exist, add it
    private boolean put(String key, Object value) {
        long hash = hash(key);
        for (Long bucket : buckets) {
            if (bucket == hash);
            // add it to the array list
        }
        // wasnt in a bucket, add to the buckets
        buckets.add(key);
        return true;
    }

    // Take in the key, hash it, see if there is a bucket for it, if so find it in that buckets array list and return
    // the object, if not in that buckets array list return null. If no bucket for that hash exists, return null
    private Object get(String key) {
        long hash = hash(key);
        for (Long bucket : buckets) {
            if (bucket == hash);
            // search for it in the array list, return it or return null if its not in the array list
        }
        // wasnt in a bucket, couldnt be in the hash
        return null;
    }

    // Take in the key, hash it, see if there is a bucket for it, if so find it in that buckets array list and delete
    // the object, if not in that buckets array list return null. If no bucket for that hash exists, return null
    private boolean delete (String key) {
        long hash = hash(key);
        for (Long bucket : buckets) {
            if (bucket == hash);
            // search for it in the array list, return it or return null if its not in the array list
            return true;
        }
        // wasnt in a bucket, couldnt be in the hash
        return false;
    }

    // Take in the key, hash it, see if there is a bucket for it, if so find it in that buckets array list and return
    // true. If not in that buckets array list return false. If no bucket for that hash exists, return false
    private boolean containsKey (String key) {
        long hash = hash(key);
        for (Long bucket : buckets) {
            if (bucket == hash);
            // search for it in the array list, return it or return null if its not in the array list
            return true;
        }
        // wasnt in a bucket, couldnt be in the hash
        return false;
    }
}
