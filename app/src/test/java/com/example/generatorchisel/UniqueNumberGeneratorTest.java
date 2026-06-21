package com.example.generatorchisel;

import java.util.HashSet;
import java.util.List;

public class UniqueNumberGeneratorTest {
    public static void main(String[] args) {
        UniqueNumberGenerator generator = new UniqueNumberGenerator();
        verifyGeneration(generator);
        verifyValidation(generator);
        System.out.println("UniqueNumberGeneratorTest passed");
    }

    private static void verifyGeneration(UniqueNumberGenerator generator) {
        List<Integer> numbers = generator.generate(10_000, 1, 10_000);
        require(numbers.size() == 10_000, "Generator must return requested amount");
        require(new HashSet<>(numbers).size() == 10_000, "All numbers must be unique");
        require(numbers.stream().allMatch(number -> number >= 1 && number <= 10_000),
                "All numbers must be inside the requested range");
    }

    private static void verifyValidation(UniqueNumberGenerator generator) {
        expectError(() -> generator.validate(0, 1, 10));
        expectError(() -> generator.validate(10_001, 1, 20_000));
        expectError(() -> generator.validate(3, 10, 5));
        expectError(() -> generator.validate(40, 5, 10));
    }

    private static void expectError(Runnable action) {
        try {
            action.run();
            throw new AssertionError("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // Expected validation error.
        }
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
