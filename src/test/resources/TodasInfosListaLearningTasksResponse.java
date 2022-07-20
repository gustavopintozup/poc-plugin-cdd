package br.com.zup.lms.alunos;
public class TodasInfosListaLearningTasksResponse {
    public final java.lang.String titulo;

    public final java.lang.String descricao;

    public final java.util.List<java.util.Map<java.lang.String, java.lang.Object>> learningTasksConvencionais;

    public final java.util.List<java.util.Map<java.lang.String, java.lang.Object>> teorias;

    public final java.util.List<java.util.Map<java.lang.String, java.lang.Object>> learningTasksPreparatorias;

    public final java.lang.String tituloTreinamento;

    public final java.lang.Long id;

    public TodasInfosListaLearningTasksResponse(br.com.zup.lms.admin.partials.taskclass.TaskClass taskClass, br.com.zup.lms.alunos.Aluno aluno, br.com.zup.lms.alunos.RespostasDeterminadoAluno respostas) {
        this.tituloTreinamento = taskClass.getTreinamento().getTitulo();
        this.titulo = taskClass.getTitulo();
        this.descricao = taskClass.getDescricaoOriginal();
        this.id = taskClass.getId();
        this.learningTasksConvencionais = taskClass.learningTasksConvencionais().map(learningTask -> geraLearningTasksResponse(aluno, respostas, learningTask)).toList();
        this.learningTasksPreparatorias = taskClass.learningTasksPreparatorias().map(learningTask -> geraLearningTasksResponse(aluno, respostas, learningTask)).toList();
        this.teorias = taskClass.getTeorias().stream().map(teoria -> br.com.zup.lms.compartilhado.infra.DataView.of(teoria).add("titulo", br.com.zup.lms.admin.Ajuda::getTitulo).add("descricao", br.com.zup.lms.admin.Ajuda::getDescricaoOriginal).build()).toList();
    }

    private java.util.Map<java.lang.String, java.lang.Object> geraLearningTasksResponse(br.com.zup.lms.alunos.Aluno aluno, br.com.zup.lms.alunos.RespostasDeterminadoAluno respostas, br.com.zup.lms.admin.partials.learningtask.LearningTask learningTask) {
        br.com.zup.lms.alunos.CacheRespostasDeDeterminadoAluno cacheRespostasDeDeterminadoAluno = new br.com.zup.lms.alunos.CacheRespostasDeDeterminadoAluno(respostas);
        return br.com.zup.lms.compartilhado.infra.DataView.of(learningTask).add("titulo", br.com.zup.lms.admin.partials.learningtask.LearningTask::getTitulo).add("descricao", br.com.zup.lms.admin.partials.learningtask.LearningTask::getDescricaoOriginal).add("id", br.com.zup.lms.admin.partials.learningtask.LearningTask::getId).add("finalizada", aluno.finalizou(learningTask, cacheRespostasDeDeterminadoAluno)).add("podeAcessar", learningTask.podeSerAcessadaPeloAluno(aluno, cacheRespostasDeDeterminadoAluno)).build();
    }
}