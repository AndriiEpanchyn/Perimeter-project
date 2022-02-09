package aepanchin;

public class Variables {

   //Array vars
    static int dimentionX;
    static int dimentionY;
    static int startX = 0;
    static int startY = 0;
    static int currentX;
    static int currentY;
    static int perimetr;
    static Direction searchDirection;
    static int[][] field;
    static int[][] defaultField= new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0},
            {0,1,1,0,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1,0,0},
            {0,1,1,0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,1,0,0,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,0,0,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,0,0,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,0},
            {0,0,0,0,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,1,1,1,1,0},
            {0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,0},
            {0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,0},
            {0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,0},
            {0,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,1,1,1,1,0},
            {0,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,1,0,1,1,1,1,0},
            {0,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0},
            {0,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0},
            {0,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0},
            {0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0},
            {0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},};

     static int cellXSize;
     static int cellYSize;

    //Canvas vars
     static int canvasXSize = 600;
     static int canvasYSize = 600;

    // Read Json from file
    static StringBuilder innerText = new StringBuilder();
    static String filePath="C:\\JavaProjects\\PerimetrProject\\maps\\constructor2.txt";
    static int[][] fromGsonArray;

}
