package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.stacks.*;
import com.example.demo.dto.GithubIdDTO;
import com.example.demo.dto.StackDTO;
import com.example.demo.dto.UserScoreDTO;
import com.example.demo.repository.UserJPARepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.stacks.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class UserScoreService {

    private static final Logger log = LoggerFactory.getLogger(UserScoreService.class);

    UserRepository userRepository;
    UserJPARepository userJPARepository;

    DotNetRepository dotNetRepository;
    AspNetRepository aspNetRepository;
    AngularRepository angularRepository;
    AngularJsRepository angularJsRepository;
    ApacheSparkRepository apacheSparkRepository;
    BlazorRepository blazorRepository;
    CRepository cRepository;
    CSharpRepository cSharpRepository;
    CppRepository cppRepository;
    DartRepository dartRepository;
    DjangoRepository djangoRepository;
    ElectronRepository electronRepository;
    ExpressRepository expressRepository;
    FlaskRepository flaskRepository;
    FlutterRepository flutterRepository;
    GoRepository goRepository;
    HtmlCssRepository htmlCssRepository;
    JavaRepository javaRepository;
    JavaScriptRepository javaScriptRepository;
    JqueryRepository jqueryRepository;
    KerasRepository kerasRepository;
    KotlinRepository kotlinRepository;
    LaravelRepository laravelRepository;
    LuaRepository luaRepository;
    NestJsRepository nestJsRepository;
    NextJsRepository nextJsRepository;
    NodeJsRepository nodeJsRepository;
    OpenGlRepository openGlRepository;
    OpenCvRepository openCvRepository;
    PhpRepository phpRepository;
    PandasRepository pandasRepository;
    PyTorchRepository pyTorchRepository;
    PythonRepository pythonRepository;
    QtRepository qtRepository;
    RRepository rRepository;
    RabbitMqRepository rabbitMqRepository;
    ReactRepository reactRepository;
    ReactNativeRepository reactNativeRepository;
    RubyRepository rubyRepository;
    RubyOnRailsRepository rubyOnRailsRepository;
    RustRepository rustRepository;
    ScikitLearnRepository scikitLearnRepository;
    SpringBootRepository springBootRepository;
    SvelteRepository svelteRepository;
    SwiftRepository swiftRepository;
    SwiftUiRepository swiftUiRepository;
    TensorFlowRepository tensorFlowRepository;
    TorchRepository torchRepository;
    TypeScriptRepository typeScriptRepository;
    VueJsRepository vueJsRepository;


    private Map<String, Integer> createDictionary() {

        Map<String, Integer> dictionary = new HashMap<>();
        dictionary.put(".NET", 0);
        dictionary.put("ASP.NET", 1);
        dictionary.put("Angular", 2);
        dictionary.put("AngularJS", 3);
        dictionary.put("Apache Spark", 4);
        dictionary.put("Blazor", 5);
        dictionary.put("C", 6);
        dictionary.put("C#", 7);
        dictionary.put("C++", 8);
        dictionary.put("Dart", 9);
        dictionary.put("Django", 10);
        dictionary.put("Electron", 11);
        dictionary.put("Express", 12);
        dictionary.put("Flask", 13);
        dictionary.put("Flutter", 14);
        dictionary.put("Go", 15);
        dictionary.put("HTML/CSS", 16);
        dictionary.put("Java", 17);
        dictionary.put("JavaScript", 18);
        dictionary.put("jQuery", 19);
        dictionary.put("Keras", 20);
        dictionary.put("Kotlin", 21);
        dictionary.put("Laravel", 22);
        dictionary.put("Lua", 23);
        dictionary.put("NestJS", 24);
        dictionary.put("Next.js", 25);
        dictionary.put("Node.js", 26);
        dictionary.put("OpenGL", 27);
        dictionary.put("Opencv", 28);
        dictionary.put("PHP", 29);
        dictionary.put("Pandas", 30);
        dictionary.put("PyTorch", 31);
        dictionary.put("Python", 32);
        dictionary.put("Qt", 33);
        dictionary.put("R", 34);
        dictionary.put("RabbitMQ", 35);
        dictionary.put("React", 36);
        dictionary.put("React Native", 37);
        dictionary.put("Ruby", 38);
        dictionary.put("Ruby on Rails", 39);
        dictionary.put("Rust", 40);
        dictionary.put("scikit-Learn", 41);
        dictionary.put("SpringBoot", 42);
        dictionary.put("Svelte", 43);
        dictionary.put("Swift", 44);
        dictionary.put("SwiftUI", 45);
        dictionary.put("TensorFlow", 46);
        dictionary.put("Torch", 47);
        dictionary.put("TypeScript", 48);
        dictionary.put("Vue.js", 49);

        return dictionary;
    }

    public String findGithubId(Long userId){

        String githubId = userJPARepository.findGithubIdByUserId(userId);
        return githubId;
    }

    // github_id 넘기기
    public void postRequestUserScore(String github_id){

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://13.125.87.187:8000";

        GithubIdDTO githubIdDTO = new GithubIdDTO();
        githubIdDTO.setGithub_id(github_id);

        ResponseEntity<Void> response = restTemplate.postForEntity(
                url,
                githubIdDTO,
                Void.class  // void 의 클래스 (객체화 불가)
        );
        log.info(response.getStatusCode().toString());
    }

    // 유저의 스택 숙련도 디비에 저장
    public void putNewUserScore(List<UserScoreDTO> userScoreDTOList){

        StringBuilder userStacksBuilder = new StringBuilder("11111111111111111111111111111111111111111111111111"); // 1 : 50개
        Map<String, Integer> dictionary = createDictionary();


        for (int i = 0; i < userScoreDTOList.size(); i++) {
            UserScoreDTO userScoreDTO = userScoreDTOList.get(i);
            // userScoreDTO의 값 읽기
            String stack = userScoreDTO.getStack();
            Float score = userScoreDTO.getScore();
            String githubId = userScoreDTO.getGithubId();

            // stack point 지정 및 userStacks 수정
            Integer stackPoint = dictionary.get(stack);
            if (stackPoint != null && stackPoint < userStacksBuilder.length()) {
                userStacksBuilder.setCharAt(stackPoint, '1');
            }

            User user = userJPARepository.findByGithubId(githubId);
            Long user_id = user.getId();


            Boolean check = false;
            String originStacks = userJPARepository.findStacksByUserId(user_id);
            // 유저의 숙련도 값 저장

            switch(stackPoint) {
                case 0:
                    if (originStacks.charAt(0) == '1') {
                        DotNet dotNet = new DotNet();
                        dotNet.setScore(score);
                        dotNet.setUser(user);
                        dotNetRepository.save(dotNet);
                    } else {
                        dotNetRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 1:
                    if (originStacks.charAt(1) == '1') {
                        AspNet aspNet = new AspNet();
                        aspNet.setScore(score);
                        aspNet.setUser(user);
                        aspNetRepository.save(aspNet);
                    } else {
                        aspNetRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 2:
                    if (originStacks.charAt(2) == '1') {
                        Angular angular = new Angular();
                        angular.setScore(score);
                        angular.setUser(user);
                        angularRepository.save(angular);
                    } else {
                        angularRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 3:
                    if (originStacks.charAt(3) == '1') {
                        AngularJs angularJs = new AngularJs();
                        angularJs.setScore(score);
                        angularJs.setUser(user);
                        angularJsRepository.save(angularJs);
                    } else {
                        angularJsRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 4:
                    if (originStacks.charAt(4) == '1') {
                        ApacheSpark apacheSpark = new ApacheSpark();
                        apacheSpark.setScore(score);
                        apacheSpark.setUser(user);
                        apacheSparkRepository.save(apacheSpark);
                    } else {
                        apacheSparkRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 5:
                    if (originStacks.charAt(5) == '1') {
                        Blazor blazor = new Blazor();
                        blazor.setScore(score);
                        blazor.setUser(user);
                        blazorRepository.save(blazor);
                    } else {
                        blazorRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 6:
                    if (originStacks.charAt(6) == '1') {
                        C c = new C();
                        c.setScore(score);
                        c.setUser(user);
                        cRepository.save(c);
                    } else {
                        cRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 7:
                    if (originStacks.charAt(7) == '1') {
                        CSharp cSharp = new CSharp();
                        cSharp.setScore(score);
                        cSharp.setUser(user);
                        cSharpRepository.save(cSharp);
                    } else {
                        cSharpRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 8:
                    if (originStacks.charAt(8) == '1') {
                        Cpp cpp = new Cpp();
                        cpp.setScore(score);
                        cpp.setUser(user);
                        cppRepository.save(cpp);
                    } else {
                        cppRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 9:
                    if (originStacks.charAt(9) == '1') {
                        Dart dart = new Dart();
                        dart.setScore(score);
                        dart.setUser(user);
                        dartRepository.save(dart);
                    } else {
                        dartRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 10:
                    if (originStacks.charAt(10) == '1') {
                        Django django = new Django();
                        django.setScore(score);
                        django.setUser(user);
                        djangoRepository.save(django);
                    } else {
                        djangoRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 11:
                    if (originStacks.charAt(11) == '1') {
                        Electron electron = new Electron();
                        electron.setScore(score);
                        electron.setUser(user);
                        electronRepository.save(electron);
                    } else {
                        electronRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 12:
                    if (originStacks.charAt(12) == '1') {
                        Express express = new Express();
                        express.setScore(score);
                        express.setUser(user);
                        expressRepository.save(express);
                    } else {
                        expressRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 13:
                    if (originStacks.charAt(13) == '1') {
                        Flask flask = new Flask();
                        flask.setScore(score);
                        flask.setUser(user);
                        flaskRepository.save(flask);
                    } else {
                        flaskRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 14:
                    if (originStacks.charAt(14) == '1') {
                        Flutter flutter = new Flutter();
                        flutter.setScore(score);
                        flutter.setUser(user);
                        flutterRepository.save(flutter);
                    } else {
                        flutterRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 15:
                    if (originStacks.charAt(15) == '1') {
                        Go go = new Go();
                        go.setScore(score);
                        go.setUser(user);
                        goRepository.save(go);
                    } else {
                        goRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 16:
                    if (originStacks.charAt(16) == '1') {
                        HtmlCss htmlCss = new HtmlCss();
                        htmlCss.setScore(score);
                        htmlCss.setUser(user);
                        htmlCssRepository.save(htmlCss);
                    } else {
                        htmlCssRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 17:
                    if (originStacks.charAt(17) == '1') {
                        Java java = new Java();
                        java.setScore(score);
                        java.setUser(user);
                        javaRepository.save(java);
                    } else {
                        javaRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 18:
                    if (originStacks.charAt(18) == '1') {
                        JavaScript javaScript = new JavaScript();
                        javaScript.setScore(score);
                        javaScript.setUser(user);
                        javaScriptRepository.save(javaScript);
                    } else {
                        javaScriptRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 19:
                    if (originStacks.charAt(19) == '1') {
                        Jquery jquery = new Jquery();
                        jquery.setScore(score);
                        jquery.setUser(user);
                        jqueryRepository.save(jquery);
                    } else {
                        jqueryRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 20:
                    if (originStacks.charAt(20) == '1') {
                        Keras keras = new Keras();
                        keras.setScore(score);
                        keras.setUser(user);
                        kerasRepository.save(keras);
                    } else {
                        kerasRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 21:
                    if (originStacks.charAt(21) == '1') {
                        Kotlin kotlin = new Kotlin();
                        kotlin.setScore(score);
                        kotlin.setUser(user);
                        kotlinRepository.save(kotlin);
                    } else {
                        kotlinRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 22:
                    if (originStacks.charAt(22) == '1') {
                        Laravel laravel = new Laravel();
                        laravel.setScore(score);
                        laravel.setUser(user);
                        laravelRepository.save(laravel);
                    } else {
                        laravelRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 23:
                    if (originStacks.charAt(23) == '1') {
                        Lua lua = new Lua();
                        lua.setScore(score);
                        lua.setUser(user);
                        luaRepository.save(lua);
                    } else {
                        luaRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 24:
                    if (originStacks.charAt(24) == '1') {
                        NestJs nestJs = new NestJs();
                        nestJs.setScore(score);
                        nestJs.setUser(user);
                        nestJsRepository.save(nestJs);
                    } else {
                        nestJsRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 25:
                    if (originStacks.charAt(25) == '1') {
                        NextJs nextJs = new NextJs();
                        nextJs.setScore(score);
                        nextJs.setUser(user);
                        nextJsRepository.save(nextJs);
                    } else {
                        nextJsRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 26:
                    if (originStacks.charAt(26) == '1') {
                        NodeJs nodeJs = new NodeJs();
                        nodeJs.setScore(score);
                        nodeJs.setUser(user);
                        nodeJsRepository.save(nodeJs);
                    } else {
                        nodeJsRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 27:
                    if (originStacks.charAt(27) == '1') {
                        OpenGl openGl = new OpenGl();
                        openGl.setScore(score);
                        openGl.setUser(user);
                        openGlRepository.save(openGl);
                    } else {
                        openGlRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 28:
                    if (originStacks.charAt(28) == '1') {
                        OpenCv openCv = new OpenCv();
                        openCv.setScore(score);
                        openCv.setUser(user);
                        openCvRepository.save(openCv);
                    } else {
                        openCvRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 29:
                    if (originStacks.charAt(29) == '1') {
                        Php php = new Php();
                        php.setScore(score);
                        php.setUser(user);
                        phpRepository.save(php);
                    } else {
                        phpRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 30:
                    if (originStacks.charAt(30) == '1') {
                        Pandas pandas = new Pandas();
                        pandas.setScore(score);
                        pandas.setUser(user);
                        pandasRepository.save(pandas);
                    } else {
                        pandasRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 31:
                    if (originStacks.charAt(31) == '1') {
                        PyTorch pyTorch = new PyTorch();
                        pyTorch.setScore(score);
                        pyTorch.setUser(user);
                        pyTorchRepository.save(pyTorch);
                    } else {
                        pyTorchRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 32:
                    if (originStacks.charAt(32) == '1') {
                        Python python = new Python();
                        python.setScore(score);
                        python.setUser(user);
                        pythonRepository.save(python);
                    } else {
                        pythonRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 33:
                    if (originStacks.charAt(33) == '1') {
                        Qt qt = new Qt();
                        qt.setScore(score);
                        qt.setUser(user);
                        qtRepository.save(qt);
                    } else {
                        qtRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 34:
                    if (originStacks.charAt(34) == '1') {
                        R r = new R();
                        r.setScore(score);
                        r.setUser(user);
                        rRepository.save(r);
                    } else {
                        rRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 35:
                    if (originStacks.charAt(35) == '1') {
                        RabbitMq rabbitMq = new RabbitMq();
                        rabbitMq.setScore(score);
                        rabbitMq.setUser(user);
                        rabbitMqRepository.save(rabbitMq);
                    } else {
                        rabbitMqRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 36:
                    if (originStacks.charAt(36) == '1') {
                        React react = new React();
                        react.setScore(score);
                        react.setUser(user);
                        reactRepository.save(react);
                    } else {
                        reactRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 37:
                    if (originStacks.charAt(37) == '1') {
                        ReactNative reactNative = new ReactNative();
                        reactNative.setScore(score);
                        reactNative.setUser(user);
                        reactNativeRepository.save(reactNative);
                    } else {
                        reactNativeRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 38:
                    if (originStacks.charAt(38) == '1') {
                        Ruby ruby = new Ruby();
                        ruby.setScore(score);
                        ruby.setUser(user);
                        rubyRepository.save(ruby);
                    } else {
                        rubyRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 39:
                    if (originStacks.charAt(39) == '1') {
                        RubyOnRails rubyOnRails = new RubyOnRails();
                        rubyOnRails.setScore(score);
                        rubyOnRails.setUser(user);
                        rubyOnRailsRepository.save(rubyOnRails);
                    } else {
                        rubyOnRailsRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 40:
                    if (originStacks.charAt(40) == '1') {
                        Rust rust = new Rust();
                        rust.setScore(score);
                        rust.setUser(user);
                        rustRepository.save(rust);
                    } else {
                        rustRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 41:
                    if (originStacks.charAt(41) == '1') {
                        ScikitLearn scikitLearn = new ScikitLearn();
                        scikitLearn.setScore(score);
                        scikitLearn.setUser(user);
                        scikitLearnRepository.save(scikitLearn);
                    } else {
                        scikitLearnRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 42:
                    if (originStacks.charAt(42) == '1') {
                        SpringBoot springBoot = new SpringBoot();
                        springBoot.setScore(score);
                        springBoot.setUser(user);
                        springBootRepository.save(springBoot);
                    } else {
                        springBootRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 43:
                    if (originStacks.charAt(43) == '1') {
                        Svelte svelte = new Svelte();
                        svelte.setScore(score);
                        svelte.setUser(user);
                        svelteRepository.save(svelte);
                    } else {
                        svelteRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 44:
                    if (originStacks.charAt(44) == '1') {
                        Swift swift = new Swift();
                        swift.setScore(score);
                        swift.setUser(user);
                        swiftRepository.save(swift);
                    } else {
                        swiftRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 45:
                    if (originStacks.charAt(45) == '1') {
                        SwiftUi swiftUi = new SwiftUi();
                        swiftUi.setScore(score);
                        swiftUi.setUser(user);
                        swiftUiRepository.save(swiftUi);
                    } else {
                        swiftUiRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 46:
                    if (originStacks.charAt(46) == '1') {
                        TensorFlow tensorFlow = new TensorFlow();
                        tensorFlow.setScore(score);
                        tensorFlow.setUser(user);
                        tensorFlowRepository.save(tensorFlow);
                    } else {
                        tensorFlowRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 47:
                    if (originStacks.charAt(47) == '1') {
                        Torch torch = new Torch();
                        torch.setScore(score);
                        torch.setUser(user);
                        torchRepository.save(torch);
                    } else {
                        torchRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 48:
                    if (originStacks.charAt(48) == '1') {
                        TypeScript typeScript = new TypeScript();
                        typeScript.setScore(score);
                        typeScript.setUser(user);
                        typeScriptRepository.save(typeScript);
                    } else {
                        typeScriptRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;
                case 49:
                    if (originStacks.charAt(49) == '1') {
                        VueJs vueJs = new VueJs();
                        vueJs.setScore(score);
                        vueJs.setUser(user);
                        vueJsRepository.save(vueJs);
                    } else {
                        vueJsRepository.updateScoreByUserId(user.getId(), score);
                    }
                    break;

            }

            // 마지막 요소인지 확인
            if (i == userScoreDTOList.size() - 1) {
                // user_id 찾기 및 데이터베이스에 저장하는 로직
                String userStacks = userStacksBuilder.toString();
                userJPARepository.updateStacksById(user_id, userStacks);
            }

        }

    }

}
