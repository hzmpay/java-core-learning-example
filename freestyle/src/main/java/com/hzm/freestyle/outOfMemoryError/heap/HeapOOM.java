package com.hzm.freestyle.outOfMemoryError.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月28日
 */
public class HeapOOM {

    static class OOMObject {
    }

    /**
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     *
     * @param args
     * @return void
     * @author Hezeming
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
