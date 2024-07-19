<img width="713" alt="image" src="https://github.com/user-attachments/assets/74c1f16c-ebc9-4c61-b2f3-5f4791732f04"># 🛍 ️JoyMall




<div align="center">
    <img width="600" alt="조이몰_로고" src="https://github.com/f-lab-edu/joy-mall/assets/59166263/59a2f76a-6d25-4dfb-ab60-a25aa0441a22">
</div>

`JoyMall`은 **쇼핑몰이 필요한 물건을 구매하기 위한 수단이 아닌 상품을 한눈에 살펴보고 소통하는 공간을 제공함으로써 즐거움을 주는 것이 이 프로젝트의 취지입니다.**

<br>

# 📗 프로젝트 아키텍쳐

<div align="center">
    <img width="566" alt="image" src="https://github.com/user-attachments/assets/05088f4b-8dbf-416b-83bb-882919ccbaaf">
</div>


<br>

# 🎯 프로젝트 목표

**1. `객체지향`의 원리를 적용하여 유지보수와 확장에 용이한 코드 작성**
- 중복되는 코드, 객체의 역할과 책임을 적절하게 분리하여 응집도를 향상시킨 코드를 작성합니다.
- SOLID 원칙과 디자인 패턴의 이해를 바탕으로 객체지향 프로그래밍의 장점을 활용합니다.

**2. `스프링`에 대한 깊은 이해와 장점을 활용**
- IOC/DI, AOP 등의 핵심 원리 및 기능을 통해 코드의 결합도를 낮추고 유지보수성을 향상시킵니다.

**3. `문서화`를 통한 기록 관리**
- wiki 를 적극 활용하여 프로젝트 설계, 요구사항, 버전 관리를 통해 변화 과정을 추적하고 공유합니다.
- 백엔드, 프론트, 디자이너, PM 등 에게 공통 관심사를 공유하고 협업하는 공간을 제공합니다. 

**4. `애자일` 방법론에 따른 프로젝트 관리**
- 스프린트 관리, 스크럼, 회고를 통해 변화하는 요구사항에 유연하게 대처하고 지속적인 개선과 목표를 수립합니다.

**5. `테스트 코드`를 통한 품질 향상**
- 테스트 코드 작성과 지속적인 테스트 자동화를 통해 서비스의 품질과 안정성을 보장하는 데 중점을 둡니다.
- 대용량 트래픽과 데이터를 처리할 수 있는지 확인하기 위해 성능 테스트 합니다. 로드 테스트, 스트레스 테스트, 속도 테스트 등을 포함하여, 시스템의 처리 용량, 응답 시간, 병목 현상 탐지 등을 평가합니다.

<br>

# 🧩 사용 기술

- Java 17
- SpringBoot 3.2.3
- Spring Data JDBC
- JUnit5
- Mockito
- MySql
- Redis
- Naver Cloud Platform
- VPC
- Github Action
- k8s
- Docker
- NAS
- NGrinder
- Pinpoint
- Apache Kafka

<br>

# ✏️️ 프로토타입

<img width="1621" alt="image" src="https://github.com/f-lab-edu/joy-mall/assets/59166263/4b93e749-ea95-4d29-88c1-511dfc4df9f2">

<br>

# 📚 설계

커뮤니케이션 다이어그램, 클래스 다이어그램, ER 다이어그램의 순서로 설계하였습니다.
- 서비스를 객체와 메세지로 분리하였습니다.
- 분리한 객체와 메세지를 생각하면서 클래스의 속성과 행위를 정의하였습니다.
- 클래스의 역할과 책임을 따라 연관관계를 생각하면서 데이터베이스를 설계하였습니다. 

<img width="1671" alt="joy-mall-use-case-스샷" src="https://github.com/f-lab-edu/joy-mall/assets/59166263/04eefffd-8aa4-4fb9-a74f-ecff0144d480">

---

<img width="1671" alt="joy-mall-class-diagram_3" src="https://github.com/f-lab-edu/joy-mall/assets/59166263/d2eb15b8-dc90-4843-806f-c33b92f1c595">

---

<img width="1671" alt="joy-mall-erd" src="https://github.com/f-lab-edu/joy-mall/assets/59166263/3874227c-b112-456f-9e90-8b6f57c13a1c">

<br>

# 🥁 Git 브랜치 전략

프로젝트의 버전 관리 및 협업을 위해 Git-Flow 전략을 채택하였습니다.

<img width="1020" alt="image" src="https://github.com/f-lab-edu/joy-mall/assets/59166263/06ab0ce6-e13d-493d-88b5-bda6da3a1bc5">

- **main**: 제품으로 출시될 수 있는 브랜치입니다. 릴리스 이력을 관리하기 위해 사용됩니다.
- **develop**: 다음 출시 버전을 개발하는 브랜치입니다. 기능 개발 및 버그 수정이 이루어지며, 모든 변경사항은 이 브랜치에 병합됩니다.
- **feature**: 새로운 기능 개발이나 실험적인 작업을 위한 브랜치입니다. 각 기능 개발이 완료되면 `develop` 브랜치로 병합됩니다.
- **release**: 이 브랜치는 준비 중인 릴리스의 최종 버그 수정 및 문서 작업 등을 위해 사용됩니다. 준비가 완료되면 `main`과 `develop` 브랜치에 병합됩니다.
- **hotfix**: 이미 출시된 버전에서 발생한 긴급한 버그를 수정하기 위한 브랜치입니다. 수정이 완료되면 `main`과 `develop` 브랜치에 병합됩니다.

<br>

> ### JoyMall 의 기록 
> #### [Sprint](https://github.com/f-lab-edu/joy-mall/projects?query=is%3Aopen) | [Wiki](https://github.com/f-lab-edu/joy-mall/wiki) <br>
