<a id="readme-top"></a>

<br />
<div align="center">
  <a href="https://github.com/most1ess/springBootApp">
    
  </a>

<h3 align="center">Spring Boot Application</h3>

  <p align="center">
    Приложение, позволяющее манипулировать сведениями о клиентах, хранящимися в базе данных.
    <br />
    <a href="https://github.com/most1ess/springBootApp"><strong>Документация »</strong></a>
    <br />
    <br />
    <a href="https://github.com/most1ess/springBootApp">Демо</a>
    &middot;
    <a href="https://github.com/most1ess/springBootApp/issues/new?labels=bug&template=bug-report---.md">Сообщить о баге</a>
    &middot;
    <a href="https://github.com/most1ess/springBootApp/issues/new?labels=enhancement&template=feature-request---.md">Предложить изменения</a>
  </p>
</div>

### Необходимые приложения и компоненты

Для использования приложения на вашем компьютере должны быть установлены:
1. Java (с добавлением jdk в системную переменную JAVA_HOME)
2. Maven (с добавлением maven в системную переменную Path)
3. Docker

### Установка

1. Клонируйте репозиторий
   ```sh
   git clone https://github.com/most1ess/springBootApp.git
   ```
2. В корневой папке проекта создайте файл .env со следующим содержанием
   ```txt
   DB_USER=имя_пользователя
   DB_PASSWORD=пароль_базы_данных
   DB_NAME=имя_базы_данных
   ```
3. Убедитесь, что в docker-compose.yml указан верный путь к пакету с вашим проектом
   ```txt
   app:
     build:
       context: путь_к_вашему_приложению
   ```
4. Соберите проект с помощью mvn
   ```mvn
   mvn clean package
   ```
5. Запустите приложение с помощью docker-compose
   ```docker
   docker-compose up --build
   ```
  

<p align="right">(<a href="#readme-top">В начало</a>)</p>



<!-- USAGE EXAMPLES -->
## Использование

Приложение будет доступно по адресу http://localhost:8080/swagger-ui/index.html.

Реализованы запросы удаления, добавления, обновления и вывода по ID клиентов, а также вывода всех клиентов, находящихся в базе данных.

<p align="right">(<a href="#readme-top">В начало</a>)</p>


<!-- CONTACT -->
## Контакты

Ссылка на проект: [https://github.com/most1ess/springBootApp](https://github.com/most1ess/springBootApp)

<p align="right">(<a href="#readme-top">В начало</a>)</p>
