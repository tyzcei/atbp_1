package org.example;

public class DeliveryCalculator {

    public double calculateTime(double distance, double speed, String terrain) {
        // Валидация входных данных [cite: 264]
        if (distance <= 0) {
            throw new IllegalArgumentException("Расстояние должно быть положительным");
        }
        if (speed < 1 || speed > 150) {
            throw new IllegalArgumentException("Скорость должна быть в диапазоне от 1 до 150 км/ч");
        }
        if (terrain.equalsIgnoreCase("город") && speed > 60) {
            throw new IllegalArgumentException("В городе скорость не превышает 60 км/ч");
        }

        double time = distance / speed;

        // В городе время увеличивается на 20% [cite: 263]
        if (terrain.equalsIgnoreCase("город")) {
            time *= 1.2;
        }

        // Округление до 2 знаков после запятой
        return Math.round(time * 100.0) / 100.0;
    }
}