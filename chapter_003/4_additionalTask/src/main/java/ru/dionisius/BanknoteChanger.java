package ru.dionisius;

/**
 * Created by Dionisius on 10.12.2016.
 */
public class BanknoteChanger {
    /**
     * Decomposes integer to sums of summands and prints them.
     * @param banknote to change.
     * @param coins range of coins.
     */
    public void change(int banknote, int[] coins) {
        int index = 0;
        int[] currentArray = null;
        if (banknote < coins[0]) {
            this.change(banknote, this.removeFirstElement(coins));
        } else {
            for (int i = 0; i < coins.length; i++) {
                currentArray = this.decompose(banknote, coins[i]);
                this.printArray(currentArray);
                System.out.println();
                for (int j = i + 1; j < coins.length; j++) {
                    this.printAr(currentArray, coins[j]);
                }
                int temp = i++;
//                    for (int j = currentArray.length - 1; j > 0; j--) {
//                        for (int k = 0; k < coins.length; k++) {
//                            if (currentArray[j] > coins[k]) {
//                                for (int l = 0; l < j; l++) {
//                                    System.out.printf("%d+", currentArray[l]);
//                                }
//                                printArray(decompose(currentArray[j], coins[k]));
//                                for (int l = j + 1; l < currentArray.length; l++) {
//                                    System.out.println(currentArray[l] + " " + "+" + " ");
//                                }
//                            }
//                        }
//                    }
                }
//            }
        }
    }

//    public int[]

    private boolean canBeDecomposed (int value, int summand) {
        return value % summand == 0 && value > summand;
    }

    private int[] removeFirstElement(int[] array) {
        int[] trimmedArray = new int[array.length - 1];
        System.arraycopy(array, 1, trimmedArray, 0, array.length - 1);
        return trimmedArray;
    }
    private int[] removeLastElement(int[] array) {
        int[] trimmedArray = new int[array.length - 1];
        System.arraycopy(array, 0, trimmedArray, 0, array.length - 1);
        return trimmedArray;
    }
    /**
     * Decomposes integer n to sum of summands.
     * @param n integer n to decompose.
     * @param summand summand.
     * @return array of summands.
     */
    public int[] decompose(int n, int summand) {
        int[] returningArray = null;
        int rest = n % summand;
        int cycles = n / summand;
        if (n != summand && cycles >= 1) {
            if (rest > 0) {
                returningArray = new int[cycles + rest];
                for (int i = 0; i < cycles; i++) {
                    returningArray[i] = summand;
                }
                returningArray[returningArray.length - 1] = rest;
            }
            if (rest == 0) {
                returningArray = new int[cycles];
                for (int i = 0; i < cycles; i++) {
                    returningArray[i] = summand;
                }
            }
        }
        if (n == summand) {
            returningArray = new int[1];
            returningArray[0] = summand;
        }
        return returningArray;
    }
    /**
     * Prints specified array as sum of summands.
     * @param array specified array.
     */
    public void printArray(int[] array) {
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length - 1; i++) {
                System.out.printf("%d+", array[i]);
            }
//            System.out.printf("%d%s", array[array.length - 1], System.lineSeparator());
            System.out.printf("%d", array[array.length - 1]);
        }
    }
    public void printAr(int[] array, int summand) {
        int[] temp = array;
        int[] trail = null;
        int index = array.length - 1;;
        for (int i = 0; i < array.length - 1; i++) {
            temp = this.removeLastElement(temp);
            this.printArray(temp);
            System.out.print("+");
            trail = this.addTrail(trail, this.decompose(array[index], summand));
            this.printArray(trail);
            index--;
            System.out.println();
//            int[] co = { 10, 5, 5};
        }
    }

    public int[] addTrail(int[] array, int[] trail) {
        int[] resultArray = null;
        if (array != null && trail != null) {
            resultArray = new int[array.length + trail.length];
            System.arraycopy(array, 0, resultArray, 0, array.length);
            System.arraycopy(trail, 0, resultArray, array.length, trail.length);
        } else {
            if (array == null && trail != null) {
                resultArray = trail;
            } else {
                resultArray = array;
            }
        }
        return resultArray;
    }

//    public int[][] decomposeArray(int[] array, int summand) {
//        int cylces = 0;
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] > summand) {
//                cylces++;
//            }
//        }
//        int x = this.decompose(array[0], summand).length;
//        int[][] decomposedArray = new int [cylces][x];
////        for (int i = 0; i < cylces ; i++) {
//            for (int j = array.length - 1; j >= 0 ; j--) {
////                if (array[j] > summand) {
//                    this.printArray(this.decompose(array[j], summand));
////                }
////            }
//        }
//        return decomposedArray;
//    }
    /**
     * Starts the program.
     * @param args arguments from console.
     */
    public static void main(String[] args) {
        BanknoteChanger bc = new BanknoteChanger();
        int[] ddd = {1,2,3};
        int[] co = { 10, 5, 1};
//        bc.decomposeArray(co, 1);
//        bc.printArray(bc.addTrail(ddd, co));
//        bc.printAr(co, 1);
//        bc.printArray(bc.removeLastElement(co));
        bc.change(20, co);
//        bc.printArray(co);
//        bc.printSquareArray(bc.decomposeArray(co, 1));
//        bc.printSquareArray(ddd);
//        bc.printArray(bc.decompose(1, 1));
}
}

