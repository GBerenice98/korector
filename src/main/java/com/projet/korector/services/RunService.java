package com.projet.korector.services;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Run;
import com.projet.korector.entity.Session;
import com.projet.korector.repository.RunRepository;
import com.projet.korector.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RunService {

    @Autowired
    private RunRepository runRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    public Run createRun(Long sessionId) {
        Session session = this.sessionRepository.findById(sessionId).get();
        Run run = new Run(session);
        return this.runRepository.save(run);
    }

    public void deleteRun(Long runId) {
        this.runRepository.deleteById(runId);
    }

}
