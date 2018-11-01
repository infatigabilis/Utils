package education.algoritmics.data_structures.priority_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ParallelHandling {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = in.readLine().split(" ");
        int procNumber = Integer.parseInt(firstLine[0]);

        List<Long> input = Arrays.stream(in.readLine().split(" ")).map(Long::parseLong).collect(Collectors.toList());

        PriorityQueue<TaskInfo> queue = new PriorityQueue<>();

        for (int i = 0; i < procNumber; i++) {
            queue.add(new TaskInfo(0, i));
        }

        for (int i = 0; i < input.size(); i++) {
            TaskInfo taskInfo = queue.poll();

            queue.add(new TaskInfo(taskInfo.endTime + input.get(i), taskInfo.proc));
            System.out.println(taskInfo.proc + " " + taskInfo.endTime);
        }
    }

    static class TaskInfo implements Comparable<TaskInfo> {
        long endTime;
        int proc;

        public TaskInfo(long endTime, int proc) {
            this.endTime = endTime;
            this.proc = proc;
        }

        @Override
        public int compareTo(TaskInfo o) {
            boolean b = o.endTime != this.endTime ? this.endTime > o.endTime : this.proc > o.proc;

            return b ? 1 : -1;
        }
    }
}
