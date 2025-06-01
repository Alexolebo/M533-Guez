package main;

import java.util.Scanner;

public class Enigma {
   private String question;
   private String answer;
   private Location linkedZone;

   public Enigma(String question, String answer, Location linkedZone) {
      this.question = question;
      this.answer = answer.toLowerCase();
      this.linkedZone = linkedZone;
   }

   public void ask() {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enigma: " + this.question);
      System.out.print("Your answer: ");
      String input = scanner.nextLine().toLowerCase();
      if (input.equals(this.answer)) {
         System.out.println("Correct! The zone " + this.linkedZone.getName() + " is now unlocked.");
         this.linkedZone.unlock();
      } else {
         System.out.println("Incorrect. Try again later.");
      }

   }
}
