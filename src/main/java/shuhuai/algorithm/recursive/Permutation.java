package shuhuai.algorithm.recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Permutation {
    public List<List<Integer>> recursive(List<Integer> r) {
        if (r.isEmpty()) {
            return new ArrayList<>();
        }
        if (r.size() == 1) {
            return new ArrayList<>(List.of(new ArrayList<>(List.of(r.getFirst()))));
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < r.size(); i++) {
            List<Integer> newR = new ArrayList<>();
            for (int j = 0; j < r.size(); j++) {
                if (i != j) {
                    newR.add(r.get(j));
                }
            }
            List<List<Integer>> newRes = recursive(newR);
            for (List<Integer> item : newRes) {
                item.addLast(r.get(i));
            }
            res.addAll(newRes);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            List<Integer> r = new ArrayList<>();
            String[] split = input.split(" ");
            for (String string : split) {
                r.add(Integer.parseInt(string));
            }
            System.out.println(new Permutation().recursive(r));
            input = sc.nextLine();
        }
    }
}