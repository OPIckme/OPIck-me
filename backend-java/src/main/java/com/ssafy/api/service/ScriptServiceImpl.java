package com.ssafy.api.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.Script;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.QuestionRepository;
import com.ssafy.db.repository.ScriptRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("scriptService")
@Transactional
@RequiredArgsConstructor
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Value("${aws.s3.accessKey}")
    private String accessKey;
    @Value("${aws.s3.secretKey}")
    private String secretKey;

    @Override
    public Script createScript(User user, Question question, String content, String audioUrl) {
        Script script = new Script();

        script.setUser(user);
        script.setQuestion(question);
        script.setScriptContent(content);
        script.setAudioUrl(audioUrl);


        return scriptRepository.save(script);
    }

    @Override
    public List<Script> getScriptList(Long userId) {
        return scriptRepository.findAll().stream().filter(v -> v.getUser().getId() == userId).collect(Collectors.toList());
    }

    @Override
    public Optional<Script> getDetail(Long scriptId, Long userId) {
        return scriptRepository.findById(scriptId).filter(v->v.getUser().getId() == userId);
    }

//    @Override
//    public Optional<Script> getDetail(Long scriptId) {return scriptRepository.findById(scriptId);}

    @Override
    public void deleteByScriptId(Long scriptId) {
        scriptRepository.deleteById(scriptId);

    }

    @Override
    public void getAudio(String keyName) {
        String bucket_name = "jaeyeong-s3";

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        System.out.format("Downloading %s from S3 bucket %s...\n", keyName, bucket_name);
        final AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
        try {
            S3Object o = s3.getObject(bucket_name, keyName);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(keyName));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Done!");

    }

    @Override
    public Script modifyScript(Long scriptId, String content) {
        Script findScript = scriptRepository.findById(scriptId).get();
        findScript.setScriptContent(content);
        return scriptRepository.save(findScript);

    }

    @Override
    public Optional<Script> getScriptByScriptId(Long scriptId) {
        return scriptRepository.findById(scriptId);
    }


}
