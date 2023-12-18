# 2023-2-OSSP1-DguHeroes-2
<img src = "../image/banner.png">


# 👩🏻‍💻 Teaming 


## 🍎 프로젝트 소개

## 🙆🏻‍♀️ Team
| 팀 | 이름 | 전공 | 역할  |
|----| ----- | ----- | -------- |
| 팀장 | 유수민 | 컴퓨터공학전공 | yet  |
| 팀원 | 이기백 | 컴퓨터공학전공 | yet  |
| 팀원 | 임성혁 | 컴퓨터공학전공 | yet  |
| 팀원 | 문현우 | 컴퓨터공학전공 | yet  |

## 1. 개발 환경 및 기술 스택
**✨Front** <br>
<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=black">
<img src="https://img.shields.io/badge/Css-1572B6?style=for-the-badge&logo=Css&logoColor=white">
<img src="https://img.shields.io/badge/TypeScript-FFFAF0?style=for-the-badge&logo=TypeScript&logoColor=3178C6">



**✨Back** <br>
<img src="https://img.shields.io/badge/Spring-228B22?style=for-the-badge&logo=Spring&logoColor=green">
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=yellow">



**✨GitHub Crawlling** <br>
<img src="https://img.shields.io/badge/FastAPI-00CED1?style=for-the-badge&logo=FastAPI&logoColor=blue">

## 2. 프로젝트 전체 구조


