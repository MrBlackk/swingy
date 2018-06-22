package com.mrb.swingy.view.game;

import com.mrb.swingy.controller.GameController;
import com.mrb.swingy.model.Game;
import com.mrb.swingy.util.Point;

import java.util.Scanner;

/**
 * Created by chvs on 19.06.2018.
 */
public class GameViewConsole implements GameView {

    private GameController controller;

    @Override
    public void start() {
        System.out.println("Game View Console");
        controller = new GameController(this);

        controller.onStart();
    }

    @Override
    public void update(Game game) {

        getUserInput();
    }

    private void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Command(MAP, NORTH, EAST, SOUTH, WEST):");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if ("map".equalsIgnoreCase(input)) {
                controller.onPrintMap();
                break;
            } else if ("north".equalsIgnoreCase(input) ||
                    "east".equalsIgnoreCase(input) ||
                    "south".equalsIgnoreCase(input) ||
                    "west".equalsIgnoreCase(input)) {
                controller.onMove(input);
                break;
            } else {
                System.out.println("Unknown command");
            }
        }
        scanner.close();
    }

    @Override
    public void printMap(boolean [][] map, Point heroCoord){
        System.out.printf("MAP %dx%d", map.length, map.length);
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (heroCoord.getX() == j && heroCoord.getY() == i)
                    System.out.print("H ");
                else if (map[i][j])
                    System.out.print("* ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    @Override
    public void gameFinished() {
        System.out.println("See you!");
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void getVillainCollisionInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Command(FIGHT, RUN):");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if ("fight".equalsIgnoreCase(input)) {
                controller.onFight();
                break;
            } else if ("run".equalsIgnoreCase(input)) {
                controller.onRun();
                break;
            } else {
                System.out.println("Unknown command");
            }
        }
//        scanner.close();
    }
}
