package com.javarush.test.level34.lesson15.big01.model;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        if (levels == null) return null;

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        try {
            FileInputStream fis = new FileInputStream(levels.toFile());
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String[] mapsOfLevels = new String(buffer).split("\\*{25,}");

            String matcher = "[\\S\\s]*Maze: " + (level % 60 == 0 ? 60 : level % 60) + "[\\S\\s]*";
            int id;
            for (id = 0; id < mapsOfLevels.length; id++) {
                if (mapsOfLevels[id].matches(matcher)) break;
            }

            BufferedReader reader = new BufferedReader(new StringReader(mapsOfLevels[id]));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("^Length.*")) {
                    reader.readLine();
                    break;
                }
            }

            int x = Model.FIELD_SELL_SIZE / 2;
            int y = Model.FIELD_SELL_SIZE / 2;

            while ((line = reader.readLine()) != null) {
                char[] chars = line.toCharArray();
                for (char c : chars) {
                    switch (c) {
                        case 'X':
                            walls.add(new Wall(x, y));
                            break;
                        case '*':
                            boxes.add(new Box(x, y));
                            break;
                        case '.':
                            homes.add(new Home(x, y));
                            break;
                        case '&':
                            boxes.add(new Box(x, y));
                            homes.add(new Home(x, y));
                            break;
                        case '@':
                            player = new Player(x, y);
                            break;
                    }
                    x += Model.FIELD_SELL_SIZE;
                }
                x = Model.FIELD_SELL_SIZE / 2;
                y += Model.FIELD_SELL_SIZE;
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException ignored) {
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}

//Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player

/*
16.2.	Реализуй метод GameObjects getLevel(int level). Он должен:
16.2.1.	Вычитывать из файла данные уровня level. Уровни должны повторяться
циклически, если пользователь прошел все 60 уровней и попал на 61 уровень, то
ему нужно вернуть 1, вместо 62 уровня вернуть 2 и т.д.
16.2.2.	Создать все игровые объекты, описанные в указанном уровне. Координаты
каждого игрового объекта должны быть рассчитаны согласно следующей логике:
16.2.2.1.	Самый верхний левый объект (если такой есть) должен иметь
координаты x = x0 = FIELD_SELL_SIZE / 2 и y = y0 = FIELD_SELL_SIZE / 2.
16.2.2.2.	Объект, который находится на одну позицию правее от него должен
иметь координаты x = x0 + FIELD_SELL_SIZE и y = y0.
16.2.2.3.	Объект, который находится на одну позицию ниже от самого верхнего
левого должен иметь координаты x = x0 и y = y0 + FIELD_SELL_SIZE.
16.2.2.4.	Аналогично должны рассчитываться координаты любого объекта на
поле.
16.2.3.	Создать новое хранилище объектов GameObjects и поместить в него все
объекты.
16.2.4.	Вернуть созданное хранилище.
 */

/*
        BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()));
        String line;
        while (reader.ready()) {
            line = reader.readLine();
            if (line.matches("^Maze: \\d+$") && Integer.parseInt(line.substring("Maze: ".length())) == 59) {
                System.out.println(line.substring("Maze: ".length()));
                break;
            }
        }
        while (!(line = reader.readLine()).isEmpty());
        System.out.println(reader.readLine());
 */

/*
        Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(Model.FIELD_SELL_SIZE / 2, Model.FIELD_SELL_SIZE / 2));
        walls.add(new Wall(Model.FIELD_SELL_SIZE / 2, Model.FIELD_SELL_SIZE / 2 * 4));
        walls.add(new Wall(Model.FIELD_SELL_SIZE / 2 * 4, Model.FIELD_SELL_SIZE / 2));
        walls.add(new Wall(Model.FIELD_SELL_SIZE / 2 * 16, Model.FIELD_SELL_SIZE / 2 * 16));
        return new GameObjects(walls, Collections.singleton(new Box(Model.FIELD_SELL_SIZE / 2 * 8, Model.FIELD_SELL_SIZE / 2 * 8)),
                Collections.singleton(new Home(Model.FIELD_SELL_SIZE / 2 * 8, Model.FIELD_SELL_SIZE / 2 * 16)),
                new Player(Model.FIELD_SELL_SIZE / 2 * 16, Model.FIELD_SELL_SIZE / 2 * 8));
 */