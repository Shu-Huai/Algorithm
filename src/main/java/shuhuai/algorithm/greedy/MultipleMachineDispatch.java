package shuhuai.algorithm.greedy;

import java.util.*;

public class MultipleMachineDispatch {
    private Integer[] times;
    private Integer machineNum;
    private Machine[] arrangement;
    private Integer result;

    public MultipleMachineDispatch(Integer[] times, Integer machineNum) {
        setData(times, machineNum);
    }

    public void setData(Integer[] times, Integer machineNum) {
        this.times = new Integer[times.length];
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
        private Integer id;
        private Integer time;
        private List<Integer> queue;

        public Integer getTime() {
            return time;
        }
    }

    public Integer greedy() {
        if (times.length <= machineNum) {
            Integer max = Integer.MIN_VALUE;
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
        Arrays.sort(times, Comparator.reverseOrder());
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
            Integer[] times = new Integer[split.length];
            for (int i = 0; i < times.length; i++) {
                times[i] = Integer.parseInt(split[i]);
            }
            Integer machineNum = scanner.nextInt();
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