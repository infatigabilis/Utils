package org.world_it_planet.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class G {
    private TreeMap<Integer, Integer> ids = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        G handler = new G();

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferRead.readLine().trim());

        for (int i = 0; i < N; i++) {
            String[] line = bufferRead.readLine().split(" ");

            if (line[0].equals("1"))
                System.out.println(handler.enter(Integer.parseInt(line[1])));
            else
                handler.leave(Integer.parseInt(line[1]));
        }
    }

    public int enter(int favorite) {
        int id = favorite;

        Map.Entry<Integer, Integer> entry = ids.floorEntry(favorite);

        if (entry != null && id <= entry.getValue()) {
            id = entry.getValue()+1;
            ids.put(entry.getKey(), id);

            Integer v = ids.get(id+1);
            if (v != null) {
                ids.remove(id+1);
                ids.put(entry.getKey(), v);
            }
        }
        else {
            Integer v = ids.get(id+1);

            if (entry != null && entry.getValue() + 1 == id) {
                if (v != null) {
                    ids.remove(id+1);
                    ids.put(entry.getKey(), v);
                    return id;
                }

                ids.put(entry.getKey(), id);
                return id;
            } else if (v != null) {
                ids.remove(id+1);
                ids.put(id, v);
            }
            else ids.put(id, id);
        }

        return id;
    }

    public void leave(int id) {
        Map.Entry<Integer, Integer> entry = ids.floorEntry(id);

        if (entry != null && id <= entry.getValue()) {
            if (id == entry.getKey() && id == entry.getValue()) {
                ids.remove(entry.getKey());
            }else if (id == entry.getKey()) {
                ids.put(entry.getKey()+1, entry.getValue());
                ids.remove(entry.getKey());
            } else {
                ids.put(entry.getKey(), id - 1);
                ids.put(id+1, entry.getValue());
            }
        }
    }
}
