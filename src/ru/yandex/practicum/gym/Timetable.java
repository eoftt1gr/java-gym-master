package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, Map<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();
    private Map<DayOfWeek, List<TrainingSession>> sessionsByDay = new HashMap<>();
    private Map<Coach, CounterOfTrainings> trainingsByCoach = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        timetable.computeIfAbsent(trainingSession.getDayOfWeek(), day -> new TreeMap<>())
                .computeIfAbsent(trainingSession.getTimeOfDay(), time -> new ArrayList<>())
                .add(trainingSession);

        List<TrainingSession> listSession =
                sessionsByDay.computeIfAbsent(trainingSession.getDayOfWeek(), day -> new ArrayList<>());
        int index = 0;
        while (index < listSession.size()
                && listSession.get(index).getTimeOfDay()
                .compareTo(trainingSession.getTimeOfDay()) <= 0) {
            index++;
        }
        listSession.add(index, trainingSession);

        Coach coach = trainingSession.getCoach();
        if (trainingsByCoach.containsKey(coach)) {
            CounterOfTrainings trainings = trainingsByCoach.get(coach);
            trainings.setTrainingsCount(trainings.getTrainingsCount() + 1);
            trainingsByCoach.put(coach, trainings);
        } else {
            trainingsByCoach.put(coach, new CounterOfTrainings(coach, 1));
        }
    }

    // не понимаю как тут можно сделать О(1), поэтому создал новую структуру данных, оставил старый вариант на всякий случай
    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        if (!sessionsByDay.containsKey(dayOfWeek)) {
            return new ArrayList<>();
        }
//        Map<TimeOfDay, List<TrainingSession>> trainingSessions = timetable.get(dayOfWeek);
//        List<TrainingSession> sessions = new ArrayList<>();
//        for (List<TrainingSession> listOfSessions : trainingSessions.values()) {
//            sessions.addAll(listOfSessions);
//        }
        return sessionsByDay.get(dayOfWeek);
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
        Collections.sort(trainings);
        return trainings;
    }
}
