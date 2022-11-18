package com.shift4.utils;

import com.shift4.response.ListResponse;

import java.util.List;

public abstract class ListResponseUtils {
    private ListResponseUtils() {
    }

    public static <T> T last(ListResponse<T> listResponse) {
        List<T> items = listResponse.getList();
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Empty response");
        }
        return items.get(items.size() - 1);
    }
}
