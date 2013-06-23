package com.eng.gp.project.util.date;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsUtil {

    /**
     * Returns a map of all values in <b>a</b> who's value is not a
     * key in <b>b</b>.
     *
     * @param a The source map of key value pairs used to populate
     * the resulting map.
     *
     * @param b The exclusion map, who's keys are the exclusions
     * matching map <b>a</b>'s values.
     *
     * @return a map of all values in <b>a</b> who's value is not a
     * key in <b>b</b>.
     */
    public static <K,V> Map<K,V> outerJoin(Map<K,V> a, Map<V,?> b) {
        Map<K,V> buffer = new HashMap<K,V>();

        for (Map.Entry<K,V> entry : a.entrySet()) {
            if (!b.containsKey(entry.getValue())) {
                buffer.put(entry.getKey(), entry.getValue());
            }
        }

        return buffer;
    }
    
    /**
     * Removes any keys not found in the element collection provided in this method
     * @param map
     * @param keysToKeep
     * @return
     */
    public static <K,V> Map<K,V> filterKeys(Map<K,V> map, K ... keysToKeep) {
        Map<K,V> filteredMap = new HashMap<K,V>();
        for(K key : keysToKeep) {
            if(map.containsKey(key)) {
                filteredMap.put(key, map.get(key));
            }
        }
        return filteredMap;
    }
    
    /**
     * can be used to collapse duplicate elements
     * @param list any list of type T
     */
    public static <T> Set<T> asSet(List<T> list) {
    	return new HashSet<T>(list);
    }
}