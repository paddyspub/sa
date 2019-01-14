package hashnocrash;

import java.util.*;

public class HashNoCrash {
    private static final int NUM_BUCKETS = 1000000000;
    private static final double LOAD_FACTOR = 1.0; // can decrease this with a smaller
    private static List<Bucket> buckets;
    private int numBuckets;

    public HashNoCrash() {
        buckets = new ArrayList<>();
        buckets.add(0, null);
        numBuckets = NUM_BUCKETS;
    }


    // This hash will take 1-8 chars and then sum the ascii values of them to create buckets.
    // There are up to 8 characters of 0-9a-zA-AZ for the key which is (10 + 26 + 26) ^ 8 which is
    // 218,340,105,584,896 possible keys.  The requirements state that there shall be no collisions for up to 1 billion
    // keys.
    //
    // 1 billion buckets will be created by bitwise XORing the ascii values of each character
    private int hash(String key) {
        int hash = 3;
        for (int i = 0; i < key.length(); i++) {
            hash = hash * 7 + key.charAt(i);
        }

        return (hash % numBuckets) % NUM_BUCKETS;
    }

    // Increases the number of buckets by a load factor of .70 to increase capacity of the bucky array
    private void handleCapacityByLoadFactor(int hash) {
        if (hash >= (buckets.size())) {

            long newSize = (long) Math.ceil((hash / LOAD_FACTOR));
            // calculate the buckets to add. Always add 1 (o(1) still, but also safe)
            long numberOfNewItemsToAdd = (newSize - buckets.size());
            System.out.println(hash - numberOfNewItemsToAdd);
            while (numberOfNewItemsToAdd-- > 0 && buckets.size() <= NUM_BUCKETS) {
                buckets.add(null);
            }
            System.out.println(buckets.size());

        }

    }

    // given a key, hash it, search for the hash in the list of buckets, if found add it to that bucket's
    // array list, else if that bucket doesnt exist, add it
    public boolean put(String key, Object value) throws Exception {
        // make sure the key is the correct length and consists only of 0-9a-zA-Z
        if (key.length() < 1 || key.length() > 8) {
            throw new Exception("Key must be 1-8 characters");
        }
        int hash = hash(key);

        // Get the bucket at the index of hash
        Bucket bucket = buckets.size() > hash + 1 ? buckets.get(hash) : null;
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
            handleCapacityByLoadFactor(hash);
            HashEntry hashEntry = new HashEntry(key, value);
            newBucketHashEntriesList.add(hashEntry);
            buckets.add(hash, new Bucket(hash, newBucketHashEntriesList));
        }
        return true;
    }

    // Take in the key, hash it, see if there is a bucket for it, if so find it in that buckets array list and return
    // the object, if not in that buckets array list return null. If no bucket for that hash exists, return null
    public Object get(String key)
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
                return hashEntryOptional.get().getValue();
            }
        }
        // nothing in the collision map or no bucket for this hash, return null
        return null;
    }

    // Take in the key, hash it, see if there is a bucket for it, if so find it in that buckets array list and return
    // true. If not in that buckets array list return false. If no bucket for that hash exists, return false
    public boolean containsKey(String key) throws Exception {
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
    public boolean delete(String key) throws Exception {
        // make sure the key is the correct length and consists only of 0-9a-zA-Z
        if (key.length() < 1 || key.length() > 8) {
            throw new Exception("Key must be 1-8 characters");
        }
        int hash = hash(key);

        // Get the bucket at the index of hash
        if (hash < buckets.size()) {
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
        }

        // nothing deleted from the collision map or No bucket for this hash, nothing was deleted
        return false;
    }

    public int getNumBuckets() {
        return buckets.size();
    }
}
