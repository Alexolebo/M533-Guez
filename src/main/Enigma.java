package main;

import java.util.Scanner;

public class Enigma {
   private String question;
   private String answer;
   private Location target;

    public Enigma(String question, String answer, Location target) {
      this.question = question;
      this.answer = answer.toLowerCase();
      this.target = target;
   }

    public String getQuestion() {
        return question;
    }


    public String getAnswer() {
        return answer;
    }

    public Location getTarget() {
        return target;
    }
}
