package ru.dionisius;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Dionisius on 28.03.2017.
 */
public class Bank {
    /**
     * The number of clients in the bank at current time.
     */
    private int nClients = 0;
    /**
     * The maximum quantity of clients in the bank at this day.
     */
    private int maxClients = 0;
    /**
     * Start cut's time of maximum quantity of clients in the bank at this day.
     */
    private int maxStart = 0;
    /**
     * Storage of clients sorted by bank entry time
     */
    private TreeMap<Long, Cut> cuts = new TreeMap<>();
    /**
     * Storage for reports about time cuts of maximum quantity of clients in the bank at this day.
     */
    private List<Cut> reports = new ArrayList<>();

    /**
     * Adds specified new client to client's storage.
     * @param client specified new client.
     */
    public void enter(final Client client) {
        this.cuts.put(client.getId(), new Cut(client.getEntryTime(), client.getExitTime()));
    }

    /**
     * Prints program's results from reports storage.
     */
    public void showResults() {
        this.handle();
        for (Cut report: this.reports) {
            System.out.println(report);
        }
    }

    /**
     * Finds the time cuts of maximum quantity of clients in the bank at this day.
     * Adds them to reports storage.
     */
    private void handle() {
        Cut current = null;
        int firstClientEntryTime = this.cuts.firstEntry().getValue().getStart();
        int lastClientExitTime = this.cuts.lastEntry().getValue().getEnd();
        int nearestPoint = lastClientExitTime;
        for (int i = firstClientEntryTime; i <= lastClientExitTime; i++) {
            for (Map.Entry entry: this.cuts.entrySet()) {
                current = (Cut) entry.getValue();
                if (nearestPoint > current.getEnd() && current.getEnd() > i) {
                    nearestPoint = current.getEnd();
                }
                if (nearestPoint > current.getStart() && current.getStart() > i ) {
                    nearestPoint = current.getStart();
                }
                if (current.getStart() == i) {
                    this.nClients++;
                    if (this.maxClients < this.nClients) {
                        this.reports.clear();
                        this.maxClients = this.nClients;
                    }
                    if ( this.maxClients == this.nClients || this.maxClients < this.nClients) {
                        this.maxStart = current.getStart();
                    }
                }
                if (current.getEnd() == i) {
                    this.nClients--;
                    if (this.maxClients == this.nClients + 1) {
                        this.reports.add(new Cut(this.maxStart, current.getEnd()));
                    }
                }
            }
            if (nearestPoint != i) {
                i = nearestPoint - 1;
                nearestPoint = lastClientExitTime;
            }
        }
    }

    /**
     * Class for time cuts.
     */
    private class Cut implements Comparable {
        private final int start;
        private final int end;

        public Cut(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public String toString() {
            LocalTime startTime = LocalTime.of(this.start / 60, this.start % 60);
            LocalTime endTime = LocalTime.of(this.end / 60, this.end % 60);
            return String.format("Max count of clients was %d. In duration from %s to %s.",
                    maxClients, startTime, endTime);
        }

        @Override
        public int compareTo(Object o) {
            return this.getStart() - ((Cut) o).getStart();
        }
    }
}
