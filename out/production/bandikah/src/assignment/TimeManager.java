package assignment;

import java.io.*;
import java.util.ArrayList;

public class TimeManager extends ArrayList<Integer> implements Serializable {

    public TimeManager(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            TimeManager timeManager = (TimeManager) in.readObject();
            this.addAll(timeManager);
        } catch (IOException e) {
            this.add(1000);
            this.add(1000);
            this.add(1000);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    public void saveTime(String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(this);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void addTime(int time) {
        if (time < get(0)) {
            set(2, get(1));
            set(1, get(0));
            set(0, time);
            return;
        } else if (time < get(1)) {
            set(2, get(1));
            set(1, time);
            return;
        } else if (time < get(2)) {
            set(2, time);
            return;
        }
    }
}

