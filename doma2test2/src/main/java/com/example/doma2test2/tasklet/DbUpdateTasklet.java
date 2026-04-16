package com.example.doma2test2.tasklet;

import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.stereotype.Component;

import com.example.doma2test2.common.BatchConstants;
import com.example.doma2test2.dao.JyuOrgDaoCustom;
import com.example.doma2test2.dto.JyuOrgDto;

// オリジナルＤＢ 抽出区分の更新
@Component
public class DbUpdateTasklet implements Tasklet {

    @Autowired
    private JyuOrgDaoCustom jyuOrgDaoCustom;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // Contextからデータを取得
        JobExecution jobExecution = chunkContext.getStepContext().getStepExecution().getJobExecution();
        @SuppressWarnings("unchecked")
        List<JyuOrgDto> stepDtoGet = (List<JyuOrgDto>) jobExecution.getExecutionContext().get(BatchConstants.CONTEXT_KEY_STEP_DTO);

        for (JyuOrgDto jyuOrgDto : stepDtoGet) {
            jyuOrgDaoCustom.updateOutFlg(jyuOrgDto.getJyuno());
        }
        return RepeatStatus.FINISHED;
    }
}
