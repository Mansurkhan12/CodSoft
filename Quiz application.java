package Codsoft;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class QuizApplication {
    // Define the Question class
    static class Question {
        String question;
        String[] options;
        int correctAnswerIndex;

        // Constructor
        public Question(String question, String[] options, int correctAnswerIndex) {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        // Check if the selected answer is correct
        public boolean isCorrect(int answerIndex) {
            return answerIndex == correctAnswerIndex;
        }
    }

    public static void main(String[] args) {
        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // List of quiz questions
        List<Question> questions = new ArrayList<>();
        
        // Add questions, options, and the correct answer index (0-based)
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0));
        questions.add(new Question("Who developed Java?", new String[]{"James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido van Rossum"}, 0));
        questions.add(new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2));
        questions.add(new Question("Which programming language is known as the language of the web?", new String[]{"Java", "C++", "Python", "JavaScript"}, 3));
        questions.add(new Question("What is the square root of 64?", new String[]{"6", "8", "10", "7"}, 1));

        int score = 0;
        int totalQuestions = questions.size();

        // Loop through each question
        for (int i = 0; i < totalQuestions; i++) {
            Question currentQuestion = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + currentQuestion.question);
            
            // Display options
            for (int j = 0; j < currentQuestion.options.length; j++) {
                System.out.println((j + 1) + ". " + currentQuestion.options[j]);
            }

            // Start timer for this question
            long startTime = System.currentTimeMillis();
            int answer = -1;
            
            // Give the user 10 seconds to answer the question
            while (System.currentTimeMillis() - startTime < TimeUnit.SECONDS.toMillis(10)) {
                System.out.print("Your answer (1-4): ");
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                    if (answer >= 1 && answer <= 4) {
                        break;
                    } else {
                        System.out.println("Invalid input! Please choose an option between 1 and 4.");
                    }
                } else {
                    scanner.next();  // Clear invalid input
                }
            }

            // Check if time is over
            if (answer == -1) {
                System.out.println("Time's up! You did not answer the question.");
            } else {
                // Check if the answer is correct
                if (currentQuestion.isCorrect(answer - 1)) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer was: " + currentQuestion.options[currentQuestion.correctAnswerIndex]);
                }
            }

            System.out.println(); // Blank line for readability
        }

        // Display the result screen
        System.out.println("Quiz Over!");
        System.out.println("Your final score: " + score + "/" + totalQuestions);
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (totalQuestions - score));
        
        // Close scanner
        scanner.close();
    }
}
