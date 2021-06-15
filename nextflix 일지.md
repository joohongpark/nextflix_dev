

# nextflix 리팩토링 및 develop 일지

## 동작 방식

현재 전달받은 프로젝트는 메이븐으로 관리되며 스프링 프레임워크 기반에서 동작되며 빌드된 war 파일을 톰캑에서 구동시키는 형식이다. DBMS는 오라클DB를 사용하며 내부 사진파일 리소스는 기존에 파이썬 크롤러를 이용해서 다운로드를 받는다고 한다.

먼저 내 PC 환경에서 실행되는 것을 목표로 한 다음 프로젝트 설정 및 실행을 자동화한 다음에 Dockerize (도커화) 하는 것 까지가 1차 목표이다.

## 리팩토링 목표

- Oracle DB -> MySQL (혹은 다른 DBMS)
- Dockerize

### 프로젝트 실행시켜보기

#### Oracle DB 설정

NEXTFLIX.sql 파일(쿼리문들)을 아무런 설정도 하지 않은 오라클db에서 실행시켜보니 오류가 난다. 오류 메시지를 보면 해당되는 사용자가 없다고 한다.

해당 프로젝트가 어떤 사용자 권한으로 접속하는지 코드를 뜯어 보기로 했다.

```properties
jdbc.driverClassName=oracle.jdbc.driver.OracleDriver
jdbc.url=jdbc:oracle:thin:@192.168.10.13:1521:xe
jdbc.username=java
jdbc.password=oracle
```

해당 계정을 생성해 주는 쿼리문을 만든다. 유저를 생성하는 쿼리문에서도 Oracle과 MySQL의 차이가 있다. MySQL 스타일은 다음과 같다.

```sql
create user 'java'@'localhost' identified by 'oracle';
```

Oracle DB 스타일이다. 권한 문제가 없게 권한도 부여한다.

```sql
create user java identified by oracle;
GRANT CONNECT, RESOURCE, DBA TO java;
```

사용자를 생성하고 다시 쿼리문을 실행해보자. `ORA-01950: no privileges on tablespace 'SYSTEM'` 라는 에러가 뜬다.

테이블스페이스가 무엇인지 잘 몰라 찾아보았다. 테이블스페이스가 오라클(다른 DBMS에도 있는 개념인것같긴 하다)이 테이블을 저장하는 일종의 공간을 의미한다고 한다. 오류를 발생시키는 쿼리문에서 SYSTEM이라는 테이블스페이스를 사용한다 명시가 되어 있는데 아마 저 사용자에 해당하는 권한이 없어서 그런 것으로 보인다.

```sql
alter user java quota 500M on SYSTEM;
```

해당 쿼리문을 날려서 "java" 라는 사용자에게 "SYSTEM" 이라는 테이블스페이스에 500M만큼 사용할 수 있는 권한을 주자.

다시 실행해보면 "MYTS" 라는 테이블스페이스가 없다고 한다. "MYTS" 라는 테이블스페이스를 만들어 보자.

```sql
CREATE TABLESPACE MYTS
DATAFILE 'MYTS'
SIZE 2048M
AUTOEXTEND ON
NEXT 4M MAXSIZE UNLIMITED
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT MANUAL
FLASHBACK ON;

alter user java quota 500M on MYTS;
```

만들고 나서 사소한 오류가 조금 뜨긴 하지만 정상적으로 데이터는 삽입된다.

### (임시) jdbc 접속 주소 변경

appconfig.properties에 DB 접속 주소가 명시되어 있다. appconfig.properties를 찾아 jdbc.url의 ip를 localhost로 변경한다.

여기까지 해놓으면 기존 빌드된 war 파일로는 정상적으로 동작함을 확인할 수 있었다.

# 프로젝트 분석 및 변경

이제 소스코드를 고치면서 빌드를 해야 하므로 프로젝트에 대한 분석을 먼저 해보자.

## 메이븐 프로젝트의 기본디렉토리 구조

프로젝트는 메이븐으로 빌드된다고 했다. 메이븐의 전형적인 디렉토리 구조는 다음과 같다.

자바 프로젝트의 패키지가 org.project 예하에 있다고 하면 패키지 구조는 다음과 같이 된다.

```
├── pom.xml
├── target (빌드 시에 생성됨)
└── src
    └── main
        └── java
            └── org
                └── project
                    └── main.java
```

그러니까 메이븐 프로젝트의 루트 디렉토리엔 pom.xml이 있고 src라는 경로가 있다. 경로 예하엔 main 폴더가 있고 또 그 내부에 java 폴더 안에 패키지 단위로 자바 코드가 들어간다. test 폴더는 말 그대로 테스트용 폴더이며 이것은 선택사항이다. target 폴더는 프로젝트를 빌드할 때 결과물이 들어가는 폴더이다.

- pom.xml
- target (빌드 시에 생성됨)
- src
  - main (프로젝트)
    - java
      - package 구조의 소스코드
  - test (테스트 코드)
    - java
      - package 구조의 테스트용 소스코드

## 웹앱 프로젝트 구조

https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html

https://download.oracle.com/otn-pub/jcp/servlet-3.0-fr-oth-JSpec/servlet-3_0-final-spec.pdf?AuthParam=1623729620_9fdad3fc83fb4cdad0c2fe652cd3fc09

- src→main→java : 자바 소스코드
- src→main→resources : 소스코드에 대한 리소스 (설정파일 등)
- src→main→webapp : 웹앱 소스(서블릿, 동적 요소 등)
  - src→main→webapp→WEB-INF→web.xml : URL이 서블릿에 매핑되는 방법을 정의함

## Maven 프로젝트 수정

nextflix 프로젝트는 기본적으로 Maven을 통해 관리된다. 이 프로젝트는 이클립스에서 생성된 프로젝트이므로 Archetype(Archetype 은 빌드 환경에 대한 일종의 템플릿이다.)이 `maven-eclipse-plugin` 으로 되어 있다. 이클립스와 연동해서 사용하지 않을 것으므로 Archetype을 삭제한다.

그리고 groubId를 수정해야 한다. 기존 값이 왜 다른 값을 가리키고 있는진 모르겠지면 현재 프로젝트의 네임스페이스는 com.movie이므로 com.movie로 변경한다.

수정하고 메이븐을 이용해서 빌드한다.

```shell
$> mvn package
```

