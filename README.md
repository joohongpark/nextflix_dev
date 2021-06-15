# 기존 프로젝트로부터 수정한 사항

## maven

- 패키지 위치 변경 (groupId : kr.co → com.movie)
- maven-eclipse-plugin 플러그인 삭제
- 자바 버전 적절하게 변경 (1.8  → [현재 사용중인 버전])
  - java 9부터 javax.annotation가 자바 표준에서 제외되어 디펜던시 추가
- Datanucleus 리포지토리 url https로 변경

## 데이터베이스 연결 설정

- appconfig.properties
  - 특정 서버 주소 -> localhost

# 빌드

src/main/webapp/images 폴더에 기존 이미지 파일을 복사해야 함 (git에서 사진파일을 관리하는걸 권장하지 않아 분리함)

```shell
$> mvn package
```

빌드 후 tomcat에서 실행