## 3. 프로젝트 디렉토리 구조
<details>
<summary>Frontend 구조 확인하기</summary>
<div markdown="1">
```
src
├── api
│   ├── base.ts
│   ├── getMainInfo.ts
│   ├── getProjectDetails.ts
│   ├── getProjectList.ts
│   ├── getUserInfo.ts
│   ├── getUserProjectManageAply.ts
│   ├── getUserProjectManageRecommend.ts
│   ├── getUserprojectList.ts
│   ├── postProjectCreate.ts
│   ├── postUpdateUrs.ts
│   ├── postUserJoin.ts
│   ├── postUserLogin.ts
│   ├── postUserLogout.ts
│   └── postUserProjectManageHire.ts
├── assets
│   └── images
│       ├── card
│       │   ├── card_img1.png
│       │   ├── card_img2.png
│       │   ├── card_img3.png
│       │   ├── card_img4.png
│       │   └── card_img5.png
│       ├── default_background.png
│       ├── logo.png
│       ├── main
│       │   ├── banner_background.png
│       │   ├── project_list_icon1.png
│       │   └── project_list_icon2.png
│       ├── missing_avatar.png
│       ├── profile
│       │   ├── github.png
│       │   └── insta.png
│       └── project
│           └── titleIcon.png
├── components
│   ├── CommonHeader
│   │   ├── CommonHeader.tsx
│   │   ├── index.ts
│   │   └── styled.ts
│   ├── Display
│   │   ├── Display.tsx
│   │   ├── index.ts
│   │   └── styled.ts
│   ├── ManageProjectCard
│   │   ├── ManageProjectCard.tsx
│   │   ├── index.ts
│   │   └── styled.ts
│   ├── ProjectCard
│   │   ├── ProjectCard.tsx
│   │   ├── index.ts
│   │   └── styled.ts
│   ├── Question
│   │   ├── Question.tsx
│   │   ├── index.ts
│   │   └── styled.ts
│   └── QuestionnaireModal
│       ├── QuestionnaireModal.tsx
│       ├── index.ts
│       └── styled.ts
├── constants
│   ├── json
│   │   ├── apply_project_list_sample.json
│   │   ├── expire_project_list_sample.json
│   │   ├── invited_project_list_sample copy.json
│   │   ├── manage_project_list_sample.json
│   │   ├── project_list_sample.json
│   │   ├── questionnaire_list_sample.json
│   │   ├── questionnaire_sample.json
│   │   ├── user_list_sample.json
│   │   └── user_manage_list_sample.json
│   ├── project
│   │   ├── developmentStack.ts
│   │   └── locationOptions.ts
│   └── system
│       ├── layout.ts
│       ├── paramFilter.ts
│       └── url.ts
├── global.d.ts
├── hooks
│   └── useModal.ts
├── index.tsx
├── pages
│   ├── Admin
│   │   ├── QuestionnaireDetails
│   │   │   ├── AdminQuestionnaireDetailsPage.tsx
│   │   │   ├── components
│   │   │   │   └── EditableQuestionCard
│   │   │   │       ├── EditableQuestionCard.tsx
│   │   │   │       ├── index.ts
│   │   │   │       └── styled.ts
│   │   │   ├── index.ts
│   │   │   └── styled.ts
│   │   └── QuestionnaireList
│   │       ├── AdminQuestionnaireListPage.tsx
│   │       ├── index.ts
│   │       └── styled.ts
│   ├── Join
│   │   ├── JoinPage.tsx
│   │   ├── index.ts
│   │   └── styled.ts
│   ├── Login
│   │   ├── LoginPage.tsx
│   │   ├── index.ts
│   │   └── styled.ts
│   ├── Main
│   │   ├── BannerSection
│   │   │   ├── BannerSection.tsx
│   │   │   ├── index.ts
│   │   │   └── styled.ts
│   │   ├── MainPage.tsx
│   │   ├── PopularProjectListSection
│   │   │   ├── PopularProjectListSection.tsx
│   │   │   ├── index.ts
│   │   │   └── styled.ts
│   │   ├── RecentProjectListSection
│   │   │   ├── RecentProjectListSection.tsx
│   │   │   ├── index.ts
│   │   │   └── styled.ts
│   │   ├── RecommendProjectListSection
│   │   │   ├── RecommendProjectListSection.tsx
│   │   │   ├── index.ts
│   │   │   └── styled.ts
│   │   ├── index.ts
│   │   └── styled.ts
│   ├── Project
│   │   ├── Details
│   │   │   ├── ProjectDetailsPage.tsx
│   │   │   ├── index.ts
│   │   │   └── styled.ts
│   │   └── List
│   │       ├── ProjectListPage.tsx
│   │       ├── index.ts
│   │       └── styled.ts
│   ├── Recommend
│   │   └── RecommendUsers
│   │       └── RecommendModal.tsx
│   └── User
│       ├── Notice
│       │   ├── Details
│       │   │   ├── UserNoticeDetailsPage.tsx
│       │   │   ├── index.ts
│       │   │   └── styled.ts
│       │   └── List
│       │       ├── UserNoticeListPage.tsx
│       │       ├── index.ts
│       │       └── styled.ts
│       ├── Profile
│       │   ├── UserProfilePage.tsx
│       │   ├── index.ts
│       │   ├── profileHeader
│       │   │   ├── index.ts
│       │   │   ├── profileHeader.tsx
│       │   │   └── styled.ts
│       │   └── styled.ts
│       └── Project
│           ├── Create
│           │   ├── CreateProjectSection
│           │   │   ├── CreateProjectSection.tsx
│           │   │   ├── index.ts
│           │   │   └── styled.ts
│           │   ├── UserProjectCreatePage.tsx
│           │   ├── index.tsx
│           │   └── styled.ts
│           ├── Manage
│           │   ├── ApproveMemberSection
│           │   │   ├── ApproveMemberSection.tsx
│           │   │   ├── index.ts
│           │   │   └── styled.ts
│           │   ├── ChangeProjectSection
│           │   │   ├── ChangeProjectSection.tsx
│           │   │   ├── index.ts
│           │   │   └── styled.ts
│           │   ├── ManageMemberSection
│           │   │   ├── ManageMemberSection.tsx
│           │   │   ├── index.ts
│           │   │   └── styled.ts
│           │   ├── SearchMemberSection
│           │   │   ├── SearchMemberSection.tsx
│           │   │   ├── index.ts
│           │   │   └── styled.ts
│           │   ├── UserProjectManagePage.tsx
│           │   ├── index.ts
│           │   └── styled.ts
│           ├── UserProjectPage.tsx
│           ├── index.ts
│           └── styled.ts
├── styles
│   └── global.css
├── types
│   ├── project.ts
│   ├── questionnaire.ts
│   ├── stacks.ts
│   └── testdata.ts
└── utils
    ├── camelizeKey.ts
    ├── cookies.ts
    ├── decamelizeKey.ts
    ├── generateQueryKey.ts
    ├── generateRandomProjectCardLogoImg.ts
    ├── gradeQuestionnaire.ts
    ├── translateDevelopmentStack.ts
    ├── translatePosition.ts
    └── translateStatus.ts
```
</div>
</details>

