

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.example.DeliveryCalculator;

class DeliveryCalculatorTest {

    DeliveryCalculator calculator = new DeliveryCalculator();

    @Test
    @DisplayName("Позитивный сценарий: расчет для трассы")
    void testHighwaySuccess() {
        // 100 км / 50 км/ч = 2.0 часа
        assertEquals(2.0, calculator.calculateTime(100, 50, "трасса"));
    }

    @Test
    @DisplayName("Позитивный сценарий: расчет для города с коэффициентом")
    void testCitySuccess() {
        // 60 км / 60 км/ч = 1.0 + 20% = 1.2 часа [cite: 263]
        assertEquals(1.2, calculator.calculateTime(60, 60, "город"));
    }

    @Test
    @DisplayName("Негативный сценарий: некорректная скорость в городе")
    void testInvalidCitySpeed() {
        // Ожидаем исключение при превышении 60 км/ч в городе [cite: 264, 196]
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTime(10, 80, "город");
        });
    }

    @Test
    @DisplayName("Негативный сценарий: отрицательное расстояние")
    void testNegativeDistance() {
        // [cite: 264]
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTime(-5, 50, "трасса");
        });
    }

    @Test
    @DisplayName("Граничные значения: минимальная скорость")
    void testBoundaryMinSpeed() {
        // Проверка работы на нижней границе диапазона (1 км/ч)
        assertEquals(10.0, calculator.calculateTime(10, 1, "трасса"));
    }
}