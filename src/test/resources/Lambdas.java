package br.com.stackedu.cdd;

import java.util.ArrayList;
import java.util.List;

public class Lambdas {

  public static void main(String[] args) {
      List<Task> tasks = getTasks();
      List<String> titles = taskTitles(tasks, task -> task.getType() == TaskType.READING);
      titles.forEach(System.out::println);
  }

  public static List<String> taskTitles(List<Task> tasks, Predicate<Task> filterTasks) {
      List<String> readingTitles = new ArrayList<>();
      for (Task task : tasks) {
          if (filterTasks.test(task)) {
              readingTitles.add(task.getTitle());
          }
      }
      return readingTitles;
  }
}