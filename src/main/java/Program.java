import java.util.ArrayList;

import static java.lang.System.exit;

public class Program {

    /*
    ЗАДАНИЕ
    1. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества
    еды (например, в миске 10 еды, а кот пытается покушать 15-20).
    2. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны).
    Если коту удалось покушать (хватило еды), сытость = true.
    Считаем, что если коту мало еды в тарелке, то он её просто не трогает,
    то есть не может быть наполовину сыт (это сделано для упрощения логики программы).
    3. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки
    и потом вывести информацию о сытости котов в консоль.
    4. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
    */

    public static void main(String[] args) {


            Cats cats1 = new Cats("Арчеш", 4);
            Cats cats2 = new Cats("Васька", 12);
            Cats cats3 = new Cats("Платон", 23);

            Plate plate = new Plate(100);

            ArrayList<Cats> cats = new ArrayList<>();
            cats.add(cats1);
            cats.add(cats2);
            cats.add(cats3);

            for (Cats cat : cats) cat.eat(plate);

            plate.info();
            plate.addFeed(25);
            plate.info();
        }
    }

    abstract class Checkout {
        protected void checking(int value) {
            if (value < 0) {
                System.out.println("Ошибка, задайте значение заново.");
                exit(-1);
            }
        }
    }

    class Cats extends Checkout {
        private String name;
        private int appetite;
        private boolean fullcat = false;

        public Cats(String name, int appetite) {
            this.name = name;
            setAppetite(appetite);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAppetite() {
            return appetite;
        }

        public void setAppetite(int appetite) {
            checking(appetite);
            this.appetite = appetite;
        }

        public boolean isFullcat() {
            return fullcat;
        }

        public void eat(Plate plate) {
            if (fullcat) {
                System.out.println(name + " не голоден");
                return;
            }

            if (plate.getFood() - appetite < 0) {
                System.out.println("Нужно дабавить еды в миску для " + name);
                return;
            }

            plate.setFood(plate.getFood() - appetite);
            fullcat = true;
            System.out.println("Кот " + name + " поел");
        }
    }

    class Plate extends Checkout {
        private int food;

        public Plate(int food) {
            checking(food);
            this.food = food;
        }

        public int getFood() {
            return food;
        }

        public void setFood(int food) {
            checking(food);
            this.food = food;
        }

        public void info() {
            System.out.println("еды в тарелке: " + food);
        }

        public void addFeed(int food) {
            setFood(this.food + food);
        }
    }
