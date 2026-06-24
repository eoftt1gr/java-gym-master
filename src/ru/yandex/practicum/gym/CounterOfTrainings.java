package ru.yandex.practicum.gym;

public class CounterOfTrainings implements Comparable<CounterOfTrainings> {
    private Coach coach;
    private int trainingsCount;

    public CounterOfTrainings(Coach coach, int trainingsCount) {
        this.coach = coach;
        this.trainingsCount = trainingsCount;
    }

    public Coach getCoach() {
        return coach;
    }

    public int getTrainingsCount() {
        return trainingsCount;
    }

    public void setTrainingsCount(int trainingsCount) {
        this.trainingsCount = trainingsCount;
    }

    @Override
    public String toString() {
        return "CounterOfTrainings{" +
                "coach=" + coach +
                ", trainingsCount=" + trainingsCount +
                '}';
    }

    @Override
    public int compareTo(CounterOfTrainings o) {
        int cmp = Integer.compare(this.trainingsCount, o.trainingsCount);
        if (cmp == 0) {
            return this.coach.getName().compareTo(o.coach.getName());
        }
        return cmp;
    }
}