<details>
<summary>Backend 구조 확인하기</summary>
<div markdown="1">
```
src
└── main
    ├── java
    │   └── com
    │       └── example
    │           └── demo
    │               ├── CorsConfig.java
    │               ├── DemoApplication.java
    │               ├── Main
    │               │   └── MainInfo.java
    │               ├── SpringConfig.java
    │               ├── apiPayload
    │               │   ├── ApiResponse.java
    │               │   ├── code
    │               │   │   ├── BaseCode.java
    │               │   │   ├── BaseErrorCode.java
    │               │   │   ├── ErrorReasonDTO.java
    │               │   │   ├── ReasonDTO.java
    │               │   │   └── status
    │               │   │       ├── ErrorStatus.java
    │               │   │       └── SuccessStatus.java
    │               │   └── exception
    │               │       └── GeneralException.java
    │               ├── constant
    │               │   └── Role.java
    │               ├── controller
    │               │   ├── AdminController.java
    │               │   ├── HelloController.java
    │               │   ├── MainController.java
    │               │   ├── ProjectController.java
    │               │   ├── UserController.java
    │               │   ├── userRecommendCalc
    │               │   │   └── UserRecommendController.java
    │               │   └── userScoreCalc
    │               │       └── UserScoreController.java
    │               ├── domain
    │               │   ├── Apply.java
    │               │   ├── Hello.java
    │               │   ├── Invitation.java
    │               │   ├── Project.java
    │               │   ├── ProjectLike.java
    │               │   ├── ProjectMember.java
    │               │   ├── User.java
    │               │   ├── position
    │               │   │   ├── PositionBase.java
    │               │   │   ├── ProjectBack.java
    │               │   │   ├── ProjectEtc.java
    │               │   │   └── ProjectFront.java
    │               │   └── stacks
    │               │       ├── Angular.java
    │               │       ├── AngularJs.java
    │               │       ├── ApacheSpark.java
    │               │       ├── AspNet.java
    │               │       ├── Blazor.java
    │               │       ├── C.java
    │               │       ├── CSharp.java
    │               │       ├── Cpp.java
    │               │       ├── Dart.java
    │               │       ├── Django.java
    │               │       ├── DotNet.java
    │               │       ├── Electron.java
    │               │       ├── Express.java
    │               │       ├── Flask.java
    │               │       ├── Flutter.java
    │               │       ├── Go.java
    │               │       ├── HtmlCss.java
    │               │       ├── Java.java
    │               │       ├── JavaScript.java
    │               │       ├── Jquery.java
    │               │       ├── Keras.java
    │               │       ├── Kotlin.java
    │               │       ├── Laravel.java
    │               │       ├── Lua.java
    │               │       ├── NestJs.java
    │               │       ├── NextJs.java
    │               │       ├── NodeJs.java
    │               │       ├── OpenCv.java
    │               │       ├── OpenGl.java
    │               │       ├── Pandas.java
    │               │       ├── Php.java
    │               │       ├── PyTorch.java
    │               │       ├── Python.java
    │               │       ├── Qt.java
    │               │       ├── R.java
    │               │       ├── RabbitMq.java
    │               │       ├── React.java
    │               │       ├── ReactNative.java
    │               │       ├── Ruby.java
    │               │       ├── RubyOnRails.java
    │               │       ├── Rust.java
    │               │       ├── ScikitLearn.java
    │               │       ├── SpringBoot.java
    │               │       ├── StackBase.java
    │               │       ├── Svelte.java
    │               │       ├── Swift.java
    │               │       ├── SwiftUi.java
    │               │       ├── TensorFlow.java
    │               │       ├── Torch.java
    │               │       ├── TypeScript.java
    │               │       └── VueJs.java
    │               ├── dto
    │               │   ├── GithubIdDTO.java
    │               │   ├── HireInfo.java
    │               │   ├── ProjectLikeDTO.java
    │               │   ├── StackDTO.java
    │               │   ├── UserProjectList.java
    │               │   └── UserScoreDTO.java
    │               ├── repository
    │               │   ├── ApplyRepository.java
    │               │   ├── ApplyRepositoryImpl.java
    │               │   ├── InvitationRepository.java
    │               │   ├── InvitationRepositoryImpl.java
    │               │   ├── ProjectLikeRepository.java
    │               │   ├── ProjectLikeRepositoryImpl.java
    │               │   ├── ProjectMemberRepository.java
    │               │   ├── ProjectMemberRepositoryImpl.java
    │               │   ├── ProjectRepository.java
    │               │   ├── ProjectRepositoryImpl.java
    │               │   ├── ResponseRepository.java
    │               │   ├── ResponseRepositoryImpl.java
    │               │   ├── UserJPARepository.java
    │               │   ├── UserRepository.java
    │               │   ├── UserRepositoryImpl.java
    │               │   └── stacks
    │               │       ├── AngularJsRepository.java
    │               │       ├── AngularRepository.java
    │               │       ├── ApacheSparkRepository.java
    │               │       ├── AspNetRepository.java
    │               │       ├── BlazorRepository.java
    │               │       ├── CRepository.java
    │               │       ├── CSharpRepository.java
    │               │       ├── CppRepository.java
    │               │       ├── DartRepository.java
    │               │       ├── DjangoRepository.java
    │               │       ├── DotNetRepository.java
    │               │       ├── ElectronRepository.java
    │               │       ├── ExpressRepository.java
    │               │       ├── FlaskRepository.java
    │               │       ├── FlutterRepository.java
    │               │       ├── GoRepository.java
    │               │       ├── HtmlCssRepository.java
    │               │       ├── JavaRepository.java
    │               │       ├── JavaScriptRepository.java
    │               │       ├── JqueryRepository.java
    │               │       ├── KerasRepository.java
    │               │       ├── KotlinRepository.java
    │               │       ├── LaravelRepository.java
    │               │       ├── LuaRepository.java
    │               │       ├── NestJsRepository.java
    │               │       ├── NextJsRepository.java
    │               │       ├── NodeJsRepository.java
    │               │       ├── OpenCvRepository.java
    │               │       ├── OpenGlRepository.java
    │               │       ├── PandasRepository.java
    │               │       ├── PhpRepository.java
    │               │       ├── PyTorchRepository.java
    │               │       ├── PythonRepository.java
    │               │       ├── QtRepository.java
    │               │       ├── RRepository.java
    │               │       ├── RabbitMqRepository.java
    │               │       ├── ReactNativeRepository.java
    │               │       ├── ReactRepository.java
    │               │       ├── RubyOnRailsRepository.java
    │               │       ├── RubyRepository.java
    │               │       ├── RustRepository.java
    │               │       ├── ScikitLearnRepository.java
    │               │       ├── SpringBootRepository.java
    │               │       ├── SvelteRepository.java
    │               │       ├── SwiftRepository.java
    │               │       ├── SwiftUiRepository.java
    │               │       ├── TensorFlowRepository.java
    │               │       ├── TorchRepository.java
    │               │       ├── TypeScriptRepository.java
    │               │       └── VueJsRepository.java
    │               ├── response
    │               │   ├── AdminResponse.java
    │               │   ├── CommonResponse.java
    │               │   ├── ListResponse.java
    │               │   ├── ResponseService.java
    │               │   └── SingleResponse.java
    │               └── service
    │                   ├── ApplyService.java
    │                   ├── InvitationService.java
    │                   ├── ProjectLikeService.java
    │                   ├── ProjectMemberService.java
    │                   ├── ProjectService.java
    │                   ├── UserScoreService.java
    │                   ├── UserService.java
    │                   └── recommendCalc
    │                       ├── AccessDB.java
    │                       ├── GetDistance.java
    │                       ├── Recommend.java
    │                       └── Urs.java
    └── resources
        ├── application.yml
        ├── data.sql
        └── http
```
</div>
</details>

