package com.dm.earth.cabricality.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

public class RandomMathUtil {

    public static <T> @NotNull ArrayList<T> randomSelect(List<T> list, int max, long seed) {
        Random random = new Random(seed);

        ArrayList<T> processList = new ArrayList<>(list);
        ArrayList<T> returnList = new ArrayList<>();
        while (!(processList.size() <= 0 || returnList.size() >= max)) {
            int index = random.nextInt(processList.size());
            if (index < 0)
                index *= -1;
            returnList.add(processList.get(index));
            processList.remove(index);
        }
        return returnList;
    }

}
