<a id="readme-top"></a>

<br />
<div align="center">
  <a href="https://github.com/most1ess/springBootApp">
    
  </a>

<h3 align="center">Spring Boot Application</h3>

  <p align="center">
    описание проекта
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
2. Установите зависимости
   ```mvn
   mvn dependency:resolve
   ```
3. В корневой папке проекта создайте файл .env со следующим содержанием
   ```txt
   DB_USER=имя_пользователя
   DB_PASSWORD=пароль_базы_данных
   DB_NAME=имя_базы_данных
   ```
4. Убедитесь, что в docker-compose.yml указан верный путь к пакету с вашим проектом
   ```txt
   app:
     build:
       context: путь_к_вашему_приложению
   ```
5. Соберите проект с помощью mvn
   ```mvn
   mvn clean package
   ```
6. В директории target найдите собранный jar-архив. Его имя запишите в Dockerfile
   ```txt
   COPY target/имя_вашего_jar_архива app.jar
   ```
7. Запустите приложение с помощью docker-compose
   ```docker
   docker-compose up --build
   ```
  

<p align="right">(<a href="#readme-top">В начало</a>)</p>



<!-- USAGE EXAMPLES -->
## Использование



<p align="right">(<a href="#readme-top">В начало</a>)</p>


<!-- CONTACT -->
## Contact

Ссылка на проект: [https://github.com/most1ess/springBootApp](https://github.com/most1ess/springBootApp)

<p align="right">(<a href="#readme-top">В начало</a>)</p>
