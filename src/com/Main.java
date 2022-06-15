package com;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Project> takeEmpProjects(List<List<String>> data) {
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            projects.add(new Project((int) Integer.parseInt(data.get(i).get(0)), (int) Integer.parseInt(data.get(i).get(1)), LocalDate.parse(data.get(i).get(2)), data.get(i).get(3)));
        }
        return projects;
    }

    static List<Project> getDistinct(List<Project> allEmps) {
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < allEmps.size(); i++) {
            if (projects.size() > 0) {
                boolean isEqual = false;
                for (int j = 0; j < projects.size(); j++) {
                    if (projects.get(j).getEmpNo() == allEmps.get(i).getEmpNo()) {
                        isEqual = true;
                    }
                }
                if (isEqual == false) {
                    projects.add(allEmps.get(i));
                }
            } else {
                projects.add(allEmps.get(i));
            }
        }
        return projects;
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<List<String>> data = new ArrayList<>();
        Scanner sc = new Scanner(new File("E:/student/Kristiana_Atanasova_SirmaTask/Book1.csv"));
        while (sc.hasNext()) {
            String line = sc.next();
            String[] values = line.split(",");
            data.add(Arrays.asList(values));
        }
        sc.close();
        List<Project> projects = takeEmpProjects(data);

        List<String[]> workedTogether = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            for (int j = i + 1; j < projects.size(); j++) {
                if((projects.get(i).getProjectId() == projects.get(j).getProjectId()) && ((projects.get(i).getDateFrom().isAfter(projects.get(j).getDateFrom()) && projects.get(i).getDateTo().isBefore(projects.get(j).getDateTo())) ||
                        (projects.get(i).getDateFrom().isBefore(projects.get(j).getDateFrom()) && projects.get(i).getDateTo().isBefore(projects.get(j).getDateTo()) && projects.get(i).getDateTo().isAfter(projects.get(j).getDateFrom())) ||
                        (projects.get(i).getDateFrom().isAfter(projects.get(j).getDateFrom()) && projects.get(i).getDateTo().isAfter(projects.get(j).getDateTo())&& projects.get(i).getDateFrom().isBefore(projects.get(j).getDateTo()))||
                        (projects.get(i).getDateFrom().isBefore(projects.get(j).getDateFrom()) && projects.get(i).getDateTo().isAfter(projects.get(j).getDateTo())))){
                    long duration = 0;
                    if(projects.get(i).getDateTo().isBefore(projects.get(j).getDateTo())){
                        if(projects.get(i).getDateFrom().isAfter(projects.get(j).getDateTo())){
                            duration = (ChronoUnit.DAYS.between(projects.get(i).getDateFrom(), projects.get(i).getDateTo()));
                        }else{
                            duration = (ChronoUnit.DAYS.between(projects.get(j).getDateFrom(), projects.get(i).getDateTo()));
                        }
                    }else{
                        if(projects.get(i).getDateFrom().isAfter(projects.get(j).getDateTo())){
                            duration = (ChronoUnit.DAYS.between(projects.get(i).getDateFrom(), projects.get(j).getDateTo()));
                        }else{
                            duration = (ChronoUnit.DAYS.between(projects.get(j).getDateFrom(), projects.get(j).getDateTo()));
                        }
                    }
                    String[] info = {Integer.toString(projects.get(i).getEmpNo()),Integer.toString(projects.get(j).getEmpNo()), Long.toString(duration)};
                    workedTogether.add(info);
                    for (int k = 0; k < 3; k++) {
                        System.out.print(info[k]);
                        System.out.print("  ");
                    }
                    System.out.println();

                }

            }

        }
    }
}
