package ru.dionisius.input;

/**
 * Created by Dionisius on 02.12.2016.
 */
public class ValidateInput extends ConsoleInput {
    public int ask(String quastion, int[] range){
        boolean invalid = true;
        int value = -1;
        do{
            try{
                value = super.ask(quastion, range);
                invalid = false;
            } catch(MenuOutException moe){
                System.out.println("Введите данные из заданного диапазона пунктов меню.");
            } catch(NumberFormatException nfe){
                System.out.println("Введите корректные данные.");
            }
        } while(invalid);
        return value;
    }
}