<details>
<summary>GitHub Crawlling 구조 확인하기</summary>
<div markdown="1">
.
├── __pycache__
│   ├── common_variable.cpython-310.pyc
│   ├── common_variable.cpython-36.pyc
│   ├── common_variable.cpython-38.pyc
│   ├── get_profile.cpython-310.pyc
│   ├── get_profile.cpython-36.pyc
│   ├── get_profile.cpython-38.pyc
│   ├── github_score_12.cpython-310.pyc
│   ├── github_score_12.cpython-36.pyc
│   ├── github_score_12.cpython-38.pyc
│   ├── github_score_3.cpython-310.pyc
│   ├── github_score_3.cpython-36.pyc
│   ├── github_score_3.cpython-38.pyc
│   ├── github_score_4.cpython-310.pyc
│   ├── github_score_4.cpython-36.pyc
│   ├── github_score_4.cpython-38.pyc
│   ├── github_score_5.cpython-310.pyc
│   ├── github_score_5.cpython-36.pyc
│   ├── github_score_5.cpython-38.pyc
│   ├── main.cpython-310.pyc
│   ├── main.cpython-36.pyc
│   ├── main.cpython-38.pyc
│   ├── sonar_crawling.cpython-310.pyc
│   ├── sonar_crawling.cpython-36.pyc
│   └── sonar_crawling.cpython-38.pyc
├── common_variable.py
├── dailyrunner_sonar_data.pkl
├── dockerfile
├── env
│   ├── __pycache__
│   │   └── settings.cpython-310.pyc
│   └── settings.py
├── get_profile.py
├── github_score_12.py
├── github_score_3.py
├── github_score_4.py
├── github_score_5.py
├── main.py
├── project_data_last1.pkl
├── project_data_last2.pkl
├── project_data_last3.pkl
├── project_data_last4.pkl
├── project_data_last5.pkl
├── project_data_last6.pkl
├── project_data_last7.pkl
├── project_data_last8.pkl
├── project_data_last_real.pkl
├── requirements.txt
├── sonar_crawling.py
└── vercel.json
</div>
</details>

