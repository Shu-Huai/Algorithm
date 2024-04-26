package shuhuai.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ActivityArrangement {
    public static class Activity {
        private int start;
        private int end;
        private boolean selected;
    }

    private Activity[] activities;
    private int count;

    public ActivityArrangement(Activity[] activities) {
        setData(activities);
    }

    public void setData(Activity[] activities) {
        this.activities = new Activity[activities.length];
        for (int i = 0; i < activities.length; i++) {
            this.activities[i] = new Activity();
            this.activities[i].start = activities[i].start;
            this.activities[i].end = activities[i].end;
            this.activities[i].selected = activities[i].selected;
        }
        count = 0;
    }

    public int greedy() {
        Arrays.sort(activities, Comparator.comparingInt(o -> o.end));
        int j = 0;
        for (int i = 0; i < activities.length; i++) {
            if (i == 0 || activities[i].start >= activities[j].end) {
                activities[i].selected = true;
                j = i;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            Activity[] activities = new Activity[split.length];
            for (int i = 0; i < activities.length; i++) {
                activities[i] = new Activity();
                activities[i].start = Integer.parseInt(split[i]);
            }
            split = sc.nextLine().split(" ");
            int[] end = new int[split.length];
            for (int i = 0; i < end.length; i++) {
                activities[i].end = Integer.parseInt(split[i]);
            }
            ActivityArrangement arr = new ActivityArrangement(activities);
            System.out.println(arr.greedy());
            for (int i = 0; i < arr.activities.length; i++) {
                if (arr.activities[i].selected) {
                    System.out.print((i + 1) + " ");
                }
            }
            System.out.println();
            input = sc.nextLine();
        }
    }
}