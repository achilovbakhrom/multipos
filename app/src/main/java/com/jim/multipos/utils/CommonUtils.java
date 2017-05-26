package com.jim.multipos.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 5/25/17.
 */

public class CommonUtils {

    public static <T> List<List<T>> combination(List<T> list) {
        if (list.size() == 0) {
            List<List<T>> result = new ArrayList<>();
            result.add(new ArrayList<T>());
            return result;
        }
        List<List<T>> returnMe = new ArrayList<>();
        T firstElement = list.remove(0);
        List<List<T>> recursiveReturn = combination(list);
        for (List<T> li : recursiveReturn) {

            for (int index = 0; index <= li.size(); index++) {
                List<T> temp = new ArrayList<>(li);
                temp.add(index, firstElement);
                returnMe.add(temp);
            }

        }
        return returnMe;
    }


}
