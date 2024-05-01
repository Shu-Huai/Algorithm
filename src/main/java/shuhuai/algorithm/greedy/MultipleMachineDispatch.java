package shuhuai.algorithm.greedy;

import java.util.*;

public class MultipleMachineDispatch {
    private int[] times;
    private int machineNum;
    private Machine[] arrangement;
    private int result;

    public MultipleMachineDispatch(int[] times, int machineNum) {
        setData(times, machineNum);
    }

    public void setData(int[] times, int machineNum) {
        this.times = new int[times.length];
        System.arraycopy(times, 0, this.times, 0, times.length);
        this.machineNum = machineNum;
        arrangement = new Machine[machineNum];
        for (int i = 0; i < arrangement.length; i++) {
            arrangement[i] = new Machine();
            Objects.requireNonNull(arrangement)[i].id = i;
            arrangement[i].queue = new ArrayList<>();
        }
        result = 0;
    }

    public static class Machine {
        private int id;
        private int time;
        private List<Integer> queue;

        public int getTime() {
            return time;
        }
    }

    public int greedy() {
        if (times.length <= machineNum) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < times.length; i++) {
                arrangement[i].time = times[i];
                arrangement[i].queue.add(times[i]);
                if (max < times[i]) {
                    max = times[i];
                }
            }
            result = max;
            return result;
        }
        PriorityQueue<Machine> minHeap = new PriorityQueue<>(Comparator.comparingInt(Machine::getTime));
        Arrays.sort(times);
        for (int i = 0; i < times.length / 2; i++) {
            int temp = times[i];
            times[i] = times[times.length - 1 - i];
            times[times.length - 1 - i] = temp;
        }
        for (int i = 0; i < machineNum; i++) {
            Machine machine = new Machine();
            machine.id = i;
            machine.time = times[i];
            machine.queue = new ArrayList<>() {{
                add(machine.time);
            }};
            minHeap.add(machine);
        }
        for (int i = machineNum; i < times.length; i++) {
            Machine machine = minHeap.poll();
            if (machine != null) {
                machine.time += times[i];
                machine.queue.add(times[i]);
            }
            minHeap.offer(machine);
        }
        arrangement = minHeap.toArray(new Machine[0]);
        result = Integer.MIN_VALUE;
        for (Machine machine : arrangement) {
            if (machine.time > result) {
                result = machine.time;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            int[] times = new int[split.length];
            for (int i = 0; i < times.length; i++) {
                times[i] = Integer.parseInt(split[i]);
            }
            int machineNum = scanner.nextInt();
            MultipleMachineDispatch dispatch = new MultipleMachineDispatch(times, machineNum);
            System.out.println(dispatch.greedy());
            for (int i = 0; i < dispatch.arrangement.length; i++) {
                System.out.println(dispatch.arrangement[i].id + ": " + dispatch.arrangement[i].queue);
            }
            scanner.nextLine();
            input = scanner.nextLine();
        }
    }
}