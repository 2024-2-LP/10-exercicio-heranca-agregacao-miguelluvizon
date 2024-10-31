package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Consultoria() {
        desenvolvedores = new ArrayList<>();
    }

    public void contratar(Desenvolvedor desenvolvedor) {
        if (desenvolvedores.size() < vagas) {
            desenvolvedores.add(desenvolvedor);
        }
    }

    public void contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (desenvolvedor.isFullstack()) {
            desenvolvedores.add(desenvolvedor);
        }
    }

    public Double getTotalSalarios() {
        Double total = 0.0;
        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            total = total + desenvolvedor.calcularSalario();
        }
        return total;
    }

    public Integer qtdDesenvolvedoresMobile() {
        Integer qtd = 0;
        for (Desenvolvedor desenvolvedore : desenvolvedores) {
            if (desenvolvedore instanceof DesenvolvedorMobile) {
                qtd++;
            }
        }
        return qtd;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        List<Desenvolvedor> maiorSalario = new ArrayList<>();

        for (Desenvolvedor desenvolvedore : desenvolvedores) {
            if (salario < desenvolvedore.calcularSalario()) {
                maiorSalario.add(desenvolvedore);
            }
        }
        return maiorSalario;
    }

    public Desenvolvedor buscarMenorSalario() {
        if (desenvolvedores.isEmpty()) {
            return null;
        }
        Double salario = desenvolvedores.get(0).calcularSalario();
        Desenvolvedor menorSalario = desenvolvedores.get(0);
        for (Desenvolvedor desenvolvedore : desenvolvedores) {
            if (salario > desenvolvedore.calcularSalario()) {
                salario = desenvolvedore.calcularSalario();
                menorSalario = desenvolvedore;
            }
        }
        return menorSalario;
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> devTech = new ArrayList<>();

        if (desenvolvedores != null && tecnologia != null) {
            for (Desenvolvedor desenvolvedor : desenvolvedores) {
                if (desenvolvedor instanceof DesenvolvedorWeb) {
                    if (((DesenvolvedorWeb) desenvolvedor).getFrontend().contains(tecnologia)) {
                        devTech.add(desenvolvedor);
                    }
                    if (((DesenvolvedorWeb) desenvolvedor).getSgbd().contains(tecnologia)) {
                        devTech.add(desenvolvedor);
                    }
                    if (((DesenvolvedorWeb) desenvolvedor).getBackend().contains(tecnologia)) {
                        devTech.add(desenvolvedor);
                    }
                }

                if (desenvolvedor instanceof DesenvolvedorMobile) {
                    if (((DesenvolvedorMobile) desenvolvedor).getPlataforma().contains(tecnologia)) {
                        devTech.add(desenvolvedor);
                    }
                    if (((DesenvolvedorMobile) desenvolvedor).getLinguagem().contains(tecnologia)) {
                        devTech.add(desenvolvedor);
                    }
                }
            }
        }
        return devTech;
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        Double salario = 0.0;
        List<Desenvolvedor> tecnologias = buscarPorTecnologia(tecnologia);
        for (Desenvolvedor desenvolvedor : tecnologias) {
            salario += desenvolvedor.calcularSalario();
        }
        return salario;
    }
}
