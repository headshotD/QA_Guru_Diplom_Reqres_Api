
<a href="https://reqres.in/ "> <img src="media/logo.png" width="350" height="200"> 
<h1 >Проект по автоматизации тестирования для компании <a href="https://reqres.in/ "> Reqres</a></h1> 

## ☑️ Содержание:

- [Технологии и инструменты](#tools)
- [Реализованные проверки](#cases)
    - [Авторизация](#авторизация)
    - [Пользователи](#пользователи)
- [Сборка в Jenkins](#jenkins-build)
- [Команда для запуска из терминала](#команда-для-запуска-из-терминала)
- [Allure Report](#allure-report)
- [Allure TestOps](#allure-testops)
- [Уведомление в Telegram](#telegram)

<a id="tools"></a>
## :ballot_box_with_check:Технологии и инструменты:
<p align="center">
<a href="https://www.java.com/ru/"> <img width="7%" title="Java" src="media/java-svgrepo-com.svg">
<a href="https://github.com/allure-framework/"> <img width="7%" title="Allure Report" src="media/Allure.svg">
<a href="https://gradle.org/"> <img width="7%" title="Gradle" src="media/gradle-svgrepo-com.svg">
<a href="https://junit.org/"> <img width="7%" title="JUnit5" src="media/Junit5.svg">
<a href="https://qameta.io/"> <img width="7%" title="Allure TestOps" src="media/Allure_TestOps.svg">
<a href="https://github.com/"> <img width="7%" title="GitHub" src="media/github-badge-svgrepo-com.svg">
<a href="https://www.jenkins.io/"> <img width="7%" title="Jenkins" src="media/Jenkins.svg">
<a href="https://telegram.org/"> <img width="7%" title="Telegram" src="media/Telegram.svg">
<a href="https://rest-assured.io/"> <img width="7%" title="RestAssured" src="media/RestAssured.svg">
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
  
<a id="jenkins-build"></a>
## <img alt="Jenkins" height="25" src="media/Jenkins.svg" width="25"/> Сборка в [Jenkins](https://jenkins.autotests.cloud/job/ONamozov_diplom_34_api/)


<p align="center">  
<a href="https://jenkins.autotests.cloud/job/ONamozov_diplom_34_api/"> <img src="media/JenkinsBuild.png" alt="Jenkins" width="950"/> </a>
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
<a id="allure-report"></a>
## <img alt="Allure" height="25" src="media/Allure.svg" width="25"/></a>  <a name="Allure"></a>[Allure Report](https://jenkins.autotests.cloud/job/ONamozov_diplom_34_api/27/allure/)	</a>


## Основная страница отчёта

<p align="center">
<a href="https://jenkins.autotests.cloud/job/ONamozov_diplom_34_api/27/allure/"> "<img title="Allure Overview Dashboard" src="media/AllureReport.png" width="850">  
</p>  

<a id="allure-testops"></a>
## <img alt="Allure" height="25" src="media/Allure_TestOps.svg" width="25"/></a>  <a name="Allure TestOps"></a>[Allure TestOps](https://allure.autotests.cloud/launch/47141)	</a>

<a id="allure-testops"></a>
## Основная страница отчёта [Allure TestOps](https://allure.autotests.cloud/launch/47141)

<p align="center">  
<a href="https://allure.autotests.cloud/launch/47141"> <img title="Allure Overview Dashboard" src="media/AllureTestOpsReport.png" width="850">  </a>
</p>  

## [Список](https://allure.autotests.cloud/project/4783/test-cases?treeId=0) тест-кейсов 
<p align="center">  
<a href="https://allure.autotests.cloud/project/4783/test-cases?treeId=0"> <img title="Allure Overview Dashboard" src="media/TestCase.png" width="850">  </a>
</p>

<a id="telegram"></a>
## <img alt="Allure" height="25" src="media/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____
<p align="center">  
<img title="Allure Overview Dashboard" src="media/tgAllert1.jpg" width="550" />  
</p>
