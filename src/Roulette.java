import java.util.Random;

public class Roulette {
    private int resultNumber;
    private String resultColor;

    private static final String[] Colors = {
            "GREEN", "RED", "BLACK", "RED", "BLACK", "RED", "BLACK", "RED", "BLACK", "RED", "BLACK", "BLACK", "RED",
            "BLACK", "RED", "BLACK", "RED", "BLACK", "RED", "RED", "BLACK", "RED", "BLACK", "RED", "BLACK", "RED",
            "BLACK", "RED", "BLACK", "BLACK", "RED", "BLACK", "RED", "BLACK", "RED", "BLACK", "RED"
    };

    private static final int[][] Columns = {
            {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34}, // колонка 1
            {2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35}, // колонка 2
            {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36} // колонка 3
    };

    public void spin() {
        Random random = new Random();
        resultNumber = random.nextInt(37);
        resultColor = Colors[resultNumber];
    }

    public boolean checkWin(BetType betType, int chosenNum, String chosenColor, int chosenParity) {
        switch (betType) {
            case NUMBER:
                return resultNumber == chosenNum;

            case COLOR:
                return resultColor.equalsIgnoreCase(chosenColor);

            case PARITY:
                if (resultNumber == 0) return false;
                if (chosenParity == 1) return resultNumber % 2 == 0;
                if (chosenParity == 2) return resultNumber % 2 != 0;
                return false;

            case COLUMN_1:
                return isInColumn(0);
            case COLUMN_2:
                return isInColumn(1);
            case COLUMN_3:
                return isInColumn(2);

            case LOW:
                return resultNumber >= 1 && resultNumber <= 18;
            case HIGH:
                return resultNumber >= 19 && resultNumber <= 36;

            case DOZEN_1:
                return resultNumber >= 1 && resultNumber <= 12;
            case DOZEN_2:
                return resultNumber >= 13 && resultNumber <= 24;
            case DOZEN_3:
                return resultNumber >= 25 && resultNumber <= 36;

            default:
                return false;
        }
    }

    private boolean isInColumn(int columnIndex) {
        for (int num : Columns[columnIndex]) {
            if (num == resultNumber) return true;
        }
        return false;
    }

    public int getResultNumber() {
        return resultNumber;
    }

    public String getResultColor() {
        return resultColor;
    }
}
