package com.ei.classroom.patterns.behavioral;

import com.ei.classroom.util.AppLogger;

// Strategy Interface
interface QuizDifficultyStrategy {
    String getDifficultyLevel();
    double calculateScoreAdjustment(double baseScore);
}

// Concrete Strategy 1: Easy
class EasyDifficulty implements QuizDifficultyStrategy {
    @Override
    public String getDifficultyLevel() { return "Easy"; }
    @Override
    public double calculateScoreAdjustment(double baseScore) {
        // Boost score slightly for easy quizzes
        return Math.min(100.0, baseScore * 1.05);
    }
}

// Concrete Strategy 2: Hard
class HardDifficulty implements QuizDifficultyStrategy {
    @Override
    public String getDifficultyLevel() { return "Hard"; }
    @Override
    public double calculateScoreAdjustment(double baseScore) {
        // Penalty or bonus for hard quizzes
        return baseScore * 1.15; // Give 15% bonus for completing a hard quiz
    }
}

// Context Class
class AdaptiveQuiz {
    private QuizDifficultyStrategy strategy;
    private final String quizName;

    public AdaptiveQuiz(String name, QuizDifficultyStrategy initialStrategy) {
        this.quizName = name;
        this.strategy = initialStrategy;
    }

    public void setStrategy(QuizDifficultyStrategy strategy) {
        this.strategy = strategy;
    }

    public void evaluatePerformance(double rawScore) {
        double adjustedScore = strategy.calculateScoreAdjustment(rawScore);
        System.out.printf("Quiz: %s | Raw Score: %.2f | Difficulty: %s | Adjusted Score: %.2f\n",
                quizName, rawScore, strategy.getDifficultyLevel(), adjustedScore);
    }
}


/**
 * STRATEGY PATTERN DEMO: Adaptive Quiz Difficulty.
 * Allows the system to dynamically select different algorithms (strategies) 
 * for scoring based on quiz difficulty.
 */
public class StrategyDemo {
    public static void runDemo() {
        AppLogger.logInfo("StrategyDemo", "Starting Strategy Pattern Demo: Adaptive Quiz Difficulty");

        // 1. Create the Context with an initial strategy
        AdaptiveQuiz mathQuiz = new AdaptiveQuiz("Algebra Test", new EasyDifficulty());
        
        // 2. Evaluate performance using the Easy Strategy
        mathQuiz.evaluatePerformance(85.0); // Raw score 85, adjusted to ~89.25

        // 3. System detects student is ready for a challenge and changes strategy
        System.out.println("\n--- Changing strategy to Hard Difficulty ---");
        mathQuiz.setStrategy(new HardDifficulty());
        
        // 4. Evaluate performance using the Hard Strategy
        mathQuiz.evaluatePerformance(70.0); // Raw score 70, adjusted to 80.50 (15% bonus)
        
        AppLogger.logInfo("StrategyDemo", "Strategy Pattern Demo Finished.\n");
    }
}
