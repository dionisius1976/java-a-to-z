# Chapter_10 "Hibernate"

1. необходимо создать простое приложение todolist.
	1.1. одна таблица в базе item с полями id, desc. created, done.
	1.2. веб приложение должно иметь одну страницу index.html. 
	1.3. все данные на форму загружаються через ajax.
	1.4. в верху форма. добавить новое задание. описание.
	1.5. список всех заданий. и галка выполено или нет.
	1.6. вверху добавить галку "показать все". если включена, то показывать все, если нет,
		 то только те, что не выполены done = false.
	1.7. данные должны сохраняться через hibernate.

2. Реализовать площадку продаж машин. 
	Это должно быть web приложение со следующими страницами.
	1. основная страница. таблица со всеми объявлениям машин на продажу.
	2. на странице должна быть кнопка. добавить новое объявление.
	3. переходить на страницу добавления.
	4. должны быть категории машины. марка. тип кузова и тд. посмотри как на авито сделано.
	5. важно!. можно добавлять фото. использовать библиотеку apache fileuppload
	6. объявление имеет статус продано. или нет.
	7. должны существовать пользователи. кто подал заявление. только он может менять статус.
    