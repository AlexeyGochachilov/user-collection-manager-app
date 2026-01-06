package ru.aston.finalproject.service;

import java.util.ArrayList;
import java.util.List;

public class ListSorter<T extends Comparable<T>> {
    private List<T> list;

    public ListSorter(List<T> list) {
        this.list = list;
        mergeSort(this.list);
    }

    private void mergeSort(List<T> list) {
        if (list.size() <= 1) return;

        int mid = list.size() / 2;
        List<T> left = new ArrayList<>(list.subList(0, mid));
        List<T> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    private void merge(List<T> result, List<T> left, List<T> right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            result.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            result.set(k++, right.get(j++));
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
