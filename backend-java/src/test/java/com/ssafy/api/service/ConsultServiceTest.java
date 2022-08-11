package com.ssafy.api.service;

import com.ssafy.db.entity.Consult;
import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.Script;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ConsultRepository;
import com.ssafy.db.repository.QuestionRepository;
import com.ssafy.db.repository.ScriptRepository;
import com.ssafy.db.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //rollback 자동으로 해준다.
class ConsultServiceTest {

    @Autowired
    ConsultService consultService;

    @Autowired
    ConsultRepository consultRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ScriptRepository scriptRepository;

    @Test
    @DisplayName("상담생성 성공!")
    public void 상담생성() throws Exception {
        //Given
        User user = createUser();
        Question question = createQuestion();
        Script script = createScript(user, question);
        String room = "randomRoomNumber";
        //When
        Consult consult = consultService.create(script, room);
        //Then
        //상담이 만들어졌는지
        assertTrue(consultRepository.findById(consult.getId()).isPresent());
        //스크립트가 맞게 들어갔는지
        assertThat(script).isEqualTo(consultRepository.findById(consult.getId()).get().getScript());

    }


    private User createUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setEmail("user@email.com");
        user.setNickname("nickname");
        user.setRole("student");
        return user;
    }

    private Question createQuestion() {
        Question question = new Question();
        question.setTopic("topic");
        question.setQuestionContent("questionContent");
        question.setLevel("level");
        question.setAudioUrl("questionAudioUrl");
        return question;
    }

    private Script createScript(User user, Question question) {
        Script script = new Script();
        script.setUser(user);
        script.setQuestion(question);
        script.setScriptContent("scriptContent");
        script.setAudioUrl("scriptAudioUrl");
        return script;
    }
}