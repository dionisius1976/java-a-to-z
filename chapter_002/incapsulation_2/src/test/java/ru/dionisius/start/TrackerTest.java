package ru.dionisius.start;

import ru.dionisius.models.*;
import java.util.*;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class TrackerTest {
	
	long create1;
		Item item1;
		Item item2;
		Item item3;
		Item item4;
		Item item5;
		Tracker tracker;
	
	
	@Before
	public void setup(){
		create1 = new Date().getTime();
		item1 = new Item("Заявка1", "Description1", create1);
		item2 = new Item("Заявка2", "Description2", create1+50);
		item3 = new Item("Заявка3", "Description3", create1+100);
		item4 = new Item("Заявка4", "Description4", create1+200);
		item5 = new Item("Заявка5", "Description5", create1+300);
		tracker = new Tracker();
		tracker.add(item1);
		tracker.add(item2);
		tracker.add(item3);
		tracker.add(item4);
		tracker.add(item5);
	}
	
	@Test
	public void whenAddAndGetAllThenExpectedArray(){
		Item[] expectedArray = {item1, item2, item3, item4, item5};
		assertArrayEquals(expectedArray, tracker.getAll());
	}
	
	@Test
	public void whenAddCommentThenExpectedComment(){
		Comment comment = new Comment("Привет, мир!");
		tracker.addComment(item3, comment);
		String expectedCommentText = "Привет, мир!";
		Comment[] comments = item3.getComments();
		String resultText = comments[0].getText();
		assertEquals(expectedCommentText, resultText);
	}
	
	@Test
		public void whenFindByIdThenExpectedItem(){
		String expectedId = item2.getId();
		String resultId = tracker.findById(item2.getId()).getId();
		assertEquals(expectedId, resultId);
	}
	
	@Test
	public void whenFindByNameThenExpectedItem(){
		String expectedName = "Заявка3";
		String result = tracker.findByName("Заявка3").getName();
		assertEquals(expectedName, result);
	}
	
	@Test
	public void whenFindByDescThenExpectedItem(){
		String expectedDesc = "Description4";
		String result = tracker.findByDesc("Description4").getDesc();
		assertEquals(expectedDesc, result);
	}
	
	@Test
	public void whenFindByCreateThenExpectedItemCreate(){
		long expectedItemCreate = item3.getCreate();
		long result = tracker.findByCreate(item3.getCreate()).getCreate();
		assertEquals(expectedItemCreate, result);
	}
	
	@Test
	public void whenEditItemNameThenExpectedName(){
		String expectedName = "Zayavka";
		tracker.editName(item1, expectedName);
		assertEquals(expectedName, item1.getName());
	}
	
	@Test
	public void whenEditItemDescThenExpectedDesc(){
		String expectedDesc = "New description";
		tracker.editDesc(item1, expectedDesc);
		assertEquals(expectedDesc, item1.getDesc());
	}
	
	@Test
	public void whenDeleteThenExpectedArray(){
		tracker.delete(item2);
		Item[] expectedArray = {item1, null, item3, item4, item5};
		assertArrayEquals(expectedArray, tracker.getAll());
	}
}