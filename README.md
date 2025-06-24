
<a href="https://reqres.in/ "> <img src="media/logo.png" width="350" height="200"> 
<h1 >Проект по автоматизации тестирования для компании <a href="https://reqres.in/ "> Reqres</a></h1> 

## ☑️ Содержание:

- Технологии и инструменты
- Список проверок, реализованных в тестах
- Запуск тестов в Jenkins и из терминала
- Allure-отчет
- Уведомление в Telegram о результатах прогона тестов

<a id="tools"></a>
## :ballot_box_with_check:Технологии и инструменты:
<p align="center">
<img width="7%" title="IntelliJ IDEA" src="media/intellij-idea-svgrepo-com.svg">
<img width="7%" title="Java" src="media/java-svgrepo-com.svg">
<img width="7%" title="Allure Report" src="media/Allure.svg">
<img width="7%" title="Gradle" src="media/gradle-svgrepo-com.svg">
<img width="7%" title="JUnit5" src="media/Junit5.svg">
<img width="7%" title="Allure TestOps" src="media/Allure_TestOps.svg">
<img width="7%" title="GitHub" src="media/github-badge-svgrepo-com.svg">
<img width="7%" title="Jenkins" src="media/Jenkins.svg">
<img width="7%" title="Telegram" src="media/Telegram.svg">
<img width="7%" title="RestAssured" src="media/RestAssured.svg">
</p>
<a id="cases"></a>

## :ballot_box_with_check: Реализованные проверки:

### Авторизация
- 🔑 Успешный вход и проверка токена
- 🔒 Неудачные попытки входа с невалидным эмейлом и паролем

### Пользователи
- ✅ Получение списка юзеров
- ✅ Сравнение данных из респонса
- ✅ Удаление юзера
- ✅ Пагинация

## <img alt="Jenkins" height="25" src="media/Jenkins.svg" width="25"/> Сборка в [Jenkins](https://jenkins.autotests.cloud/job/C34-Olejjka1337-unit14-UseTech/)


<p align="center">  
<img src="media/JenkinsBuild.png" alt="Jenkins" width="950"/>  
</p>


## Команда для запуска из терминала
Локальный запуск
```bash
gradle clean AllApi
./gradlew AllApi
```

Удаленный запуск через Jenkins:
```bash  
clean AllApi
```

## <img alt="Allure" height="25" src="media/Allure.svg" width="25"/></a>  <a name="Allure"></a>Allure Report	</a>


## Основная страница отчёта

<p align="center">  
<img title="Allure Overview Dashboard" src="media/AllureReport.png" width="850">  
</p>  

## <img alt="Allure" height="25" src="media/Allure_TestOps.svg" width="25"/></a>  <a name="Allure TestOps"></a>Allure TestOps	</a>
    
## Основная страница отчёта Allure TestOps

<p align="center">  
<img title="Allure Overview Dashboard" src="media/AllureTestOpsReport.png" width="850">  
</p>  

## Список тест-кейсов
<p align="center">  
<img title="Allure Overview Dashboard" src="media/TestCase.png" width="850">  
</p>  
____
<p align="center">  
<img title="Allure Overview Dashboard" src="media/tgAllert.jpg" width="550">  
</p>

____
## <img alt="Allure" height="25" src="media/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____
<p align="center">  
<img title="Allure Overview Dashboard" src="media/tgAllert1.jpg" width="550" />  
</p>