## 4. 기능 소개

## 5. 개선 결과

## ✏️ 사용법 
**✨공통 사항**
```git
git clone https://github.com/CSID-DGU/2023-2-OSSP1-DguHeroes-2
```

**✨Frontend**
```linux
cd Frontend
```

**✨Backend**
```linux
cd Backend
```

**✨GitHub Crawlling**
```linux
cd Recommend/userScore
```

## 🌴 Branch
| 이름 | 설명 |
| --- | --- |
| main | PR을 거쳐 오류가 없는 브랜치 |
| back | BE 기능 개발 및 관리 |
| front | FE 기능 개발 및 관리 |
| recommend | 추천 기능 개발 및 관리 |
| crawlling | gitHub 크롤링 및 유저 스택의 숙련도 계산 |


## 🎯 Commit Convention
| 제목 | 설명 |
| --- | --- |
| Feat: | 새로운 기능 추가 |
| Fix: | 버그 수정 |
| Docs: | 문서 수정 |
| Update: | 기타 업데이트 |
| Style: | 코드 포맷 변경, 세미콜론 누락, 코드 변경 없음 |
| Refactor: | 프로덕션 코드 리팩터링 |
| Comment: | 주석 생성, 수정 및 삭제 |
| test: | 테스트 추가, 테스트 코드 리팩터링, 프로덕션 코드 변경 없음 |
| Chore: | 빌드 테스크 업데이트, 패키지 매니저 환경설정, 프로덕션 코드 변경 없음 |

## 🔮 Reference
1. [2023-1.ver] (https://github.com/CSID-DGU/2023-1-OSSP1-colorful-7?tab=readme-ov-file "동국대학교 teaming")
2. [2022.ver] (https://github.com/kookmin-sw/capstone-2022-17 "국민대학교 teaming")
