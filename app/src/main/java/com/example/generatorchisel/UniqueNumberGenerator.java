package com.example.generatorchisel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class UniqueNumberGenerator {
    public static final int MIN_COUNT = 1;
    public static final int MAX_COUNT = 10_000;

    private final Random random;

    public UniqueNumberGenerator() {
        this(new Random());
    }

    UniqueNumberGenerator(Random random) {
        this.random = random;
    }

    public List<Integer> generate(int count, int min, int max) {
        validate(count, min, max);
        Set<Integer> numbers = new HashSet<>(count * 2);
        while (numbers.size() < count) {
            numbers.add(random.nextInt(max - min + 1) + min);
        }
        return new ArrayList<>(numbers);
    }

    public void validate(int count, int min, int max) {
        if (count < MIN_COUNT || count > MAX_COUNT) {
            throw new IllegalArgumentException("Количество чисел должно быть от 1 до 10 000.");
        }
        if (min > max) {
            throw new IllegalArgumentException("Минимальное значение не может быть больше максимального.");
        }
        long rangeSize = (long) max - min + 1L;
        if (rangeSize < count) {
            throw new IllegalArgumentException("Диапазон меньше требуемого количества уникальных чисел.");
        }
    }
}
