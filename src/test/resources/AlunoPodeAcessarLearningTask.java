package br.com.zup.lms.alunos;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
@org.springframework.stereotype.Component
public class AlunoPodeAcessarLearningTask {
    private br.com.zup.lms.alunos.RespostasDeterminadoAluno respostasDeterminadoAluno;

    public AlunoPodeAcessarLearningTask(br.com.zup.lms.alunos.RespostasDeterminadoAluno respostasDeterminadoAluno) {
        super();
        this.respostasDeterminadoAluno = respostasDeterminadoAluno;
    }

    public <T> T tenta(br.com.zup.lms.alunos.Aluno aluno, br.com.zup.lms.admin.partials.learningtask.LearningTask learningTask, java.util.function.Supplier<T> acao) {
        // 2
        // 1
        if (learningTask.podeSerAcessadaPeloAluno(aluno, respostasDeterminadoAluno)) {
            return acao.get();
        }
        throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.FORBIDDEN, "Provavelmente o aluno não respondeu alguma atividade obrigatória anterior a essa");
    }
}