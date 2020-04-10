# Study_Spring_MVC

## Spring Web MVC

- Spring Web MVC는 서블릿 API를 기반으로 만들어진 웹 프레임 워크다.
- 공식 이름은 Spring Web MVC이지만 Spring MVC라는 이름으로  더 많이 불린다.
- Spring MVC에는 웹 애플리케이션 개발을 위한 다양한 라이브러리가 포함되어 있으며 이를 통해 반복적인 작업을 상당히 줄일 수 있어 프로젝트 수행의 생산성 및 유지 보수성을 높일 수 있다.
- Spring Framework에서 제공되는 다양한 기능을 모두 사용할 수 있으며 웹 애플리케이션 개발을 위한 기능들을 포함하고 있다.

<br>

## 개발 방식

- Spring MVC 개발 방식은 Spring Framework와 동일하게 XML을 이용한 방법과 자바 어노테이션을 이용하는 방법을 제공한다.

<br>

## MVC 개념

- MVC(Model-View-Controller)는 소프트웨어 엔지니어링에서 사용자 인터페이스와 애플리케이션 로직을 분리하는 데 사용되는 패턴이다.
- 이름에서 알 수 있듯이 MVC 패턴에는 3개의 레이어가 있다.
  - Model
    - 애플리케이션의 비즈니스 계층을 정의한다. 
    - 시스템의 비즈니스 로직을 포함하고 애플리케이션의 상태를 나타내는 데이터 계층이다. 
    - 프레젠테이션 레이어와 무관하며 컨트롤러는 모델 레이어에서 데이터를 가져와서 뷰 레이어로 보낸다.
  - View
    - 애플리케이션의 프레젠테이션 계층을 정의한다. 
    - 일반적으로 UI 형식의 응용 프로그램 출력을 나타낸다. 프레젠테이션 레이어는 컨트롤러가 가져온 모델 데이터를 표시하는데 사용한다.
  - Controller
    - 애플리케이션의 흐름을 관리한다.
    - View와 Model 간의 인터페이스 역할을 한다. View계층에서 요청을 받고 필요한 유효성 검사를 포함하여 요청을 처리한다.
    - 요청은 데이터 처리를 위해 모델 계층으로 추가 전성되고, 일단 처리되면 데이터는 데이터 컨트롤러로 다시 전송된 다음 View에 표시된다.
- Java Context에서 Model은 간단한 Java 클래스로 구성되고 Controller는 서블릿으로 구성되며, View는 JSP 페이지로 구성된다.

<br>

## DispatcherServlet

- Servlet/JSP에서 사용자 요청이 발생하면 이 요청 정보를 해석하고 개발자가 만든 코드를 동작시키는 첫 번째 서블릿이다.
- Spring MVC는 DispatcherServlet을 확대하여 Spring Framework가 가지고 있는 기능을 사용할 수 있도록 이 클래스를 재정의하고 있다.
- Spring MVC 프로젝트 설정에서 가장 먼저 해야하는 일은 DispatcherServlet 클래스를 Spring MVC에서 재정의한 클래스로 설정하는 일이다.

<br>

## 설정(Setting)하기

Spring을 세팅하는 방법에는 2가지 방법이 있다.(XML, Java)

Spring 4버전에서는 XML만 지원했으나 5버전은 Java로 세팅하는 방법도 제공한다.

### XML로 세팅하기

- DispatcherServlet 설정

  - 클래스를 Spring에서 제공하는 클래스로 설정한다.

    ```
    <!-- 요청 정보를 분석해서 컨트롤러를 선택하는 서블릿을 지원한다. -->
    <servlet>
        <servlet-name>appServlet</servlet-name>
        <!-- Spring MVC에서 제공하고 있는 기본 서블릿을 지정한다. -->
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    	<!-- 현재 웹 애플리케이션에서 받아들이는 모든 요청에 대해 appServlet이라는 이름으로 정의되어 있는 서블릿을 사용하겠다. -->
    <servlet-mapping>
        <servlet-name>appServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    ```

- ApplicationContext 설정

  - Spring MVC 로 만든 웹 애플리케이션에 대한 설정을 하는 파일이다.

    ```xml
    <!-- 요청 정보를 분석해서 컨트롤러를 선택하는 서블릿을 지원한다. -->
    <servlet>
    	<servlet-name>appServlet</servlet-name>
    	<!-- Spring MVC에서 제공하고 있는 기본 서블릿을 지정한다. -->
    	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    	<!-- Spring MVC 설정을 위한 xml 파일을 지정한다. -->
    	<init-param>
    		<param-name>contextConfigLocation</param-name>
    		<param-value>/WEB-INF/config/servlet-context.xml</param-value>
    	</init-param>
    	<load-on-startup>1</load-on-startup>
    </servlet>
    ```

- RootContext 파일 설정

  - Spring MVC 프로젝트 수행 시 사용할 Bean들을 정의하는 파일이다.

    ```xml
    <!-- Bean을 정의할 xml 파일을 지정한다.  -->
    <context-param>
    	<param-name>contextConfigLocation</param-name>
    	<param-value>/WEB-INF/config/root-context.xml</param-value></context-param>
    	
    <!-- 리스너 설정 -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    ```

- encodingFilter 설정

  - 파라미터 필터 설정. 파라미터에 한글이 있을 경우를 위해 인코딩을 설정한다.

    ```xml
    <!-- 파라미터 인코딩 필터 설정 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    ```

- Controller 설정

  - app-context.xml 파일에 Controller를 찾을 수 있도록 설정을 한다.

    ```xml
    <annotation-driven/>
    <!-- 스캔한 bean들이 모여있는 패키지를 지정한다. -->
    <context:component-scan base-package="com.ksm.controller"/>
    ```

  - Controller를 구현한다.

    ```java
    @Controller
    public class HomeController {
        
        @RequestMapping(value ="/", method = RequestMethod.GET)
        public String home() {
            return "index";
        }
    }
    ```

- JSP 설정

  - Controller가 수행되고 랜더링 될 jsp를 설정한다.

    ```xml
    <!-- Controller의 메서드에서 반환하는 문자열 앞 뒤에 붙힐 경로 정보를 세팅한다. -->
    	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
       <beans:property name="prefix" value="/WEB-INF/views/"/>
       <beans:property name="suffix" value=".jsp"/>
    </beans:bean>
    ```

- 정적 파일 경로 설정

  - HTML에서 사용할 정적 파일들(이미지, 사운드, JS, CSS 등)이 배치될 경로를 지정한다.

    ```
    <!-- 정적파일(이미지, 사운드, 동영상, JS, CSS 등등) 경로 세팅 -->
    	<resources mapping="/**" location="/resources/"/>
    ```

<br>

### Java로 세팅하기