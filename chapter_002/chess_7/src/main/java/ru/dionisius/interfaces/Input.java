package ru.dionisius.models;

import java.util.*;

interface Input {
	
	public String ask(String quastion);
	
	public int ask(String quastion, int[] range);
	
}