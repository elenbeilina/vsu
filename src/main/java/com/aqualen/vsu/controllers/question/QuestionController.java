package com.aqualen.vsu.controllers.question;

import com.aqualen.vsu.dto.AddQuestion;
import com.aqualen.vsu.log.SimpleLog;
import com.aqualen.vsu.entity.Question;
import com.aqualen.vsu.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @SimpleLog
    @GetMapping
    public ResponseEntity<Question> get(@RequestParam Long id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    @SimpleLog
    @PutMapping
    public void edit(@RequestBody Question question) {
        questionService.update(question);
    }

    @SimpleLog
    @DeleteMapping
    public void delete(@RequestParam Long id) {
        questionService.delete(id);
    }

    @SimpleLog
    @PostMapping
    public ResponseEntity<Question> add(@RequestBody AddQuestion question) {
        return ResponseEntity.ok(questionService.add(question));
    }
}
