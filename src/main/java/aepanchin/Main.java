package aepanchin;
/*
рассчистать периметр острова из 1 в двухмерном массиве из 0 и 1 и визуализировать периметр и остров.
массив использовать или из дефолтного массива или Json из файла.
 */

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static aepanchin.Variables.*;

public class Main extends Application {
    public static void main(String[] args) throws Exception {

        //Init common directions
        Direction up = new Direction("UP", 0, -1);
        Direction left = new Direction("LEFT", -1, 0);
        Direction down = new Direction("DOWN", 0, 1);
        Direction right = new Direction("RIGHT", 1, 0);
        up.next = left;
        up.previous = right;
        left.next = down;
        left.previous = up;
        down.next = right;
        down.previous = left;
        right.next = up;
        right.previous = down;

        //init variables and searchDirection
        currentX = 0;
        currentY = 0;
        perimetr = 0;
        searchDirection = up;

        // LAUNCH
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //realize drawing field

        primaryStage.setTitle("Drawing maps");
        Canvas canvas = new Canvas();
        canvas.setWidth(canvasXSize);
        canvas.setHeight(canvasYSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        BorderPane group = new BorderPane();
        group.setCenter(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        startMove(gc);
    }

    private void startMove(final GraphicsContext gc) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                chouseSource(gc);
                drawField(gc);
                drawEarth(gc);
                findStartCell();
                calculatePerimetr(gc);
                status(gc);
            }
        }).start();
    }

    void drawField(GraphicsContext gc) {
        // draw canvas
        gc.setFill(Color.LIGHTBLUE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.fillRect(0, 0, canvasXSize, canvasYSize);
        gc.strokeRect(0, 0, canvasXSize, canvasYSize);

    }

    void drawEarth(GraphicsContext gc) {
        Color earthCellColor = Color.LIMEGREEN;
        gc.setFill(earthCellColor);
        gc.setLineWidth(1);

        for (int x = 0; x < dimentionX; x++) {
            for (int y = 0; y < dimentionY; y++) {

                boolean isLand = (field[y][x] == 1);
                Color color = (isLand ? Color.BLACK : Color.DARKBLUE);
                double dx = x * (cellXSize + 1);
                double dy = y * (cellYSize + 1);

                gc.setStroke(color);
                if (isLand) {
                    gc.fillRect(dx, dy, cellXSize, cellYSize);
                }
                gc.strokeRect(dx, dy, cellXSize, cellYSize);
            }
        }
    }


    void findStartCell() {
        boolean foundCell = false;
        while (!foundCell) {
            for (int x = 0; x < dimentionX; x++) {
                for (int y = 0; y < dimentionY; y++) {
                    if (field[y][x] == 1) {
                        foundCell = true;
                        startX = x;
                        currentX = x;
                        startY = y;
                        currentY = y;
                        perimetr++;
                        break;
                    }
                    if (foundCell) break;
                }
                if (foundCell) break;
            }
        }
    }

    void calculatePerimetr(GraphicsContext gc) {

        boolean isPerimetrClosed = false;
        while (!isPerimetrClosed) {
            // Checking do I get start point passed one full circle
            if (perimetr >= 4 && currentX == startX && currentY == startY && searchDirection.name == "UP") {
                isPerimetrClosed = true;
                System.out.println("the perimeter is closed ");
            } else if (!isMovePossible(currentX, currentY, gc)) {
                isPerimetrClosed = true;
            } else {
                makeMove(currentX, currentY, searchDirection, gc);
            }
        }
    }

    private static void makeMove(int x, int y, Direction currentDirection, GraphicsContext gc) {

        if ((x + currentDirection.deltaX < 0) || (x + currentDirection.deltaX > dimentionX - 1) ||
                (y + currentDirection.deltaY < 0) || (y + currentDirection.deltaY > dimentionY - 1)) {
            searchDirection = currentDirection.next;
            perimetr++;
            drawLine(x, y, currentDirection, gc);
        } else if (field[y + currentDirection.deltaY][x + currentDirection.deltaX] == 0) {
            perimetr++;
            drawLine(x, y, currentDirection, gc);
            searchDirection = currentDirection.next;
        } else if (field[y + currentDirection.deltaY][x + currentDirection.deltaX] == 1) {
            currentX = x + currentDirection.deltaX;
            currentY = y + currentDirection.deltaY;
            searchDirection = currentDirection.previous;
        }
    }

    private static boolean isMovePossible(int currentX, int currentY, GraphicsContext gc) {
        boolean answer = false;

        //Check next step inside the array
        if (currentX < 0 || currentX > dimentionX - 1 || currentY < 0 || currentY > dimentionY - 1) {
            System.out.println("Something went wrong, you come out of the field border");
            status(gc);
        }
        //Check out of left side
        else if (currentX < startX) {
            System.out.println("Something went wrong, you come over the left border");
            status(gc);
        } else answer = true;
        return answer;
    }

    static void drawLine(int x, int y, Direction currentDirection, GraphicsContext gc) {
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(4);
        int startX = x * (cellXSize + 1);
        int endX = startX;
        int startY = y * (cellYSize + 1);
        int endY = startY;

        if (currentDirection.name == "UP") {
            startX = x * (cellXSize + 1);
            endX = startX + cellXSize;
            startY = y * (cellYSize + 1);
            endY = startY;
        } else if (currentDirection.name == "DOWN") {
            startX = x * (cellXSize + 1);
            endX = startX + cellXSize;
            startY = (y + 1) * (cellYSize + 1);
            endY = startY;
        } else if (currentDirection.name == "RIGHT") {
            startX = (x + 1) * (cellXSize + 1);
            endX = startX;
            startY = y * (cellYSize + 1);
            endY = startY + cellYSize;
        } else if (currentDirection.name == "LEFT") {
            startX = x * (cellXSize + 1);
            endX = startX;
            startY = y * (cellYSize + 1);
            endY = startY + cellYSize;
        } else {
            System.out.println("something goes wrong");
        }

        gc.strokeLine(startX, startY, endX, endY);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void status(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        String p = "Current point is (" + currentX + "; " + currentY + ") = " + field[currentY][currentX] +
                "; \n direction = " + searchDirection + "; Perimetr = " + perimetr + "\n";

        gc.fillText(p, 15, 10);

    }

    static char getCharAnswer(GraphicsContext gc) {
        System.out.println("Enter 1 to use default array, enter any other key to use array from file... \nPress Enter");
        Scanner voidscan = new Scanner(System.in);
        String myString = voidscan.nextLine();
        char answer = myString.charAt(0);
        System.out.println("answer = " + answer);
        return answer;
    }

    void chouseSource(GraphicsContext gc) {
        if (getCharAnswer(gc) == '1') {
            System.out.println("you choosen default array");
            field = defaultField;
        } else {
            fieldFromFile();
        }

        dimentionX = field[0].length;
        dimentionY = field.length;
        cellXSize = (canvasXSize / dimentionX) - 2;
        cellYSize = (canvasYSize / dimentionY) - 2;
    }

    void fieldFromFile() {
        System.out.println("you chosen array from file");
        try {
            FileReader newReader = new FileReader(filePath);
            Scanner newScan = new Scanner(newReader);
            while (newScan.hasNextLine()) {
                innerText.append(newScan.nextLine());
            }
            newReader.close();
            newScan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson intArrayGsonModel = new Gson();
        field= intArrayGsonModel.fromJson(innerText.toString(), int[][].class);

    }
}
