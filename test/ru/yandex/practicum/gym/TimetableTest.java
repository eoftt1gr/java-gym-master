package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);
        //Проверить, что за понедельник вернулось одно занятие
        List<TrainingSession> sessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        Assertions.assertEquals(1, sessions.size());
        //Проверить, что за вторник не вернулось занятий
        sessions = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);

        Assertions.assertEquals(0, sessions.size());
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        List<TrainingSession> sessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        Assertions.assertEquals(1, sessions.size());
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        sessions = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        Assertions.assertEquals(2, sessions.size());
        System.out.println(sessions);
        System.out.println("--------------------------------------------------------" + "\n\n\n");
        // Проверить, что за вторник не вернулось занятий
        sessions = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        Assertions.assertEquals(0, sessions.size());
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        List<TrainingSession> sessions = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        Assertions.assertEquals(1, sessions.size());
        //Проверить, что за понедельник в 14:00 не вернулось занятий
        sessions = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0));
        Assertions.assertEquals(0, sessions.size());
    }

    @Test
    void testGetTrainingsSessionsByCoachSingleCoach() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));


        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));


        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        List<CounterOfTrainings> list = timetable.getCountByCoaches();
        System.out.println(list);
        System.out.println("--------------------------------------------------------" + "\n\n\n");
    }

    @Test
    void testGetTrainingsSessionsByCoachesIf2AreIdenticalCount() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Петров", "Евгений", "Анатольевич");
        Coach coach3 = new Coach("Биляева", "Екатерина", "Никифоровна");
        Coach coach4 = new Coach("Рин", "Георгий", "Львович");
        Coach coach5 = new Coach("Лодожская", "Анастасия", "Банан");


        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession mondayAdultTrainingSession = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(20, 0));
        TrainingSession tuesdayAdultTrainingSession = new TrainingSession(groupAdult, coach2,
                DayOfWeek.TUESDAY, new TimeOfDay(20, 0));
        TrainingSession wednesdayAdultTrainingSession = new TrainingSession(groupAdult, coach3,
                DayOfWeek.WEDNESDAY, new TimeOfDay(20, 0));
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach4,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));
        TrainingSession fridayAdultTrainingSession = new TrainingSession(groupAdult, coach5,
                DayOfWeek.FRIDAY, new TimeOfDay(20, 0));


        timetable.addNewTrainingSession(mondayAdultTrainingSession);
        timetable.addNewTrainingSession(tuesdayAdultTrainingSession);
        timetable.addNewTrainingSession(wednesdayAdultTrainingSession);
        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        timetable.addNewTrainingSession(fridayAdultTrainingSession);
        timetable.addNewTrainingSession(mondayAdultTrainingSession);
        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        timetable.addNewTrainingSession(wednesdayAdultTrainingSession);
        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        timetable.addNewTrainingSession(tuesdayAdultTrainingSession);


        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach3,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach3,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach5,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));


        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);


        List<CounterOfTrainings> listOfCoaches = timetable.getCountByCoaches();
        Assertions.assertEquals(5, listOfCoaches.size());
        Assertions.assertEquals(coach3, listOfCoaches.get(0).getCoach());
        Assertions.assertEquals(coach4, listOfCoaches.get(1).getCoach());
        Assertions.assertEquals(coach5, listOfCoaches.get(2).getCoach());

        System.out.println(listOfCoaches);
        System.out.println("--------------------------------------------------------" + "\n\n\n");
    }

    @Test
    void testGetTrainingsSessionsByCoachesInOrder() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Петров", "Евгений", "Анатольевич");
        Coach coach3 = new Coach("Биляева", "Екатерина", "Никифоровна");
        Coach coach4 = new Coach("Рин", "Георгий", "Львович");
        Coach coach5 = new Coach("Лодожская", "Анастасия", "Банан");


        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession mondayAdultTrainingSession = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(20, 0));
        TrainingSession tuesdayAdultTrainingSession = new TrainingSession(groupAdult, coach2,
                DayOfWeek.TUESDAY, new TimeOfDay(20, 0));
        TrainingSession wednesdayAdultTrainingSession = new TrainingSession(groupAdult, coach3,
                DayOfWeek.WEDNESDAY, new TimeOfDay(20, 0));
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach4,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));
        TrainingSession fridayAdultTrainingSession = new TrainingSession(groupAdult, coach5,
                DayOfWeek.FRIDAY, new TimeOfDay(20, 0));


        timetable.addNewTrainingSession(mondayAdultTrainingSession);
        timetable.addNewTrainingSession(tuesdayAdultTrainingSession);
        timetable.addNewTrainingSession(wednesdayAdultTrainingSession);
        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        timetable.addNewTrainingSession(fridayAdultTrainingSession);
        timetable.addNewTrainingSession(mondayAdultTrainingSession);
        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        timetable.addNewTrainingSession(wednesdayAdultTrainingSession);
        timetable.addNewTrainingSession(thursdayAdultTrainingSession);



        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach3,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach3,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach5,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));


        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);


        List<CounterOfTrainings> listOfCoaches = timetable.getCountByCoaches();
        Assertions.assertEquals(5, listOfCoaches.size());
        Assertions.assertEquals(coach3, listOfCoaches.get(0).getCoach());
        Assertions.assertEquals(coach4, listOfCoaches.get(1).getCoach());
        Assertions.assertEquals(coach5, listOfCoaches.get(2).getCoach());
        Assertions.assertEquals(coach1, listOfCoaches.get(3).getCoach());
        Assertions.assertEquals(coach2, listOfCoaches.get(4).getCoach());

        System.out.println(listOfCoaches);
        System.out.println("--------------------------------------------------------" + "\n\n\n");
    }
}
