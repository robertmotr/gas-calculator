package sample;

import java.util.LinkedList;
import java.util.List;

public class Distance {

    private List<String> distanceList = new LinkedList<String>();

    public void push(String element) {

        distanceList.add(element);

    }

    public String pop() {

        return distanceList.remove(0);

    }

    public List<String> getDistanceList() {
        return distanceList;

    }

    public void popAll() {

        distanceList.clear();
    }

}

