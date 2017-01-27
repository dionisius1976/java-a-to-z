//package ru.dionisius;
//
///**
// * Created by Dionisius on 20.01.2017.
// */
//public class Fain {
//    Button squares[];
//    Button newGameButton;
//    Label score;
//    int emptySquaresLeft=9;
//    /**
//     * Метод init – это конструктор апплета
//     */
//    public void init(){
////Устанавливаем менеджер расположения апплета, шрифт и цвет
//        this.setLayout(new BorderLayout());
//        this.setBackground(Color.CYAN);
//        // Изменяем шрифт апплета так, чтобы он был жирным
//        // и имел размер 20
//        Font appletFont=new Font("Monospased",Font.BOLD, 20);
//        this.setFont(appletFont);
//        // Создаем кнопку “New Game” и регистрируем в ней
//        // слушатель действия
//        Программирование на Java для детей, родителей, бабушек и дедушек 127
//        newGameButton=new Button("New Game");
//        newGameButton.addActionListener(this);
//        Panel topPanel=new Panel();
//        topPanel.add(newGameButton);
//        this.add(topPanel,"North");
//        Panel centerPanel=new Panel();
//        centerPanel.setLayout(new GridLayout(3,3));
//        this.add(centerPanel,"Center");
//        score=new Label("Your turn!");
//        this.add(score,"South");
//        // создаем массив, чтобы хранить ссылки на 9 кнопок
//        squares=new Button[9];
//        // Создаем кнопки, сохраняем ссылки на них в массиве
//        // регистрируем в них слушатель, красим их
//        // в оранжевый цвет и добавляем на панель
//        for(int i=0;i<9;i++){
//            squares[i]=new Button();
//            squares[i].addActionListener(this);
//            squares[i].setBackground(Color.ORANGE);
//            centerPanel.add(squares[i]);
//        }
//    }
//    /**
//     * Этот метод будет обрабатывать все события
//     * @param ActionEvent объект
//     */
//    public void actionPerformed(ActionEvent e) {
//        Button theButton = (Button) e.getSource();
//        // Это кнопка New Game ?
//        if (theButton ==newGameButton){
//            for(int i=0;i<9;i++){
//                squares[i].setEnabled(true);
//                squares[i].setLabel("");
//                squares[i].setBackground(Color.ORANGE);
//            }
//            emptySquaresLeft=9;
//            score.setText("Your turn!");
//            newGameButton.setEnabled(false);
//            return; // выходим из метода
//        }
//        String winner = "";
//// Это одна из клеток?
//        for ( int i=0; i<9; i++ ) {
//            if ( theButton == squares[i] ) {
//                squares[i].setLabel("X");
//                winner = lookForWinner();
//                if(!"".equals(winner)){
//                    endTheGame();
//                } else {
//                    computerMove();
//                    winner = lookForWinner();
//                    if ( !"".equals(winner)){
//                        endTheGame();
//                    }
//                }
//                break;
//            }
//        } // конец цикла for
//        if ( winner.equals("X") ) {
//            score.setText("You won!");
//        } else if (winner.equals("O")){
//            score.setText("You lost!");
//        } else if (winner.equals("T")){
//            score.setText("It's a tie!");
//        }
//    } // конец метода actionPerformed
//    /**
//     * Этот метод вызывается после каждого хода, чтобы узнать,
//     * есть ли победитель. Он проверяет каждый ряд, колонку
//     * и диагональ, чтобы найти три клетки с одинаковыми надписями
//     * (не пустыми)
//     * @return "X", "O", "T" – ничья, "" - еще нет победителя
//     */
//    String lookForWinner() {
//        String theWinner = "";
//        emptySquaresLeft--;
//        if (emptySquaresLeft==0){
//            return "T"; // это ничья. T от английского слова tie
//        }
//
//// Проверяем ряд 1 – элементы массива 0,1,2
//        if (!squares[0].getLabel().equals("") &&
//                squares[0].getLabel().equals(squares[1].getLabel()) &&
//                squares[0].getLabel().equals(squares[2].getLabel())) {
//            theWinner = squares[0].getLabel();
//            highlightWinner(0,1,2);
//// Проверяем ряд 2 – элементы массива 3,4,5
//        } else if (!squares[3].getLabel().equals("") &&
//                squares[3].getLabel().equals(squares[4].getLabel()) &&
//                squares[3].getLabel().equals(squares[5].getLabel())) {
//            theWinner = squares[3].getLabel();
//            highlightWinner(3,4,5);
//// Проверяем ряд 3 – элементы массива 6,7,8
//        } else if ( ! squares[6].getLabel().equals("") &&
//                squares[6].getLabel().equals(squares[7].getLabel()) &&
//                squares[6].getLabel().equals(squares[8].getLabel())) {
//            theWinner = squares[6].getLabel();
//            highlightWinner(6,7,8);
//// Проверяем колонку 1 – элементы массива 0,3,6
//        } else if ( ! squares[0].getLabel().equals("") &&
//                squares[0].getLabel().equals(squares[3].getLabel()) &&
//                squares[0].getLabel().equals(squares[6].getLabel())) {
//            theWinner = squares[0].getLabel();
//            highlightWinner(0,3,6);
//// Проверяем колонку 2 – элементы массива 1,4,7
//        } else if ( ! squares[1].getLabel().equals("") &&
//                squares[1].getLabel().equals(squares[4].getLabel()) &&
//                squares[1].getLabel().equals(squares[7].getLabel())) {
//            theWinner = squares[1].getLabel();
//            highlightWinner(1,4,7);
//// Проверяем колонку 3 – элементы массива 2,5,8
//        } else if ( ! squares[2].getLabel().equals("") &&
//                squares[2].getLabel().equals(squares[5].getLabel()) &&
//                squares[2].getLabel().equals(squares[8].getLabel())) {
//            Программирование на Java для детей, родителей, бабушек и дедушек 130
//            theWinner = squares[2].getLabel();
//            highlightWinner(2,5,8);
//// Проверяем первую диагональ – элементы массива 0,4,8
//        } else if ( ! squares[0].getLabel().equals("") &&
//                squares[0].getLabel().equals(squares[4].getLabel()) &&
//                squares[0].getLabel().equals(squares[8].getLabel())) {
//            theWinner = squares[0].getLabel();
//            highlightWinner(0,4,8);
//// Проверяем вторую диагональ – элементы массива 2,4,6
//        } else if ( ! squares[2].getLabel().equals("") &&
//                squares[2].getLabel().equals(squares[4].getLabel()) &&
//                squares[2].getLabel().equals(squares[6].getLabel())) {
//            theWinner = squares[2].getLabel();
//            highlightWinner(2,4,6);
//        }
//        return theWinner;
//    }
//    /**
//     * Этот метод применяет набор правил, чтобы найти
//     * лучший компьютерный ход. Если хороший ход
//     * не найден, выбирается случайная клетка.
//     */
//    void computerMove() {
//        int selectedSquare;
//        // Сначала компьютер пытается найти пустую клетку
//        // рядом с двумя клетками с ноликами, чтобы выиграть
//        selectedSquare = findEmptySquare("O");
//        // Если он не может найти два нолика, то хотя бы
//        // попытается не дать оппоненту сделать ряд из 3-х
//        // крестиков,поместив нолик рядом с двумя крестиками
//        if ( selectedSquare == -1 )
//            selectedSquare = findEmptySquare("X");
//    }
//    // если selectedSquare все еще равен -1, то
//    // попытается занять центральную клетку
// if ( (selectedSquare == -1)
//            &&(squares[4].getLabel().equals("")) ){
//        selectedSquare=4;
//    }
//    // не повезло с центральной клеткой...
//    // просто занимаем случайную клетку
//            if ( selectedSquare == -1 ){
//        selectedSquare = getRandomSquare();
//
//
//    }
//    squares[selectedSquare].setLabel("O");
//}
//    /**
//     * Этот метод проверяет каждый ряд, колонку и диагональ
//     * чтобы узнать, есть ли в ней две клетки
//     * с одинаковыми надписями и пустой клеткой.
//     * @param передается X – для пользователя и O – для компа
//     * @return количество свободных клеток,
//     * или -1, если не найдено две клетки
//     * с одинаковыми надписями
//     */
//    int findEmptySquare(String player) {
//        int weight[] = new int[9];
//        for ( int i = 0; i < 9; i++ ) {
//            if ( squares[i].getLabel().equals("O") )
//                weight[i] = -1;
//            else if ( squares[i].getLabel().equals("X") )
//                weight[i] = 1;
//            else
//                weight[i] = 0;
//        }
//        int twoWeights = player.equals("O") ? -2 : 2;
//        // Проверим, есть ли в ряду 1 две одинаковые клетки и
//        // одна пустая.
//        if ( weight[0] + weight[1] + weight[2] == twoWeights ) {
//            if ( weight[0] == 0 )
//                return 0;
//            else if ( weight[1] == 0 )
//                return 1;
//            else
//                return 2;
//        }
//        // Проверим, есть ли в ряду 2 две одинаковые клетки и
//        // одна пустая.
//        if (weight[3] +weight[4] + weight[5] == twoWeights) {
//            if ( weight[3] == 0 )
//                return 3;
//            else if ( weight[4] == 0 )
//                return 4;
//            else
//                return 5;
//        }
//// Проверим, есть ли в ряду 3 две одинаковые клетки и
//// одна пустая.
//        if (weight[6] + weight[7] +weight[8] == twoWeights ) {
//            if ( weight[6] == 0 )
//                return 6;
//            else if ( weight[7] == 0 )
//                return 7;
//            else
//                return 8;
//        }
//// Проверим, есть ли в колонке 1 две одинаковые клетки и
//// одна пустая.
//        if (weight[0] + weight[3] + weight[6] == twoWeights) {
//            if ( weight[0] == 0 )
//                return 0;
//            else if ( weight[3] == 0 )
//                return 3;
//            else
//                return 6;
//        }
//// Проверим, есть ли в колонке 2 две одинаковые клетки
//// и одна пустая.
//        if (weight[1] +weight[4] + weight[7] == twoWeights ) {
//            if ( weight[1] == 0 )
//                return 1;
//            else if ( weight[4] == 0 )
//                return 4;
//            else
//                return 7;
//        }
//// Проверим, есть ли в колонке 3 две одинаковые клетки
//// и одна пустая.
//        if (weight[2] + weight[5] + weight[8] == twoWeights ){
//            if ( weight[2] == 0 )
//                return 2;
//            else if ( weight[5] == 0 )
//                return 5;
//            else
//                return 8;
//        }
//// Проверим, есть ли в диагонали 1 две одинаковые клетки
//// и одна пустая.
//        if (weight[0] + weight[4] + weight[8] == twoWeights ){
//            if ( weight[0] == 0 )
//                return 0;
//            else if ( weight[4] == 0 )
//                return 4;
//            else
//                return 8;
//        }
//// Проверим, есть ли в диагонали 2 две одинаковые клетки
//// и одна пустая.
//        if (weight[2] + weight[4] + weight[6] == twoWeights ){
//            if ( weight[2] == 0 )
//                return 2;
//            else if ( weight[4] == 0 )
//                return 4;
//            else
//                return 6;
//        }
//// Не найдено двух одинаковых соседних клеток
//        return -1;
//    } // конец метода findEmptySquare()
//    /**
//     * Этот метод выбирает любую пустую клетку
//     * @return случайно выбранный номер клетки
//     */
//    int getRandomSquare() {
//        boolean gotEmptySquare = false;
//        int selectedSquare = -1;
//        do {
//            selectedSquare = (int) (Math.random() * 9 );
//            if (squares[selectedSquare].getLabel().equals("")){
//                gotEmptySquare = true; // чтобы закончить цикл
//            }
//        } while (!gotEmptySquare );
//        return selectedSquare;
//    } // конец метода getRandomSquare()
//    /**
//     * Этот метод выделяет выигравшую линию.
//     * @param первая, вторая и третья клетки для выделения
//     */
//    void highlightWinner(int win1, int win2, int win3) {
//        squares[win1].setBackground(Color.CYAN);
//        squares[win2].setBackground(Color.CYAN);
//        squares[win3].setBackground(Color.CYAN);
//    }
//    // Делаем недоступными клетки и доступной кнопку ”New Game”
//    void endTheGame(){
//        newGameButton.setEnabled(true);
//        for(int i=0;i<9;i++){
//            squares[i].setEnabled(false);
//        }
//    }
//} // конец класса
//}
