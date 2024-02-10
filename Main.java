import java.util.Random;
import java.util.Arrays; // Добавлен импорт

class Tovar {
    private String name;
    private int price;
    private int quantity;
    private int expirationDate;
    private int category;

    public Tovar(String name, int price, int quantity, int expirationDate, int category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public int getCategory() {
        return category;
    }

    // Метод для генерации случайного товара
    public static Tovar generateRandomTovar() {
        Random random = new Random();
        String[] categories = {"Еда", "Одежда", "Электроника", "Книги", "Бытовая техника"};
        String name = "Товар " + (random.nextInt(1000) + 1);
        int price = random.nextInt(1000) + 1;    // случайная цена от 1 до 1000
        int quantity = random.nextInt(1000) + 1;   // случайное количество от 1 до 1000
        int expirationDate = random.nextInt(365) + 1; // случайный срок годности от 1 до 365 дней
        int category = random.nextInt(categories.length);
        return new Tovar(name, price, quantity, expirationDate, category);
    }

    @Override
    public String toString() {
        return "Название: " + name + " | Цена: " + price + " | Количество: " + quantity +
                " | Срок годности (дней): " + expirationDate + " | Категория: " + category;
    }

    // Статические методы анализа для выбранного столбца параметров товаров
    //***********************
    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int num : numbers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static double average(int[] numbers) {
        int sum = Arrays.stream(numbers).sum();
        return (double) sum / numbers.length;
    }

    public static double median(int[] numbers) {
        Arrays.sort(numbers);
        int middle = numbers.length / 2;
        if (numbers.length % 2 == 0) {
            return (double) (numbers[middle - 1] + numbers[middle]) / 2;
        } else {
            return numbers[middle];
        }
    }

    public static int mode(int[] numbers) {
        // Создаем массив для подсчета количества вхождений каждого числа
        int[] frequency = new int[30]; // Предполагаем, что числа в массиве могут быть от 0 до 29

        // Заполняем массив частот
        for (int number : numbers) {
            frequency[number]++;
        }

        // Находим моду (число с наибольшим количеством вхождений)
        int mode = 0;
        int maxFrequency = 0;
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > maxFrequency) {
                mode = i;
                maxFrequency = frequency[i];
            }
        }

        // Выводим результат
        System.out.print("мода массива: " + mode);
        return maxFrequency;
    }

    public static double standardDeviation(int[] numbers) {
        double mean = average(numbers);
        double sumOfSquaredDifferences = 0;
        for (int num : numbers) {
            sumOfSquaredDifferences += Math.pow(num - mean, 2);
        }
        return Math.sqrt(sumOfSquaredDifferences / numbers.length);
    }

    public static double calculateCorrelationCoefficient(int[] numbers1, double mean1, int[] numbers2, double mean2) {
        double numerator = 0;
        double denominator1 = 0;
        double denominator2 = 0;

        for (int i = 0; i < numbers1.length; i++) {
            numerator += (numbers1[i] - mean1) * (numbers2[i] - mean2);
            denominator1 += Math.pow(numbers1[i] - mean1, 2);
            denominator2 += Math.pow(numbers2[i] - mean2, 2);
        }

        return numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем 200 случайных товаров
        Tovar[] tovars = new Tovar[200];
        for (int i = 0; i < 200; i++)
        {
            tovars[i] = Tovar.generateRandomTovar();
        }

        // Выводим информацию о каждом товаре
        for (Tovar tovar : tovars)
        {
            System.out.println(tovar);
        }

        // Получаем массив цен товаров
        int[] prices = new int[tovars.length];
        for (int i = 0; i < tovars.length; i++) {
            prices[i] = tovars[i].getPrice();
        }

        // Получаем массив количества товаров
        int[] quantity = new int[tovars.length];
        for (int i = 0; i < tovars.length; i++) {
            quantity[i] = tovars[i].getQuantity();
        }


        // Пример использования методов анализа
        System.out.println("Минимальная цена: " + Tovar.min(prices));
        System.out.println("Максимальная цена: " + Tovar.max(prices));
        System.out.println("Средняя цена: " + Tovar.average(prices));
        System.out.println("Медиана цены: " + Tovar.median(prices));
        System.out.println("Стандартное отклонение цены: " + Tovar.standardDeviation(prices));

        // Находим среднее значение для каждого массива
        double mean1 = Tovar.average(prices);
        double mean2 = Tovar.average(quantity);

        // Находим коэффициент корреляции
        double correlationCoefficient = Tovar.calculateCorrelationCoefficient(prices, mean1, quantity, mean2);

        // Выводим результат
        System.out.println("Коэффициент корреляции: " + correlationCoefficient);
    }
}