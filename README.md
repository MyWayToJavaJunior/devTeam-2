DevTeam
================

**@devTeam** is a project, that created to help developers commands to improve their communications with customers and establish workflow.


## Specification
<p>
<blockquote>
 Система Команда разработчиков. Заказчик представляет Техническое Задание (ТЗ), в котором в вольной форме текстом описано задание. Задание попадает менеджеру. Менеджер рассматривает ТЗ и оформляет Проект, назначая на него незанятых Разработчиков требуемой квалификации, cоздает перечень Работ, которые нужно выполнить,  рассчитывается стоимость Проекта и Заказчику выставляется Счет. Разработчик имеет возможность отметить количество часов, затраченных на работу над проектом. >
</blockquote>
</p>

## DataBase structure
![](https://cloud.githubusercontent.com/assets/4557935/6166427/d41a53bc-b2b8-11e4-9ab1-d973506f58a6.png)

DataBase chema for MySQLWorkBench in file *model.mwb* . DB has normalized to the 3thd normal form.

## Requirements
	* MySQL
	* Servlet, JSP, JSTL
	* Log4j2
	* JUnit
	* BootStrap
	

## Behavior (Возможности)

В программе реализованы 3 модели поведения : **customer** , **developer**  и **manager** . 

**Customer** имеет возможность отправлять в текстовой форме техническое задание (*specification*) в комманду разработчиков. Так же на его *dashboard*'е  отображаеться статус его проектов, цена за проекты.  

**Developer** изначально имеет статус занятости (*isFree*). Если он хочет попасть в новый проект , он выставляет свою кандидатуру для менеджеров, меняя статус на "свободен". Таким образом он становиться видимым для отбора менеджеров. На его *dashboard*'е  отображаються все проекты, в которых он задействован. Также *Developer* может добавить количество часов, потраченых на определенный проект.

**Manager** также  имеет статус занятости (*isFree*). Если он имеет желание взяться за новый проект, он, меняя статус на "свободен", получает отображение на  *dashboard*'е всех технических заданий, присланых заказчиками, по которым еще не создали *Project*ы. Он имеет возможность выбрать ТЗ, и начать подготавливать проект. В процессе приготовления, **Manager** определяет имя *Project*'а , рассчитывает его стоимость и назначает "незанятых разработчиков" (*isFree*).
На его *dashboard*'е  отображаються все проекты, в которых он задействован. Также **Manager** может добавить количество часов, потраченых на определенный проект.

## Distribution and Contribution

No special license is provided. This software is considered to be confidential.

The following people are involved in development:

  *  Sashyn Vitalii <sashyn.v@gmail.com>

Mail them any suggestions, bugreports and comments.

