package sample;

import java.util.LinkedList;
import java.util.List;

public class Query {

    public List<String> queryList = new LinkedList<String>();

    public void push(String element) {

        queryList.add(element);

    }

    public String pop() {

       return queryList.remove(0);

    }

    public List<String> getQueryList() {
        return queryList;

    }

    public void popAll() {

        queryList.clear();
    }

}
