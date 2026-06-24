package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, Map<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();
//    private Map<DayOfWeek, List<TrainingSession>> sessionsByDay = new HashMap<>();
    private Map<Coach, CounterOfTrainings> trainingsByCoach = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        timetable.computeIfAbsent(trainingSession.getDayOfWeek(), day -> new TreeMap<>())
                .computeIfAbsent(trainingSession.getTimeOfDay(), time -> new ArrayList<>())
                .add(trainingSession);

        Coach coach = trainingSession.getCoach();
        if (trainingsByCoach.containsKey(coach)) {
            CounterOfTrainings trainings = trainingsByCoach.get(coach);
            trainings.setTrainingsCount(trainings.getTrainingsCount() + 1);
        } else {
            trainingsByCoach.put(coach, new CounterOfTrainings(coach, 1));
        }
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        Map<TimeOfDay, List<TrainingSession>> trainingSessionsByDay = timetable.get(dayOfWeek);
        if (trainingSessionsByDay == null) {
            return new ArrayList<>();
        }
        List<TrainingSession> result = new ArrayList<>();
        for (List<TrainingSession> sessions : trainingSessionsByDay.values()) {
            result.addAll(sessions);
        }
        return result;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        if (!timetable.containsKey(dayOfWeek)) {
            return new ArrayList<>();
        }
        Map<TimeOfDay, List<TrainingSession>> trainingSessions = timetable.get(dayOfWeek);
        if (!trainingSessions.containsKey(timeOfDay)) {
            return new ArrayList<>();
        }
        return trainingSessions.get(timeOfDay);
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        List<CounterOfTrainings> trainings = new ArrayList<>(trainingsByCoach.values());
        Collections.sort(trainings, Collections.reverseOrder());
        return trainings;
    }
}
