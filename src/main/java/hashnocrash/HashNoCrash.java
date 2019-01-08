package hashnocrash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class HashNoCrash {
    private static final int ONE_BILLION = 1000000000;
    private static List<Bucket> buckets;

    // This hash will take 1-8 chars and then sum the ascii values of them to create buckets.
    // There are up to 8 characters of 0-9a-zA-AZ for the key which is (10 + 26 + 26) ^ 8 which is
    // 218,340,105,584,896 possible keys.  The requirements state that there shall be no collisions for up to 1 billion
    // keys.
    //
    // 1 billion buckets will be created by bitwise XORing the ascii values of each character
    public int hash(String key) {
        int hash = (int) key.charAt(1);
        for (int i = 1; i < key.length(); ++i) {
            hash ^= (int) key.charAt(i);
        }
        return hash % ONE_BILLION;
    }

    // given a key, hash it, search for the hash in the list of buckets, if found add it to that bucket's
    // array list, else if that bucket doesnt exist, add it
    private boolean put(String key, Object value) throws Exception {
        // make sure the key is the correct length and consists only of 0-9a-zA-Z
        if (key.length() < 1 || key.length() > 8) {
            throw new Exception("Key must be 1-8 characters");
        }
        int hash = hash(key);

        // Get the bucket at the index of hash
        Bucket bucket = buckets.get(hash);
        // If there is already a bucket for this hash search its collision list
        if (bucket != null) {
            // search the collision list for the key
            Optional<HashEntry> hashEntryOptional = bucket.getHashEntries().stream().filter(hashEntry -> hashEntry.getKey().equals(key)).findFirst();
            // if there is an entry for the hash, update the value, else add a new hash entry
            if (hashEntryOptional.isPresent()) {
                HashEntry hashEntry = hashEntryOptional.get();
                hashEntry.setValue(value);
            } else {
                bucket.getHashEntries().add(new HashEntry(key, value));
            }
        } else {
            // add the new bucket at the index of hash
            List<HashEntry> newBucketHashEntriesList = new ArrayList<>();
            buckets.add(hash, new Bucket(hash, newBucketHashEntriesList));
        }
        return true;
    }

    // Take in the key, hash it, see if there is a bucket for it, if so find it in that buckets array list and return
    // the object, if not in that buckets array list return null. If no bucket for that hash exists, return null
    private HashEntry get(String key)
            throws Exception {
        // make sure the key is the correct length and consists only of 0-9a-zA-Z
        if (key.length() < 1 || key.length() > 8) {
            throw new Exception("Key must be 1-8 characters");
        }
        int hash = hash(key);

        // Get the bucket at the index of hash
        Bucket bucket = buckets.get(hash);
        // Search the collision list at the hash bucket
        if (bucket != null) {
            // search the collision list for the key
            Optional<HashEntry> hashEntryOptional = bucket.getHashEntries().stream().filter(hashEntry -> hashEntry.getKey().equals(key)).findFirst();
            // if there is an entry for the key in the collision map, return the hash entry
            if (hashEntryOptional.isPresent()) {
                return hashEntryOptional.get();
            }
        }
        // nothing in the collision map or no bucket for this hash, return null
        return null;
    }

    // Take in the key, hash it, see if there is a bucket for it, if so find it in that buckets array list and return
    // true. If not in that buckets array list return false. If no bucket for that hash exists, return false
    private boolean containsKey(String key) throws Exception {
        // make sure the key is the correct length and consists only of 0-9a-zA-Z
        if (key.length() < 1 || key.length() > 8) {
            throw new Exception("Key must be 1-8 characters");
        }

        int hash = hash(key);

        // Get the bucket at the index of hash
        Bucket bucket = buckets.get(hash);
        // Search the collision list at the hash bucket
        if (bucket != null) {
            // search the collision and remove the hash entry
            Optional<HashEntry> hashEntryOptional = bucket.getHashEntries().stream().filter(hashEntry -> hashEntry.getKey().equals(key)).findFirst();
            // if there is an entry for the key in the collision map, return the hash entry
            if (hashEntryOptional.isPresent()) {
                return true;
            }
        }
        //nothing in the collision map or no bucket for this hash, nothing was deleted
        return false;
    }

    // Take in the key, hash it, see if there is a bucket for it, if so find it in that buckets array list and delete
    // the object, if not in that buckets array list return null. If no bucket for that hash exists, return null
    private boolean delete(String key) throws Exception {
        // make sure the key is the correct length and consists only of 0-9a-zA-Z
        if (key.length() < 1 || key.length() > 8) {
            throw new Exception("Key must be 1-8 characters");
        }
        int hash = hash(key);

        // Get the bucket at the index of hash
        Bucket bucket = buckets.get(hash);
        // Search the collision list at the hash bucket
        if (bucket != null) {
            // search the collision and remove the hash entry
            Iterator<HashEntry> hashEntryIterator = bucket.getHashEntries().iterator();
            while (hashEntryIterator.hasNext()) {
                // if there is an entry for the key in the collision map, return the hash entry and return true
                if (hashEntryIterator.next().getKey().equals(key)) {
                    hashEntryIterator.remove();
                    return true;
                }
            }
        }
        // nothing deleted from the collision map or No bucket for this hash, nothing was deleted
        return false;
    }
}
